package Team;

import teams.ucmTeam.*;

public class GoalKeeper extends Behaviour{

	KeeperState state;
	int lado;
	
	public KeeperState getState(){
		return state;
	}
	
	public void setState(KeeperState s){
		this.state = s;
	}
	
	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		if (myRobotAPI.blocked())
            myRobotAPI.avoidCollisions();
		
			if (myRobotAPI.getBall().r < 0.5 && myRobotAPI.getOurGoal().r < 0.45){
				this.setState(new Salida());
			} else if (myRobotAPI.getOurGoal().r >= 0.07){
				this.setState(new IrPorteria());
			} else {
				this.setState(new Parado());
			}
		this.state.action();
		return myRobotAPI.ROBOT_OK;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("goalKeeperBehaviour");
		lado = myRobotAPI.getFieldSide();
	}
	
	public void end() {
		// No hacemos nada
	}
	
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
	//PATRÓN STATE ------------------------------------------------------------------------------------------------------------------
		//Interfaz para implementar
		private interface KeeperState{
			void action();
		}
		
		//Sub clases que implementan la interfaz
		private class Salida implements KeeperState{
			public void action(){
				myRobotAPI.setSpeed(1.5);
				myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
				myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getBall());
				if (myRobotAPI.canKick()) {
					//myRobotAPI.setSteerHeading(myRobotAPI.getClosestMate().t);
					//myRobotAPI.passBall(myRobotAPI.getClosestMate());
					myRobotAPI.kick();
				}
			}
		}
		
		private class IrPorteria implements KeeperState{
			public void action(){
				myRobotAPI.setSpeed(3.0);
				myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);
				myRobotAPI.surroundPoint(myRobotAPI.getPosition(), myRobotAPI.getOurGoal());
			}
		}
		
		private class Parado implements KeeperState{
			public void action(){
				myRobotAPI.setSpeed(0.0);
				myRobotAPI.setSteerHeading(myRobotAPI.getBall().t);
			}
		}
		
		
}
