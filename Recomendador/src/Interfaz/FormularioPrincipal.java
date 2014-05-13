package Interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class FormularioPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	
	private JComboBox comboLocalizacion;
	private JComboBox comboArea;
	private JComboBox comboMinPrecio;
	private JComboBox comboMaxPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioPrincipal frame = new FormularioPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 10, 998, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboArea = new JComboBox();
		comboArea.setEnabled(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 962, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(195, 11, 679, 31);
		panel_1.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(10, 75, 186, 326);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		// Formulario de filtrado
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		//c.gridwidth = 2;
		panel.add(new JLabel("Localizacion"),c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.gridwidth = 2;
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
		comboLocalizacion = new JComboBox(comboList);
		comboLocalizacion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println(comboBox.getSelectedItem().toString());
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.HORIZONTAL;
				panel.remove(comboArea);
				areas();
				c.gridx = 0;
				c.gridy = 3;
				c.weightx = 0.5;
				panel.add(comboArea,c);
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(comboLocalizacion,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Area"),c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		//c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboArea,c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		//c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Precio"),c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 1;
		String[] pMin = new String[] {"Min"};
		comboMinPrecio = new JComboBox(pMin);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboMinPrecio,c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1;
		String[] pMax = new String[] {"Max"};
		comboMaxPrecio = new JComboBox(pMax);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboMaxPrecio,c);
		
		// Fin formulario
		JScrollPane scrollPane_2 = new JScrollPane(panel);
		scrollPane_2.setBounds(10, 75, 218, 326);
		scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(239, 75, 733, 626);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		
		//contentPane.add(panel);
		setVisible(true);
	}
	
	public void areas() {
		
		if (comboLocalizacion.getSelectedItem().toString().equals("arroyomolinos-madrid")) {
			String[] newList = new String[] {"---","centro-y-la-rinconada","coto-redondo-monte-de-batres","zona-bulevar-y-europa","zona-las-castaneras","zona-zarzalejos"};
			comboArea = new JComboBox(newList);
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
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("alcobendas")) {
			
			String[] newList = new String[] {"---","norte-de-alcobendas","el-soto-de-la-moraleja","	arroyo-de-la-vega","la-moraleja-distrito","	centro-de-alcobendas","	ensanche","	encinar-de-los-reyes"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("alcorcon")) {
			
			String[] newList = new String[] {"---","prado-santo-domingo-ensanche","parque-lisboa-la-paz","parque-oeste-fuente-cisneros","torresbellas","casco-antiguo","campodon-ventorro-del-cano"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("algete")) {
			
			String[] newList = new String[] {"---","algete-pueblo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("alpedrete-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("aranjuez")) {
			
			String[] newList = new String[] {"---","nuevo-aranjuez","foso-moreras","la-montana-el-cortijo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("arganda-del-rey")) {
			
			String[] newList = new String[] {"---","los-villares","centro-de-arganda-del-rey"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("arganzuela")) {
			
			String[] newList = new String[] {"---","delicias","acacias","legazpi","	chopera","palos-de-moguer","imperial"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("barajas")) {
			
			String[] newList = new String[] {"---","alameda-de-osuna","	timon","barajas-pueblo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("boadilla-del-monte")) {
			
			String[] newList = new String[] {"---","las-lomas","sector-s","parque-boadilla","sector-b","urb-este-monteprincipe","vinas-viejas"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("brunete-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("carabanchel")) {
			
			String[] newList = new String[] {"---","comillas","vista-alegre	opanel","san-isidro	puerta-bonita","pau-de-carabanchel"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("centro-de-madrid-capital")) {
			
			String[] newList = new String[] {"---","embajadores","universidad","cortes","palacio","justicia","sol"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("chamartin")) {
			
			String[] newList = new String[] {"---","el-viso	nueva-espana","	castilla","	hispanoamerica","ciudad-jardin","prosperidad"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("chamberi")) {
			
			String[] newList = new String[] {"---","trafalgar","gaztambide","almagro","	rios-rosas","arapiles","vallehermoso"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("ciempozuelos")) {
			
			String[] newList = new String[] {"---","cuevas-ilustracion"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("ciudad-lineal")) {
			
			String[] newList = new String[] {"---","pueblo-nuevo","	ventas","quintana","costillares","san-pascual","concepcion","colina"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("collado-villalba")) {
			
			String[] newList = new String[] {"---","los-valles","villalba-estacion"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("colmenar-viejo")) {
			
			String[] newList = new String[] {"---","las-vegas-el-pozanco","el-olivar-la-magdalena","centro-de-colmenar-viejo","	san-andres","el-cerrillo","	el-mirador"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("coslada")) {
			
			String[] newList = new String[] {"---","barrio-de-la-estacion-de-coslada","	valleaguado-la-canada","coslada-pueblo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("daganzo-de-arriba-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("el-boalo-cerceda-mataelpino")) {
			
			String[] newList = new String[] {"---","el-boalo","	cerceda"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("el-molar-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("fuencarral")) {
			
			String[] newList = new String[] {"---","montecarmelo","	penagrande","tres-olivos-valverde"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("fuenlabrada")) {
			
			String[] newList = new String[] {"---","la-serna","	el-naranjo","la-avanzada-la-cueva","centro-de-fuenlabrada","sudeste-industrial-de-fuenlabrada","fuenlabrada-ii-el-molino"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("fuente-el-saz-de-jarama-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("galapagar")) {
			
			String[] newList = new String[] {"---","el-guijo-colonia-espana"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("getafe")) {
			
			String[] newList = new String[] {"---","centro-de-getafe","	norte-de-getafe	juan-de-la-cierva","zona-universidad-en-getafe","la-alhondiga","perales-del-rio	el-roson-kelvinator"};
			comboArea = new JComboBox(newList);
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
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("humanes-zona-de")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("las-rozas-de-madrid")) {
			
			String[] newList = new String[] {"---","el-pinar-punta-galea","europolis","dehesa-navalcarbon-montecillo","casco-antiguo","marazuela-el-torreon","	club-de-golf","	el-cantizal","las-matas-penascales","parque-empresarial","zona-auditorio","	dehesa-navalcarbon-montecillo","molino-de-la-hoz","	el-burgo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("latina")) {
			
			String[] newList = new String[] {"---","lucero","puerta-del-angel","aguilas	aluche"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("leganes")) {
			
			String[] newList = new String[] {"---","zarzaquemada","centro-de-leganes","san-nicasio-campo-de-tiro-solagua","la-fortuna","norte-de-leganes","valdepelayo-montepinos-arroyo-culebro","el-carrascal","las-dehesillas-vereda-de-los-estudiantes"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("madrid-provincia")) {
			pintaNoArea();
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("majadahonda")) {
			
			String[] newList = new String[] {"---","casco-antiguo-de-majadahonda","zona-monte-el-pilar","zona-carretera-del-plantio","golf-el-carralero","zona-norte-de-majadahonda"};
			comboArea = new JComboBox(newList);
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
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}else if (comboLocalizacion.getSelectedItem().toString().equals("moralzarzal")) {
			
			String[] newList = new String[] {"---","zona-centro-de-moralzarzal","zona-los-lagos","zona-colegios"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}
		else if (comboLocalizacion.getSelectedItem().toString().equals("moratalaz")) {
			
			String[] newList = new String[] {"---","media-legua","vinateros","pavones","marroquina"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}
		else if (comboLocalizacion.getSelectedItem().toString().equals("mostoles")) {
			
			String[] newList = new String[] {"---","centro-de-mostoles","mariblanca-villafontana","sur-pau-4","coimbra-guadarrama","zona-norte-universidad-en-mostoles","el-soto-coveta","suroeste-zona-hospital-en-mostoles"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("navalcarnero")) {
			
			String[] newList = new String[] {"---","el-pijorro","zona-casco-antiguo-de-navalcarnero","urb-calipo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("paracuellos-de-jarama")) {
			
			String[] newList = new String[] {"---","miramadrid"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("parla")) {
			
			String[] newList = new String[] {"---","villa-juventus","las-americas-parla-este","fuentebella-el-nido","centro-de-parla"};
			comboArea = new JComboBox(newList);
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
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("pozuelo-de-alarcon")) {
			
			String[] newList = new String[] {"---","zona-pueblo","zona-norte-de-pozuelo-de-alarcon","zona-avenida-europa","zona-prado-de-somosaguas-la-finca","zona-estacion-de-pozuelo-de-alarcon","somosaguas","urbanizaciones"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("puente-de-vallecas")) {
			
			String[] newList = new String[] {"---","palomeras-sureste","numancia","san-diego","portazgo","palomeras-bajas	entrevias"};
			comboArea = new JComboBox(newList);
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
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("rivas-vaciamadrid")) {
			
			String[] newList = new String[] {"---","centro-de-rivas-vaciamadrid","rivas-futura","covibar-pablo-iglesias","casco-historico-de-rivas-vaciamadrid","rivas-urbanizaciones"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("salamanca")) {
			
			String[] newList = new String[] {"---","recoletos","goya","guindalera","lista","castellana"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-agustin-de-guadalix-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-blas")) {
			
			String[] newList = new String[] {"---","arcos-de-jalon","simancas","canillejas","rosas-musas","salvador"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-fernando-de-henares")) {
			
			String[] newList = new String[] {"---","parque-roma-coronas","centro-de-san-fernando-de-henares","montserrat-parque-empresarial"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-lorenzo-de-el-escorial")) {
			
			String[] newList = new String[] {"---","centro-casco-historico-de-san-lorenzo-de-el-escorial"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-martin-de-la-vega-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("san-sebastian-de-los-reyes")) {
			
			String[] newList = new String[] {"---","ciudalcampo","centro-urbano-de-san-sebastian-de-los-reyes","rosa-luxemburgo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("tetuan")) {
			
			String[] newList = new String[] {"---","bellas-vistas","berruguete","cuatro-caminos-azca","valdeacederas","la-ventilla","castillejos"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("torrejon-de-ardoz")) {
			
			String[] newList = new String[] {"---","parque-cataluna-canada-soto","centro-de-torrejon-de-ardoz","fresnos","veredillas-juncal-zarzuela"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("torrelodones")) {
			
			String[] newList = new String[] {"---","casco-antiguo-de-torrelodones","zona-hospital-en-torrelodones","las-marias","los-robles"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("tres-cantos")) {
			
			String[] newList = new String[] {"---","primera-fase","zona-estacion-centro-de-tres-cantos","segunda-fase"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("usera")) {
			
			String[] newList = new String[] {"---","pradolongo","almendrales","orcasitas","moscardo","zofio"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("valdemorillo")) {
			
			String[] newList = new String[] {"---","pueblo-urb-norte","cerro-de-alarcon-puente-la-sierra-mirador-del-romero"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("valdemoro")) {
			
			String[] newList = new String[] {"---","centro-de-valdemoro","reston-i-reston-ii","zona-estacion-en-valdemoro","altos-del-olivar-el-caracol"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("velilla-de-san-antonio-zona-de")) {
			pintaNoArea();
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("venturada")) {
			
			String[] newList = new String[] {"---","los-cotos-de-monterrey","venturada-pueblo"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("vicalvaro")) {
			
			String[] newList = new String[] {"---","san-juan","valdebernardo-valderribas","casco-historico-de-vicalvaro"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villa-de-vallecas")) {
			
			String[] newList = new String[] {"---","vallecas-pueblo","ensanche-de-vallecas-valdecarros"};
			comboArea = new JComboBox(newList);
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
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villaverde")) {
			
			String[] newList = new String[] {"---","los-rosales","san-andres","butarque"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		} else if (comboLocalizacion.getSelectedItem().toString().equals("villaviciosa-de-odon")) {
			
			String[] newList = new String[] {"---","casco-urbano-de-villaviciosa-de-odon","el-castillo-campodon","el-bosque"};
			comboArea = new JComboBox(newList);
			comboArea.setEnabled(true);
			
			repaint();
		}
		
			comboArea.revalidate();
			comboArea.repaint();
	}
	
	public void pintaNoArea() {
		comboArea.setEnabled(false);
	}
}
