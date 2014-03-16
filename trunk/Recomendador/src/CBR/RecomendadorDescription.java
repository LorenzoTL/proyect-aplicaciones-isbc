package CBR;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class RecomendadorDescription implements CaseComponent {

	public Attribute getIdAttribute() {
		return new Attribute("id",RecomendadorDescription.class);
	}

}
