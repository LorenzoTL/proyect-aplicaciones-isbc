package Ejemplo;

import Team.Cierre;
import Team.Defensa;
import Team.Forward;
import Team.ForwardAgresivo;
import Team.GoToBall;
import Team.GoalKeeper;
import Team.Lateral;
import Team.LateralDefensivo;
import Team.LateralOfensivo;
import Team.LateralX;
import Team.MedioCentro;
import teams.ucmTeam.*;
import teams.ucmTeam.Message.Type;

public class Entrenador extends TeamManager{
	
	public int onConfigure() {
		return RobotAPI.ROBOT_OK;
	}
	
	public void onTakeStep() {
//		RobotAPI robot = _players[2].getRobotAPI();
//		if (robot.getPosition().x * robot.getFieldSide()>=0)
//			_players[2].setBehaviour(_behaviours[0]);
//		else
//			_players[2].setBehaviour(_behaviours[1]);
//		
//		RobotAPI robot0 = getPlayer(0).getRobotAPI();
//		MensajePosicion message = new MensajePosicion();
//		message.setReceiver(0);
//		message.setType(Type.unicast);
//		message.setPosicion(robot0.toFieldCoordinates(robot0.getOpponentsGoal()));
//		sendMessage(message);
		
	}
	
	public Behaviour getDefaultBehaviour(int id) {
		if (id == 0)
			return getBehaviour(0);//GoalKeeper
		else if (id == 2){
			Lateral l = (Lateral)getBehaviour(5);
			l.setId(id);
			return (Behaviour)l;//LateralX
		}
		else if (id == 4){
			return getBehaviour(9);//LateralDefensivo
		}
		else if (id==3)
			return getBehaviour(8);//Forward
		else if (id==1)
			return getBehaviour(4);//Cierre
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
				new Cierre(),
				new ForwardAgresivo(),
				new MedioCentro()};
	}
}
