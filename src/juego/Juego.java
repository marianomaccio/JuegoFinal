package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;

import objetos.Circulos;
import objetos.Cuadrados;
import objetos.Objetos;
import objetos.Pelotas;
import players.Player;
import players.Puntaje;
import sonido.Sonidos;

public class Juego extends JComponent implements KeyListener, Runnable {

	private static final long serialVersionUID = 1l;
	private int ancho;
	private int largo;
	private Objetos circulo;
	private List<Pelotas> pelotas;
	private Objetos cuadrado1;
	private Objetos cuadrado2;
	private Objetos cuadrado3;
	private Objetos cuadrado4;
	private Player paletaRojo;
	private Player paletaAzul;
	private Puntaje puntajeRojo;
	private Puntaje puntajeAzul;
	private int tiempoDeEsperaEntreActualizaciones;
	private boolean pararJuego;
	private boolean juegoCorriendo;
	private int cantidadMaxPelotasPorJugador;
	private Sonidos circuloSonido;
	private Sonidos rebote;
	private Sonidos gol;
	private Sonidos victory;


	public Juego(int ancho, int largo, int tiempoDeEsperaEntreActualizaciones, int cantidadDePelotas) {
		this.ancho = ancho;
		this.largo = largo;
		this.circulo = new Circulos(ancho / 2, largo / 2, 0, 3, 15, 15, Color.green,0);
		this.cuadrado1 = new Cuadrados(ancho / 3, largo / 4, 0, 1, 15, 100, Color.blue,0);
		this.cuadrado2 = new Cuadrados(1200, largo / 4, 0, 1, 15, 100, Color.red,0);
		this.cuadrado3 = new Cuadrados(1200, 600, 0, -1, 15, 100, Color.red,0);
		this.cuadrado4 = new Cuadrados(600, 600, 0, -1, 15, 100, Color.blue,0);
		this.paletaAzul = new Player(1, largo / 2, 0, 0, 15, 130, Color.blue,0);
		this.paletaRojo = new Player(ancho - 16, largo / 2, 0, 0, 15, 130, Color.red,0);
		this.pelotas = new ArrayList<Pelotas>();
		crearPelotasInicio();
		puntajeAzul = new Puntaje(10, 20, new Font("ArtBrush", 8, 20),Color.blue);
		puntajeRojo = new Puntaje(1680, 20, new Font("ArtBrush", 8, 20),Color.red);
		this.juegoCorriendo = true;
		this.pararJuego = false;
		this.tiempoDeEsperaEntreActualizaciones = tiempoDeEsperaEntreActualizaciones;
		this.circuloSonido = new Sonidos("circuloS.wav");
		this.gol = new Sonidos("gol.wav");
		this.rebote = new Sonidos("pelotaRebote.wav");
		this.victory = new Sonidos("victory.wav");
		this.cantidadMaxPelotasPorJugador = cantidadDePelotas;
		
	}

	public Dimension mapa() {
		return new Dimension(ancho, largo);
	}

	public void moverPelotas() {
		for (objetos.Pelotas pelotas : pelotas) {
			pelotas.moverse();
		}
	}

	public void dibujarPelotas(Graphics g) {
		for (Pelotas pelotas : pelotas) {
			pelotas.dibujarse(g);
		}

	}

	public void agregarPelotas(Pelotas pelotas) {
		this.pelotas.add(pelotas);
	}

	public void crearPelotasRojas() {
		
		if (paletaRojo.getPelotas().size() < cantidadMaxPelotasPorJugador) {
			Pelotas pelota = new Pelotas(paletaRojo.getPosicionX() - 15, paletaRojo.getPosicionY() + 50, -1, 0, 15, 15,Color.red,1);
			agregarPelotas(pelota);
			paletaRojo.getPelotas().add(pelota);
		}

	}
	public void crearPelotasInicio() {
		
			Pelotas pelota1 = new objetos.Pelotas(ancho / 2 - 30, largo / 2 - 30, -0.5, -0.5, 15, 15, Color.WHITE,2);
			agregarPelotas(pelota1);
			Pelotas pelota2 = new objetos.Pelotas(ancho / 2 + 30, largo / 2 + 30, 0.5, 0.5, 15, 15, Color.WHITE,2);
			agregarPelotas(pelota2);
		
		}

