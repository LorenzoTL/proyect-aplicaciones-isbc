package CBR;

import Viviendas.ExtrasBasicos;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualExtrasBasicos implements LocalSimilarityFunction{
	
	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException{
		if ((caseObject == null) || (queryObject == null))
			return 0;
		if (! ((caseObject instanceof ExtrasBasicos)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof ExtrasBasicos)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());
		
		ExtrasBasicos caseValue  = (ExtrasBasicos) caseObject;
		ExtrasBasicos queryValue = (ExtrasBasicos) queryObject;
	    
	    int numAt = 18;
	    int atEquals = 0;
	    
    	 if (caseValue.isLavadero() == queryValue.isLavadero()) atEquals ++;
    	 if (caseValue.isInternet() == queryValue.isInternet()) atEquals ++;
    	 if (caseValue.isMicroondas() == queryValue.isMicroondas()) atEquals ++;
    	 if (caseValue.isHorno() == queryValue.isHorno()) atEquals ++;
    	 if (caseValue.isAmueblado() == queryValue.isAmueblado()) atEquals ++;
    	 if (caseValue.isCocinaOffice() == queryValue.isCocinaOffice()) atEquals ++;
    	 if (caseValue.isParquet() == queryValue.isParquet()) atEquals ++;
    	 if (caseValue.isDomotica() == queryValue.isDomotica()) atEquals ++;
    	 if (caseValue.isArmarios() == queryValue.isArmarios()) atEquals ++;
    	 if (caseValue.isTv() == queryValue.isTv()) atEquals ++;
    	 if (caseValue.isLavadora() == queryValue.isLavadora()) atEquals ++;
    	 if (caseValue.isElectrodomesticos() == queryValue.isElectrodomesticos()) atEquals ++;
    	 if (caseValue.isSuiteConBanio() == queryValue.isSuiteConBanio()) atEquals ++;
    	 if (caseValue.isPuertaBlindada() == queryValue.isPuertaBlindada()) atEquals ++;
    	 if (caseValue.isGresCeramica() == queryValue.isGresCeramica()) atEquals ++;
    	 if (caseValue.isCalefaccion() == queryValue.isCalefaccion()) atEquals ++;
    	 if (caseValue.isAireAcondicionado() == queryValue.isAireAcondicionado()) atEquals ++;
    	 if (caseValue.isNevera() == queryValue.isNevera()) atEquals ++;
    	 
    	 if(atEquals == numAt) return 1;
    	 
	    return atEquals/numAt;
	}
	
	public boolean isApplicable(Object o1, Object o2){
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return (o2 instanceof ExtrasBasicos);
		else if(o2==null)
			return (o1 instanceof ExtrasBasicos);
		else
			return ((o1 instanceof ExtrasBasicos)&&(o2 instanceof ExtrasBasicos));
	}
	
}
