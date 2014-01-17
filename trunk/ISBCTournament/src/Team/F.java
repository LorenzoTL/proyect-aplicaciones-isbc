package Team;

import EDU.gatech.cc.is.util.Vec2;

public class F {

	public static int quadrant(Vec2 v){
		double x = v.x;
		double y = v.y;
		if(x > 0.0 && y > 0.0) return 0;
		if(x < 0.0 && y > 0.0) return 1;
		if(x < 0.0 && y < 0.0) return 2;
		return 3;
	}
	
	public static boolean estoyCerca(Vec2 origen,Vec2 destino){
		return origen.distance(destino) <= 0.65;
	}
	
}
