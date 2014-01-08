package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Lateral extends Behaviour {
	
	Vec2 me;
	LateralState state;
	
	public LateralState getState(){
		return state;
	}
	
	public void setState(LateralState s){
		this.state = s;
	}
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		double y = 0.55;
		if(myRobotAPI.getID() == 2) y = -0.55;
		me = new Vec2(-1.0,y);
		int q = myRobotAPI.getPosition().quadrant();
		if(q == 0 || q == 3){
			this.setState(new Ofensive());
		}else {
			this.setState(new Defensive());
		}
		this.state.action();
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
	
	public void volverAPosicionInicial(){
		myRobotAPI.setSteerHeading(me.t);
		myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.toEgocentricalCoordinates(me));
	}
	
//PATRÓN STATE ------------------------------------------------------------------------------------------------------------------
	//Interfaz para implementar
	private interface LateralState{
		void action();
	}
	
	//Sub clases que implementan la interfaz
	private class Defensive implements LateralState{
		public void action(){
			Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
			int q = ball.quadrant();
			myRobotAPI.setSpeed(0.0);
			if(myRobotAPI.closestToBall() || q == 1 || q == 2){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
				myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getBall());
				if(myRobotAPI.canKick()){
					myRobotAPI.passBall(myRobotAPI.getClosestMate());
				}
			}else{
				myRobotAPI.setSpeed(0);
			}
		}
	}
	
	private class Ofensive implements LateralState{
		public void action(){
			myRobotAPI.setSpeed(3.0);
			if (myRobotAPI.closestToBall()){
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				if (myRobotAPI.canKick()){
					myRobotAPI.kick();
				}
			}else{
				myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
				Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
				if (ball.quadrant() == 1 || ball.quadrant() == 2){
					myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getBall());
				}else{
					volverAPosicionInicial();
				}
			}
		}	
	}
	
	
}
