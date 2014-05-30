package CBR;

import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class Interval implements FilterPredicate{

	int min;
	int max;
	
	public Interval(int min, int max){
		this.min = min;
		this.max = max;
	}
	
	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException
    {
	if(caseObject == null)
	    return false;
	else if (! ((caseObject instanceof java.lang.Number)||(caseObject instanceof Enum)))
		throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
	else 
	{
	    double caseValue;
	    if(caseObject instanceof Number)
	    {
		Number n1  = (Number) caseObject;
		caseValue  = n1.doubleValue();
	    }
	    else
	    {
		Enum enum1 = (Enum)caseObject;
		caseValue  = enum1.ordinal();
	    }
	    return  min <= caseValue && caseValue <= max;
	}
    }
	
}
