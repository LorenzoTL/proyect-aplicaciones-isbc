package Interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Viviendas.DescripcionVivienda;

import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;

import CBR.RecomendadorCBR;
import Clases_Auxiliares.Preferencias;
import Interfaz.ConfigPanel.ChoiceOption;
import Interfaz.ConfigPanel.ConfigListener;
import Interfaz.ConfigPanel.IntegerOption;

public class FormularioInicial extends JFrame {

	private static final long serialVersionUID = 5393378737313833016L;
	
	Preferencias preferencias;
	JPanel panelCentral;
	RecomendadorCBR recomendador = RecomendadorCBR.getInstance();
	boolean correcto = true;
	
	public FormularioInicial(){
		super("Recomendador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		panelCentral = new JPanel(new BorderLayout());
		add(panelCentral, BorderLayout.CENTER);
		preferencias = new Preferencias();
		JPanel form = new JPanel(new BorderLayout());
		ConfigPanel<Preferencias> cp = creaPanelConfiguracion();
		cp.setTarget(preferencias);
		cp.initialize();
		form.add(cp,BorderLayout.CENTER);
		cp.addConfigListener(new ConfigListener() {
			public void configChanged(boolean isConfigValid) {
				correcto = isConfigValid;				
			}
		});
		JButton boton = new JButton("BUSCAR");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (correcto){
					try {
						recomendador.setPreferences(false);
						recomendador.configure();
						recomendador.preCycle();
						CBRQuery query = new CBRQuery();
						DescripcionVivienda description = new DescripcionVivienda();
						description.setDescripcion(preferencias);
						query.setDescription(description);
						recomendador.cycle(query);
						recomendador.postCycle();
					} catch (ExecutionException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		form.add(boton,BorderLayout.SOUTH);
		add(form,BorderLayout.WEST);
	}
	
	public ConfigPanel<Preferencias> creaPanelConfiguracion(){
		ConfigPanel<Preferencias> config = new ConfigPanel<Preferencias>();
		String[] zonas = new String[] {	"arroyomolinos-madrid","ajalvir-zona-de","alamo-el-zona-de","alcala-de-henares","alcobendas",
										"alcorcon","algete","alpedrete-zona-de","aranjuez","arganda-del-rey","arganzuela","barajas","boadilla-del-monte","brunete-zona-de",
										"carabanchel","centro-de-madrid-capital","chamartin","chamberi","ciempozuelos","ciudad-lineal",
										"collado-villalba","colmenar-viejo","coslada","daganzo-de-arriba-zona-de","el-boalo-cerceda-mataelpino","el-molar-zona-de",
										"fuencarral","fuenlabrada","fuente-el-saz-de-jarama-zona-de","galapagar","getafe","grinon-zona-de","guadalix-de-la-sierra-zona-de",
										"hortaleza","humanes-zona-de","las-rozas-de-madrid","latina","leganes","madrid-provincia","majadahonda","manzanares-el-real-zona-de","mejorada-del-campo-zona-de",
										"moncloa","moralzarzal","moratalaz","mostoles","navalcarnero","paracuellos-de-jarama","parla","pedrezuela-zona-de",
										"perales-de-tajuna-zona-de","pinto","pozuelo-de-alarcon","puente-de-vallecas","resto-A5","resto-a5","resto-poblaciones-a4","resto-poblaciones-A4","retiro",
										"rivas-vaciamadrid","salamanca","san-agustin-de-guadalix-zona-de","san-blas","san-fernando-de-henares","san-lorenzo-de-el-escorial","san-martin-de-la-vega-zona-de",
										"san-sebastian-de-los-reyes","tetuan","torrejon-de-ardoz","torrelodones","tres-cantos","usera","valdemorillo","valdemoro","velilla-de-san-antonio-zona-de",
										"venturada","vicalvaro","villa-de-vallecas","villalbilla-zona-de","villanueva-de-la-canada-zona-de","villanueva-del-pardillo","villaverde","villaviciosa-de-odon"};
		config.addOption(new ChoiceOption<Preferencias>(
				"Localización",
				"Localización",
				"localizacion",
				zonas
				))
				.addOption(new IntegerOption<Preferencias>(
				"Habitaciones",
				"Número de habitaciones",
				"habitaciones",
				0,100
				))
				.addOption(new IntegerOption<Preferencias>(
				"Superficie (m2)",
				"Superficie",
				"superficie",
				0,10000
				))
				.endOptions();
		return config;
	}
	
	public static void main(String[] args) {	
		FormularioInicial cp = new FormularioInicial();
		cp.setSize(1200, 600);
		cp.setVisible(true);	
	}
}
