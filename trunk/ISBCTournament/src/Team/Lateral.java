package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Lateral extends Behaviour {
	
	Vec2 me;
	LateralState state;
	
	public LateralState getState(){
		return state;
	}
	
	public void setState(LateralState s){
		this.state = s;
	}
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		if(myRobotAPI.closestToBall()){
			this.setState(new Ofensive());
		}else {
			this.setState(new Defensive());
		}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("lateralBehaviour");
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
	//PATRÓN STATE
	private interface LateralState{
		void action();
	}
	
	private class Defensive implements LateralState{
		public void action(){
			double y = 0.55;
			if(myRobotAPI.getID() == 4) y = -0.55;
			me = new Vec2(-1.0,y);
			Vec2 ball = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
			Vec2 i = myRobotAPI.getPosition();
			int q = ball.quadrant();
			int qi = i.quadrant();
			if (q == qi){
				myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				if (myRobotAPI.canKick()){
					myRobotAPI.kick();
				}else{
					Vec2 f = myRobotAPI.toFieldCoordinates(myRobotAPI.getClosestMate());
					myRobotAPI.passBall(f);
				}
			}else{
				if (me.distance(i)>0.35){
					myRobotAPI.setSpeed(3);
					myRobotAPI.setBehindBall(me);
					Vec2 aux = myRobotAPI.toEgocentricalCoordinates(me);
					myRobotAPI.surroundPoint(aux, new Vec2(0,0));
				}
				if (me.distance(i)<=0.35) myRobotAPI.setSpeed(0);
			}
		}
	}
	
	private class Ofensive implements LateralState{
		public void action(){
			myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			if (myRobotAPI.canKick()){
				myRobotAPI.kick();
			}
		}
	}
	
}
