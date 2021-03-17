package objetos;

import java.awt.Color;
import java.awt.Graphics;

public interface Elemento {
	
	public int posicionX();
	
	public int posicionY();
	
	public int velocidadX();
	
	public int velocidadY();
	
	public int ancho();
	
	public int largo();
	
	public Color color();
	
	public void dibujarse(Graphics graphis);
		
	public boolean colision(Elemento elemento);
	
	public void reboteX();
	
	public void reboteY();
	
	public void moverse();
	
	
}
