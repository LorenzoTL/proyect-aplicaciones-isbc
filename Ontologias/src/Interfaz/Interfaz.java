package Interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import OWL.FamiliaReal;

public class Interfaz extends JFrame{
	private FamiliaReal fr;
	private ArrayList<String> results;
	
	private JPanel contentPane;
	private JPanel contentPane2;
	private JPanel panel;
	
	private JButton buttonAssert;
	private JButton btnCargarFoto;
	private JLabel label;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	
	private JPanel panelFoto;
	private JButton buttonAssert2;
	private JButton buttonNietos;
	private JButton buttonSobrinos;
	private JButton buttonRey;
	private JButton buttonFamiliares;
	private JButton buttonDeportes;
	private JButton buttonTrabajo;
	private JButton buttonPrincipe;
	private JScrollPane scrollPaneCentral;
	
	private JLabel[] labelFotos;
	
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JCheckBox checkBox_4;
	private JCheckBox checkBox_5;
	private JCheckBox checkBox_6;
	private JCheckBox checkBox_7;
	private JCheckBox checkBox_8;
	private JCheckBox checkBox_9;
	private JCheckBox checkBox_10;
	private JCheckBox checkBox_11;
	private JCheckBox checkBox_12;
	private JCheckBox checkBox_13;
	private JCheckBox checkBox_14;
	private JCheckBox checkBox_15;

	private static final long serialVersionUID = 5393378737313833016L;
	
