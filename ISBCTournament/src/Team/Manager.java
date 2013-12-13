package Team;

import Ejemplo.GoToBall;
import teams.ucmTeam.*;
//import teams.ucmTeam.Message.Type;

public class Manager extends TeamManager{

	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
	}
	
	public Behaviour getDefaultBehaviour(int id) {
		if (id == 0)
			return getBehaviour(0);
		return getBehaviour(1);
	}
	
	public Behaviour[] createBehaviours() {
		return new Behaviour[] {new GoalKeeper(),new GoToBall() };
	}
	
}
