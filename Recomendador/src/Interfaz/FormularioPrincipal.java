package Interfaz;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import jcolibri.cbrcore.CBRQuery;

import CBR.RecomendadorCBR;
import Viviendas.DescripcionVivienda;
import Viviendas.DescripcionVivienda.EstadoVivienda;
import Viviendas.DescripcionVivienda.TipoVivienda;
import Viviendas.ExtrasBasicos;
import Viviendas.ExtrasFinca;
import Viviendas.ExtrasOtros;

public class FormularioPrincipal extends JFrame {

	private static final long serialVersionUID = 5393378737313833016L;
	
	private RecomendadorCBR recomendador;
	
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelList;
	
	private JComboBox<String> comboLocalizacion;
	private JComboBox<String> comboArea;
	private JComboBox<String> comboMinPrecio;
	private JComboBox<String> comboMaxPrecio;
	private JComboBox<String> comboMinTam;
	private JComboBox<String> comboMaxTam;
	private JComboBox<String> comboTipoPiso;
	private JCheckBox checkDorm1;
	private JCheckBox checkDorm2;
	private JCheckBox checkDorm3;
	private JCheckBox checkDorm4;
	private JCheckBox checkBano1;
	private JCheckBox checkBano2;
	private JCheckBox checkBano3;
	private JComboBox<String> comboEstado;
	private JCheckBox char1;
	private JCheckBox char2;
	private JCheckBox char3;
	private JCheckBox char4;
	private JCheckBox char5;
	private JCheckBox char6;
	private JCheckBox char7;
	private JCheckBox char8;
	private JCheckBox char9;
	private JCheckBox char10;
	private JCheckBox char11;
	private JCheckBox char12;
	private JCheckBox char13;
	private JCheckBox char14;
	private JCheckBox char15;
	private JCheckBox char16;
	private JCheckBox char17;
	private JCheckBox char18;
	private JCheckBox has1;
	private JCheckBox has2;
	private JCheckBox has3;
	private JCheckBox has4;
	private JCheckBox has5;
	private JCheckBox has6;
	private JCheckBox has7;
	private JCheckBox has8;
	private JCheckBox has9;
	private JCheckBox has10;
	private JCheckBox has11;
	private JCheckBox has12;
	private JCheckBox has13;
	private JCheckBox has14;
	private JCheckBox has15;
	private JCheckBox has16;
	
	private JButton buttonBuscar;
	
	private JButton[] buttonLike;
	private JTextPane[] textPane;
	
	//region Contructora del Formulario Principal
	public FormularioPrincipal(String localizacion,String area,int habitaciones,int superficie,RecomendadorCBR r) {
		super("Recomendador de Pisos");
		this.recomendador = r;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 10, 998, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DescripcionVivienda[] resultados = this.recomendador.getResults(true); 
		
		comboArea = new JComboBox<String>();
		comboArea.setEnabled(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 962, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		String titulo = resultados.length + " anuncios";
		
		if (!localizacion.equals("---")) {
			titulo = titulo + " en";
			if (!area.equals("---")) {
				titulo = titulo + " " + area + ",";
			}
			titulo = titulo + " " + localizacion;
		}
		
		JLabel lblNewLabel = new JLabel(titulo);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(100, 11, 879, 31);
		panel_1.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(10, 75, 236, 626);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		// Formulario de filtrado
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.gridwidth = 4;
		panel.add(new JLabel("Localizacion"),c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
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
		comboLocalizacion = new JComboBox<String>(comboList);
		boolean crearArea = false;
		if (!localizacion.equals("---")) {
			comboLocalizacion.setSelectedItem(localizacion);
			crearArea = true;
		}
		comboLocalizacion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.HORIZONTAL;
				panel.remove(comboArea);
				areas();
				c.gridx = 0;
				c.gridy = 3;
				c.weightx = 1;
				panel.add(comboArea,c);
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(comboLocalizacion,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Area"),c);
		
		if (crearArea) {
			c.fill = GridBagConstraints.HORIZONTAL;
			//panel.remove(comboArea);
			areas();
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 1;
			if (!area.equals("---")) {
				comboArea.setSelectedItem(area);
			}
			panel.add(comboArea,c);
			panel.revalidate();
			panel.repaint();
		} else {
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 1;
			c.gridwidth = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			panel.add(comboArea,c);
		}
		
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Precio"),c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 1;
		c.gridwidth = 2;
		String[] pMin = new String[] {"0","50000","100000","150000","200000","250000","300000","350000","400000","450000","500000",
				"600000","700000","800000","900000","1000000","2000000","3000000","4000000","5000000"};
		comboMinPrecio = new JComboBox<String>(pMin);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboMinPrecio,c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.weightx = 1;
		c.gridwidth = 2;
		String[] pMax = new String[] {"0","50000","100000","150000","200000","250000","300000","350000","400000","450000","500000",
				"600000","700000","800000","900000","1000000","2000000","3000000","4000000","5000000"};
		comboMaxPrecio = new JComboBox<String>(pMax);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboMaxPrecio,c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Tamaño"),c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 1;
		c.gridwidth = 2;
		String[] tMin = new String[] {"0","20","40","60","80","100","150","200","250","300","350",
				"400","450","500","600","700","800","900","1000","1200","1400",
				"1600","1800","2000"};
		comboMinTam = new JComboBox<String>(tMin);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboMinTam,c);
		
		c.gridx = 2;
		c.gridy = 7;
		c.weightx = 1;
		c.gridwidth = 2;
		String[] tMax = new String[] {"0","20","40","60","80","100","150","200","250","300","350",
				"400","450","500","600","700","800","900","1000","1200","1400",
				"1600","1800","2000"};
		comboMaxTam = new JComboBox<String>(tMax);
		comboMaxTam.setSelectedItem(String.valueOf(superficie));
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboMaxTam,c);
		
		c.gridx = 0;
		c.gridy = 8;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("¿Qué buscas?"),c);
		
		c.gridx = 0;
		c.gridy = 9;
		c.weightx = 1;
		c.gridwidth = 4;
		String[] buscaList = new String[] {"---","Atico", "Plantabaja", "Piso", "Loft", "Casaadosada", "CasaChalet", "Duplex", "Estudio", "Fincarustica", "Apartamento"};
		comboTipoPiso = new JComboBox<String>(buscaList);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboTipoPiso,c);
		
