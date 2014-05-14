package CBR;

import java.util.Collection;

import Viviendas.DescripcionVivienda;
import Viviendas.ViviendasConnector;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaLessIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaMoreIsBetter;
import jcolibri.method.retrieve.selection.SelectCases;

public class RecomendadorCBR implements StandardCBRApplication{

	Connector _connector;
	CBRCaseBase _caseBase;
	Collection<CBRCase> casesFormInitial;
	Collection<CBRCase> casesFormPrincipal;
	boolean preferences;
	
	private static RecomendadorCBR _instance = null;
	
	public static RecomendadorCBR getInstance(){
		if (_instance == null)
			_instance = new RecomendadorCBR();
		return _instance;
	}
	
	public void configure() throws ExecutionException {
		_connector = new ViviendasConnector();
		_caseBase = new LinealCaseBase();
	}
	
	public void cycle(CBRQuery query) throws ExecutionException {
		//ejecutamos KNN
		if (!preferences){
			sequence1(query);
		}else{
			sequence2(query);
		}
	}
	
	public void postCycle() throws ExecutionException { 
		_caseBase.close();
	}
	
	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);	
		return _caseBase;
	}
	
	public void setPreferences(boolean value){
		preferences = value;
	}
	
	public DescripcionVivienda[] getResults(boolean ini){
		Object[] array = null;
		if (ini) array = casesFormInitial.toArray();
		else array = casesFormPrincipal.toArray();
		int size = array.length;
		DescripcionVivienda[] results = new DescripcionVivienda[size];
		for(int i=0;i<size;i++)
			results[i] = (DescripcionVivienda)((CBRCase)array[i]).getDescription();
		return results;
	}
	
	private void sequence1(CBRQuery query){
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		simConfig.addMapping(new Attribute("localizacion", DescripcionVivienda.class),new EqualLocation());
		simConfig.addMapping(new Attribute("superficie", DescripcionVivienda.class),new InrecaMoreIsBetter(0.5));
		simConfig.addMapping(new Attribute("habitaciones", DescripcionVivienda.class),new InrecaMoreIsBetter(0.5));
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		casesFormInitial = SelectCases.selectAll(eval);
	}
	
	//TODO: filtrado para Extras basicos, finca y otros
	private void sequence2(CBRQuery query){
		Collection<CBRCase> cases = casesFormInitial;
		DescripcionVivienda dv =  (DescripcionVivienda)query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		if(dv.getLocalizacion() != null){ 
			cases = _caseBase.getCases();
			simConfig.addMapping(new Attribute("localizacion", DescripcionVivienda.class),new EqualLocation());
		}
		if(dv.getPrecioMedio() != 0)
			simConfig.addMapping(new Attribute("precioMedio", DescripcionVivienda.class),new InrecaLessIsBetter(2000,0.5));
		if(dv.getSuperficie() != 0)
			simConfig.addMapping(new Attribute("superficie", DescripcionVivienda.class),new InrecaMoreIsBetter(0.5));
		if(dv.getTipo() != null)
			simConfig.addMapping(new Attribute("tipo", DescripcionVivienda.class),new Equal());
		if(dv.getHabitaciones() != 0)
			simConfig.addMapping(new Attribute("habitaciones", DescripcionVivienda.class),new InrecaMoreIsBetter(0.5));
		if(dv.getBanios() != 0)
			simConfig.addMapping(new Attribute("banios", DescripcionVivienda.class),new InrecaMoreIsBetter(0.5));
		if(dv.getEstado() != null)
			simConfig.addMapping(new Attribute("estado", DescripcionVivienda.class),new Equal());
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(cases, query, simConfig);
		casesFormPrincipal = SelectCases.selectAll(eval);
	}
}
