package CBR;

import Viviendas.ExtrasBasicos;
import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class EqualExtrasBasicos implements FilterPredicate{
	
	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException{
		if ((caseObject == null) || (queryObject == null))
			return true;
		if (! ((caseObject instanceof ExtrasBasicos)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof ExtrasBasicos)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
		
		ExtrasBasicos caseValue  = (ExtrasBasicos) caseObject;
		ExtrasBasicos queryValue = (ExtrasBasicos) queryObject;
	    
	    boolean b = false;
	    
    	 b = (caseValue.isLavadero() == queryValue.isLavadero())
    	 && (caseValue.isInternet() == queryValue.isInternet())
    	 && (caseValue.isMicroondas() == queryValue.isMicroondas())
    	 && (caseValue.isHorno() == queryValue.isHorno())
    	 && (caseValue.isAmueblado() == queryValue.isAmueblado())
    	 && (caseValue.isCocinaOffice() == queryValue.isCocinaOffice())
    	 && (caseValue.isParquet() == queryValue.isParquet())
    	 && (caseValue.isDomotica() == queryValue.isDomotica())
    	 && (caseValue.isArmarios() == queryValue.isArmarios())
    	 && (caseValue.isTv() == queryValue.isTv()) 
    	 && (caseValue.isLavadora() == queryValue.isLavadora())
    	 && (caseValue.isElectrodomesticos() == queryValue.isElectrodomesticos())
    	 && (caseValue.isSuiteConBanio() == queryValue.isSuiteConBanio()) 
    	 && (caseValue.isPuertaBlindada() == queryValue.isPuertaBlindada()) 
    	 && (caseValue.isGresCeramica() == queryValue.isGresCeramica()) 
    	 && (caseValue.isCalefaccion() == queryValue.isCalefaccion())
    	 && (caseValue.isAireAcondicionado() == queryValue.isAireAcondicionado())
    	 && (caseValue.isNevera() == queryValue.isNevera());
    	 
    	 return b;
	}
	
}
