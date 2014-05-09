package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import CBR.RecomendadorCBR;
import Clases_Auxiliares.Preferencias;
import Interfaz.ConfigPanel.ChoiceOption;
import Interfaz.ConfigPanel.IntegerOption;
import Viviendas.DescripcionVivienda;

public class FormularioInicial extends JFrame {

	private static final long serialVersionUID = 5393378737313833016L;
	
	Preferencias preferencias;
	RecomendadorCBR recomendador = RecomendadorCBR.getInstance();
	boolean correcto = true;
	private JTextField textFieldHab;
	private JTextField textFieldSup;
	private JLabel labelHab;
	private JLabel labelSup;
	private JLabel labelArea = null;
	private JComboBox comboBox;
	private JComboBox comboNew = null;
	
	public FormularioInicial(){
		super("Recomendador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		/*preferencias = new Preferencias();
		
		ConfigPanel<Preferencias> cp = creaPanelConfiguracion();
		cp.setTarget(preferencias);
		cp.initialize();
		cp.addConfigListener(new ConfigListener() {
			public void configChanged(boolean isConfigValid) {
				correcto = isConfigValid;	
				//this.
				System.out.println(preferencias.getLocalizacion());
			}
		});
		cp.setBounds(5, 15, 300, 200);
		getContentPane().add(cp);*/
		
		JLabel lblNewLabel = new JLabel("Localizaci\u00F3n");
		lblNewLabel.setBounds(10, 21, 77, 14);
		getContentPane().add(lblNewLabel);
		
		labelHab = new JLabel("Habitaciones");
		labelHab.setBounds(10, 59, 77, 14);
		getContentPane().add(labelHab);
		
		labelSup = new JLabel("Superficie (m2)");
		labelSup.setBounds(10, 90, 90, 14);
		getContentPane().add(labelSup);
		
		String[] comboList = new String[] {	"---","arroyomolinos-madrid","ajalvir-zona-de","alamo-el-zona-de","alcala-de-henares","alcobendas",
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
		comboBox = new JComboBox(comboList);
		comboBox.setBounds(105, 18, 193, 20);
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println(comboBox.getSelectedItem().toString());
				areas();
			}
		});
		getContentPane().add(comboBox);
		
		textFieldHab = new JTextField();
		textFieldHab.setBounds(105, 56, 121, 20);
		getContentPane().add(textFieldHab);
		textFieldHab.setColumns(10);
		
		textFieldSup = new JTextField();
		textFieldSup.setBounds(105, 87, 121, 20);
		getContentPane().add(textFieldSup);
		textFieldSup.setColumns(10);
		
		JButton boton = new JButton("BUSCAR");
		boton.setBounds(116, 228, 89, 23);
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
		getContentPane().add(boton);
	}
	
	public void areas() {
		if (labelArea != null) {
			getContentPane().remove(labelArea);
			getContentPane().remove(comboNew);
		}
		if (comboBox.getSelectedItem().toString().equals("algete")) {
			pintaArea();
			String[] newList = new String[] {"---","algete-pueblo"};
			comboNew = new JComboBox(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("alpedrete-zona-de")) {
			pintaNoArea();
			repaint();
		}
	}
	
	public void pintaArea() {
		textFieldHab.setBounds(textFieldHab.getX(), 106, textFieldHab.getWidth(), textFieldHab.getHeight());
		textFieldSup.setBounds(textFieldSup.getX(), 137, textFieldSup.getWidth(), textFieldSup.getHeight());
		labelHab.setBounds(labelHab.getX(), 108, labelHab.getWidth(), labelHab.getHeight());
		labelSup.setBounds(labelSup.getX(), 139, labelSup.getWidth(), labelSup.getHeight());
		
		labelArea = new JLabel("Area");
		labelArea.setBounds(10, 65, 77, 14);
		getContentPane().add(labelArea);
	}
	
	public void pintaNoArea() {
		textFieldHab.setBounds(textFieldHab.getX(), 56, textFieldHab.getWidth(), textFieldHab.getHeight());
		textFieldSup.setBounds(textFieldSup.getX(), 87, textFieldSup.getWidth(), textFieldSup.getHeight());
		labelHab.setBounds(labelHab.getX(), 59, labelHab.getWidth(), labelHab.getHeight());
		labelSup.setBounds(labelSup.getX(), 90, labelSup.getWidth(), labelSup.getHeight());
	}
	
	public ConfigPanel<Preferencias> creaPanelConfiguracion(){
		ConfigPanel<Preferencias> config = new ConfigPanel<Preferencias>();
		String[] zonas = new String[] {	"---","arroyomolinos-madrid","ajalvir-zona-de","alamo-el-zona-de","alcala-de-henares","alcobendas",
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
		cp.setSize(329,300);
		cp.setLocation(500, 200);
		cp.setVisible(true);	
	}
}
