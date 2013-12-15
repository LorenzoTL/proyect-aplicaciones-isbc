package Team;

//import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class Lateral extends Behaviour {

	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		
		if(myRobotAPI.closestToBall()){
			myRobotAPI.setSpeed(3);
			myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			if (myRobotAPI.canKick()){
				myRobotAPI.kick();
			}
		}else{
			
		}
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
	
}
