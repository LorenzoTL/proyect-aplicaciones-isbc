package CBR;

import Viviendas.ExtrasFinca;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualExtrasFinca implements LocalSimilarityFunction{
	
	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException{
		if ((caseObject == null) || (queryObject == null))
			return 0;
		if (! ((caseObject instanceof ExtrasFinca)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		if (! ((queryObject instanceof ExtrasFinca)))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());
		
		ExtrasFinca caseValue  = (ExtrasFinca) caseObject;
		ExtrasFinca queryValue = (ExtrasFinca) queryObject;
	    
	    int numAt = 7;
	    int atEquals = 0;
	    
    	 if (caseValue.isAscensor() == queryValue.isAscensor()) atEquals ++;
    	 if (caseValue.isEnergiaSolar() == queryValue.isEnergiaSolar()) atEquals ++;
    	 if (caseValue.isGarajePrivado() == queryValue.isGarajePrivado()) atEquals ++;
    	 if (caseValue.isParkingComunitario() == queryValue.isParkingComunitario()) atEquals ++;
    	 if (caseValue.isServPorteria() == queryValue.isServPorteria()) atEquals ++;
    	 if (caseValue.isTrastero() == queryValue.isTrastero()) atEquals ++;
    	 if (caseValue.isVideoportero()== queryValue.isVideoportero()) atEquals ++;
    	 
    	 if(atEquals == numAt) return 1;
    	 
	    return atEquals/numAt;
	}
	
	public boolean isApplicable(Object o1, Object o2){
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return (o2 instanceof ExtrasFinca);
		else if(o2==null)
			return (o1 instanceof ExtrasFinca);
		else
			return ((o1 instanceof ExtrasFinca)&&(o2 instanceof ExtrasFinca));
	}
}