	public Interfaz(){
		super("Familia Real");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fr = FamiliaReal.getInstance();
		fr.createInstanceFamiliaReal();
		fr.createInstanceFoto();
		fr.createInstanceObjetos();
		
		results = new ArrayList<String>();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		setContentPane(tabbedPane);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		String[] paths = {"---","Fotos/foto1.png","Fotos/foto2.png","Fotos/foto3.jpg","Fotos/foto4.jpg"
							,"Fotos/foto5.jpg","Fotos/foto6.jpg","Fotos/foto7.png","Fotos/foto8.jpg"
							,"Fotos/foto9.png","Fotos/foto10.png","Fotos/foto11.png","Fotos/foto12.png"
							,"Fotos/foto13.png","Fotos/foto14.jpg","Fotos/foto15.jpg","Fotos/foto16.jpg"
							,"Fotos/foto17.JPG","Fotos/foto18.jpg","Fotos/foto19.jpg","Fotos/foto20.jpg"
							,"Fotos/foto21.jpg","Fotos/foto22.jpg","Fotos/foto23.jpg"};
		
		comboBox = new JComboBox<String>(paths);
		comboBox.setBounds(101, 11, 205, 20);
		contentPane.add(comboBox);
		
		btnCargarFoto = new JButton("Cargar Foto");
		btnCargarFoto.setBounds(366, 10, 103, 23);
		btnCargarFoto.setEnabled(false);
		btnCargarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = comboBox.getSelectedItem().toString();
				if (!s.equals("---")) {
	                contentPane.remove(scrollPane);
	                scrollPane.remove(panel);
	                panel = null;
	                creaPanelCentral(s);
					buttonAssert2.setEnabled(true);
				}
				revalidate();
                repaint();
			}
		});
		contentPane.add(btnCargarFoto);
		
		checkBox = new JCheckBox("Juan Carlos");
		checkBox.setBounds(65, 426, 97, 23);
		contentPane.add(checkBox);
		
		checkBox_1 = new JCheckBox("Cristina");
		checkBox_1.setBounds(246, 426, 97, 23);
		contentPane.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("Sofia");
		checkBox_2.setBounds(442, 426, 97, 23);
		contentPane.add(checkBox_2);
		
		checkBox_3 = new JCheckBox("Urdangarin");
		checkBox_3.setBounds(65, 452, 97, 23);
		contentPane.add(checkBox_3);
		
		checkBox_4 = new JCheckBox("Elena");
		checkBox_4.setBounds(246, 452, 97, 23);
		contentPane.add(checkBox_4);
		
		checkBox_5 = new JCheckBox("Felipe");
		checkBox_5.setBounds(442, 452, 97, 23);
		contentPane.add(checkBox_5);
		
		checkBox_6 = new JCheckBox("Irene");
		checkBox_6.setBounds(65, 478, 97, 23);
		contentPane.add(checkBox_6);
		
		checkBox_7 = new JCheckBox("Marichalar");
		checkBox_7.setBounds(246, 478, 97, 23);
		contentPane.add(checkBox_7);
		
		checkBox_8 = new JCheckBox("Leticia");
		checkBox_8.setBounds(442, 478, 97, 23);
		contentPane.add(checkBox_8);
		
		checkBox_9 = new JCheckBox("Miguel");
		checkBox_9.setBounds(65, 400, 97, 23);
		contentPane.add(checkBox_9);
		
		checkBox_10 = new JCheckBox("Froilan");
		checkBox_10.setBounds(246, 400, 97, 23);
		contentPane.add(checkBox_10);
		
		checkBox_11 = new JCheckBox("Infanta Leonor");
		checkBox_11.setBounds(442, 400, 110, 23);
		contentPane.add(checkBox_11);
		
		checkBox_12 = new JCheckBox("Pablo");
		checkBox_12.setBounds(65, 374, 97, 23);
		contentPane.add(checkBox_12);
		
		checkBox_13 = new JCheckBox("Victoria");
		checkBox_13.setBounds(246, 374, 97, 23);
		contentPane.add(checkBox_13);
		
		checkBox_14 = new JCheckBox("Infanta Sofía");
		checkBox_14.setBounds(442, 374, 97, 23);
		contentPane.add(checkBox_14);
		
		checkBox_15 = new JCheckBox("Juan");
		checkBox_15.setBounds(65, 504, 97, 23);
		contentPane.add(checkBox_15);
		
		panel = new JPanel();
		//panel.setBounds(65, 42, 466, 325);
		contentPane.add(panel);
		
		label = new JLabel("");
		panel.add(label);
		
		scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(65, 42, 466, 325);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scrollPane);
		
		buttonAssert2 = new JButton("ASSERT");
		buttonAssert2.setBounds(265, 535, 97, 23);
		buttonAssert2.setEnabled(false);
        buttonAssert2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						if (comboBox != null && comboBox.getSelectedItem()!= null && !
							comboBox.getSelectedItem().toString().equals("---")){
							String foto = getFotoSelected();
							ArrayList<String> personas = getPeopleSelected();
							fr.assertPropertieAparece(foto, personas);
						}
				}
		});
		contentPane.add(buttonAssert2);
		tabbedPane.addTab("Panel Usuario", contentPane);
		// Fin panel usuario
		
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 157, 437);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		buttonAssert = new JButton("ASSERT");
		buttonAssert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.createPropertiesFotos();
				fr.createPropertiesFotosObjetos();
				fr.createPropertiesFamilia();
				buttonNietos.setEnabled(true);
				buttonSobrinos.setEnabled(true);
				buttonRey.setEnabled(true);
				buttonFamiliares.setEnabled(true);
				buttonDeportes.setEnabled(true);
				buttonTrabajo.setEnabled(true);
				buttonPrincipe.setEnabled(true);
				btnCargarFoto.setEnabled(true);
				buttonAssert.setEnabled(false);
			}
		});
		panel.add(buttonAssert,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		buttonNietos = new JButton("Nietos del rey");
		buttonNietos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scrollPaneCentral != null) {
					contentPane2.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				//Método al que pasar la lista de URL de las fotos
				results = fr.instanceInfered("Fotos_Nietos_del_Rey");
				creaPanelCentralFotos(results);
				revalidate();
				repaint();
			}
		});
		buttonNietos.setEnabled(false);
		panel.add(buttonNietos,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		buttonSobrinos = new JButton("Sobrinos del principe");
		buttonSobrinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scrollPaneCentral != null) {
					contentPane2.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_Sobrinos_del_principe");
				creaPanelCentralFotos(results);
				revalidate();
				repaint();
			}
		});
		buttonSobrinos.setEnabled(false);
		panel.add(buttonSobrinos,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		buttonRey = new JButton("El Rey");
		buttonRey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scrollPaneCentral != null) {
					contentPane2.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_Rey");
				creaPanelCentralFotos(results);
				revalidate();
				repaint();
			}
		});
		buttonRey.setEnabled(false);
		panel.add(buttonRey,c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		buttonFamiliares = new JButton("Fotos Familiares");
		buttonFamiliares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scrollPaneCentral != null) {
					contentPane2.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_familiares");
				creaPanelCentralFotos(results);
				revalidate();
				repaint();
			}
		});
		buttonFamiliares.setEnabled(false);
		panel.add(buttonFamiliares,c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 1;
		buttonDeportes = new JButton("Eventos deportivos");
		buttonDeportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scrollPaneCentral != null) {
					contentPane2.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_eventos_deportivos");
				creaPanelCentralFotos(results);
				revalidate();
				repaint();
			}
		});
		buttonDeportes.setEnabled(false);
		panel.add(buttonDeportes,c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 1;
		buttonTrabajo = new JButton("Fotos de trabajo");
		buttonTrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scrollPaneCentral != null) {
					contentPane2.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_de_trabajo");
				creaPanelCentralFotos(results);
				revalidate();
				repaint();
			}
		});
		buttonTrabajo.setEnabled(false);
		panel.add(buttonTrabajo,c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 1;
		buttonPrincipe = new JButton("Familia del príncipe");
		buttonPrincipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scrollPaneCentral != null) {
					contentPane2.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Foto_Familia_Principe");
				creaPanelCentralFotos(results);
				revalidate();
				repaint();
			}
		});
		buttonPrincipe.setEnabled(false);
		panel.add(buttonPrincipe,c);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(10, 11, 157, 437);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane2.add(scrollPane);
		
		panelFoto = new JPanel();
		panelFoto.setBounds(177, 11, 437, 437);
		panelFoto.setLayout(new GridBagLayout());
		
		scrollPaneCentral = new JScrollPane(panelFoto);
		scrollPaneCentral.setBounds(177, 11, 437, 437);
		scrollPaneCentral.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCentral.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane2.add(scrollPaneCentral);
		
		tabbedPane.addTab("Panel busquedas", contentPane2);
	}
	
	private ArrayList<String> getPeopleSelected() {
                ArrayList<String> list = new ArrayList<String>();
                if (checkBox.isSelected()) list.add("Juan_Carlos");
                if (checkBox_1.isSelected()) list.add("Cristina");
                if (checkBox_2.isSelected()) list.add("Sofia");
                if (checkBox_3.isSelected()) list.add("Urdangarin");
                if (checkBox_4.isSelected()) list.add("Elena");
                if (checkBox_5.isSelected()) list.add("Felipe");
                if (checkBox_6.isSelected()) list.add("Irene");
                if (checkBox_7.isSelected()) list.add("Marichalar");
                if (checkBox_8.isSelected()) list.add("Leticia");
                if (checkBox_9.isSelected()) list.add("Miguel");
                if (checkBox_10.isSelected()) list.add("Froilan");
                if (checkBox_11.isSelected()) list.add("Infanta_Leonor");
                if (checkBox_12.isSelected()) list.add("Pablo");
                if (checkBox_13.isSelected()) list.add("Victoria");
                if (checkBox_14.isSelected()) list.add("Infanta_Sofia");
                if (checkBox_15.isSelected()) list.add("Juan");
                return list;
    }
	
	private String getFotoSelected() {
                String foto = comboBox.getSelectedItem().toString();
                String[] f = foto.split("/");
                foto = f[1].substring(0, f[1].length() - 4);
                return foto;
    }
	
	public void creaPanelCentral(String s) {
		ImageIcon icon = new ImageIcon(s);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        label = new JLabel(icon);
        
        panel.add(label,c);
        
        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(65, 42, 466, 325);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scrollPane);
	}
	
	public void creaPanelCentralFotos(ArrayList<String> listFotos) {
		labelFotos = new JLabel[listFotos.size()];
		
		panelFoto = new JPanel();
		panelFoto.setBounds(177, 11, 437, 437);
		panelFoto.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		int i = 0;
		int j = 0;
		
		c.gridx = 0;
		c.gridy = i + j;
		c.weightx = 1;
		JLabel label = new JLabel(" ");
		
		panelFoto.add(label,c);
		j++;
		
		for (String foto : listFotos) {
			
			String[] path = foto.split("file:");
			ImageIcon icon = new ImageIcon(path[1]); 
			
			c.gridx = 0;
			c.gridy = i + j;
			c.weightx = 1;
			labelFotos[i] = new JLabel(icon);
			
			panelFoto.add(labelFotos[i],c);
			i++;
			
			c.gridx = 0;
			c.gridy = i + j;
			c.weightx = 1;
			label = new JLabel(" ");
			
			panelFoto.add(label,c);
			j++;
		}
		scrollPaneCentral = new JScrollPane(panelFoto);
		scrollPaneCentral.setBounds(177, 11, 437, 437);
		scrollPaneCentral.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCentral.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane2.add(scrollPaneCentral);
	}
	
	public static void main(String[] args){
		Interfaz interfaz = new Interfaz();
		interfaz.setSize(640,635);
		interfaz.setLocation(350, 100);
		interfaz.setVisible(true);
	}
}
