package CBR;

import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class PuntuacionPreferences implements FilterPredicate{

	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException{
		if((caseObject == null))
		    return false;
		else if (!(caseObject instanceof java.lang.Number))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
		else 
		{
		    int caseValue;
		    if((caseObject instanceof Number))
		    {
			Number n1  = (Number) caseObject;
			caseValue  = n1.intValue();
		    }
		    else
		    {
			Enum enum1 = (Enum)caseObject;
			caseValue  = enum1.ordinal();
		    }
		    return caseValue > 0;
		}
    }
}
