package Team;


import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Forward extends Behaviour {
	
	Vec2 me;
	ForwardState state;
	
	public ForwardState getState(){
		return state;
	}
	
	public void setState(ForwardState s){
		this.state = s;
	}
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		me = myRobotAPI.toEgocentricalCoordinates(new Vec2(0.25,0));
		Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		int q = ball.quadrant();
		//int q = myRobotAPI.getPosition().quadrant();
		if(q == 0 || q==3){
			this.setState(new Ofensive());
		}else {
			this.setState(new Defensive());
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("forwardBehaviour");
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
	
//PATRÓN STATE ------------------------------------------------------------------------------------------------------------------
	//Interfaz para implementar
	private interface ForwardState{
		void action();
	}
	
	//Sub clases que implementan la interfaz
	private class Defensive implements ForwardState{
		public void action(){
			
			Vec2 pos=myRobotAPI.getPosition();
			if (pos.x >= 0.24 && pos.x <=0.26)
			{
				myRobotAPI.setSpeed(0.0);
				myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
			}
			else{
				
				myRobotAPI.setSpeed(3.0);
				volverAPosicionInicial();
			}
			
		}
	}
	
	private class Ofensive implements ForwardState{
		public void action(){
			myRobotAPI.setSpeed(3.0);
			myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			if (myRobotAPI.canKick()){
				myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
				myRobotAPI.kick();
			}
			// Si tengo un mate cerca de porteria rival, se la paso 
			// Coming soon...
		}	
	}
	
	
}