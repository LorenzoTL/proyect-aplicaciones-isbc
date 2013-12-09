package Ejemplo;

import teams.ucmTeam.*;

public class Entrenador extends TeamManager{
	
	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
		// No hacemos nada
	}
	
	public Behaviour getDefaultBehaviour(int id) {
		return getBehaviour(0);
	}
	
	public Behaviour[] createBehaviours() {
		Behaviour[] behav =new Behaviour[1];
		behav[0]=new GoToBall();
		return behav;
	}
}
