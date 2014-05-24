package CBR;

import Viviendas.ExtrasFinca;
import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class EqualExtrasFinca implements FilterPredicate{
	
	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException{
		if ((caseObject == null) || (queryObject == null))
			return true;
		if (! ((caseObject instanceof ExtrasFinca)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof ExtrasFinca)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
		
		ExtrasFinca caseValue  = (ExtrasFinca) caseObject;
		ExtrasFinca queryValue = (ExtrasFinca) queryObject;
	    
	    boolean b = false;
	    
    	 b =  (caseValue.isAscensor() == queryValue.isAscensor()) 
    	 && (caseValue.isEnergiaSolar() == queryValue.isEnergiaSolar()) 
    	 && (caseValue.isGarajePrivado() == queryValue.isGarajePrivado()) 
    	 && (caseValue.isParkingComunitario() == queryValue.isParkingComunitario())
    	 && (caseValue.isServPorteria() == queryValue.isServPorteria()) 
    	 && (caseValue.isTrastero() == queryValue.isTrastero())
    	 && (caseValue.isVideoportero()== queryValue.isVideoportero());

	    return b;
	}
}
