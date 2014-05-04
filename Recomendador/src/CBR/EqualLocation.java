package CBR;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualLocation implements LocalSimilarityFunction{

	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException{
		if ((caseObject == null) || (queryObject == null))
			return 0;
		if (! ((caseObject instanceof String)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof String)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());
		
		String caseValue;
		String queryValue;
		
	    caseValue  = (String) caseObject;
	    queryValue = (String) queryObject;
	    
	    if(caseValue.contains(queryValue)) return 1;
	    return 0;
		
	}

	public boolean isApplicable(Object o1, Object o2){
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return (o2 instanceof String);
		else if(o2==null)
			return (o1 instanceof String);
		else
			return ((o1 instanceof String)&&(o2 instanceof String));
	}
}
