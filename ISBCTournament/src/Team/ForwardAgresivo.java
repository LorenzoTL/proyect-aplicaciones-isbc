package Team;


import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class ForwardAgresivo extends Behaviour {
	
	Vec2 me;
	State state;
	int lado;
	int cont=0;
	double centro;

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
		me = myRobotAPI.toEgocentricalCoordinates(new Vec2(centro,-0.4));
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		int q = ball.quadrant();
		//int q = myRobotAPI.getPosition().quadrant();
		if (lado == -1){
			if(q == 0 || q==3){
				this.setState(new Ofensive());
			}else {
				this.setState(new Defensive());
			}
		}else{
			if(q == 1 || q==2){
				this.setState(new Ofensive());
			}else {
				this.setState(new Defensive());
			}
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("forwardBehaviour");
		lado = myRobotAPI.getFieldSide();
		if (lado == -1) centro = 1;
		else centro = -1;
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
	public void volverAPosicionInicial(){
		myRobotAPI.setSteerHeading(me.t);
		myRobotAPI.surroundPoint(myRobotAPI.getPosition(), me);
	}
	 public  void pasea(RobotAPI myRobotAPI) {
		 if (cont%2==0){
         myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
		 }else{
			 myRobotAPI.setSteerHeading(-myRobotAPI.getBall().t);
		 }
		 cont++;
         myRobotAPI.setSpeed(0.5);
	 }
	
//PATRÓN STATE ------------------------------------------------------------------------------------------------------------------
	
	//Sub clases que implementan la interfaz
	private class Defensive implements State{
		public void action(){
			
			Vec2 pos=myRobotAPI.getPosition();
			if ((lado == -1 && (pos.x >= 0.90 && pos.x <=1.10)) ||
				(lado == 1 && (pos.x <= -0.90 && pos.x >= -1.10)))
			{
				myRobotAPI.setSpeed(0.0);
				myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
				pasea(myRobotAPI);
			}
			else{
				
				myRobotAPI.setSpeed(3.0);
				volverAPosicionInicial();
			}
			
		}
	}
	
	private class Ofensive implements State{
		public void action(){
			myRobotAPI.setSpeed(3.0);
			if (myRobotAPI.blocked())
                myRobotAPI.avoidCollisions();
			Vec2 ball = myRobotAPI.getBall();
			Vec2 pos=myRobotAPI.getPosition();
			if ((lado == -1 && (pos.x >= 0.90 && pos.x <=1.10) && ball.x<=0.9) ||
				(lado == 1 && (pos.x <= -0.90 && pos.x >= -1.10)&& ball.x >=-0.9)){
				 myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
		            if (myRobotAPI.getBall().r < 0.1){
		           
		                myRobotAPI.setSpeed(1.5);
		                if (!F.estaDetrasBalon(myRobotAPI)){
		                    myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
		    			}
		                myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
		            }
		            if (myRobotAPI.canKick() && myRobotAPI.getOpponentsGoal().r < 0.8)
		                myRobotAPI.kick();
			}
			else{
				volverAPosicionInicial();
			}
		}	
	}
	
	
}
