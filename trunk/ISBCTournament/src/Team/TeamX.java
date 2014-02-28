package Team;

import teams.ucmTeam.*;

public class TeamX extends UCMPlayer{
	protected TeamManager createTeamManager() {
		return new Manager();
	}
}
