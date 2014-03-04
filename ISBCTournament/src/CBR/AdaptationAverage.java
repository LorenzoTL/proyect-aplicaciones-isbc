package CBR;

import java.util.Collection;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.AttributeAccessException;

public class AdaptationAverage {

	public static void adaptationAverage(Attribute querySource, CBRQuery query, Collection<CBRCase> _cases){
		
		Object queryValue = jcolibri.util.AttributeUtils.findValue(querySource, query);		
		
		
		for(CBRCase _case: _cases){
			Object cc = jcolibri.util.AttributeUtils.findBelongingComponent(querySource, _case);
			Object caseValue;
			try {
				caseValue = querySource.getValue(cc);
				
				if (queryValue == null || caseValue == null) return;
				if (!(queryValue instanceof Number) || !(caseValue instanceof Number)) return;
				
				Number q = (Number)queryValue;
				Number c = (Number)caseValue;
				
				double average = (q.doubleValue() + c.doubleValue())/2;
				querySource.setValue(cc, average);
				
			} catch (AttributeAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
}
