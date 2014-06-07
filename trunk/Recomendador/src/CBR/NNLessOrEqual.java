package CBR;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class NNLessOrEqual implements LocalSimilarityFunction{

	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException{
		if ((caseObject == null) || (queryObject == null))
			return 0;
		if (! ((caseObject instanceof java.lang.Number)||(caseObject instanceof Enum)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof java.lang.Number)||(queryObject instanceof Enum)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());

		double caseValue;
		double queryValue;

		if(caseObject instanceof Number)
		{
		    Number n1  = (Number) caseObject;
		    Number n2  = (Number) queryObject;
		    caseValue  = n1.doubleValue();
		    queryValue = n2.doubleValue();
		}
		else
		{
		    Enum enum1 = (Enum)caseObject;
		    Enum enum2 = (Enum)queryObject;
		    caseValue  = enum1.ordinal();
		    queryValue = enum2.ordinal();
		}
		
		if(caseValue <= queryValue)
		    return 1;
		
		else return 0;
		
	}
	
	public boolean isApplicable(Object o1, Object o2)
	{
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return (o2 instanceof Number)||(o2 instanceof Enum);
		else if(o2==null)
			return (o1 instanceof Number)||(o1 instanceof Enum);
		else
			return ((o1 instanceof Number)&&(o2 instanceof Number)) ||
				((o1 instanceof Enum)&&(o2 instanceof Enum));
	}
	
}
