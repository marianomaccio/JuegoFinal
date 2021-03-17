package players;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import objetos.Elemento;
import objetos.Objetos;
import objetos.Pelotas;

public class Player extends objetos.Objetos {

	private List<Pelotas> pelotas = new ArrayList<>();

	 public Player(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo, Color color,int puntaje) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color, puntaje);
	}

	public void dibujarse(Graphics graphics) {
	        graphics.setColor(getColor());
	        graphics.fillRect(getPosicionX(), getPosicionY(), getAncho(), getLargo());
	        
	    }
	 
	@Override
	public int posicionX() {
		return this.getPosicionX();
	}

	@Override
	public int posicionY() {
		return this.getPosicionY();
	}

	@Override
	public int velocidadX() {
		return this.velocidadX();
	}

	@Override
	public int velocidadY() {
		return this.velocidadY();
	}

	@Override
	public int ancho() {
		return this.getAncho();
	}

	@Override
	public int largo() {
		return this.getLargo();
	}

	@Override
	public Color color() {
		return this.getColor();
	}

	@Override
	public void reboteX() {
		
	}

	@Override
	public void reboteY() {
		
	}

	public Objetos disparo() {
			return new objetos.Pelotas(getAncho()/2,getLargo()/2, 10, 0, 30, 30, this.getColor(),1);
	}

	
	public List<Pelotas> getPelotas() {
		return pelotas;
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
