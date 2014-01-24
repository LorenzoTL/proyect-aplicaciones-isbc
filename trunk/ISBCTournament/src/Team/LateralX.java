package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class LateralX extends Behaviour{

	State state;
	int posD; //defensive quadrant
	int posA; //offensive quadrant
	double x; 
	double y;
	int l; // left = -1 ; right = 1
	int id;
	
	public LateralX(int id){
		this.id = id;
	}
	
	//region gets and sets

	public int getId() {
		return id;
	}
	
	public int getPosA() {
		return posA;
	}
	
	public void setPosA(int posA) {
		this.posA = posA;
	}
	
	public int getPosD() {
		return posD;
	}
	
	public void setPosD(int posD) {
		this.posD = posD;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State s){
		this.state = s;
	}
	
	public int getL() {
		return l;
	}
	
	public void setL(int l) {
		this.l = l;
	}
	
	//endregion
	
	//region Methods from Behaviour
	public void configure() {}
	
	public int takeStep() {
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		int q = F.quadrant(ball);
		if (getL() == -1){
			if(q == 0 || q == 3){
				this.setState(new Offensive());
			}else {
				this.setState(new Defensive());
			}
		}else{
			if(q == 1 || q == 2){
				this.setState(new Offensive());
			}else {
				this.setState(new Defensive());
			}
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("lateralXBehaviour");
		this.initialization(myRobotAPI.getFieldSide());
	}

	public void end() {}
	
	public void onRelease(RobotAPI r) {}
	
	//endregion

	//region Private Methods
	private boolean detrasDelBalon(Vec2 me,Vec2 ball){
		Vec2 b = myRobotAPI.toEgocentricalCoordinates(ball);
		if (getL() == -1) return b.x >= me.x;
		return b.x < me.x;
	}
	
	private void volverAPosicionInicial(Vec2 me,boolean ataque){
		Vec2 pos = myRobotAPI.getPosition();
		int p = getPosD();
		if(ataque) p = getPosA();
		if(F.quadrant(pos) == p) {
			myRobotAPI.setSpeed(0.0);
			return;
		}
		double i = 0;
		double j = 0;
		if (ataque){
			if (getL() == -1){
				i = 0.13;
				j = 0.16;
			}else{
				i = -0.16;
				j = -0.13;
			}
		}else{
			if (getL() == -1){
				i = -0.16;
				j = -0.14;
			}else{
				i = 0.14;
				j = 0.16;
			}
		}
		if ( i <= pos.x && pos.x <= j){
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		}else{
			myRobotAPI.setSpeed(3.0);
			myRobotAPI.setSteerHeading(me.t);
			myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.toEgocentricalCoordinates(me));
		}
	}
	
	private void initialization(int fieldSide) {
		this.setL(fieldSide);
		switch(this.getId()){
			case 2:
				if(this.getL() == -1){
					this.setPosD(1);
					this.setPosA(0);
					this.setX(-0.15);
					this.setY(0.55);
				}else{
					this.setPosD(0);
					this.setPosA(1);
					this.setX(0.15);
					this.setY(0.55);
				}
				break;
			case 4:
				if(this.getL() == -1){
					this.setPosD(2);
					this.setPosA(3);
					this.setX(-0.15);
					this.setY(-0.55);
				}else{
					this.setPosD(3);
					this.setPosA(2);
					this.setX(0.15);
					this.setY(-0.55);
				}
				break;
			default: break;
		}
	}
	//endregion
	
	//region Patron State
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
				if (b == getPosD()){
					if (detrasDelBalon(me,ball)){
						if(myRobotAPI.behindEverybody()) myRobotAPI.blockForward();
						else{
							myRobotAPI.setSteerHeading(ball.t);
							if (myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
								myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.toEgocentricalCoordinates(myRobotAPI.getOpponentsGoal()));
								if (myRobotAPI.canKick()){
									if(myRobotAPI.getObstacles().length == 0) myRobotAPI.kick();
									else myRobotAPI.passBall(myRobotAPI.getClosestMate());
								}
							}else volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())),false);
						}
					}else{
						myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
						if (myRobotAPI.canKick()) myRobotAPI.kick();
					}
				}else volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())),false);
			}
		}
		
	}
	
	private class Offensive implements State{
		
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
				if (q == getPosA()){
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
							volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(-1*getX(),getY())),true);
						}
					}else{
						volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(-1*getX(),getY())),true);
					}
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(-1*getX(),getY())),true);
				}
			}
		}
		
	}
	//endregion
}
