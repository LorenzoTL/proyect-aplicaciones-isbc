package CBR;

import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class EqualLocationPreferences implements FilterPredicate {
	
	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException {
		if((caseObject == null)&&(queryObject==null))
		    return true;
		else if(caseObject == null)
		    return false;
		else if(queryObject == null)
		    return true;
		else if (! ((caseObject instanceof String)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
		else if (! ((queryObject instanceof String)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
		else {
			boolean b = false;
		    if((caseObject instanceof String)&&(queryObject instanceof String))
		    {
		    	String caseValue  = (String) caseObject;
				String queryValue = (String) queryObject;
				if (queryValue.contains("#")){
					String[] split = queryValue.split("#");
					b = caseValue.contains(split[0]) && caseValue.contains(split[1]);
				}else{
					b = caseValue.contains(queryValue);
				}
		    }   
		    return b;
		}
    }
	
}
