package CBR;

import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import CBR.TeamCBR;
import CBR.TeamDescription;
import CBR.TeamSolution;
import t1314grupo03.*;
import teams.ucmTeam.*;

public class ManagerCBR extends TeamManager{

	int[] b;
	int actualDF;
	
	public Behaviour[] createBehaviours() {
		return new Behaviour[] {new GoalKeeper(),
								new GoToBall(),
								new LateralX(),
								new Forward(), 
								new Defensa(),
								new LateralOfensivo(),
								new LateralDefensivo(),
								new Cierre(),
								new ForwardAgresivo(),
								new MedioCentro()};
	}

	public Behaviour getDefaultBehaviour(int id) {
		if(b == null) b = new int[5];
		if (id == 0){
			b[id] = 0;
			return getBehaviour(0);
		}
		else if (id == 2){
			b[id] = 2;
			Lateral l = (Lateral)getBehaviour(2);
			l.setId(id);
			return (Behaviour)l;
		}
		else if (id == 4){
			b[id] = 2;
			Lateral l = (Lateral)getBehaviour(6);
			l.setId(id);
			return (Behaviour)l;
		}
		else if (id==3){
			b[id] = 3;
			return getBehaviour(3);
		}
		else if (id==1){
			b[id] = 4;
			return getBehaviour(7);
		}
		return getBehaviour(1);
	}

	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}

	protected void onTakeStep() {
		RobotAPI robot = _players[0].getRobotAPI();
		int df = Math.abs(robot.getMyScore() + robot.getOpponentScore());
		int k = 6;
		long actualtime = robot.getTimeStamp();
		long totaltime = robot.getMatchTotalTime();
		boolean fin = robot.getMatchRemainingTime() < 2000;
		long interval = totaltime/k;
		long step = 20;
		int i = (int)actualtime / (int)interval + 1;
		
		if ((df != this.actualDF) ||  
			(!fin && ((interval*i-step) < actualtime && actualtime < (interval*i + step)))){
			long time = actualtime +step;
			if (time >totaltime) time = totaltime;
			//CICLO CBR
			TeamCBR cbr = TeamCBR.getInstance();
			try {
				cbr.configure();
				cbr.preCycle(); 
				TeamDescription td = mappingDescription(robot.getMyScore()-robot.getOpponentScore(),time); 
				CBRQuery query = new CBRQuery();
				query.setDescription(td);
				cbr.cycle(query);
				cbr.postCycle();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			TeamSolution ts = (TeamSolution)cbr.getResult();
			if (ts != null){
				changeBehaviour(ts);
			}
		}
		this.actualDF = df;
	}
	
	private TeamDescription mappingDescription(int df,long time){
		if(b==null)return null;
		TeamDescription td = new TeamDescription();
		td.setDefensa(b[1]);
		td.setForward(b[3]);
		td.setLateral2(b[2]);
		td.setLateral4(b[4]);
		td.setGoalKeeper(b[0]);
		td.setDf(df);
		td.setTime(time);
		return td;
	}
	
	private void changeBehaviour(TeamSolution ts){
		_players[0].setBehaviour(getBehaviour(ts.getGoalKeeper()));
		b[0] = ts.getGoalKeeper();
		
		_players[1].setBehaviour(getBehaviour(ts.getDefensa()));
		b[1] = ts.getDefensa();
		
		Lateral l = (Lateral)getBehaviour(ts.getLateral2());
		l.setId(2);
		_players[2].setBehaviour((Behaviour)l);
		b[2] = ts.getLateral2();
		
		_players[3].setBehaviour(getBehaviour(ts.getForward()));
		b[3] = ts.getForward();
		
		if (ts.getLateral4() == 9){
			_players[4].setBehaviour(getBehaviour(ts.getLateral4()));
		}else{
			Lateral l4 = (Lateral)getBehaviour(ts.getLateral4());
			l4.setId(4);
			_players[4].setBehaviour((Behaviour)l4);
		}
		b[4] = ts.getLateral4();
	}

}
