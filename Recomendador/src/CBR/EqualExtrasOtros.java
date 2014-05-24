package CBR;

import Viviendas.ExtrasOtros;
import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class EqualExtrasOtros implements FilterPredicate{
	
	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException{
		if ((caseObject == null) || (queryObject == null))
			return true;
		if (! ((caseObject instanceof ExtrasOtros)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof ExtrasOtros)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
		
		ExtrasOtros caseValue  = (ExtrasOtros) caseObject;
		ExtrasOtros queryValue = (ExtrasOtros) queryObject;
	    
	    boolean b =  (caseValue.isBalcon() == queryValue.isBalcon())
    	 && (caseValue.isJardinPrivado() == queryValue.isJardinPrivado()) 
    	 && (caseValue.isPatio() == queryValue.isPatio())
    	 && (caseValue.isPiscina() == queryValue.isPiscina())
    	 && (caseValue.isPiscinaComunitaria() == queryValue.isPiscinaComunitaria())
    	 && (caseValue.isTerraza() == queryValue.isTerraza()) 
    	 && (caseValue.isZonaComunitaria()== queryValue.isZonaComunitaria()) 
    	 && (caseValue.isZonaDeportiva()== queryValue.isZonaDeportiva())
    	 && (caseValue.isZonaInfantil()== queryValue.isZonaInfantil());
    	 
    	 return b;
	}
}
