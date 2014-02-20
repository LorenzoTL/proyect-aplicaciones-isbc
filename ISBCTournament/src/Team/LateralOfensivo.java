package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class LateralOfensivo extends Behaviour implements Lateral {

	State state;
	int posD;
	int posA;
	double x;
	double y;
	double a;
	int id;
	int l;
	
	public LateralOfensivo(int id){
		this.id = id;
	}
	public LateralOfensivo(){}
	
	//region Gets and Sets
	public double getA() {
		return a;
	}
	
	public void setA(double a) {
		this.a = a;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getL() {
		return l;
	}
	
	public void setL(int l) {
		this.l = l;
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
	//endregion
	
	//region Methods from Behaviour
	public void configure() {}
	
	public int takeStep() {
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		int q = F.quadrant(ball);
		if (q == getPosA()){
			this.setState(new OfensivaTotal());
		}else{
			this.setState(new MantenerPosicion());
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("LO_" + getId());
		initialization(myRobotAPI.getFieldSide());
	}
	
	public void end() {}
	
	public void onRelease(RobotAPI r) {}
	//endregion
	
	//region Private Methods
	
	public void volverAPosicionInicial(Vec2 me,boolean ataque){
		Vec2 pos = myRobotAPI.getPosition();
		double i = 0;
		double j = 0;
		if (ataque){
			if(getL() == -1){
				i = 0.63;
				j = 0.67;
			}else{
				i = -0.67;
				j = -0.63;
			}
		}else{
			if(getL() == -1){
				i = -0.20;
				j = -0.10;
			}else{
				i = 0.10;
				j = 0.20;
			}
		}
		
		if (i <= pos.x && pos.x <= j){
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		}else{
			myRobotAPI.setSpeed(2.0);
			myRobotAPI.setSteerHeading(me.t);
			myRobotAPI.surroundPoint(myRobotAPI.getPosition(),me);
		}
	}
	
	public void initialization(int fieldSide) {
		this.setL(fieldSide);
		switch(this.getId()){
			case 2:
				if(this.getL() == -1){
					this.setPosD(1);
					this.setPosA(0);
					this.setX(0.65);
					this.setY(0.50);
				}else{
					this.setPosD(0);
					this.setPosA(1);
					this.setX(-0.65);
					this.setY(0.50);
				}
				break;
			case 4:
				if(this.getL() == -1){
					this.setPosD(2);
					this.setPosA(3);
					this.setX(0.65);
					this.setY(-0.55);
				}else{
					this.setPosD(3);
					this.setPosA(2);
					this.setX(-0.65);
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
	
	private class MantenerPosicion implements State{
		
		public void action(){
			myRobotAPI.setSpeed(3.0);
			if (myRobotAPI.teammateBlocking()) myRobotAPI.avoidCollisions();
			if (myRobotAPI.behindEverybody()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			}else{
				Vec2  ball = myRobotAPI.getBall();
				double b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
				if (b == getPosD()){
					if (myRobotAPI.closestToBall() || F.estoyCerca(myRobotAPI.getPosition(),myRobotAPI.toFieldCoordinates(ball))){ 
						myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
						if(myRobotAPI.canKick()){
							myRobotAPI.kick();
						}
					}
				}else{ 
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getL()*0.15,getY())), false);
				}
			}
			
		}
		
	}
	
	private class OfensivaTotal implements State{
		
		public void action(){
			myRobotAPI.setSpeed(3.0);
			if (myRobotAPI.teammateBlocking()) myRobotAPI.avoidCollisions();
			Vec2 ball = myRobotAPI.getBall();
			Vec2 me = myRobotAPI.getPosition();
			int b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
			if (b == getPosA()){
				if(myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
					//myRobotAPI.setSteerHeading(ball.t);
					if(myRobotAPI.canKick()) {
						myRobotAPI.kick();
					}else{
						myRobotAPI.alignedToBallandGoal();
						myRobotAPI.setSteerHeading(ball.t);
					}
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())), true);
				}
			}else{
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			}
			/*}else{
				volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())), true);
			}*/
		}
		
	}
	//endregion
}
