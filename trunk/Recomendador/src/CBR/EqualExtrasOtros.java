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
	    
	    boolean b = true; 
	    int cont = 0;
	    int c = 0;
	    
	    if (queryValue.isBalcon()){
	    	b = caseValue.isBalcon();
	    	cont++;
	    	if (b) c++;
	    }
    	if (queryValue.isJardinPrivado()){
    		b = b && caseValue.isJardinPrivado();
    		cont++;
	    	if (b) c++;
    	}
    	if (queryValue.isPatio()){
    		b = b && caseValue.isPatio();
    		cont++;
	    	if (b) c++;
    	}
    	if (queryValue.isPiscina()){
    		b = b && caseValue.isPiscina();
    		cont++;
	    	if (b) c++;
    	}
    	if (queryValue.isPiscinaComunitaria()){
    		b = b && caseValue.isPiscinaComunitaria();
    		cont++;
	    	if (b) c++;
    	}
    	if (queryValue.isTerraza()){
    		b = b && caseValue.isTerraza();
    		cont++;
	    	if (b) c++;
    	}
    	if (queryValue.isZonaComunitaria()){
    		b = b && caseValue.isZonaComunitaria();
    		cont++;
	    	if (b) c++;
    	}
    	if (queryValue.isZonaDeportiva()){
    		b = b && caseValue.isZonaDeportiva();
    		cont++;
	    	if (b) c++;
    	}
    	if (queryValue.isZonaInfantil()){
    		b = b && caseValue.isZonaInfantil();
    		cont++;
	    	if (b) c++;
    	}
    	 
    	 return b || cont/2 < c;
	}
}
