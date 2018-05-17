package concurso;

import java.util.ArrayList;
import java.util.Arrays;

public class Partida {
	/*
	 * La clase Partida instancia una partida de maximo 200 intentos donde dos
	 * jugadores juegan para adivinar el codigo contrario.
	 *
	 */
	private Jugador jugador1;
	private Jugador jugador2;
	private boolean haTerminado;
	private byte[] resultado1;
	private byte[] resultado2;
	private final int NUMRONDAS = 200;
	private boolean esGanador;
	private byte[] combinacionOcultaJugador1;
	private byte[] combinacionOcultaJugador2;
	private byte[] combinacionPropuestaJugador1;
	private byte[] combinacionPropuestaJugador2;
	private byte[] mejorCombinacionJugador1;
	private byte[] mejorCombinacionJugador2;

	/*
	 * Instancia una partida
	 */
	Partida(Jugador jugador1, Jugador jugador2) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}

	public byte partidaDificil() {

		byte resultado = 0;

		/*
		 * 1. Los jugadores introducen las combinaciones secretas. 2. El jugador
		 * introduce la combinación propuesta 3. Tras obtener la combinación propuesta,
		 * se comprueba el resultado. 4. Se comprueba el ganador: 4.1. Si obtiene todos
		 * los pinchos negros gana. 4.2. Si no obtiene todos los pinchos negros sigue
		 * jugando hasta llegar al nº máximo de intentos. 5. A la hora de ganar o perder
		 * la partida: 5.1. Si gana jugador 1, devuelve 1. 5.2. Si gana jugador 2,
		 * devuelve 2. 5.3. Si los intentos llegan al nº máximo de intentos y ambos
		 * jugadores no han ganado, devuelve 0.
		 */

		esGanador = false;

		// 1. Los jugadores introducen las combinaciones secretas.
		combinacionOcultaJugador1 = jugador1.crearCombSecreta();
		combinacionOcultaJugador2 = jugador2.crearCombSecreta();

		// 2. El jugador introduce la combinación propuesta
		for (int i = 0; i < NUMRONDAS && !esGanador; i++) {
			setCombinacionPropuestaJugador1(jugador1.crearCombPropuesta());
			setCombinacionPropuestaJugador2(jugador2.crearCombPropuesta());

			// 3. Tras obtener la combinación propuesta, se comprueba el resultado.
			resultado1 = calcularResultado(combinacionOcultaJugador1, combinacionPropuestaJugador2);
			resultado2 = calcularResultado(combinacionOcultaJugador2, combinacionPropuestaJugador1);

			// Dibujar

			dibujar(combinacionPropuestaJugador1, resultado2);
			dibujar(combinacionPropuestaJugador2, resultado1);
			if (Arrays.equals(combinacionOcultaJugador1, combinacionPropuestaJugador2)
					|| Arrays.equals(combinacionOcultaJugador2, combinacionPropuestaJugador1)) {
				esGanador = true;
			}

		}
		if (!esGanador) {
			// 4. Se comprueba el ganador (caso empate)
			comprobarGanador(calcularResultado(null, null));
		} else if (Arrays.equals(combinacionOcultaJugador1, combinacionPropuestaJugador2)
				&& Arrays.equals(combinacionOcultaJugador2, combinacionPropuestaJugador1)) {
			resultado = 0;
		} else if (Arrays.equals(combinacionOcultaJugador1, combinacionPropuestaJugador2)) {
			resultado = 2;
		} else if (Arrays.equals(combinacionOcultaJugador2, combinacionPropuestaJugador1)) {
			resultado = 1;
		}

		return resultado;

	}

	public void setCombinacionPropuestaJugador1(byte[] combinacionPropuestaJugador1) {
		this.combinacionPropuestaJugador1 = combinacionPropuestaJugador1;
	}

	public void setCombinacionPropuestaJugador2(byte[] combinacionPropuestaJugador2) {
		this.combinacionPropuestaJugador2 = combinacionPropuestaJugador2;
	}

	public void comprobarGanador(byte[] resultadoNuevo) {
		
	}

	private void dibujar(byte[] combinacion, byte[] resultado) {
		int i;
		for (i = 0; i < 8; i++) {
			System.out.printf("%s " + (char) 9209 + RESET, traducirColor(combinacion[i]));
		}
		System.out.printf(" | ");
		for (i = 0; i < 8; i++) {
			System.out.printf("%s " + (char) 9210 + RESET, traducirColor(resultado[i]));
		}
	}

	private byte[] calcularResultado(byte combinacionOculta[], byte combinacionPropuesta[]) {
		byte negrasBlancas[] = { 0, 0 };
		byte contadorNegras = 0;
		byte contadorBlancas = 0;
		final byte NUM_CASILLAS = 8;
		boolean aparece;
		boolean comprobadaOculta[] = new boolean[NUM_CASILLAS];
		for (byte i = 0; i < comprobadaOculta.length; i++) // ---- Esto sirve para que las casillas
			comprobadaOculta[i] = false; // comprobadas no se vuelvan a comprobar.
		for (byte i = 0; i < combinacionPropuesta.length; i++) { // ---- Compara las casillas de la
			aparece = false; // combinación propuesta por
			for (byte j = 0; j < combinacionOculta.length && !aparece; j++) { // las casillas una a una de
				if (combinacionPropuesta[i] == combinacionOculta[j] && !comprobadaOculta[j]) { // la combinación
																								// original.
					if (combinacionPropuesta[i] == combinacionOculta[i] && !comprobadaOculta[i]) { // Al hacer esto se
																									// meten las
						contadorNegras++; // casillas comprobadas de la
						comprobadaOculta[i] = true; // combinación original en un
					} else if (combinacionPropuesta[j] == combinacionOculta[j]) { // array para marcarlas y que
						contadorNegras++; // no se comparen 2 veces. La
						comprobadaOculta[j] = true; // ccombinación propuesta no
					} else { // lo necesita porque se
						contadorBlancas++; // recorre de uno en uno.
						comprobadaOculta[j] = true; // Si el color de ficha de
					} // las 2 casillas es la misma,
					aparece = true; // según la posición de ambas
				} // se marcará como ficha negra
			} // o ficha blanca.
		}
		negrasBlancas[0] = contadorNegras;
		negrasBlancas[1] = contadorBlancas;
		return negrasBlancas;
	}

	private String traducirColor(byte byteColor) {
		String color = "";
		switch (byteColor) {
		case 0:
			color = "\u001B[31m";
			break;
		case 1:
			color = "\u001B[32m";
			break;
		case 2:
			color = "\u001B[33m";
			break;
		case 3:
			color = "\u001B[34m";
			break;
		case 4:
			color = "\u001B[35m";
			break;
		case 5:
			color = "\u001B[36m";
			break;
		case 6:
			color = "\u001B[38;5;208mm";
			break;
		case 7:
			color = "\u001B[90m";
			break;
		case 8:
			color = "\u001B[95m";
			break;
		case 9:
			color = "\u001B[92m";
			break;
		}
		return color;
	}

	private static final String RESET = "\u001B[0m";

	public static void main(String[] args) {
		System.out.printf("\u001B[31m" + (char) 9209);
		System.out.printf("\u001B[32m" + (char) 9209);
		System.out.printf("\u001B[33m" + (char) 9209);
		System.out.printf("\u001B[34m" + (char) 9210);
		System.out.printf("\u001B[35m" + (char) 9210);
		System.out.printf("\u001B[36m" + (char) 9210);
		System.out.printf("\u001B[38;5;208m" + "sdadasd");
		System.out.printf("\u001B[90m" + "sdadasd");
		System.out.printf("\u001B[95m" + "sdadasd");
		System.out.printf("\u001B[92m" + "sdadasd");
	}
}
