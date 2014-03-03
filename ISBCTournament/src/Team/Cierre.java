package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Cierre extends Behaviour{

	private int lado;
	private State s;
	
	public int getLado() {
		return lado;
	}
	
	public void setLado(int lado) {
		this.lado = lado;
	}
	
	public State getState() {
		return s;
	}
	
	public void setState(State s) {
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
		if (F.blokingOurGoalKeeper(myRobotAPI)){
			setState(new Blocking());
		}else if (getLado()*ball.x > 0){
			setState(new Defensa());
		}else{
			setState(new Cerrar());
		}
		getState().action();
		return myRobotAPI.ROBOT_OK;
	}
	
	class Defensa implements State{
		public void action() {
			myRobotAPI.setDisplayString("Defendiendo");
			Vec2 me = myRobotAPI.getPosition();
			Vec2 ball = myRobotAPI.getBall();
			Vec2 fieldBall = myRobotAPI.toFieldCoordinates(ball);
			myRobotAPI.setSpeed(3.0);
			if ( (lado == -1 && fieldBall.x < (me.x + 0.25)) || (lado == 1 && fieldBall.x > (me.x + 0.25)) || 
					(me.distance(fieldBall) <= 0.50 && !F.estanDefendiendo(myRobotAPI))){
				myRobotAPI.setSteerHeading(ball.t);
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				if (myRobotAPI.canKick()) myRobotAPI.kick();
			}else{
				Vec2 pos = F.closestToOurGoal(myRobotAPI);
				F.volverAPosicionInicial(myRobotAPI,pos);
			}
		}
	}
	
	class Cerrar implements State{
		public void action(){
			myRobotAPI.setDisplayString("volverPosicion");
			F.volverAPosicionInicial( myRobotAPI,new Vec2(0.75*lado,0.0));
		}
	}

	class Blocking implements State{
		public void action(){
			myRobotAPI.setSpeed(3.0);
			myRobotAPI.setDisplayString("Bloqueado");
			myRobotAPI.avoidCollisions();
		}
	}
}