package Team;

import teams.ucmTeam.*;

public class Manager extends TeamManager{

	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
	}
	
	public Behaviour getDefaultBehaviour(int id) {
		if (id == 0)
			return getBehaviour(0);
		else if (id == 2){
			Lateral l = (Lateral)getBehaviour(2);
			l.setId(id);
			return (Behaviour)l;
		}
		else if (id == 4){
			Lateral l = (Lateral)getBehaviour(2);
			l.setId(id);
			return (Behaviour)l;
		}
		else if (id==3)
			return getBehaviour(3);
		else if (id==1)
			return getBehaviour(4);
		return getBehaviour(1);
	}
	
	public Behaviour[] createBehaviours() {
		return new Behaviour[] {new GoalKeeper(),new GoToBall(),new LateralX(),new Forward(), new Defensa(),new LateralOfensivo(),new LateralDefensivo() };
	}
	
}
