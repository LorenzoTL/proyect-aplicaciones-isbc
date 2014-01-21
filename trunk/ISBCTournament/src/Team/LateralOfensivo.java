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
	int id;
	int l;
	
	public LateralOfensivo(int id){
		this.id = id;
	}
	
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
		if (this.getL() == -1){
			if(q == 0 || q == 3){
				this.setState(new OfensivaTotal());
			}else {
				this.setState(new MantenerPosicion());
			}
		}else{
			if(q == 1 || q == 2){
				this.setState(new OfensivaTotal());
			}else {
				this.setState(new MantenerPosicion());
			}
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("lateralOfensivoBehaviour");
		initialization(myRobotAPI.getFieldSide());
	}
	
	public void end() {}
	
	public void onRelease(RobotAPI r) {}
	//endregion
	
	//region Private Methods
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
			if(getL() == -1){
				i = 0.55;
				j = 0.70;
			}else{
				i = -0.70;
				j = -0.55;
			}
		}else{
			i = -0.4;
			j = 0.4;
		}
		if (i <= pos.x && pos.x <= j){
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
		this.setA(-1.145*fieldSide);
		switch(this.getId()){
			case 2:
				if(this.getL() == -1){
					this.setPosD(1);
					this.setPosA(0);
					this.setX(0.65);
					this.setY(0.40);
				}else{
					this.setPosD(0);
					this.setPosA(1);
					this.setX(-0.65);
					this.setY(0.40);
				}
				break;
			case 4:
				if(this.getL() == -1){
					this.setPosD(2);
					this.setPosA(3);
					this.setX(0.65);
					this.setY(-0.40);
				}else{
					this.setPosD(3);
					this.setPosA(2);
					this.setX(-0.65);
					this.setY(-0.40);
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
			if (myRobotAPI.blocked()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.avoidCollisions();
			}else{
				if (myRobotAPI.behindEverybody()){
					myRobotAPI.setSpeed(3.0);
					myRobotAPI.blockForward();
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(0,getY())), false);
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
				if (b == getPosA()){
					if(myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
						myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
						if(myRobotAPI.alignedToBallandGoal()) myRobotAPI.kick();
						else{
							Vec2 f = null;
							if(myRobotAPI.toFieldCoordinates(myRobotAPI.getClosestMate()).x > me.x)
								f = myRobotAPI.getClosestMate();
							else f = myRobotAPI.toEgocentricalCoordinates(new Vec2(getA(),0));							
							myRobotAPI.setSteerHeading(f.t);
							if (myRobotAPI.canKick()){
								myRobotAPI.passBall(f);
							}
						}
					}else{
						if(F.quadrant(me) != getPosA()) 
							volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())), true);
						else myRobotAPI.setSpeed(0.0);
					}
				}else{
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())),true);	
				}
			}
		}
		
	}
	//endregion
}
