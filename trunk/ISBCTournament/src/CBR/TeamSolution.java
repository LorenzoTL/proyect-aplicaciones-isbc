package CBR;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class TeamSolution implements CaseComponent{
	
	int id;
	int goalKeeper;
	int defensa;
	int lateral2;
	int lateral4;
	int forward;
	
	public Attribute getIdAttribute() {
		return new Attribute("id",TeamSolution.class);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getGoalKeeper() {
		return goalKeeper;
	}

	public void setGoalKeeper(int goalKeeper) {
		this.goalKeeper = goalKeeper;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getLateral2() {
		return lateral2;
	}

	public void setLateral2(int lateral2) {
		this.lateral2 = lateral2;
	}

	public int getLateral4() {
		return lateral4;
	}

	public void setLateral4(int lateral4) {
		this.lateral4 = lateral4;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}
	
	
}
