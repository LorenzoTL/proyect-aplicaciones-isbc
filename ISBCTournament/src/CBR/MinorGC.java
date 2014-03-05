package CBR;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class MinorGC implements LocalSimilarityFunction{
	
	double interval;
	int step;
	
	public MinorGC(double interval,int step){
		this.interval = interval;
		this.step = step;
	}
	
	public double compute(Object o1, Object o2) throws NoApplicableSimilarityFunctionException {
		if ((o1 == null) || (o2 == null))
			return 0;
		if (!(o1 instanceof java.lang.Number))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
		if (!(o2 instanceof java.lang.Number))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o2.getClass());

		Number i1 = (Number) o1;
		Number i2 = (Number) o2;
		
		double v1 = i1.doubleValue();
		double v2 = i2.doubleValue();
		
		int _v1 = (int)(v1/step) + 1;
		int _v2 = (int)(v2/step) + 1;
		
		if ((_v1 < 6 && _v2 < 6) || (_v1 == 6 && _v2 == 6)) return 1 - ((double) Math.abs(v1 - v2) / interval);
		return 0.0;
	}

	public boolean isApplicable(Object o1, Object o2) {
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return o2 instanceof Number;
		else if(o2==null)
			return o1 instanceof Number;
		else
			return (o1 instanceof Number)&&(o2 instanceof Number);
	}
}
