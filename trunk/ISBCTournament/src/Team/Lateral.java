package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Lateral extends Behaviour {
	
	static final double x = -1.0;
	static final double y = 0.55;
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		/***TODO: esto solo funciona cuando el equipo esta en la izquierda, modificar
		 * para que el comportamiento distinga cual es nuestro campo***/
		myRobotAPI.setSpeed(3);
		if(myRobotAPI.closestToBall()){
			if (myRobotAPI.canKick()){
				myRobotAPI.kick();
			}
		}else{
			Vec2 me = new Vec2(x,y);
			if(me.distance(myRobotAPI.getPosition()) <= 0.35){
				myRobotAPI.setSpeed(0);
			}else{
				if (myRobotAPI.getBall().x > 0 && myRobotAPI.getBall().y > 0){
					myRobotAPI.setSpeed(3);
					if(myRobotAPI.getPosition().x > 0 && myRobotAPI.getPosition().y < 0){
						// si estamos en el mismo cuadrante que el balon
						myRobotAPI.surroundPoint(myRobotAPI.getPosition(), new Vec2(myRobotAPI.getBall().x,myRobotAPI.getPosition().y));
					}else{
						myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getBall());
					}
				}else if (myRobotAPI.getBall().x < 0 && myRobotAPI.getBall().y > 0){
					myRobotAPI.setSpeed(3);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());	
				}else{
					myRobotAPI.setSpeed(3);
					Vec2 aux = new Vec2(x,y);
					aux = myRobotAPI.toEgocentricalCoordinates(aux);
					myRobotAPI.surroundPoint(aux, new Vec2(0,0));
				}
			}
		}
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("lateralBehaviour");
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
}
