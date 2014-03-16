package CBR;

import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;

public class RecomendadorCBR implements StandardCBRApplication{

	Connector _connector;
	CBRCaseBase _casebase;
	CaseComponent result;
	
	private static RecomendadorCBR _instance = null;
	
	public static RecomendadorCBR getInstance(){
		if (_instance == null)
			_instance = new RecomendadorCBR();
		return _instance;
	}
	
	public void configure() throws ExecutionException {
		// TODO 	
	}
	
	public void cycle(CBRQuery query) throws ExecutionException {
		// TODO 
	}
	
	public void postCycle() throws ExecutionException {
		// TODO 
	}
	
	public CBRCaseBase preCycle() throws ExecutionException {
		// TODO 
		return null;
	}
	
	public CaseComponent getResult() {
		return result;
	}
	
}
