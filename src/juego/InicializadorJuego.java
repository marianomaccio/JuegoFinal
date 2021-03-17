package juego;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class InicializadorJuego {

	public static void main(String[] args) {

		JFrame pantallaSeleccion = new JFrame("Inicio juego");

		
		String cantidadBolas = JOptionPane.showInputDialog(pantallaSeleccion, "Ingrese cantidad de pelotas de jugador");
		
		//Por defecto son 3 bolas por jugador
		int cantidadDePelotas = 3;
		
		try {
			cantidadDePelotas = Integer.valueOf(cantidadBolas);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
				    "La cantidad de bolas ingresada es incorrecta. Se toma por defecto 3 bolas por jugador.",
				    "Numero incorrecto",
				    JOptionPane.WARNING_MESSAGE);
		}

		int ancho = 1800;
		int largo = 900;
		int tiempoDeEsperaEntreActualizaciones = 5;

		System.setProperty("sun.java2d.opengl", "true");

		JFrame juego = new JFrame("PintBall Loco");

		juego.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		juego.setLocationRelativeTo(null);

		juego.setVisible(true);

		Juego paintBallLoco = new Juego(ancho, largo, tiempoDeEsperaEntreActualizaciones, cantidadDePelotas);

		juego.add(paintBallLoco);

		juego.addKeyListener(paintBallLoco);

		juego.pack();

		Thread thread = new Thread(paintBallLoco);

		thread.start();
	}

}
