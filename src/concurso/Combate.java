package concurso;

public class Combate {

	// Variables
	private Jugador jugador1;
	private Jugador jugador2;
	private byte rondasGanadasJ1;
	private byte rondasGanadasJ2;
	
	// Constructor
	Combate(Jugador jugador1,Jugador jugador2) {
		this.jugador1=jugador1;
		this.jugador2=jugador2;
		rondasGanadasJ1=0;
		rondasGanadasJ2=0;
	}
	
	// Métodos
	protected Jugador comprobarGanadorCombate(byte maxRondas) {
		Jugador ganador=null;
		do {
			jugarPartida();
			if (rondasGanadasJ1==maxRondas)
				ganador=jugador1;
			else if (rondasGanadasJ2==maxRondas)
				ganador=jugador2;
		}while(ganador==null);
		return ganador;
	}
	private void jugarPartida() {
		byte ganador;
		Partida partida = new Partida(jugador1,jugador2);
		ganador=partida.partidaDificil();
		if (ganador==1)
			rondasGanadasJ1++;
		else if (ganador==2)
			rondasGanadasJ2++;
	}
}