		c.gridx = 0;
		c.gridy = 10;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Numero dormitorios"),c);
		
		c.gridx = 0;
		c.gridy = 11;
		c.weightx = 1;
		c.gridwidth = 1;
		checkDorm1 = new JCheckBox("1");
		c.fill = GridBagConstraints.HORIZONTAL;
		if (habitaciones == 1) {
			checkDorm1.setSelected(true);
		}
		panel.add(checkDorm1,c);
		
		c.gridx = 1;
		c.gridy = 11;
		c.weightx = 1;
		c.gridwidth = 1;
		checkDorm2 = new JCheckBox("2");
		c.fill = GridBagConstraints.HORIZONTAL;
		if (habitaciones == 2) {
			checkDorm2.setSelected(true);
		}
		panel.add(checkDorm2,c);
		
		c.gridx = 2;
		c.gridy = 11;
		c.weightx = 1;
		c.gridwidth = 1;
		checkDorm3 = new JCheckBox("3");
		c.fill = GridBagConstraints.HORIZONTAL;
		if (habitaciones == 3) {
			checkDorm3.setSelected(true);
		}
		panel.add(checkDorm3,c);
		
		c.gridx = 3;
		c.gridy = 11;
		c.weightx = 1;
		c.gridwidth = 1;
		checkDorm4 = new JCheckBox("4 o +");
		c.fill = GridBagConstraints.HORIZONTAL;
		if (habitaciones == 4 || habitaciones == 5 || habitaciones == 6) {
			checkDorm4.setSelected(true);
		}
		panel.add(checkDorm4,c);
		
		c.gridx = 0;
		c.gridy = 12;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Numero baños"),c);
		
		c.gridx = 0;
		c.gridy = 13;
		c.weightx = 1;
		c.gridwidth = 1;
		checkBano1 = new JCheckBox("1");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(checkBano1,c);
		
		c.gridx = 1;
		c.gridy = 13;
		c.weightx = 1;
		c.gridwidth = 1;
		checkBano2 = new JCheckBox("2");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(checkBano2,c);
		
		c.gridx = 2;
		c.gridy = 13;
		c.weightx = 1;
		c.gridwidth = 1;
		checkBano3 = new JCheckBox("3 o +");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(checkBano3,c);
		
		c.gridx = 0;
		c.gridy = 14;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Estado"),c);
		
