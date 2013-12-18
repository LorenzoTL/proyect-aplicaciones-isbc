package Ejemplo;

import teams.ucmTeam.*;
import teams.ucmTeam.Message.Type;

public class Entrenador extends TeamManager{
	
	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
		RobotAPI robot = _players[2].getRobotAPI();
		if (robot.getPosition().x * robot.getFieldSide()>=0)
			_players[2].setBehaviour(_behaviours[0]);
		else
			_players[2].setBehaviour(_behaviours[1]);
		
		RobotAPI robot0 = getPlayer(0).getRobotAPI();
		MensajePosicion message = new MensajePosicion();
		message.setReceiver(0);
		message.setType(Type.unicast);
		message.setPosicion(robot0.toFieldCoordinates(robot0.getOpponentsGoal()));
		sendMessage(message);
	}
	
	public Behaviour getDefaultBehaviour(int id) {
		/*if (id==0)
			return getBehaviour(2);
		else*/
			return getBehaviour(0);
	}
	
	public Behaviour[] createBehaviours() {
		return new Behaviour[] {new GoToBall(), new Blocker(), new GoToPosition()};
	}
}
