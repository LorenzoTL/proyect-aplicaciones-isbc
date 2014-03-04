package t1314grupo03;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class MedioCentro extends Behaviour{
	
	State state;
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void configure() {}

	public void end() {}

	public void onInit(RobotAPI r) {
		r.setDisplayString("Medio_Centro");
	}

	public void onRelease(RobotAPI arg0) {}

	public int takeStep() {
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		int lado = myRobotAPI.getFieldSide();
		if ((lado==-1 && ball.x <= 0.75) || (lado==1 && -0.75<=ball.x))
			this.setState(new Presionar());
		else
			this.setState(new Esperar());
		this.state.action();
		return RobotAPI.ROBOT_OK;
	}
	
	class Esperar implements State{
		public void action(){
			if (myRobotAPI.blocked()) myRobotAPI.avoidCollisions();
			F.volverAPosicionInicial(myRobotAPI, new Vec2(0,0));
		}
	}
	
	class Presionar implements State{
		public void action(){
			myRobotAPI.setSpeed(3.0);
			if (myRobotAPI.blocked()) myRobotAPI.avoidCollisions();
			if (F.estaDetrasBalon(myRobotAPI)){
				myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
				if (myRobotAPI.alignedToBallandGoal()){
					myRobotAPI.kick();
				}else{
					Vec2 v = F.teamMateOffensive(myRobotAPI);
					myRobotAPI.setBehindBall(v);
					myRobotAPI.setSpeed(1.5);
					myRobotAPI.passBall(myRobotAPI.toFieldCoordinates(v));
				}
			}else{
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			}
		}
	}
}
