package Ejemplo;

import t1314grupo03.Cierre;
import t1314grupo03.Defensa;
import t1314grupo03.Forward;
import t1314grupo03.ForwardAgresivo;
import t1314grupo03.GoToBall;
import t1314grupo03.GoalKeeper;
import t1314grupo03.Lateral;
import t1314grupo03.LateralDefensivo;
import t1314grupo03.LateralOfensivo;
import t1314grupo03.LateralX;
import t1314grupo03.MedioCentro;
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
