package Team;

import teams.ucmTeam.*;
//import teams.ucmTeam.Message.Type;

public class Manager extends TeamManager{

	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
	}
	
	public Behaviour getDefaultBehaviour(int id) {
		return null;
	}
	
	public Behaviour[] createBehaviours() {
		return null;
	}
	
}
