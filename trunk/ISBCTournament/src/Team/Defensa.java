package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Defensa extends Behaviour {
	
	Vec2 me;
	DefensaState state;
	int lado;
	
	public DefensaState getState(){
		return state;
	}
	
	public void setState(DefensaState s){
		this.state = s;
	}
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {

		me = myRobotAPI.toEgocentricalCoordinates(new Vec2(-1.145,0));
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		int q = ball.quadrant();
		if (lado == -1){
			if(q == 0 || q == 3){
				this.setState(new Ofensive());
			}else {
				this.setState(new Defensive());
			}
		}else{
			if(q == 1 || q == 2){
				this.setState(new Ofensive());
			}else {
				this.setState(new Defensive());
			}
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("defensaBehaviour");
		lado = myRobotAPI.getFieldSide();
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
	public void volverAPosicionInicial(Vec2 centro){
		myRobotAPI.setSteerHeading(centro.t);
		myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.toEgocentricalCoordinates(centro));
	}
	
//PATR�N STATE ------------------------------------------------------------------------------------------------------------------
	//Interfaz para implementar
	private interface DefensaState{
		void action();
	}
	
	//Sub clases que implementan la interfaz
	private class Defensive implements DefensaState{
		public void action(){
			 Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
             myRobotAPI.setSpeed(3.0);
             myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
             //myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getBall());
             myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
             if(myRobotAPI.canKick()){
                     //myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
                     myRobotAPI.kick();
                     
             }
		}
	}
	
	private class Ofensive implements DefensaState{
		public void action(){
			myRobotAPI.setSpeed(3.0);
			if(myRobotAPI.teammateBlocking()) myRobotAPI.avoidCollisions();
			Vec2 centro = myRobotAPI.toEgocentricalCoordinates(new Vec2(0.45*lado,0));
			Vec2 pos=myRobotAPI.getPosition();
			if ((lado==-1 && (pos.x <= -0.44 && pos.x >=-0.46)) ||
				(lado == 1 && (pos.x >= 0.44 && pos.x >= 0.46))){
					myRobotAPI.setSpeed(0.0);
					myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
			}
			else{	
				myRobotAPI.setSpeed(3.0);
				volverAPosicionInicial(centro);
			}			
			
		}	
	}
	
	
}
