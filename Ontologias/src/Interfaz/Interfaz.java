package Interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import OWL.FamiliaReal;

public class Interfaz extends JFrame{
	private FamiliaReal fr;
	private ArrayList<String> results;
	
	private JPanel contentPane;
	private JPanel panelFoto;
	
	private JButton buttonNietos;
	private JButton buttonSobrinos;
	private JButton buttonAssert;
	private JScrollPane scrollPaneCentral;

	private static final long serialVersionUID = 5393378737313833016L;
	
	public Interfaz(){
		super("Familia Real");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fr = FamiliaReal.getInstance();
		fr.createInstanceFamiliaReal();
		fr.createInstanceFoto();
		
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
				fr.createPropertiesFamilia();
			}
		});
		panel.add(buttonAssert,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		buttonNietos = new JButton("Nietos del rey");
		buttonNietos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if (scrollPaneCentral != null) {
					contentPane.remove(scrollPaneCentral);
					//scrollPane = null;
					scrollPaneCentral.remove(panelFoto);
					panelFoto = null;
				}
				//buscarFormularioPrincipal();
				//Método al que pasar la lista de URL de las fotos
				creaPanelCentral();
				repaint();*/
				results = fr.instanceInfered("Fotos_Nietos_del_Rey");
			}
		});
		panel.add(buttonNietos,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		buttonSobrinos = new JButton("Sobrinos del principe");
		buttonSobrinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				results = fr.instanceInfered("Fotos_Sobrinos_del_principe");
			}
		});
		panel.add(buttonSobrinos,c);
		
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
		getContentPane().add(scrollPaneCentral);
		//TODO Panel de potones y panel central
	}
	
	public void creaPanelCentral() {
		
	}
	
	public static void main(String[] args){
		Interfaz interfaz = new Interfaz();
		interfaz.setSize(640,495);
		interfaz.setLocation(500, 200);
		interfaz.setVisible(true);
	}
}
