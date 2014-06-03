package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import CBR.RecomendadorCBR;
import Viviendas.DescripcionVivienda;

public class FormularioInicial extends JFrame {

	private static final long serialVersionUID = 5393378737313833016L;
	
	RecomendadorCBR recomendador = RecomendadorCBR.getInstance();
	boolean correcto = true;
	private JComboBox<String> comboHab;
	private JComboBox<String> comboSup;
	private JLabel labelHab;
	private JLabel labelSup;
	private JLabel labelArea = null;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboNew = null;
	
	//region Contructora del Formulario Inicial
	public FormularioInicial(){
		super("Recomendador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
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
		comboBox = new JComboBox<String>(comboList);
		comboBox.setBounds(105, 18, 193, 20);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				areas();
			}
		});
		getContentPane().add(comboBox);
		
		String[] numHab = new String[] {"1","2","3","4","5","6"};
		comboHab = new JComboBox<String>(numHab);
		comboHab.setBounds(105, 56, 121, 20);
		comboHab.setSelectedItem("1");
		getContentPane().add(comboHab);
		
		String[] tMax = new String[] {"20","40","60","80","100","150","200","250","300","350",
				"400","450","500","600","700","800","900","1000","1200","1400",
				"1600","1800","2000"};
		comboSup = new JComboBox<String>(tMax);
		comboSup.setBounds(105, 87, 121, 20);
		comboSup.setSelectedItem("20");
		getContentPane().add(comboSup);
		
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
						String localizacion;
						String auxLoc;
						String l = "";
						boolean b = false;
						if (comboNew != null && comboNew.getSelectedItem()!=null && 
								!comboNew.getSelectedItem().toString().equals("---")) {
							localizacion = comboNew.getSelectedItem().toString();
							l = comboBox.getSelectedItem().toString() + "#" + localizacion;
							auxLoc = comboBox.getSelectedItem().toString();
						} else {
							localizacion = comboBox.getSelectedItem().toString();
							l = localizacion;
							auxLoc = "---";
							b = true;
						}
						recomendador.setFiltroLocalizacion(l);
						Integer habitaciones = Integer.parseInt(comboHab.getSelectedItem().toString());
						Integer superficie = Integer.parseInt(comboSup.getSelectedItem().toString());
						description.setDescripcion(l,habitaciones,superficie);
						query.setDescription(description);
						recomendador.cycle(query);
						recomendador.postCycle();
						FormularioPrincipal fp = null;
						if (b) {
							fp = new FormularioPrincipal(localizacion,auxLoc,habitaciones,superficie,recomendador);
							fp.setVisible(true);
						} else {
							fp = new FormularioPrincipal(auxLoc,localizacion,habitaciones,superficie,recomendador);
							fp.setVisible(true);
						}
						setVisible(false);
					} catch (ExecutionException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		getContentPane().add(boton);
	}
	//endregion
	
	//region Areas
	public void areas() {
		if (labelArea != null) {
			getContentPane().remove(labelArea);
			getContentPane().remove(comboNew);
			labelArea = null;
			comboNew = null;
		}
		if (comboBox.getSelectedItem().toString().equals("arroyomolinos-madrid")) {
			pintaArea();
			String[] newList = new String[] {"---","centro-y-la-rinconada","coto-redondo-monte-de-batres","zona-bulevar-y-europa","zona-las-castaneras","zona-zarzalejos"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("ajalvir-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("alamo-el-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("alcala-de-henares")) {
			pintaArea();
			String[] newList = new String[] {"---","val","ensanche","casco-historico-de-alcala-de-henares","pryconsa-juan-de-austria","estacion-parque-o-donnell","	reyes-catolicos	chorrillo","la-garena","espartales"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("alcobendas")) {
			pintaArea();
			String[] newList = new String[] {"---","norte-de-alcobendas","el-soto-de-la-moraleja","	arroyo-de-la-vega","la-moraleja-distrito","	centro-de-alcobendas","	ensanche","	encinar-de-los-reyes"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("alcorcon")) {
			pintaArea();
			String[] newList = new String[] {"---","prado-santo-domingo-ensanche","parque-lisboa-la-paz","parque-oeste-fuente-cisneros","torresbellas","casco-antiguo","campodon-ventorro-del-cano"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("algete")) {
			pintaArea();
			String[] newList = new String[] {"---","algete-pueblo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("alpedrete-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("aranjuez")) {
			pintaArea();
			String[] newList = new String[] {"---","nuevo-aranjuez","foso-moreras","la-montana-el-cortijo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("arganda-del-rey")) {
			pintaArea();
			String[] newList = new String[] {"---","los-villares","centro-de-arganda-del-rey"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("arganzuela")) {
			pintaArea();
			String[] newList = new String[] {"---","delicias","acacias","legazpi","	chopera","palos-de-moguer","imperial"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("barajas")) {
			pintaArea();
			String[] newList = new String[] {"---","alameda-de-osuna","	timon","barajas-pueblo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("boadilla-del-monte")) {
			pintaArea();
			String[] newList = new String[] {"---","las-lomas","sector-s","parque-boadilla","sector-b","urb-este-monteprincipe","vinas-viejas"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("brunete-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("carabanchel")) {
			pintaArea();
			String[] newList = new String[] {"---","comillas","vista-alegre	opanel","san-isidro	puerta-bonita","pau-de-carabanchel"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("centro-de-madrid-capital")) {
			pintaArea();
			String[] newList = new String[] {"---","embajadores","universidad","cortes","palacio","justicia","sol"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("chamartin")) {
			pintaArea();
			String[] newList = new String[] {"---","el-viso	nueva-espana","	castilla","	hispanoamerica","ciudad-jardin","prosperidad"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("chamberi")) {
			pintaArea();
			String[] newList = new String[] {"---","trafalgar","gaztambide","almagro","	rios-rosas","arapiles","vallehermoso"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("ciempozuelos")) {
			pintaArea();
			String[] newList = new String[] {"---","cuevas-ilustracion"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("ciudad-lineal")) {
			pintaArea();
			String[] newList = new String[] {"---","pueblo-nuevo","	ventas","quintana","costillares","san-pascual","concepcion","colina"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("collado-villalba")) {
			pintaArea();
			String[] newList = new String[] {"---","los-valles","villalba-estacion"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("colmenar-viejo")) {
			pintaArea();
			String[] newList = new String[] {"---","las-vegas-el-pozanco","el-olivar-la-magdalena","centro-de-colmenar-viejo","	san-andres","el-cerrillo","	el-mirador"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("coslada")) {
			pintaArea();
			String[] newList = new String[] {"---","barrio-de-la-estacion-de-coslada","	valleaguado-la-canada","coslada-pueblo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("daganzo-de-arriba-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("el-boalo-cerceda-mataelpino")) {
			pintaArea();
			String[] newList = new String[] {"---","el-boalo","	cerceda"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("el-molar-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("fuencarral")) {
			pintaArea();
			String[] newList = new String[] {"---","montecarmelo","	penagrande","tres-olivos-valverde"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("fuenlabrada")) {
			pintaArea();
			String[] newList = new String[] {"---","la-serna","	el-naranjo","la-avanzada-la-cueva","centro-de-fuenlabrada","sudeste-industrial-de-fuenlabrada","fuenlabrada-ii-el-molino"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("fuente-el-saz-de-jarama-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("galapagar")) {
			pintaArea();
			String[] newList = new String[] {"---","el-guijo-colonia-espana"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("getafe")) {
			pintaArea();
			String[] newList = new String[] {"---","centro-de-getafe","	norte-de-getafe	juan-de-la-cierva","zona-universidad-en-getafe","la-alhondiga","perales-del-rio	el-roson-kelvinator"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("grinon-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("guadalix-de-la-sierra-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("hortaleza")) {
			pintaArea();
			String[] newList = new String[] {"---","sanchinarro","virgen-del-cortijo-manoteras","pinar-del-rey","conde-orgaz","valdebebas-valdefuentes","el-encinar-de-los-reyes","canillas","apostol-santiago"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("humanes-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("las-rozas-de-madrid")) {
			pintaArea();
			String[] newList = new String[] {"---","el-pinar-punta-galea","europolis","dehesa-navalcarbon-montecillo","casco-antiguo","marazuela-el-torreon","	club-de-golf","	el-cantizal","las-matas-penascales","parque-empresarial","zona-auditorio","	dehesa-navalcarbon-montecillo","molino-de-la-hoz","	el-burgo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("latina")) {
			pintaArea();
			String[] newList = new String[] {"---","lucero","puerta-del-angel","aguilas	aluche"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("leganes")) {
			pintaArea();
			String[] newList = new String[] {"---","zarzaquemada","centro-de-leganes","san-nicasio-campo-de-tiro-solagua","la-fortuna","norte-de-leganes","valdepelayo-montepinos-arroyo-culebro","el-carrascal","las-dehesillas-vereda-de-los-estudiantes"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("madrid-provincia")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("majadahonda")) {
			pintaArea();
			String[] newList = new String[] {"---","casco-antiguo-de-majadahonda","zona-monte-el-pilar","zona-carretera-del-plantio","golf-el-carralero","zona-norte-de-majadahonda"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("manzanares-el-real-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("mejorada-del-campo-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("moncloa")) {
			pintaArea();
			String[] newList = new String[] {"---","la-florida-el-plantio","arguelles","valdemarin","aravaca","casa-de-campo","ciudad-universitaria"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}else if (comboBox.getSelectedItem().toString().equals("moralzarzal")) {
			pintaArea();
			String[] newList = new String[] {"---","zona-centro-de-moralzarzal","zona-los-lagos","zona-colegios"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}
		else if (comboBox.getSelectedItem().toString().equals("moratalaz")) {
			pintaArea();
			String[] newList = new String[] {"---","media-legua","vinateros","pavones","marroquina"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}
		else if (comboBox.getSelectedItem().toString().equals("mostoles")) {
			pintaArea();
			String[] newList = new String[] {"---","centro-de-mostoles","mariblanca-villafontana","sur-pau-4","coimbra-guadarrama","zona-norte-universidad-en-mostoles","el-soto-coveta","suroeste-zona-hospital-en-mostoles"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("navalcarnero")) {
			pintaArea();
			String[] newList = new String[] {"---","el-pijorro","zona-casco-antiguo-de-navalcarnero","urb-calipo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("paracuellos-de-jarama")) {
			pintaArea();
			String[] newList = new String[] {"---","miramadrid"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("parla")) {
			pintaArea();
			String[] newList = new String[] {"---","villa-juventus","las-americas-parla-este","fuentebella-el-nido","centro-de-parla"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("pedrezuela-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("perales-de-tajuna-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("pinto")) {
			pintaArea();
			String[] newList = new String[] {"---","san-jose-buenos-aires","teneria-los-olivos","parque-europa-los-pitufos","zona-centro-ayuntamiento-de-pinto"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("pozuelo-de-alarcon")) {
			pintaArea();
			String[] newList = new String[] {"---","zona-pueblo","zona-norte-de-pozuelo-de-alarcon","zona-avenida-europa","zona-prado-de-somosaguas-la-finca","zona-estacion-de-pozuelo-de-alarcon","somosaguas","urbanizaciones"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("puente-de-vallecas")) {
			pintaArea();
			String[] newList = new String[] {"---","palomeras-sureste","numancia","san-diego","portazgo","palomeras-bajas	entrevias"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("resto-A5")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("resto-a5")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("resto-poblaciones-a4")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("resto-poblaciones-A4")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("retiro")) {
			pintaArea();
			String[] newList = new String[] {"---","ibiza","estrella","jeronimos","nino-jesus","pacifico","adelfas"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("rivas-vaciamadrid")) {
			pintaArea();
			String[] newList = new String[] {"---","centro-de-rivas-vaciamadrid","rivas-futura","covibar-pablo-iglesias","casco-historico-de-rivas-vaciamadrid","rivas-urbanizaciones"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("salamanca")) {
			pintaArea();
			String[] newList = new String[] {"---","recoletos","goya","guindalera","lista","castellana"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("san-agustin-de-guadalix-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("san-blas")) {
			pintaArea();
			String[] newList = new String[] {"---","arcos-de-jalon","simancas","canillejas","rosas-musas","salvador"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("san-fernando-de-henares")) {
			pintaArea();
			String[] newList = new String[] {"---","parque-roma-coronas","centro-de-san-fernando-de-henares","montserrat-parque-empresarial"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("san-lorenzo-de-el-escorial")) {
			pintaArea();
			String[] newList = new String[] {"---","centro-casco-historico-de-san-lorenzo-de-el-escorial"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("san-martin-de-la-vega-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("san-sebastian-de-los-reyes")) {
			pintaArea();
			String[] newList = new String[] {"---","ciudalcampo","centro-urbano-de-san-sebastian-de-los-reyes","rosa-luxemburgo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("tetuan")) {
			pintaArea();
			String[] newList = new String[] {"---","bellas-vistas","berruguete","cuatro-caminos-azca","valdeacederas","la-ventilla","castillejos"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("torrejon-de-ardoz")) {
			pintaArea();
			String[] newList = new String[] {"---","parque-cataluna-canada-soto","centro-de-torrejon-de-ardoz","fresnos","veredillas-juncal-zarzuela"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("torrelodones")) {
			pintaArea();
			String[] newList = new String[] {"---","casco-antiguo-de-torrelodones","zona-hospital-en-torrelodones","las-marias","los-robles"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("tres-cantos")) {
			pintaArea();
			String[] newList = new String[] {"---","primera-fase","zona-estacion-centro-de-tres-cantos","segunda-fase"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("usera")) {
			pintaArea();
			String[] newList = new String[] {"---","pradolongo","almendrales","orcasitas","moscardo","zofio"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("valdemorillo")) {
			pintaArea();
			String[] newList = new String[] {"---","pueblo-urb-norte","cerro-de-alarcon-puente-la-sierra-mirador-del-romero"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("valdemoro")) {
			pintaArea();
			String[] newList = new String[] {"---","centro-de-valdemoro","reston-i-reston-ii","zona-estacion-en-valdemoro","altos-del-olivar-el-caracol"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("velilla-de-san-antonio-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("venturada")) {
			pintaArea();
			String[] newList = new String[] {"---","los-cotos-de-monterrey","venturada-pueblo"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("vicalvaro")) {
			pintaArea();
			String[] newList = new String[] {"---","san-juan","valdebernardo-valderribas","casco-historico-de-vicalvaro"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("villa-de-vallecas")) {
			pintaArea();
			String[] newList = new String[] {"---","vallecas-pueblo","ensanche-de-vallecas-valdecarros"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("villalbilla-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("villanueva-de-la-canada-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("villanueva-del-pardillo")) {
			pintaArea();
			String[] newList = new String[] {"---","centro-de-villanueva-del-pardillo","ibiza-san-pedro"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("villaverde")) {
			pintaArea();
			String[] newList = new String[] {"---","los-rosales","san-andres","butarque"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		} else if (comboBox.getSelectedItem().toString().equals("villaviciosa-de-odon")) {
			pintaArea();
			String[] newList = new String[] {"---","casco-urbano-de-villaviciosa-de-odon","el-castillo-campodon","el-bosque"};
			comboNew = new JComboBox<String>(newList);
			comboNew.setBounds(105, 63, 193, 20);
			getContentPane().add(comboNew);
			repaint();
		}
		if (comboNew != null) {
			comboNew.revalidate();
			comboNew.repaint();
		}
	}
	
	public void pintaArea() {
		comboHab.setBounds(comboHab.getX(), 106, comboHab.getWidth(), comboHab.getHeight());
		comboSup.setBounds(comboSup.getX(), 137, comboSup.getWidth(), comboSup.getHeight());
		labelHab.setBounds(labelHab.getX(), 108, labelHab.getWidth(), labelHab.getHeight());
		labelSup.setBounds(labelSup.getX(), 139, labelSup.getWidth(), labelSup.getHeight());
		
		labelArea = new JLabel("Area");
		labelArea.setBounds(10, 65, 77, 14);
		getContentPane().add(labelArea);
	}
	
	public void pintaNoArea() {
		comboHab.setBounds(comboHab.getX(), 56, comboHab.getWidth(), comboHab.getHeight());
		comboSup.setBounds(comboSup.getX(), 87, comboSup.getWidth(), comboSup.getHeight());
		labelHab.setBounds(labelHab.getX(), 59, labelHab.getWidth(), labelHab.getHeight());
		labelSup.setBounds(labelSup.getX(), 90, labelSup.getWidth(), labelSup.getHeight());
	}
	//endregion
	
	public static void main(String[] args) {	
		FormularioInicial fi = new FormularioInicial();
		fi.setSize(329,300);
		fi.setLocation(500, 200);
		fi.setVisible(true);	
	}
}