	public void crearPelotasAzules() {
		if (paletaAzul.getPelotas().size() < cantidadMaxPelotasPorJugador) {
			Pelotas pelota = new objetos.Pelotas(paletaAzul.getPosicionX() + 15, paletaAzul.getPosicionY() + 50, 1, 0,15, 15, Color.blue,1);
			agregarPelotas(pelota);
			paletaAzul.getPelotas().add(pelota);
		}

	}
	
	private void rebotePelotas() {
		Iterator<Pelotas> iterador = pelotas.iterator();
	    while (iterador.hasNext()) {
	        Pelotas pelotas = iterador.next();

		
				if (pelotas.getPosicionX() <= 0){
					puntajeRojo.sumarPunto(pelotas.getPuntaje());
					iterador.remove();
					gol.plays();

				
			}
			if(pelotas.getPosicionX() >= ancho) {
				puntajeAzul.sumarPunto(pelotas.getPuntaje());
				iterador.remove();
				gol.plays();
				

			}
		
			if (pelotas.getPosicionY() <= 0 || pelotas.getPosicionY() + pelotas.getLargo() >= largo) {
				pelotas.rebotarEnEjeY();
				rebote.plays();
				}
			}
		
	    
	}

	private void colisionPelotas() {
		Iterator<Pelotas> iterador = pelotas.iterator();
		while (iterador.hasNext()) {
			Pelotas pelotas = iterador.next();

			if (pelotas.colision(paletaAzul)) {
				pelotas.rebotarEnEjeX();
				rebote.plays();
				
			}
			if (pelotas.colision(paletaRojo)) {
				pelotas.rebotarEnEjeX();
				rebote.plays();
				
			}
			if (pelotas.colision(cuadrado1)) {
				pelotas.rebotarEnEjeX();
				rebote.plays();
				
			}
			if (pelotas.colision(cuadrado2)) {
				pelotas.rebotarEnEjeX();
				rebote.plays();
				
			}
			if (pelotas.colision(cuadrado3)) {
				pelotas.rebotarEnEjeX();
				rebote.plays();
				
			}
			if (pelotas.colision(cuadrado4)) {
				pelotas.rebotarEnEjeX();
				rebote.plays();

			}
			if (circulo.colision(pelotas)) {
				pelotas.aumentarVelocidad();
				pelotas.aumentarPuntaje();
				circuloSonido.plays();
				
			}

		}
	}

	private void actualizarJuego() {
		verificarEstadoAmbiente();
		moverPelotas();
		circulo.moverse();
		cuadrado1.moverse();
		cuadrado2.moverse();
		cuadrado3.moverse();
		cuadrado4.moverse();
		paletaAzul.moverse();
		paletaRojo.moverse();
		

	}

	public void run() {
		while (juegoCorriendo) {
			actualizarJuego();
			dibujarJuego();
			esperar(tiempoDeEsperaEntreActualizaciones);
		}
	}

	public void keyTyped(KeyEvent tecla) {

	}

	public void keyPressed(KeyEvent tecla) {

		if (tecla.getKeyCode() == 87) {
			this.paletaAzul.setVelocidadY(-1.5);
		}

		if (tecla.getKeyCode() == 83) {
			this.paletaAzul.setVelocidadY(1.5);
		}
		if (tecla.getKeyCode() == 32) {

			crearPelotasAzules();

		}

		if (tecla.getKeyCode() == 104) {
			this.paletaRojo.setVelocidadY(-1.5);
		}

		if (tecla.getKeyCode() == 101) {
			this.paletaRojo.setVelocidadY(1.5);
		}
		if (tecla.getKeyCode() == 39) {

			crearPelotasRojas();
		}

	}

	private void dibujarJuego() {
		this.repaint();
	}

