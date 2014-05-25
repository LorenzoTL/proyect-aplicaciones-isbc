package CBR;

import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class PuntuacionPreferences implements FilterPredicate{

	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException{
		if((caseObject == null)&&(queryObject==null))
		    return true;
		else if(caseObject == null)
		    return false;
		else if(queryObject == null)
		    return true;
		else if (! ((caseObject instanceof java.lang.Number)||(caseObject instanceof Enum)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
		else if (! ((queryObject instanceof java.lang.Number)||(queryObject instanceof Enum)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
		else 
		{
		    double caseValue;
		    //double queryValue;
		    if((caseObject instanceof Number)&&(queryObject instanceof Number))
		    {
			Number n1  = (Number) caseObject;
			caseValue  = n1.doubleValue();
			//Number n2  = (Number) queryObject;
			//queryValue = n2.doubleValue();
		    }
		    else
		    {
			Enum enum1 = (Enum)caseObject;
			caseValue  = enum1.ordinal();
			//Enum enum2 = (Enum)queryObject;
			//queryValue = enum2.ordinal();
		    }
		    return caseValue > 0;
		}
    }
}
