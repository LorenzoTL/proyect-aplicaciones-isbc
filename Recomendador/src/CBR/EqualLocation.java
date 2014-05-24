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
	    
	    int value = 0;
	    
	    if (queryValue.contains("#")){
	    	String[] split = queryValue.split("#");
	    	if (caseValue.contains(split[0]) || caseValue.contains(split[1])) value = 1;
	    }else{
	    	if(caseValue.contains(queryValue)) value = 1;
	    }
	    
	    return value;
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
