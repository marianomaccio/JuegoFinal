package players;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import objetos.Pelotas;

public class Puntaje {

    private int posicionX;
    private int posicionY;
    private Font font;
    private Color color;
    private int puntaje;

    public Puntaje(int i, int j, Font font2, Color blue) {
        this.posicionX = i;
        this.posicionY = j;
        this.font = font2;
        this.color = blue;
        this.puntaje = 0;
	}



	public void dibujarse(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Puntaje: " + String.valueOf(puntaje), posicionX, posicionY);
    }

    public void sumarPunto(int puntaje) {
    	this.puntaje +=  puntaje;
        
    }

    public int getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

}
