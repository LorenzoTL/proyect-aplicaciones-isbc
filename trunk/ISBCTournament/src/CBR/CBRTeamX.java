package CBR;

import teams.ucmTeam.*;

public class CBRTeamX extends UCMPlayer {
	protected TeamManager createTeamManager() {
		return new ManagerCBR();
	}
}
