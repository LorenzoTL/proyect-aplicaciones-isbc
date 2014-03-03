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
	
	public static boolean estaDetrasBalon(RobotAPI r) {
        double ang = r.getBall().t;
        while (ang < 0){
                ang += Math.PI*2;
        }
        double aux = Math.PI/9;
        if ((r.getFieldSide() == -1 && (ang < aux || ang > 2*Math.PI-aux)))// ||
                return true;
        if ((r.getFieldSide() == 1 && (ang > Math.PI-aux && ang < Math.PI-aux)))
                return true;
        else
                return false;
	}
	
	public static boolean estanDefendiendo(RobotAPI r){
		Vec2 ball = r.getBall();
		Vec2 player = r.closestTo(r.getTeammates(), ball);
		if (player.distance(ball) < ball.distance(new Vec2(0,0))){
			return true;
		}
		return false;
	}
	
	public static Vec2 closestToOurGoal(RobotAPI myRobotAPI){
		int lado = myRobotAPI.getFieldSide();
		Vec2 pos =  myRobotAPI.closestTo(myRobotAPI.getOpponents(), myRobotAPI.getOurGoal());
		pos = myRobotAPI.toFieldCoordinates(pos);
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOurGoal()).distance(pos) <= 0.75)
				return pos;
		return new Vec2(0.75*lado,0.0);
	}
	
	public static boolean blokingOurGoalKeeper(RobotAPI myRobotAPI){
		Vec2 goalKeeper = myRobotAPI.getGoalkeeper();
		return myRobotAPI.isBlocking(myRobotAPI.getPosition(), goalKeeper) || myRobotAPI.teammateBlocking();
	}
	
	public static void volverAPosicionInicial(RobotAPI myRobotAPI, Vec2 centro){
		Vec2 me = myRobotAPI.getPosition();
		if (me.distance(centro) <= 0.20 ){
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		}else{
			myRobotAPI.setSpeed(3.0);
			Vec2 egoC = myRobotAPI.toEgocentricalCoordinates(centro);
			myRobotAPI.setSteerHeading(egoC.t);
			myRobotAPI.surroundPoint(me,egoC);
		}
	}
	
	public static Vec2 teamMateOffensive(RobotAPI r){
		Vec2[] team = r.getTeammates();
		for (Vec2 v : team) {
			Vec2 field = r.toFieldCoordinates(v);
			if (field.x*r.getFieldSide() > 0) return v;
		}
		return r.toEgocentricalCoordinates(new Vec2(r.getFieldSide(),0));
	}
}
