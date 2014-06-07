package CBR;

import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualLocation implements LocalSimilarityFunction{
	
    public double compute(Object o1, Object o2) throws jcolibri.exception.NoApplicableSimilarityFunctionException{
    	if ((o1 == null) || (o2 == null))
			return 0;
		if (! ((o1 instanceof String)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
		if (! ((o2 instanceof String)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o2.getClass());

		String caseValue  = (String) o1;
		String queryValue = (String) o2;
		
		boolean b = false;
		
		if (queryValue.contains("#")){
			String[] split = queryValue.split("#");
			b = caseValue.contains(split[0]) && caseValue.contains(split[1]);
		}else{
			b = caseValue.contains(queryValue);
		}
		
		if (b) return 1;
		return 0;
    }
    
	public boolean isApplicable(Object o1, Object o2)
	{
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return (o2 instanceof String)||(o2 instanceof String);
		else if(o2==null)
			return (o1 instanceof String)||(o1 instanceof String);
		else
			return ((o1 instanceof String)&&(o2 instanceof String));
	}
}
