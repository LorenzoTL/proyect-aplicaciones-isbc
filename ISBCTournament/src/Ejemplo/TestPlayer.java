package Ejemplo;

import teams.ucmTeam.*;

public class TestPlayer extends UCMPlayer {
	
	protected TeamManager createTeamManager() {
		return new Entrenador();
	}
	
}
