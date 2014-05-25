package Clases_Auxiliares;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Viviendas.ViviendasConnector;

public class LikeListener implements ActionListener {

	int id;
	
	public LikeListener(int id){
		super();
		this.id = id;
	}
	
	public void actionPerformed(ActionEvent e) {
		ViviendasConnector.storeCases(id);
	}
}
