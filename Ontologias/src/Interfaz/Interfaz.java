package Interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import OWL.FamiliaReal;

public class Interfaz extends JFrame{
	private FamiliaReal fr;
	private ArrayList<String> results;
	
	private JPanel contentPane;
	private JPanel panelFoto;
	
	private JButton buttonAssert;
	private JButton buttonNietos;
	private JButton buttonSobrinos;
	private JButton buttonRey;
	private JButton buttonFamiliares;
	private JButton buttonDeportes;
	private JButton buttonTrabajo;
	private JScrollPane scrollPaneCentral;
	
	private JLabel[] labelFotos;

	private static final long serialVersionUID = 5393378737313833016L;
	
	public Interfaz(){
		super("Familia Real");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fr = FamiliaReal.getInstance();
		fr.createInstanceFamiliaReal();
		fr.createInstanceFoto();
		fr.createInstanceObjetos();
		
		results = new ArrayList<String>();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
					contentPane.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				//Método al que pasar la lista de URL de las fotos
				results = fr.instanceInfered("Fotos_Nietos_del_Rey");
				creaPanelCentral(results);
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
					contentPane.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_Sobrinos_del_principe");
				creaPanelCentral(results);
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
					contentPane.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_Rey");
				creaPanelCentral(results);
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
					contentPane.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_familiares");
				creaPanelCentral(results);
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
					contentPane.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_eventos_deportivos");
				creaPanelCentral(results);
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
					contentPane.remove(scrollPaneCentral);
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				results = fr.instanceInfered("Fotos_de_trabajo");
				creaPanelCentral(results);
				revalidate();
				repaint();
			}
		});
		buttonTrabajo.setEnabled(false);
		panel.add(buttonTrabajo,c);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(10, 11, 157, 437);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPane);
		
		panelFoto = new JPanel();
		panelFoto.setBounds(177, 11, 437, 437);
		panelFoto.setLayout(new GridBagLayout());
		
		//Aquí irian las fotos
		//JLabel lab = new JLabel();
		//lab.setIcon(arg0);
		
		scrollPaneCentral = new JScrollPane(panelFoto);
		scrollPaneCentral.setBounds(177, 11, 437, 437);
		scrollPaneCentral.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCentral.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPaneCentral);
	}
	
	public void creaPanelCentral(ArrayList<String> listFotos) {
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
		//labelFotos[i] = new JLabel(icon);
		//labelFotos[i].setIcon(icon);
		
		panelFoto.add(label,c);
		j++;
		
		for (String foto : listFotos) {
			
			String[] path = foto.split("file:");
			ImageIcon icon = new ImageIcon(path[1]); 
			
			c.gridx = 0;
			c.gridy = i + j;
			c.weightx = 1;
			labelFotos[i] = new JLabel(icon);
			//labelFotos[i].setIcon(icon);
			
			panelFoto.add(labelFotos[i],c);
			i++;
			
			c.gridx = 0;
			c.gridy = i + j;
			c.weightx = 1;
			label = new JLabel(" ");
			//labelFotos[i] = new JLabel(icon);
			//labelFotos[i].setIcon(icon);
			
			panelFoto.add(label,c);
			j++;
		}
		scrollPaneCentral = new JScrollPane(panelFoto);
		scrollPaneCentral.setBounds(177, 11, 437, 437);
		scrollPaneCentral.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneCentral.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPaneCentral);
	}
	
	public static void main(String[] args){
		Interfaz interfaz = new Interfaz();
		interfaz.setSize(640,495);
		interfaz.setLocation(500, 200);
		interfaz.setVisible(true);
	}
}
