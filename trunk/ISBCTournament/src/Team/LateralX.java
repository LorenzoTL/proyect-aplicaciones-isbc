package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class LateralX extends Behaviour implements Lateral{

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
	
	public LateralX(){}
	
	//region gets and sets

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		r.setDisplayString("LX_" + getId());
		this.initialization(myRobotAPI.getFieldSide());
	}

	public void end() {}
	
	public void onRelease(RobotAPI r) {}
	
	//endregion

	//region Private Methods
//	private boolean detrasDelBalon(Vec2 me,Vec2 ball){
//		Vec2 b = myRobotAPI.toFieldCoordinates(ball);
//		if (getL() == -1) return b.x >= me.x;
//		return b.x < me.x;
//	}
	
	public void volverAPosicionInicial(Vec2 me,boolean ataque){
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
				i = -0.64;
				j = -0.58;
			}else{
				i = 0.58;
				j = 0.64;
			}
		}
		if ( i <= pos.x && pos.x <= j){
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		}else{
			myRobotAPI.setSpeed(3.0);
			myRobotAPI.setSteerHeading(me.t);
			myRobotAPI.surroundPoint(myRobotAPI.getPosition(), me);
		}
	}
	
	public void initialization(int fieldSide) {
		this.setL(fieldSide);
		switch(this.getId()){
			case 2:
				if(this.getL() == -1){
					this.setPosD(1);
					this.setPosA(0);
					this.setX(-0.15);
					this.setY(0.45);
				}else{
					this.setPosD(0);
					this.setPosA(1);
					this.setX(0.15);
					this.setY(0.45);
				}
				break;
			case 4:
				if(this.getL() == -1){
					this.setPosD(2);
					this.setPosA(3);
					this.setX(-0.15);
					this.setY(-0.45);
				}else{
					this.setPosD(3);
					this.setPosA(2);
					this.setX(0.15);
					this.setY(-0.45);
				}
				break;
			default: break;
		}
	}
	//endregion
	
	//region Patron State
	private class Defensive implements State{
		
		public void action(){
			myRobotAPI.setDisplayString("Defender");
			myRobotAPI.setSpeed(3.0);
			Vec2 me = myRobotAPI.getPosition();
			int q = F.quadrant(me);
			Vec2 ball = myRobotAPI.getBall();
			int b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
			if (myRobotAPI.blocked()) myRobotAPI.avoidCollisions();
			if (b == getPosD()){
				if (F.estaDetrasBalon(myRobotAPI)){
					myRobotAPI.setSteerHeading(ball.t);
					if (myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
						myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getOpponentsGoal());
						if (myRobotAPI.canKick()){
							if(myRobotAPI.getObstacles().length == 0) myRobotAPI.kick();
							else myRobotAPI.passBall(myRobotAPI.getClosestMate());
						}else{
							myRobotAPI.alignedToBallandGoal();
						}
					}else{
						volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2((l*0.60),getY())),false);
						//myRobotAPI.setSpeed(0.0);
						//myRobotAPI.setSteerHeading(ball.t);
					}
				}else{
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				}
			}else{
				volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2((l*0.60),getY())),false);
			}
		}
		
	}
	
	private class Offensive implements State{
		
		public void action(){
			myRobotAPI.setDisplayString("Atacar");
			myRobotAPI.setSpeed(3.0);
			Vec2 me = myRobotAPI.getPosition();
			int q = F.quadrant(me);
			Vec2 ball = myRobotAPI.getBall();
			int b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
			if (b == getPosA()){
				if (F.estaDetrasBalon(myRobotAPI)){
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
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				}
			}else{
				volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(-1*getX(),getY())),true);
			}
		}
		
	}
	//endregion
}
