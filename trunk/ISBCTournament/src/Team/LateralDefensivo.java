package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class LateralDefensivo extends Behaviour implements Lateral {
	
	LateralState state;
	int posD;
	int posA;
	double x;
	double y;
	int id;
	int l;
	
	public LateralDefensivo(int id){
		this.id = id;
	}
	
	public LateralDefensivo(){}
	
	//region Gets and Sets
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
	
	public LateralState getState(){
		return state;
	}
	
	public void setState(LateralState s){
		this.state = s;
	}
	//endregion
	
	//region Methods from Behaviour
	public void configure() {}
	
	public int takeStep() {
		Vec2 me = myRobotAPI.getPosition();
		int q = F.quadrant(me);
		if (this.getL() == -1){
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
		r.setDisplayString("lateralBehaviour");
		initialization(myRobotAPI.getFieldSide());
	}
	
	public void end() {}
	
	public void onRelease(RobotAPI r) {}
	//endregion
	
	//region private methods
	public void volverAPosicionInicial(Vec2 me,boolean ataque){
		Vec2 pos = myRobotAPI.getPosition();
		double i,j = 0;
		if(this.getL() == -1){
			i = -0.12;
			j = -0.09;
		}else{
			i = 0.09;
			j = 0.12;
		}
		if (i <= pos.x && pos.x <= j){
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		}else{
			myRobotAPI.setSpeed(3.0);
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
					this.setX(-0.10);
					this.setY(0.55);
				}else{
					this.setPosD(0);
					this.setPosA(1);
					this.setX(0.10);
					this.setY(0.55);
				}
				break;
			case 4:
				if(this.getL() == -1){
					this.setPosD(2);
					this.setPosA(3);
					this.setX(-0.10);
					this.setY(-0.55);
				}else{
					this.setPosD(3);
					this.setPosA(2);
					this.setX(0.10);
					this.setY(-0.55);
				}
				break;
			default: break;
		}
	}
	//endregion
	
	//region Patron State
	private interface LateralState{
		void action();
	}
	
	private class Defensive implements LateralState{
		
		public void action(){
			Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
			int q = F.quadrant(ball);
			if(q == getPosD()){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				if (myRobotAPI.canKick()){
					myRobotAPI.kick();
				}
			}else{
				volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(x,y)),false);
			}	
		}
		
	}
	
	private class Ofensive implements LateralState{
		
		public void action(){
			myRobotAPI.setSpeed(3.0);
			Vec2 ball = myRobotAPI.getBall();
			int b = F.quadrant(myRobotAPI.toFieldCoordinates(ball));
			Vec2 me = myRobotAPI.getPosition();
			if (b == getPosA()){
				if(myRobotAPI.closestToBall() || F.estoyCerca(me,myRobotAPI.toFieldCoordinates(ball))){
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
					if (myRobotAPI.canKick()){
						myRobotAPI.kick();
					}
				}else {
					volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())),false);
				}
			}else{
				volverAPosicionInicial(myRobotAPI.toEgocentricalCoordinates(new Vec2(getX(),getY())),false);
			}
			
		}
		
	}
	//endregion
}
