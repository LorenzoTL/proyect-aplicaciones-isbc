package Team;

import EDU.gatech.cc.is.util.Vec2;

public interface Lateral {

	public int getId();
	
	public void setId(int id);
	
	public int getL() ;
	
	public void setL(int l);
	
	public int getPosA();
	
	public void setPosA(int posA);
	
	public int getPosD();
	
	public void setPosD(int posD) ;
	
	public double getX() ;
	
	public void setX(double x);
	
	public double getY();
	
	public void setY(double y);
	
	public void volverAPosicionInicial(Vec2 me,boolean ataque);
	
	public void initialization(int fieldSide);
	
}
