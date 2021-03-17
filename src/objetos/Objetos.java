package objetos;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objetos implements Elemento{
	private double posicionX;
    private double posicionY;
    private double velocidadX;
    private double velocidadY;
    private int ancho;
    private int largo;
    private Color color;
    private int puntaje;
    
    
	public Objetos(int posicionX, int posicionY, double velocidadX2, double velocidadY2, int ancho, int largo,
			Color color,int puntaje) {
		
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.velocidadX = velocidadX2;
		this.velocidadY = velocidadY2;
		this.ancho = ancho;
		this.largo = largo;
		this.color = color;
		this.puntaje = puntaje;
	}
	 public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public void aumentarPuntaje () {
		 puntaje = puntaje *2;
   
   }
	public abstract void dibujarse(Graphics graphics) ;
	
    	public void aumentarVelocidad() {
    		 velocidadX = velocidadX *1.1;
 	         velocidadY = velocidadY *1.1;
        }
	    public void moverse() {
	        posicionX = posicionX + velocidadX;
	        posicionY = posicionY + velocidadY;
	    }

	    public void rebotarEnEjeX() {
	        velocidadX = -velocidadX;
	    }

	    public void rebotarEnEjeY() {
	        velocidadY = -velocidadY;
	    }
	    public void rebotarPaletas() {
	        velocidadY = -velocidadY--;
	    }

	    public double getVelocidadX() {
	        return velocidadX;
	    }

	    public void setVelocidadX(double velocidadX) {
	        this.velocidadX = velocidadX;
	    }

	    public double getVelocidadY() {
	        return velocidadY;
	    }

	    public void setVelocidadY(double velocidadY) {
	        this.velocidadY = velocidadY;
	    }

	    public int getPosicionX() {
	        return (int) posicionX;
	    }

	    public int getPosicionY() {
	        return (int) posicionY;
	    }

	    public void setPosicionX(int posicionX) {
	        this.posicionX = posicionX;
	    }

	    public void setPosicionY(int posicionY) {
	        this.posicionY = posicionY;
	    }

	    public int getAncho() {
	        return ancho;
	    }

	    public int getLargo() {
	        return largo;
	    }

	    public Color getColor() {
	        return color;
	    }

	    public void setAncho(int ancho) {
	        this.ancho = ancho;
	    }

	    public void setLargo(int largo) {
	        this.largo = largo;
	    }

	    public void setColor(Color color) {
	        this.color = color;
	    }

	}


