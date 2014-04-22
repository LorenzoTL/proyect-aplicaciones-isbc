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
import jcolibri.method.retrieve.selection.SelectCases;

public class RecomendadorCBR implements StandardCBRApplication{

	Connector _connector;
	CBRCaseBase _caseBase;
	NNConfig simConfig;
	
	private static RecomendadorCBR _instance = null;
	
	public static RecomendadorCBR getInstance(){
		if (_instance == null)
			_instance = new RecomendadorCBR();
		return _instance;
	}
	
	public void configure() throws ExecutionException {
		_connector = new ViviendasConnector();
		_caseBase = new LinealCaseBase();
		//TODO funciones de comparacion para todos los atributos de la descripción
		simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		simConfig.addMapping(new Attribute("id", DescripcionVivienda.class), new Equal());
		//simConfig.addMapping(new Attribute("puntuacion", DescripcionVivienda.class), new Equal());
	}
	
	public void cycle(CBRQuery query) throws ExecutionException {
		//ejecutamos KNN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		Collection<CBRCase> retrievedCases = SelectCases.selectTopK(eval, 1);
		//_caseBase.learnCases(retrievedCases);
	}
	
	public void postCycle() throws ExecutionException { 
		_caseBase.close();
	}
	
	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);	
		return _caseBase;
	}
	
	public static void main(String[] args){
		StandardCBRApplication recomendador = RecomendadorCBR.getInstance();
		try{
			recomendador.configure();
			recomendador.preCycle();
			CBRQuery query = new CBRQuery();
			DescripcionVivienda dv = new DescripcionVivienda();
			dv.setId(1);
			dv.setPuntuacion(0);
			query.setDescription(dv);
			recomendador.cycle(query);
			recomendador.postCycle();	
		}catch(Exception ex){
			
		}
	}
}
