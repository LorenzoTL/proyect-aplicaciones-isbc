package Ejemplo;

import java.util.Queue;

import EDU.gatech.cc.is.util.Vec2;

import teams.ucmTeam.*;

public class GoToPosition extends Behaviour {

	public void configure() {
		// No hacemos nada
	}
	
	public int takeStep() {
		Queue<Message> pendingMessages = this.getPendingMessages();
		if (!pendingMessages.isEmpty()) {
			for (Message m:pendingMessages) {
				if (m instanceof MensajePosicion){
					Vec2 pos = (((MensajePosicion)m).getPosicion());
					myRobotAPI.setSteerHeading(pos.t);
					myRobotAPI.setSpeed(1.0);
					// Miro cómo de lejos estoy
					Vec2 dist = pos;
					dist.sub(myRobotAPI.getPosition());
					if (dist.r<0.2){
						myRobotAPI.setSpeed(0.0);
						myRobotAPI.setDisplayString("Arrive");
					}
				}
			}
		}
		return 0;
	}
	
	public void onInit(RobotAPI r) {
		r.setDisplayString("ToGoal");
	}
		
	public void end() {
		// No hacemos nada
	}
		
	public void onRelease(RobotAPI r) {
		// No hacemos nada
	}
	
}
