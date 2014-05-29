package Interfaz;

import javax.swing.JFrame;

public class Interfaz extends JFrame{

	private static final long serialVersionUID = 5393378737313833016L;
	
	public Interfaz(){
		super("Familia Real");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//TODO Panel de potones y panel central
	}
	
	public static void main(String[] args){
		Interfaz interfaz = new Interfaz();
		interfaz.setSize(329,300);
		interfaz.setLocation(500, 200);
		interfaz.setVisible(true);
	}
	
}
