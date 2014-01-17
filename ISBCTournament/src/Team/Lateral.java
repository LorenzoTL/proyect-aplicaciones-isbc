package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Lateral extends Behaviour {
	
	LateralState state;
	int posD;
	int posA;
	double x;
	double y;
	
	public Lateral(int id){
		if (id == 2){
			this.posD = 1;
			this.posA = 0;
			this.x = -0.10;
			this.y = 0.55;
		}else{
			this.posD = 2;
			this.posA = 3;
			this.x = -0.10;
			this.y = -0.55;
		}
	}
	
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
		Vec2 me = myRobotAPI.getPosition();
		int q = F.quadrant(me);//F.quadrant(myRobotAPI.toFieldCoordinates(myRobotAPI.getBall())); 
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
	
	public void volverAPosicionInicial(Vec2 me){
		Vec2 pos = myRobotAPI.getPosition();
		if (pos.x <= -0.09 && pos.x >=-0.12){
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		}else{
			myRobotAPI.setSpeed(3.0);
			myRobotAPI.setSteerHeading(me.t);
			myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.toEgocentricalCoordinates(me));
		}
	}
	
//PATRÓN STATE ------------------------------------------------------------------------------------------------------------------
	//Interfaz para implementar
	private interface LateralState{
		void action();
	}
	
	//Sub clases que implementan la interfaz
	private class Defensive implements LateralState{
		
		public void action(){
			if(myRobotAPI.blocked()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.avoidCollisions();
			}else{
				Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
				int q = F.quadrant(ball);
				myRobotAPI.setSpeed(0.0);
				if(q == posD){
					myRobotAPI.setSpeed(3.0);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
					if (myRobotAPI.canKick()){
						myRobotAPI.kick();
					}
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)));
				}
			}
		}
		
	}
	
	private class Ofensive implements LateralState{
		
		public void action(){
			myRobotAPI.setSpeed(3.0);
			if(myRobotAPI.blocked()) {
				myRobotAPI.avoidCollisions();
			}else{
				Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
				Vec2 me = myRobotAPI.getPosition();
				if (myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
					if(F.quadrant(me) == posA){
						myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
						if (myRobotAPI.canKick()){
							myRobotAPI.kick();
						}
					}else {
						volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)));
					}
				}else{
					myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
					int q = F.quadrant(ball);
					if (q == posD){
						myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
						if (myRobotAPI.canKick()){
							myRobotAPI.passBall(myRobotAPI.getClosestMate());
						}
					}else{
						volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)));
					}
				}
			}
		}
		
	}	
}
