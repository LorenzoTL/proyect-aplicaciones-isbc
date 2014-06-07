package Clases_Auxiliares;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CompraListener implements ActionListener {

	String titulo;
	String precio;
	
	public CompraListener(String titulo, String precio){
		super();
		this.titulo = titulo;
		this.precio = precio;
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Usted ha comprado \"" + titulo + "\" por " + precio + " €");
		System.exit(0);
	}
}