package CBR;

import Viviendas.ExtrasOtros;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualExtrasOtros implements LocalSimilarityFunction{
	
	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException{
		if ((caseObject == null) || (queryObject == null))
			return 0;
		if (! ((caseObject instanceof ExtrasOtros)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof ExtrasOtros)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());
		
		ExtrasOtros caseValue  = (ExtrasOtros) caseObject;
		ExtrasOtros queryValue = (ExtrasOtros) queryObject;
	    
	    int numAt = 9;
	    int atEquals = 0;
	    
    	 if (caseValue.isBalcon() == queryValue.isBalcon()) atEquals ++;
    	 if (caseValue.isJardinPrivado() == queryValue.isJardinPrivado()) atEquals ++;
    	 if (caseValue.isPatio() == queryValue.isPatio()) atEquals ++;
    	 if (caseValue.isPiscina() == queryValue.isPiscina()) atEquals ++;
    	 if (caseValue.isPiscinaComunitaria() == queryValue.isPiscinaComunitaria()) atEquals ++;
    	 if (caseValue.isTerraza() == queryValue.isTerraza()) atEquals ++;
    	 if (caseValue.isZonaComunitaria()== queryValue.isZonaComunitaria()) atEquals ++;
    	 if (caseValue.isZonaDeportiva()== queryValue.isZonaDeportiva()) atEquals ++;
    	 if (caseValue.isZonaInfantil()== queryValue.isZonaInfantil()) atEquals ++;
    	 
    	 if(atEquals == numAt) return 1;
    	 
	    return atEquals/numAt;
	}
	
	public boolean isApplicable(Object o1, Object o2){
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return (o2 instanceof ExtrasOtros);
		else if(o2==null)
			return (o1 instanceof ExtrasOtros);
		else
			return ((o1 instanceof ExtrasOtros)&&(o2 instanceof ExtrasOtros));
	}
}
