package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Cierre extends Behaviour{

	private int lado;
	private CierreState s;
	
	public int getLado() {
		return lado;
	}
	
	public void setLado(int lado) {
		this.lado = lado;
	}
	
	public CierreState getState() {
		return s;
	}
	
	public void setState(CierreState s) {
		this.s = s;
	}
	
	public void volverAPosicionInicial(Vec2 me, Vec2 centro){
		if (me.distance(centro) <= 0.20 ){
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		}else{
			myRobotAPI.setSpeed(3.0);
			Vec2 egoC = myRobotAPI.toEgocentricalCoordinates(centro);
			myRobotAPI.setSteerHeading(egoC.t);
			myRobotAPI.surroundPoint(me,egoC);
		}
	}
	
	public Vec2 closestToOurGoal(){
		Vec2 pos =  myRobotAPI.closestTo(myRobotAPI.getOpponents(), myRobotAPI.getOurGoal());
		pos = myRobotAPI.toFieldCoordinates(pos);
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOurGoal()).distance(pos) <= 0.75)
				return pos;
		return new Vec2(0.75*lado,0.0);
	}
	
	public void configure() {
	}

	public void end() {
	}

	public void onInit(RobotAPI r) {
		r.setDisplayString("Cierre");
		lado = myRobotAPI.getFieldSide();
	}

	public void onRelease(RobotAPI r) {
	}

	public int takeStep() {
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		if (getLado()*ball.x > 0){
			setState(new Defensa());
		}else{
			setState(new Cerrar());
		}
		getState().action();
		return myRobotAPI.ROBOT_OK;
	}
	
	interface CierreState{
		void action();
	}
	
	class Defensa implements CierreState{
		public void action() {
			if (myRobotAPI.blocked()) myRobotAPI.avoidCollisions();
			Vec2 me = myRobotAPI.getPosition();
			Vec2 ball = myRobotAPI.getBall();
			Vec2 fieldBall = myRobotAPI.toFieldCoordinates(ball);
			myRobotAPI.setSpeed(3.0);
			if ((lado == -1 && fieldBall.x < me.x) || (lado == 1 && fieldBall.x > me.x) || 
					me.distance(fieldBall) <= 0.40){
				myRobotAPI.setSteerHeading(ball.t);
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				if (myRobotAPI.canKick()) myRobotAPI.kick();
			}else{
				Vec2 pos = closestToOurGoal();
				volverAPosicionInicial( me,pos);
			}
		}
	}
	
	class Cerrar implements CierreState{
		public void action(){
			//Vec2 pos = closestToOurGoal();
			if (myRobotAPI.blocked()) myRobotAPI.avoidCollisions();
			volverAPosicionInicial( myRobotAPI.getPosition(),new Vec2(0.75*lado,0.0));
		}
	}
}
