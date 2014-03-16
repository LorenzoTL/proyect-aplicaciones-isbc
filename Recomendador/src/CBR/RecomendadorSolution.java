package CBR;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class RecomendadorSolution implements CaseComponent {

	public Attribute getIdAttribute() {
		return new Attribute("id",RecomendadorSolution.class);
	}

}
