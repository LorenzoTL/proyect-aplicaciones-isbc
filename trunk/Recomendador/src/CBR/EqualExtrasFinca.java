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
	    
	    boolean b = true;
	    int cont = 0;
	    int c = 0;
	    
    	 if (queryValue.isAscensor()){
    		 b = caseValue.isAscensor();
    		 cont ++;
    		 if (b) c++;
    	 }
    	 if (queryValue.isEnergiaSolar()){
    		 b = b && caseValue.isEnergiaSolar(); 
    		 cont ++;
    		 if (b) c++;
    	 }
    	 if (queryValue.isGarajePrivado()){
    		 b = b && caseValue.isGarajePrivado(); 
    		 cont ++;
    		 if (b) c++;
    	 }
    	 if (queryValue.isParkingComunitario()){
    		 b = b && caseValue.isParkingComunitario();
    		 cont ++;
    		 if (b) c++;
    	 }
    	 if (queryValue.isServPorteria()){
    		 b = b && caseValue.isServPorteria();
    		 cont ++;
    		 if (b) c++;
    	 }
    	 if (queryValue.isTrastero()){
    		 b = b && caseValue.isTrastero();
    		 cont ++;
    		 if (b) c++;
    	 }
    	 if (queryValue.isVideoportero()){
    		 b = b && caseValue.isVideoportero();
    		 cont ++;
    		 if (b) c++;
    	 }

	    return b || cont/2 < c;
	}
}