		c.gridx = 0;
		c.gridy = 15;
		c.weightx = 1;
		c.gridwidth = 4;
		String[] estadoList = new String[] {"---","Muybien","Reformado","Areformar","Casinuevo","Bien"};
		comboEstado = new JComboBox<String>(estadoList);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboEstado,c);
		
		c.gridx = 0;
		c.gridy = 16;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Características"),c);
		
		c.gridx = 0;
		c.gridy = 17;
		c.weightx = 1;
		c.gridwidth = 2;
		char1 = new JCheckBox("lavadero");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char1,c);
		
		c.gridx = 2;
		c.gridy = 17;
		c.weightx = 1;
		c.gridwidth = 2;
		char2 = new JCheckBox("internet");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char2,c);
		
		c.gridx = 0;
		c.gridy = 18;
		c.weightx = 1;
		c.gridwidth = 2;
		char3 = new JCheckBox("microondas");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char3,c);
		
		c.gridx = 2;
		c.gridy = 18;
		c.weightx = 1;
		c.gridwidth = 2;
		char4 = new JCheckBox("horno");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char4,c);
		
		c.gridx = 0;
		c.gridy = 19;
		c.weightx = 1;
		c.gridwidth = 2;
		char5 = new JCheckBox("amueblado");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char5,c);
		
		c.gridx = 2;
		c.gridy = 19;
		c.weightx = 1;
		c.gridwidth = 2;
		char6 = new JCheckBox("cocinaOffice");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char6,c);
		
		c.gridx = 0;
		c.gridy = 20;
		c.weightx = 1;
		c.gridwidth = 2;
		char7 = new JCheckBox("parquet");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char7,c);
		
		c.gridx = 2;
		c.gridy = 20;
		c.weightx = 1;
		c.gridwidth = 2;
		char8 = new JCheckBox("domotica");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char8,c);
		
		c.gridx = 0;
		c.gridy = 21;
		c.weightx = 1;
		c.gridwidth = 2;
		char9 = new JCheckBox("armarios");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char9,c);
		
		c.gridx = 2;
		c.gridy = 21;
		c.weightx = 1;
		c.gridwidth = 2;
		char10 = new JCheckBox("lavadora");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char10,c);
		
		c.gridx = 0;
		c.gridy = 22;
		c.weightx = 1;
		c.gridwidth = 2;
		char11 = new JCheckBox("tv");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char11,c);
		
		c.gridx = 2;
		c.gridy = 22;
		c.weightx = 1;
		c.gridwidth = 2;
		char12 = new JCheckBox("electrodomesticos");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char12,c);
		
		c.gridx = 0;
		c.gridy = 23;
		c.weightx = 1;
		c.gridwidth = 2;
		char13 = new JCheckBox("suiteConBanio");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char13,c);
		
		c.gridx = 2;
		c.gridy = 23;
		c.weightx = 1;
		c.gridwidth = 2;
		char14 = new JCheckBox("puertaBlindada");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char14,c);
		
		c.gridx = 0;
		c.gridy = 24;
		c.weightx = 1;
		c.gridwidth = 2;
		char15 = new JCheckBox("gresCeramica");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char15,c);
		
		c.gridx = 2;
		c.gridy = 24;
		c.weightx = 1;
		c.gridwidth = 2;
		char16 = new JCheckBox("calefaccion");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char16,c);
		
		c.gridx = 0;
		c.gridy = 25;
		c.weightx = 1;
		c.gridwidth = 2;
		char17 = new JCheckBox("aireAcondicionado");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char17,c);
		
		c.gridx = 2;
		c.gridy = 25;
		c.weightx = 1;
		c.gridwidth = 2;
		char18 = new JCheckBox("nevera");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(char18,c);
		
		c.gridx = 0;
		c.gridy = 26;
		c.weightx = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Que tengan"),c);
		
		c.gridx = 0;
		c.gridy = 27;
		c.weightx = 1;
		c.gridwidth = 2;
		has1 = new JCheckBox("patio");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has1,c);
		
		c.gridx = 2;
		c.gridy = 27;
		c.weightx = 1;
		c.gridwidth = 2;
		has2 = new JCheckBox("zonaDeportiva");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has2,c);
		
		c.gridx = 0;
		c.gridy = 28;
		c.weightx = 1;
		c.gridwidth = 2;
		has3 = new JCheckBox("balcon");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has3,c);
		
		c.gridx = 2;
		c.gridy = 28;
		c.weightx = 1;
		c.gridwidth = 2;
		has4 = new JCheckBox("zonaComunitaria");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has4,c);
		
		c.gridx = 0;
		c.gridy = 29;
		c.weightx = 1;
		c.gridwidth = 2;
		has5 = new JCheckBox("terraza");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has5,c);
		
		c.gridx = 2;
		c.gridy = 29;
		c.weightx = 1;
		c.gridwidth = 2;
		has6 = new JCheckBox("piscinaComunitaria");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has6,c);
		
		c.gridx = 0;
		c.gridy = 30;
		c.weightx = 1;
		c.gridwidth = 2;
		has7 = new JCheckBox("jardinPrivado");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has7,c);
		
		c.gridx = 2;
		c.gridy = 30;
		c.weightx = 1;
		c.gridwidth = 2;
		has8 = new JCheckBox("piscina");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has8,c);
		
		c.gridx = 0;
		c.gridy = 31;
		c.weightx = 1;
		c.gridwidth = 2;
		has9 = new JCheckBox("zonaInfantil");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has9,c);
		
		c.gridx = 2;
		c.gridy = 31;
		c.weightx = 1;
		c.gridwidth = 2;
		has10 = new JCheckBox("ascensor");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has10,c);
		
		c.gridx = 0;
		c.gridy = 32;
		c.weightx = 1;
		c.gridwidth = 2;
		has11 = new JCheckBox("trastero");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has11,c);
		
		c.gridx = 2;
		c.gridy = 32;
		c.weightx = 1;
		c.gridwidth = 2;
		has12 = new JCheckBox("parkingComunitario");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has12,c);
		
		c.gridx = 0;
		c.gridy = 33;
		c.weightx = 1;
		c.gridwidth = 2;
		has13 = new JCheckBox("energiaSolar");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has13,c);
		
		c.gridx = 2;
		c.gridy = 33;
		c.weightx = 1;
		c.gridwidth = 2;
		has14 = new JCheckBox("garajePrivado");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has14,c);
		
		c.gridx = 0;
		c.gridy = 34;
		c.weightx = 1;
		c.gridwidth = 2;
		has15 = new JCheckBox("servPorteria");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has15,c);
		
		c.gridx = 2;
		c.gridy = 34;
		c.weightx = 1;
		c.gridwidth = 2;
		has16 = new JCheckBox("videoportero");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(has16,c);
		
		c.gridx = 1;
		c.gridy = 36;
		c.weightx = 1;
		c.gridwidth = 2;
		buttonBuscar = new JButton("Buscar");
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(buttonBuscar,c);
		
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarFormularioPrincipal();
				creaPanelCentral(recomendador.getResults(false));
			}
		});
		
		// Fin formulario
		JScrollPane scrollPane_2 = new JScrollPane(panel);
		scrollPane_2.setBounds(10, 75, 268, 626);
		scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane_2);
		
		creaPanelCentral(resultados);
	}
	//endregion
	
	//region Creación del Panel de resultados
	public void creaPanelCentral(DescripcionVivienda[] resultados) {
		buttonLike = new JButton[resultados.length];
		textPane = new JTextPane[resultados.length];
		
		panelList = new JPanel();
		panelList.setBounds(289, 75, 683, 626);
		panelList.setLayout(new GridBagLayout());
		GridBagConstraints cPanel = new GridBagConstraints();
		int num = resultados.length;
		
		for (int i = 0; i < num;i++) {
			cPanel.gridx = 0;
			cPanel.gridy = i;
			cPanel.weightx = 1;
			//cPanel.gridheight = 2;
			textPane[i] = new JTextPane();
			textPane[i].setText(resultados[i].getTitulo());
			textPane[i].setEditable(false);
			cPanel.fill = GridBagConstraints.HORIZONTAL;
			panelList.add(textPane[i],cPanel);
			
			cPanel.gridx = 1;
			cPanel.gridy = i;
			cPanel.weightx = 0;
			//cPanel.gridheight = 2;
			buttonLike[i] = new JButton("Me Gusta");
			cPanel.fill = GridBagConstraints.HORIZONTAL;
			panelList.add(buttonLike[i],cPanel);
		}
		
		JScrollPane scrollPane = new JScrollPane(panelList);
		scrollPane.setBounds(289, 75, 683, 626);
		contentPane.add(scrollPane);
	}
	//endregion
	
	//region Areas
	public void areas() {
		
		if (comboLocalizacion.getSelectedItem().toString().equals("arroyomolinos-madrid")) {
			String[] newList = new String[] {"---","centro-y-la-rinconada","coto-redondo-monte-de-batres","zona-bulevar-y-europa","zona-las-castaneras","zona-zarzalejos"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("ajalvir-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("alamo-el-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("alcala-de-henares")) {
			
			String[] newList = new String[] {"---","val","ensanche","casco-historico-de-alcala-de-henares","pryconsa-juan-de-austria","estacion-parque-o-donnell","	reyes-catolicos	chorrillo","la-garena","espartales"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("alcobendas")) {
			
			String[] newList = new String[] {"---","norte-de-alcobendas","el-soto-de-la-moraleja","	arroyo-de-la-vega","la-moraleja-distrito","	centro-de-alcobendas","	ensanche","	encinar-de-los-reyes"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("alcorcon")) {
			
			String[] newList = new String[] {"---","prado-santo-domingo-ensanche","parque-lisboa-la-paz","parque-oeste-fuente-cisneros","torresbellas","casco-antiguo","campodon-ventorro-del-cano"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("algete")) {
			
			String[] newList = new String[] {"---","algete-pueblo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("alpedrete-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("aranjuez")) {
			
			String[] newList = new String[] {"---","nuevo-aranjuez","foso-moreras","la-montana-el-cortijo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("arganda-del-rey")) {
			
			String[] newList = new String[] {"---","los-villares","centro-de-arganda-del-rey"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("arganzuela")) {
			
			String[] newList = new String[] {"---","delicias","acacias","legazpi","	chopera","palos-de-moguer","imperial"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("barajas")) {
			
			String[] newList = new String[] {"---","alameda-de-osuna","	timon","barajas-pueblo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("boadilla-del-monte")) {
			
			String[] newList = new String[] {"---","las-lomas","sector-s","parque-boadilla","sector-b","urb-este-monteprincipe","vinas-viejas"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("brunete-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("carabanchel")) {
			
			String[] newList = new String[] {"---","comillas","vista-alegre	opanel","san-isidro	puerta-bonita","pau-de-carabanchel"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("centro-de-madrid-capital")) {
			
			String[] newList = new String[] {"---","embajadores","universidad","cortes","palacio","justicia","sol"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("chamartin")) {
			
			String[] newList = new String[] {"---","el-viso	nueva-espana","	castilla","	hispanoamerica","ciudad-jardin","prosperidad"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("chamberi")) {
			
			String[] newList = new String[] {"---","trafalgar","gaztambide","almagro","	rios-rosas","arapiles","vallehermoso"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("ciempozuelos")) {
			
			String[] newList = new String[] {"---","cuevas-ilustracion"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("ciudad-lineal")) {
			
			String[] newList = new String[] {"---","pueblo-nuevo","	ventas","quintana","costillares","san-pascual","concepcion","colina"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("collado-villalba")) {
			
			String[] newList = new String[] {"---","los-valles","villalba-estacion"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("colmenar-viejo")) {
			
			String[] newList = new String[] {"---","las-vegas-el-pozanco","el-olivar-la-magdalena","centro-de-colmenar-viejo","	san-andres","el-cerrillo","	el-mirador"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("coslada")) {
			
			String[] newList = new String[] {"---","barrio-de-la-estacion-de-coslada","	valleaguado-la-canada","coslada-pueblo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("daganzo-de-arriba-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("el-boalo-cerceda-mataelpino")) {
			
			String[] newList = new String[] {"---","el-boalo","	cerceda"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("el-molar-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("fuencarral")) {
			
			String[] newList = new String[] {"---","montecarmelo","	penagrande","tres-olivos-valverde"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("fuenlabrada")) {
			
			String[] newList = new String[] {"---","la-serna","	el-naranjo","la-avanzada-la-cueva","centro-de-fuenlabrada","sudeste-industrial-de-fuenlabrada","fuenlabrada-ii-el-molino"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("fuente-el-saz-de-jarama-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("galapagar")) {
			
			String[] newList = new String[] {"---","el-guijo-colonia-espana"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("getafe")) {
			
			String[] newList = new String[] {"---","centro-de-getafe","	norte-de-getafe	juan-de-la-cierva","zona-universidad-en-getafe","la-alhondiga","perales-del-rio	el-roson-kelvinator"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("grinon-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("guadalix-de-la-sierra-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("hortaleza")) {
			
			String[] newList = new String[] {"---","sanchinarro","virgen-del-cortijo-manoteras","pinar-del-rey","conde-orgaz","valdebebas-valdefuentes","el-encinar-de-los-reyes","canillas","apostol-santiago"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("humanes-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("las-rozas-de-madrid")) {
			
			String[] newList = new String[] {"---","el-pinar-punta-galea","europolis","dehesa-navalcarbon-montecillo","casco-antiguo","marazuela-el-torreon","	club-de-golf","	el-cantizal","las-matas-penascales","parque-empresarial","zona-auditorio","	dehesa-navalcarbon-montecillo","molino-de-la-hoz","	el-burgo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("latina")) {
			
			String[] newList = new String[] {"---","lucero","puerta-del-angel","aguilas	aluche"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("leganes")) {
			
			String[] newList = new String[] {"---","zarzaquemada","centro-de-leganes","san-nicasio-campo-de-tiro-solagua","la-fortuna","norte-de-leganes","valdepelayo-montepinos-arroyo-culebro","el-carrascal","las-dehesillas-vereda-de-los-estudiantes"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("madrid-provincia")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("majadahonda")) {
			
			String[] newList = new String[] {"---","casco-antiguo-de-majadahonda","zona-monte-el-pilar","zona-carretera-del-plantio","golf-el-carralero","zona-norte-de-majadahonda"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("manzanares-el-real-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("mejorada-del-campo-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("moncloa")) {
			
			String[] newList = new String[] {"---","la-florida-el-plantio","arguelles","valdemarin","aravaca","casa-de-campo","ciudad-universitaria"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("moralzarzal")) {
			
			String[] newList = new String[] {"---","zona-centro-de-moralzarzal","zona-los-lagos","zona-colegios"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}
		else if (comboLocalizacion.getSelectedItem().toString().equals("moratalaz")) {
			
			String[] newList = new String[] {"---","media-legua","vinateros","pavones","marroquina"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}
		else if (comboLocalizacion.getSelectedItem().toString().equals("mostoles")) {
			
			String[] newList = new String[] {"---","centro-de-mostoles","mariblanca-villafontana","sur-pau-4","coimbra-guadarrama","zona-norte-universidad-en-mostoles","el-soto-coveta","suroeste-zona-hospital-en-mostoles"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("navalcarnero")) {
			
			String[] newList = new String[] {"---","el-pijorro","zona-casco-antiguo-de-navalcarnero","urb-calipo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("paracuellos-de-jarama")) {
			
			String[] newList = new String[] {"---","miramadrid"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("parla")) {
			
			String[] newList = new String[] {"---","villa-juventus","las-americas-parla-este","fuentebella-el-nido","centro-de-parla"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("pedrezuela-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("perales-de-tajuna-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("pinto")) {
			
			String[] newList = new String[] {"---","san-jose-buenos-aires","teneria-los-olivos","parque-europa-los-pitufos","zona-centro-ayuntamiento-de-pinto"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("pozuelo-de-alarcon")) {
			
			String[] newList = new String[] {"---","zona-pueblo","zona-norte-de-pozuelo-de-alarcon","zona-avenida-europa","zona-prado-de-somosaguas-la-finca","zona-estacion-de-pozuelo-de-alarcon","somosaguas","urbanizaciones"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("puente-de-vallecas")) {
			
			String[] newList = new String[] {"---","palomeras-sureste","numancia","san-diego","portazgo","palomeras-bajas	entrevias"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("resto-A5")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("resto-a5")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("resto-poblaciones-a4")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("resto-poblaciones-A4")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("retiro")) {
			
			String[] newList = new String[] {"---","ibiza","estrella","jeronimos","nino-jesus","pacifico","adelfas"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("rivas-vaciamadrid")) {
			
			String[] newList = new String[] {"---","centro-de-rivas-vaciamadrid","rivas-futura","covibar-pablo-iglesias","casco-historico-de-rivas-vaciamadrid","rivas-urbanizaciones"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("salamanca")) {
			
			String[] newList = new String[] {"---","recoletos","goya","guindalera","lista","castellana"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-agustin-de-guadalix-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-blas")) {
			
			String[] newList = new String[] {"---","arcos-de-jalon","simancas","canillejas","rosas-musas","salvador"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-fernando-de-henares")) {
			
			String[] newList = new String[] {"---","parque-roma-coronas","centro-de-san-fernando-de-henares","montserrat-parque-empresarial"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-lorenzo-de-el-escorial")) {
			
			String[] newList = new String[] {"---","centro-casco-historico-de-san-lorenzo-de-el-escorial"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-martin-de-la-vega-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-sebastian-de-los-reyes")) {
			
			String[] newList = new String[] {"---","ciudalcampo","centro-urbano-de-san-sebastian-de-los-reyes","rosa-luxemburgo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("tetuan")) {
			
			String[] newList = new String[] {"---","bellas-vistas","berruguete","cuatro-caminos-azca","valdeacederas","la-ventilla","castillejos"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("torrejon-de-ardoz")) {
			
			String[] newList = new String[] {"---","parque-cataluna-canada-soto","centro-de-torrejon-de-ardoz","fresnos","veredillas-juncal-zarzuela"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("torrelodones")) {
			
			String[] newList = new String[] {"---","casco-antiguo-de-torrelodones","zona-hospital-en-torrelodones","las-marias","los-robles"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("tres-cantos")) {
			
			String[] newList = new String[] {"---","primera-fase","zona-estacion-centro-de-tres-cantos","segunda-fase"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("usera")) {
			
			String[] newList = new String[] {"---","pradolongo","almendrales","orcasitas","moscardo","zofio"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("valdemorillo")) {
			
			String[] newList = new String[] {"---","pueblo-urb-norte","cerro-de-alarcon-puente-la-sierra-mirador-del-romero"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("valdemoro")) {
			
			String[] newList = new String[] {"---","centro-de-valdemoro","reston-i-reston-ii","zona-estacion-en-valdemoro","altos-del-olivar-el-caracol"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("velilla-de-san-antonio-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("venturada")) {
			
			String[] newList = new String[] {"---","los-cotos-de-monterrey","venturada-pueblo"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("vicalvaro")) {
			
			String[] newList = new String[] {"---","san-juan","valdebernardo-valderribas","casco-historico-de-vicalvaro"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villa-de-vallecas")) {
			
			String[] newList = new String[] {"---","vallecas-pueblo","ensanche-de-vallecas-valdecarros"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villalbilla-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villanueva-de-la-canada-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villanueva-del-pardillo")) {
			
			String[] newList = new String[] {"---","centro-de-villanueva-del-pardillo","ibiza-san-pedro"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villaverde")) {
			
			String[] newList = new String[] {"---","los-rosales","san-andres","butarque"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villaviciosa-de-odon")) {
			
			String[] newList = new String[] {"---","casco-urbano-de-villaviciosa-de-odon","el-castillo-campodon","el-bosque"};
			comboArea = new JComboBox<String>(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}
		
			comboArea.revalidate();
			comboArea.repaint();
	}
	
	public void pintaNoArea() {
		comboArea.setEnabled(false);
	}
	//endregion
	
	//region Obtencion de datos para el CBR
	public DescripcionVivienda getMapeoDescripcionVivienda(){
		String value = "";
		int v = 0;
		DescripcionVivienda dv = new DescripcionVivienda();
		value = getLocalizacion();
		dv.setLocalizacion(value);
		v = getPrecioMedio();
		dv.setPrecioMedio(v);
		v = getTamMedio();
		dv.setSuperficie(v);
		TipoVivienda tipo = getTipoVivienda();
		if (tipo != null) dv.setTipo(tipo);
		v = getNumHabitaciones();
		dv.setHabitaciones(v);
		v = getNumBanios();
		dv.setBanios(v);
		EstadoVivienda estado = getEstadoVivienda();
		if (estado != null) dv.setEstado(estado);
		ExtrasBasicos extrasBasicos = getExtrasBasicos();
		if (extrasBasicos != null) dv.setExtrasBasicos(extrasBasicos);
		ExtrasOtros extrasOtros = getExtrasOtros();
		if (extrasOtros != null) dv.setExtrasOtros(extrasOtros);
		ExtrasFinca extrasFinca = getExtrasFinca();
		if (extrasFinca != null) dv.setExtrasFinca(extrasFinca);
		return dv;
	}
	
	private void buscarFormularioPrincipal(){
		DescripcionVivienda dv = getMapeoDescripcionVivienda(); 
		try{
			recomendador.setPreferences(true);
			recomendador.configure();
			recomendador.preCycle();
			CBRQuery query = new CBRQuery();
			query.setDescription(dv);
			recomendador.cycle(query);
			recomendador.postCycle();
		}catch(Exception e){
			
		}
	}
	
	private String getLocalizacion(){
		String value = comboLocalizacion.getSelectedItem().toString();
		if (value != null && !value.equals("---")){
			if (comboArea != null && comboArea.getSelectedItem() != null && !comboArea.getSelectedItem().toString().equals("---")){
				value = value + "#" + comboArea.getSelectedItem().toString();
				return value;
			}
			return null;
		}
		return null;
	}
	
	private TipoVivienda getTipoVivienda() {
		String value = comboTipoPiso.getSelectedItem().toString();
		switch(value){
			case "Atico": return TipoVivienda.Atico;
			case "Plantabaja": return TipoVivienda.Plantabaja; 
			case "Piso": return TipoVivienda.Piso;
			case "Loft": return TipoVivienda.Loft;
			case "Casaadosada": return TipoVivienda.Casaadosada;
			case "CasaChalet": return TipoVivienda.CasaChalet;
			case "Duplex": return TipoVivienda.Duplex;
			case "Estudio": return TipoVivienda.Estudio;
			case "Fincarustica": return TipoVivienda.Fincarustica;
			case "Apartamento": return TipoVivienda.Apartamento;
			default: return null;
		}
	}
	
	private int getPrecioMedio(){
		int v = 0;
		try{
			String value = comboMinPrecio.getSelectedItem().toString();
			v = Integer.parseInt(value);
			value = comboMaxPrecio.getSelectedItem().toString();
			v = (v + Integer.parseInt(value))/2;
		}catch(Exception ex){
			v = 0;
		}
		return v;
	}
	
	private int getTamMedio(){
		int v = 0;
		try{
			String value = comboMinTam.getSelectedItem().toString();
			v = Integer.parseInt(value);
			value = comboMaxTam.getSelectedItem().toString();
			v = (v + Integer.parseInt(value))/2;
		}catch(NumberFormatException ex){
			v = 0;
		}
		return v;
	}
	
	private int getNumHabitaciones() {
		if (checkDorm4.isSelected()) return 4;
		if (checkDorm3.isSelected()) return 3;
		if (checkDorm2.isSelected()) return 2;
		if (checkDorm1.isSelected()) return 1;
		return 0;
	}
	
	private int getNumBanios() {
		if (checkBano3.isSelected()) return 3;
		if (checkBano2.isSelected()) return 2;
		if (checkBano1.isSelected()) return 1;
		return 0;
	}

	private EstadoVivienda getEstadoVivienda(){
		String value = comboEstado.getSelectedItem().toString();
		switch(value){
			case "Muybien": return EstadoVivienda.Muybien; 
			case "Reformado": return EstadoVivienda.Reformado; 
			case "Areformar": return EstadoVivienda.Areformar;
			case "Casinuevo": return EstadoVivienda.Casinuevo;
			case "Bien": return EstadoVivienda.Bien;
			default: return null;
		}
	}
	
	private ExtrasBasicos getExtrasBasicos(){
		boolean b = char1.isSelected() || char2.isSelected() || char3.isSelected() || char4.isSelected() || char5.isSelected()
				|| char6.isSelected() || char7.isSelected() || char8.isSelected() || char9.isSelected() || char10.isSelected()
				|| char11.isSelected() || char12.isSelected() || char13.isSelected() || char14.isSelected() || char15.isSelected()
				|| char16.isSelected() || char17.isSelected() || char18.isSelected();
		if(!b) return null;
		ExtrasBasicos e = new ExtrasBasicos();
		e.setLavadero(char1.isSelected());
		e.setInternet(char2.isSelected());
		e.setMicroondas(char3.isSelected());
		e.setHorno(char4.isSelected());
		e.setAmueblado(char5.isSelected());
		e.setCocinaOffice(char6.isSelected());
		e.setParquet(char7.isSelected());
		e.setDomotica(char8.isSelected());
		e.setArmarios(char9.isSelected());
		e.setLavadora(char10.isSelected());
		e.setTv(char11.isSelected());
		e.setElectrodomesticos(char12.isSelected());
		e.setSuiteConBanio(char13.isSelected());
		e.setPuertaBlindada(char14.isSelected());
		e.setGresCeramica(char15.isSelected());
		e.setCalefaccion(char16.isSelected());
		e.setAireAcondicionado(char17.isSelected());
		e.setNevera(char18.isSelected());
		return e;
	}
	
	private ExtrasOtros getExtrasOtros(){
		boolean b = has1.isSelected() || has2.isSelected() || has3.isSelected() || has4.isSelected() || has5.isSelected()
				|| has6.isSelected() || has7.isSelected() || has8.isSelected() || has9.isSelected();
		if (!b) return null;
		ExtrasOtros e = new ExtrasOtros();
		e.setPatio(has1.isSelected());
		e.setZonaDeportiva(has2.isSelected()); 
		e.setBalcon(has3.isSelected());
		e.setZonaComunitaria(has4.isSelected());
		e.setTerraza(has5.isSelected());
		e.setPiscinaComunitaria(has6.isSelected());
		e.setJardinPrivado(has7.isSelected());
		e.setPiscina(has8.isSelected());
		e.setZonaInfantil(has9.isSelected());
		return e;
	}
	
	private ExtrasFinca getExtrasFinca(){
		boolean b = has10.isSelected() || has11.isSelected() || has12.isSelected() || has13.isSelected() || has14.isSelected()
				|| has15.isSelected() || has16.isSelected();
		if (!b) return null;
		ExtrasFinca e = new ExtrasFinca();
		e.setAscensor(has10.isSelected());
		e.setTrastero(has11.isSelected()); 
		e.setParkingComunitario(has12.isSelected()); 
		e.setEnergiaSolar(has13.isSelected()); 
		e.setGarajePrivado(has14.isSelected()); 
		e.setServPorteria(has15.isSelected()); 
		e.setVideoportero(has16.isSelected()); 
		return e;
	}
	//endregion
}
