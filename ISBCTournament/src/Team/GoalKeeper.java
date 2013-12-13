package Team;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class GoalKeeper extends Behaviour{

	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		
		Vec2 pos = myRobotAPI.getPosition();
		Vec2 v = myRobotAPI.getBall();
		v.add(pos);
		double[] area = null;
		if (myRobotAPI.getFieldSide() == -1)
			area = myRobotAPI.getWestGoalArea();
		else
			area = myRobotAPI.getEastGoalArea();
		
		if(v.x > area[0] && v.x <area[1] && v.y < area[2] && v.y > area[3]){
			myRobotAPI.setSpeed(3);
			myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			if (myRobotAPI.canKick())
				myRobotAPI.kick();
		}else{
			myRobotAPI.setSpeed(0);
		}
			
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("goalKeeperBehaviour");
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
}
