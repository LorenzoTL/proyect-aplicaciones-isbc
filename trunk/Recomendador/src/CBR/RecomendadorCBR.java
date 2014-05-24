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
import jcolibri.method.retrieve.FilterBasedRetrieval.FilterBasedRetrievalMethod;
import jcolibri.method.retrieve.FilterBasedRetrieval.FilterConfig;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryLessOrEqual;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryMoreOrEqual;

public class RecomendadorCBR implements StandardCBRApplication{

	Connector _connector;
	CBRCaseBase _caseBase;
	Collection<CBRCase> cases;
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
		FilterConfig preferences = new FilterConfig();
		preferences.addPredicate(new Attribute("localizacion", DescripcionVivienda.class), new EqualLocationPreferences());
		
		cases = FilterBasedRetrievalMethod.filterCases(_caseBase.getCases(), query, preferences);
		
		FilterConfig preferences2 = new FilterConfig();
		preferences.addPredicate(new Attribute("superficie", DescripcionVivienda.class), new QueryMoreOrEqual());
		preferences.addPredicate(new Attribute("habitaciones", DescripcionVivienda.class), new QueryMoreOrEqual());
		casesFormInitial = FilterBasedRetrievalMethod.filterCases(cases, query, preferences2);
	}
	
	private void sequence2(CBRQuery query){
		Collection<CBRCase> casesBase = cases;
		DescripcionVivienda dv =  (DescripcionVivienda)query.getDescription();
		
		FilterConfig preferences = new FilterConfig();
		
		if(dv.getLocalizacion() != null){ 
			casesBase = _caseBase.getCases();
			preferences.addPredicate(new Attribute("localizacion", DescripcionVivienda.class),new EqualLocationPreferences());
		}
		if(dv.getPrecioMedio() != 0)
			preferences.addPredicate(new Attribute("precioMedio", DescripcionVivienda.class),new QueryLessOrEqual());
		if(dv.getSuperficie() != 0)
			preferences.addPredicate(new Attribute("superficie", DescripcionVivienda.class),new QueryLessOrEqual());
		if(dv.getTipo() != null)
			preferences.addPredicate(new Attribute("tipo", DescripcionVivienda.class),new jcolibri.method.retrieve.FilterBasedRetrieval.predicates.Equal());
		if(dv.getHabitaciones() != 0)
			preferences.addPredicate(new Attribute("habitaciones", DescripcionVivienda.class),new QueryMoreOrEqual());
		if(dv.getBanios() != 0)
			preferences.addPredicate(new Attribute("banios", DescripcionVivienda.class),new QueryMoreOrEqual());
		if(dv.getEstado() != null)
			preferences.addPredicate(new Attribute("estado", DescripcionVivienda.class),new jcolibri.method.retrieve.FilterBasedRetrieval.predicates.Equal());
		if(dv.getExtrasBasicos() != null)
			preferences.addPredicate(new Attribute("extrasBasicos", DescripcionVivienda.class),new EqualExtrasBasicos());
		if(dv.getExtrasFinca() != null)
			preferences.addPredicate(new Attribute("extrasFinca", DescripcionVivienda.class),new EqualExtrasFinca());
		if(dv.getExtrasOtros() != null)
			preferences.addPredicate(new Attribute("extrasOtros", DescripcionVivienda.class),new EqualExtrasOtros());
		
		casesFormPrincipal = FilterBasedRetrievalMethod.filterCases(casesBase, query, preferences);
	}
}
