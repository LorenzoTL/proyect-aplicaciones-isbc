package CBR;

import Viviendas.ExtrasBasicos;
import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

public class EqualExtrasBasicos implements FilterPredicate{
	
	public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException
	{
		if ((caseObject == null) || (queryObject == null))
			return true;
		if (! ((caseObject instanceof ExtrasBasicos)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof ExtrasBasicos)))
			throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
		else{
			ExtrasBasicos caseValue  = (ExtrasBasicos) caseObject;
			ExtrasBasicos queryValue = (ExtrasBasicos) queryObject;
		    
		    boolean b = true;
		    int cont = 0;
		    int c = 0;
		    
	    	 if (queryValue.isLavadero()){
	    		b = caseValue.isLavadero();
	    		cont++;
	 	    	if (b) c++;
	    	 }
			 if (queryValue.isInternet()){
				b = b && caseValue.isInternet();
				cont++;
		    	if (b) c++;
			 }
		     if (queryValue.isMicroondas()){
		    	b = b && caseValue.isMicroondas();
		    	cont++;
		    	if (b) c++;
		     }
	    	 if (queryValue.isHorno()){
	    		b = b && caseValue.isHorno();
	    		cont++;
	 	    	if (b) c++;
	    	 }
			 if (queryValue.isAmueblado()){
				b = b && caseValue.isAmueblado();
				cont++;
		    	if (b) c++;
			 }
			 if (queryValue.isCocinaOffice()){
				b = b && caseValue.isCocinaOffice();
				cont++;
		    	if (b) c++;
			 }
	    	 if (queryValue.isParquet()){
	    		b = b && caseValue.isParquet();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isDomotica()){
	    		b = b && caseValue.isDomotica();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isArmarios()){
	    		b = b && caseValue.isArmarios();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isTv()){
	    		b = b && caseValue.isTv();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isLavadora()){
	    		b = b && caseValue.isLavadora();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isElectrodomesticos()){
	    		b = b && caseValue.isElectrodomesticos();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isSuiteConBanio()){
	    		b = b && caseValue.isSuiteConBanio();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isPuertaBlindada()){
	    		b = b && caseValue.isPuertaBlindada(); 
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isGresCeramica()){
	    		b = b && caseValue.isGresCeramica();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isCalefaccion()){
	    		b = b && caseValue.isCalefaccion();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isAireAcondicionado()){
	    		b = b && caseValue.isAireAcondicionado();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 if (queryValue.isNevera()){
	    		b = b && caseValue.isNevera();
	    		cont++;
	 	    	if (b) c++;
	    	 }
	    	 
	    	 return b || cont/2 < c;
		}
	}
	
}
