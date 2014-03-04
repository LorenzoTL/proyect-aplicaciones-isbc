package t1314grupo03;

import teams.ucmTeam.*;

public class Manager extends TeamManager{

	public int actualDF;
	
	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
		RobotAPI robot = _players[0].getRobotAPI();
		int k = 6;
		long time = robot.getTimeStamp();
		long mt = robot.getMatchRemainingTime();
		long quartertime = robot.getMatchTotalTime()/k;
		long step = robot.getTimestep();
		int gf = robot.getMyScore();
		int gc = robot.getOpponentScore();
		int i = (int)time / (int)quartertime + 1;
		int _df = Math.abs(robot.getMyScore() - robot.getOpponentScore());
		
		if (this.actualDF != _df || 
			(quartertime*i-step <time && time < quartertime*i + step)){
			if (gf < gc){ 
				if (mt <= quartertime + step) 
					estrategiaOfensiva();
				else estrategiaOfensivaEquilibrada();	
			}else if (gf > gc){
				if (mt <= quartertime + step) 
					estrategiaDefensiva();
				else estrategiaDefensivaEquilibrada();
			}else	
				estrategiaEquilibrada();
		}
		
		this.actualDF = _df;
	}
	
	public Behaviour getDefaultBehaviour(int id) {
		if (id == 0)
			return getBehaviour(0);//GoalKeeper
		else if (id == 2){
			Lateral l = (Lateral)getBehaviour(2);
			l.setId(id);
			return (Behaviour)l;//LateralX
		}
		else if (id == 4){
			Lateral l = (Lateral)getBehaviour(6);
			l.setId(id);
			return (Behaviour)l;//LateralDefensivo
		}
		else if (id==3)
			return getBehaviour(3);//Forward
		else if (id==1)
			return getBehaviour(7);//Cierre
		return getBehaviour(1);
	}
	
	public Behaviour[] createBehaviours() {
		return new Behaviour[] {new GoalKeeper(),      //0
								new GoToBall(),        //1
								new LateralX(),        //2
								new Forward(),         //3
								new Defensa(),         //4
								new LateralOfensivo(), //5
								new LateralDefensivo(),//6
								new Cierre(),          //7 
								new ForwardAgresivo(), //8
								new MedioCentro()};    //9
	}
	
	private void estrategiaOfensiva(){
		_players[1].setBehaviour(getBehaviour(4));//Defensa
		Lateral l2 = (Lateral)getBehaviour(5);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);//LateralX 
		_players[3].setBehaviour(getBehaviour(8));//ForwardAgresivo
		_players[4].setBehaviour(getBehaviour(9));//MedioCentro
	}
	
	private void estrategiaOfensivaEquilibrada(){
		_players[1].setBehaviour(getBehaviour(7));//Cierre
		Lateral l2 = (Lateral)getBehaviour(2);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);//LateralX
		_players[3].setBehaviour(getBehaviour(3));//Forward
		Lateral l4 = (Lateral)getBehaviour(2);
		l4.setId(4); 
		_players[4].setBehaviour((Behaviour)l4);//LateralX
	}
	
	private void estrategiaDefensiva(){
		_players[1].setBehaviour(getBehaviour(7));//Cierre
		Lateral l2 = (Lateral)getBehaviour(6);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);//LateralDefensivo
		_players[3].setBehaviour(getBehaviour(3));//Forward
		Lateral l4 = (Lateral)getBehaviour(6);
		l4.setId(4); 
		_players[4].setBehaviour((Behaviour)l4);//LateralDefensivo
	}
	
	private void estrategiaDefensivaEquilibrada(){
		_players[1].setBehaviour(getBehaviour(7));//Cierre
		Lateral l2 = (Lateral)getBehaviour(6);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);//LateralDefensivo
		_players[3].setBehaviour(getBehaviour(3));//Forward
		Lateral l4 = (Lateral)getBehaviour(2);
		l4.setId(4); 
		_players[4].setBehaviour((Behaviour)l4);//LateralX
	}
	
	private void estrategiaEquilibrada(){
		_players[1].setBehaviour(getBehaviour(7));//Cierre
		Lateral l2 = (Lateral)getBehaviour(2);
		l2.setId(2); 
		_players[2].setBehaviour((Behaviour)l2);//LateralX
		_players[3].setBehaviour(getBehaviour(3));//Forward
		Lateral l4 = (Lateral)getBehaviour(6);
		l4.setId(4); 
		_players[4].setBehaviour((Behaviour)l4);//LateralDefensivo
	}
	
}
