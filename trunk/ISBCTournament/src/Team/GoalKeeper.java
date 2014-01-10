package Team;

import teams.ucmTeam.*;

public class GoalKeeper extends Behaviour{

	KeeperState state;
	
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
		if (myRobotAPI.getBall().x < 0.35 && myRobotAPI.getBall().y < 0.4 && 
			 myRobotAPI.getPosition().x < -1.15 && (myRobotAPI.getPosition().y < 0.35 && myRobotAPI.getPosition().y > -0.35)) {
			this.setState(new Salida());
		} else if (Math.abs(myRobotAPI.getOurGoal().x) > 0.1 || Math.abs(myRobotAPI.getOurGoal().y) > 0.01) {
			this.setState(new IrPorteria());
		} else {
			this.setState(new Parado());
		}
		this.state.action();
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
	
	//PATRÓN STATE ------------------------------------------------------------------------------------------------------------------
		//Interfaz para implementar
		private interface KeeperState{
			void action();
		}
		
		//Sub clases que implementan la interfaz
		private class Salida implements KeeperState{
			public void action(){
				myRobotAPI.setSpeed(3.0);
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
