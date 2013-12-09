package Ejemplo;

import teams.ucmTeam.*;

public class Blocker extends Behaviour {
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		myRobotAPI.blockGoalKeeper();
		return RobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("BlockerBehaviour");
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}

}
