package Ejemplo;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.*;

public class MensajePosicion extends Message{

	private Vec2 posicion;
	
	public Vec2 getPosicion() {
		return posicion;
	}
	public void setPosicion(Vec2 posicion) {
		this.posicion = posicion;
	}
	
}
