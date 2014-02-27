package Team;

import teams.ucmTeam.RobotAPI;
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
		return origen.distance(destino) <= 0.72;
	}
	
	public static boolean estaDetrasBall(RobotAPI r) {
        double ang = r.getBall().t;
        // Transformamos el ángulo a positivo
        while (ang < 0)
                ang += Math.PI*2;
        // Accuracy
        double accuracy = Math.PI/9;
        if ((r.getFieldSide() == -1 && (ang < accuracy || ang > 2*Math.PI-accuracy)))// ||
                return true;
        if ((r.getFieldSide() == 1 && (ang > Math.PI-accuracy && ang < Math.PI-accuracy)))
                return true;
        else
                return false;
	}
	
}
