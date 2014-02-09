package CBR;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;

public class TeamCBR implements StandardCBRApplication{

	Connector _connector;
	CBRCaseBase _casebase;
	
	private static TeamCBR _instance = null;
	
	public static TeamCBR getInstance(){
		if (_instance == null ) 
			_instance = new TeamCBR();
		return _instance;
	}
	
	public void configure() throws ExecutionException {
		try{
			_connector = new DataBaseConnector();
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("databaseconfig.xml"));
			_casebase = new LinealCaseBase();
		}catch (Exception e){
			throw new ExecutionException(e);
		}
	}

	public void cycle(CBRQuery arg0) throws ExecutionException {
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
	}

	public void postCycle() throws ExecutionException {
		_casebase.close();
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_casebase.init(_connector);
		return _casebase;
	}

}
