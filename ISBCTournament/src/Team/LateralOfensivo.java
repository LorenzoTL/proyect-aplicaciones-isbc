package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class LateralOfensivo extends Behaviour {

	State state;
	int posD;
	int posA;
	double x;
	double y;
	double a;
	
	public LateralOfensivo(int id){
		this.a = 1.145;
		this.x = 0.65;
		if (id == 2){
			this.posD = 1;
			this.posA = 0;
			this.y = 0.40;
		}else{
			this.posD = 2;
			this.posA = 3;
			this.y = -0.40;
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
			this.setState(new OfensivaTotal());
		}else {
			this.setState(new MantenerPosicion());
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("lateralOfensivoBehaviour");
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
			i = 0.55;
			j = 0.70;
		}else{
			i = -0.4;
			j = 0.4;
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
	
	private class MantenerPosicion implements State{
		
		public void action(){
			if (myRobotAPI.blocked()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.avoidCollisions();
			}else{
				if (myRobotAPI.behindEverybody()){
					myRobotAPI.setSpeed(3.0);
					myRobotAPI.blockForward();
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(0,y)), false);
				}
			}
		}
		
	}
	
	private class OfensivaTotal implements State{
		
		public void action(){
			if (myRobotAPI.blocked()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.avoidCollisions();
			}else{
				Vec2 ball = myRobotAPI.getBall();
				Vec2 me = myRobotAPI.getPosition();
				int b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
				myRobotAPI.setSpeed(3.0);
				if (b == posA){
					if(myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
						myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
						if(myRobotAPI.alignedToBallandGoal()) myRobotAPI.kick();
						else{
							Vec2 f = null;
							if(myRobotAPI.toFieldCoordinates(myRobotAPI.getClosestMate()).x > me.x)
								f = myRobotAPI.getClosestMate();
							else f = myRobotAPI.toEgocentricalCoordinates(new Vec2(a,0));							
							myRobotAPI.setSteerHeading(f.t);
							if (myRobotAPI.canKick()){
								myRobotAPI.passBall(f);
							}
						}
					}else{
						if(F.quadrant(me) != posA) 
							volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)), true);
						else myRobotAPI.setSpeed(0.0);
					}
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)),true);	
				}
			}
		}
		
	}
}
