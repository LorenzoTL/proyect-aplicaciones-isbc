package Team;

import teams.ucmTeam.*;

public class Manager extends TeamManager{

	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
		RobotAPI robot = _players[0].getRobotAPI();
		int k = 4;
		long time = robot.getTimeStamp();
		long quartertime = robot.getMatchTotalTime()/k;
		long step = robot.getTimestep();
		long mrt = robot.getMatchRemainingTime();
		int gf = robot.getMyScore();
		int gc = robot.getOpponentScore();
		int i = (int)time / (int)quartertime + 1;
		
		if(mrt <= quartertime){
			if (gf < gc) 
				estrategiaOfensiva();
			else if (gf > gc)
				estrategiaDefensiva();
			else	
				estrategiaEquilibrada();
		}else if(quartertime*i-step <time && time < quartertime*i + step){
			if (gf < gc) 
				estrategiaOfensivaEquilibrada();
			else if (gf > gc)
				estrategiaDefensivaEquilibrada();
			else	
				estrategiaEquilibrada();
		}
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
			return getBehaviour(7);
		return getBehaviour(1);
	}
	
	public Behaviour[] createBehaviours() {
		return new Behaviour[] {new GoalKeeper(),
								new GoToBall(),
								new LateralX(),
								new Forward(), 
								new Defensa(),
								new LateralOfensivo(),
								new LateralDefensivo(),
								new Cierre()};
	}
	
	private void estrategiaOfensiva(){
		Lateral l2 = (Lateral)getBehaviour(5);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);
		Lateral l4 = (Lateral)getBehaviour(5);
		l4.setId(4); 
		_players[4].setBehaviour((Behaviour)l4);
	}
	
	private void estrategiaOfensivaEquilibrada(){
		Lateral l2 = (Lateral)getBehaviour(5);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);
	}
	
	private void estrategiaDefensiva(){
		Lateral l2 = (Lateral)getBehaviour(6);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);
		Lateral l4 = (Lateral)getBehaviour(6);
		l4.setId(4); 
		_players[4].setBehaviour((Behaviour)l4);
	}
	
	private void estrategiaDefensivaEquilibrada(){
		Lateral l2 = (Lateral)getBehaviour(6);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);
	}
	
	private void estrategiaEquilibrada(){
		Lateral l2 = (Lateral)getBehaviour(2);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);
		Lateral l4 = (Lateral)getBehaviour(2);
		l4.setId(4); 
		_players[4].setBehaviour((Behaviour)l4);
	}
	
}
