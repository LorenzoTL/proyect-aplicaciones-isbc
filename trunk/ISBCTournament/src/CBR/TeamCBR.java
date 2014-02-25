package CBR;

import java.util.Collection;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.selection.SelectCases;

public class TeamCBR implements StandardCBRApplication{

	Connector _connector;
	CBRCaseBase _casebase;
	CaseComponent result;
	
	private static TeamCBR _instance = null;
	
	public static TeamCBR getInstance(){
		if (_instance == null ) 
			_instance = new TeamCBR();
		return _instance;
	}
	
	public void configure() throws ExecutionException {
		try{
			TeamDB.init();//HSQLDB
			_connector = new DataBaseConnector();
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("CBR/databaseconfig.xml"));
			_casebase = new LinealCaseBase();
		}catch (Exception e){
			throw new ExecutionException(e);
		}
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		//RECUPERACION
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		simConfig.addMapping(new Attribute("gf",TeamDescription.class),new MajorGF());
		simConfig.addMapping(new Attribute("gc",TeamDescription.class), new MinorGC());
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_casebase.getCases(), query, simConfig);
		Collection<CBRCase> myCases = SelectCases.selectTopK(eval, 1);
		//REUSE
		//REVISE
		//RETAIN
		if (myCases != null && myCases.toArray().length > 0){
			_casebase.learnCases(myCases);
			result = ((CBRCase)myCases.toArray()[0]).getSolution();
		}
	}

	public void postCycle() throws ExecutionException {
		_casebase.close();
		TeamDB.shutDown(); //HSQLDB
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_casebase.init(_connector);
		return _casebase;
	}
	
	public CaseComponent getResult() {
		return result;
	}

}
