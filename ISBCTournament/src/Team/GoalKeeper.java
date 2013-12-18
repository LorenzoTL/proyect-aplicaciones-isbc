package Team;

import EDU.gatech.cc.is.util.Vec2;
import Ejemplo.MensajePosicion;
import teams.ucmTeam.*;

public class GoalKeeper extends Behaviour{

	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		/*Vec2 v = myRobotAPI.getBall();
		Vec2 pos = myRobotAPI.getPosition();
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
		}*/
		if (myRobotAPI.getBall().x < 0.35 && myRobotAPI.getBall().y < 0.4 && 
			 myRobotAPI.getPosition().x < -1.15 && (myRobotAPI.getPosition().y < 0.35 && myRobotAPI.getPosition().y > -0.35)) {
			myRobotAPI.setSpeed(3.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
			myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getBall());
			if (myRobotAPI.canKick()) {
				//myRobotAPI.setSteerHeading(myRobotAPI.getClosestMate().t);
				myRobotAPI.passBall(myRobotAPI.getClosestMate());
			}
		} else if (Math.abs(myRobotAPI.getOurGoal().x) > 0.1 || Math.abs(myRobotAPI.getOurGoal().y) > 0.01) {
			myRobotAPI.setSpeed(3.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);
			myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getOurGoal());
		} else {
			myRobotAPI.setSpeed(0.0);
			myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
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