	private void limpiarPantalla(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, ancho, largo);
	}

	public void keyReleased(KeyEvent tecla) {
		if (tecla.getKeyCode() == 87 || tecla.getKeyCode() == 83) {
			this.paletaAzul.setVelocidadY(0);
		}
		if (tecla.getKeyCode() == 104 || tecla.getKeyCode() == 101) {
			this.paletaRojo.setVelocidadY(0);
		}

	}

	protected void paintComponent(Graphics g) {
		limpiarPantalla(g);
		if (!pararJuego) {
			dibujarPelotas(g);
			paletaRojo.dibujarse(g);
			cuadrado1.dibujarse(g);
			cuadrado2.dibujarse(g);
			cuadrado3.dibujarse(g);
			cuadrado4.dibujarse(g);
			circulo.dibujarse(g);
			paletaAzul.dibujarse(g);
			puntajeRojo.dibujarse(g);
			puntajeAzul.dibujarse(g);
			puntajeRojo.dibujarse(g);
			puntajeAzul.dibujarse(g);

		} else {
			finJuego(g);
			victory.plays();
			juegoCorriendo = false;
		}

	}

	private void verificarRebote1() {

		if (circulo.getPosicionY() >= largo) {
			circulo.rebotarEnEjeY();
		}
		if (cuadrado1.getPosicionY() >= largo - 100 || cuadrado1.getPosicionY() == 350) {
			cuadrado1.rebotarEnEjeY();
		}
		if (cuadrado2.getPosicionY() >= largo - 100 || cuadrado2.getPosicionY() == 350) {
			cuadrado2.rebotarEnEjeY();
		}
		if (cuadrado3.getPosicionY() >= largo - 100 || cuadrado3.getPosicionY() <= largo / 2) {
			cuadrado3.rebotarEnEjeY();
		}
		if (cuadrado4.getPosicionY() >= largo - 100 || cuadrado4.getPosicionY() <= largo / 2) {
			cuadrado4.rebotarEnEjeY();
		}
	}

	private void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}

	private void rebotePelotaContraLaParedSuperior() {

		if (circulo.getPosicionY() <= 0) {
			circulo.rebotarEnEjeY();
		}
		if (cuadrado1.getPosicionY() <= 0) {
			cuadrado1.rebotarEnEjeY();
		}
		if (cuadrado2.getPosicionY() <= 0) {
			cuadrado2.rebotarEnEjeY();
		}
		if (cuadrado3.getPosicionY() <= 0) {
			cuadrado3.rebotarEnEjeY();
		}
		if (cuadrado4.getPosicionY() <= 0) {
			cuadrado4.rebotarEnEjeY();
		}
	}

	private void rebotePaletas() {
		if (paletaRojo.getPosicionY() == (largo - 110)) {
			paletaRojo.rebotarPaletas();

		}
		if (paletaRojo.getPosicionY() == 1) {
			paletaRojo.rebotarPaletas();
		}
		if (paletaAzul.getPosicionY() == (largo - 110)) {
			paletaAzul.rebotarPaletas();

		}
		if (paletaAzul.getPosicionY() == 1) {
			paletaAzul.rebotarPaletas();
		}

	}

	 private void mensaje(Graphics g, String mensaje) {
	        this.limpiarPantalla(g);
	        g.setColor(Color.WHITE);
	        g.setFont(new Font("ArtBrush", 15, 50));
	        g.drawString(mensaje, 200, 200);
	    }
	 private void finJuego(Graphics g) {
		 if(puntajeRojo.getPuntaje()>puntajeAzul.getPuntaje()){
			 mensaje(g, "Fin del juego " +" Ganador Equipo Rojo: " + String.valueOf(puntajeRojo.getPuntaje())+"   Puntaje Equipo Azul: "+ String.valueOf(puntajeAzul.getPuntaje()));
		 }
		 if(puntajeRojo.getPuntaje()==puntajeAzul.getPuntaje()) {
			 mensaje(g, "Fin del juego" +" Empate!! Puntaje Equipo Azul: " + String.valueOf(puntajeAzul.getPuntaje())+"  Puntaje Equipo Rojo: "+ String.valueOf(puntajeRojo.getPuntaje()));
		 }if(puntajeRojo.getPuntaje()<puntajeAzul.getPuntaje()) {
			 mensaje(g, "Fin del juego" +" Ganador Equipo Azul Puntaje: " + String.valueOf(puntajeAzul.getPuntaje())+"  Puntaje Equipo Rojo: "+ String.valueOf(puntajeRojo.getPuntaje()));
		 }
	        
	    }
    private void verificarFinDeJuego() {
        if (pelotas.size() == 0) {
        	pararJuego = true;
     
           
        }

    }

	private void verificarEstadoAmbiente() {
		verificarRebote1();
		rebotePaletas();
		rebotePelotaContraLaParedSuperior();
		rebotePelotas();
		colisionPelotas();
		verificarFinDeJuego();
	}

}
