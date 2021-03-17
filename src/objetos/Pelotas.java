package objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

public class Pelotas extends Objetos {
	
	public Pelotas(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo, Color color,int puntaje) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color,puntaje);
		
		
	}


	public void dibujarse(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.fillOval(getPosicionX(), getPosicionY(), getAncho(), getLargo());
    }

	@Override

	public int posicionX() {
		// TODO Auto-generated method stub
		return this.getPosicionX();
	}

	@Override
	public int posicionY() {
		// TODO Auto-generated method stub
		return this.getPosicionY();
	}

	@Override
	public int velocidadX() {
		// TODO Auto-generated method stub
		return this.velocidadX();
	}

	@Override
	public int velocidadY() {
		// TODO Auto-generated method stub
		return this.velocidadY();
	}

	@Override
	public int ancho() {
		// TODO Auto-generated method stub
		return this.getAncho();
	}

	@Override
	public int largo() {
		// TODO Auto-generated method stub
		return this.getLargo();
	}

	@Override
	public Color color() {
		// TODO Auto-generated method stub
		return this.getColor();
	}

	@Override
	public void reboteX() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reboteY() {
		// TODO Auto-generated method stub
		
	}
	public int puntaje() {
		return this.puntaje();
	}
		
		@Override
	public boolean colision(Elemento elemento) {
		if (this.getPosicionX() < elemento.posicionX() + elemento.ancho() &&
	            this.getPosicionX() + this.getAncho() > elemento.posicionX() &&
	            this.getPosicionY() < elemento.posicionY() + elemento.largo() &&
	            this.getLargo() + this.getPosicionY() > elemento.posicionY()) {
	            return true;
			}
		return false;
	}

}
 