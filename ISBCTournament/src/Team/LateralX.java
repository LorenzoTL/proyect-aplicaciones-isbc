package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class LateralX extends Behaviour{

	State state;
	int posD;
	int posA;
	double x;
	double y;
	
	public LateralX(int id){
		if (id == 2){
			this.posD = 1;
			this.posA = 0;
			this.x = -0.15;
			this.y = 0.55;
		}else{
			this.posD = 2;
			this.posA = 3;
			this.x = -0.15;
			this.y = -0.55;
		}
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State s){
		this.state = s;
	}
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		int q = F.quadrant(ball);
		if(q == 0 || q == 3){
			this.setState(new Ofensive());
		}else {
			this.setState(new Defensive());
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("lateralXBehaviour");
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
	public void volverAPosicionInicial(Vec2 me,boolean ataque){
		Vec2 pos = myRobotAPI.getPosition();
		int p = posD;
		if(ataque) p = posA;
		if(F.quadrant(pos) == p) {
			myRobotAPI.setSpeed(0.0);
			return;
		}
		double i = 0;
		double j = 0;
		if (ataque){
			i = 0.13;
			j = 0.16;
		}else{
			i = -0.16;
			j = -0.14;
		}
		if (pos.x <= j && pos.x >=i){
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
	private interface State{
		void action();
	}
	
	private class Defensive implements State{
		
		public void action(){
			if(myRobotAPI.blocked()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.avoidCollisions();
			}else{
				myRobotAPI.setSpeed(3.0);
				Vec2 me = myRobotAPI.getPosition();
				int q = F.quadrant(me);
				Vec2 ball = myRobotAPI.getBall();
				int b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
				if (b == posD){
					if (myRobotAPI.toFieldCoordinates(ball).x >= me.x){
						if(myRobotAPI.behindEverybody()) myRobotAPI.blockForward();
						else{
							myRobotAPI.setSteerHeading(ball.t);
							if (myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
								myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.toEgocentricalCoordinates(myRobotAPI.getOpponentsGoal()));
								if (myRobotAPI.canKick()){
									if(myRobotAPI.getObstacles().length == 0) myRobotAPI.kick();
									else myRobotAPI.passBall(myRobotAPI.getClosestMate());
								}
							}else volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)),false);
						}
					}else{
						myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
						if (myRobotAPI.canKick()) myRobotAPI.kick();
					}
				}else volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)),false);
			}
		}
		
	}
	
	private class Ofensive implements State{
		
		public void action(){
			if(myRobotAPI.blocked()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.avoidCollisions();
			}else{
				myRobotAPI.setSpeed(3.0);
				Vec2 me = myRobotAPI.getPosition();
				int q = F.quadrant(me);
				Vec2 ball = myRobotAPI.getBall();
				int b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
				if (q == posA){
					if (q == b){
						if (myRobotAPI.toFieldCoordinates(ball).x >= me.x){
							myRobotAPI.setSteerHeading(ball.t);
							if (myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
								if (myRobotAPI.alignedToBallandGoal()){
									myRobotAPI.kick();
								}else{
									myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
									if (myRobotAPI.canKick()) myRobotAPI.kick();
								}
							}else{
								myRobotAPI.setSpeed(0.0);
							}
						}else{
							volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(-1*x,y)),true);
						}
					}else{
						volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(-1*x,y)),true);
					}
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(-1*x,y)),true);
				}
			}
		}
		
	}
	
	
	
}
