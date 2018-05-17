package concurso;

import java.util.ArrayList;
import java.util.Random;

public class Concurso {
	
	ArrayList<Jugador> participantes=new ArrayList<Jugador>();
	ArrayList<Jugador> ganadores=new ArrayList<Jugador>();

	Concurso(){
		Pablo pablo = new Pablo();
		Salva salva = new Salva();
		Ruben ruben = new Ruben();
		Lolo lolo = new Lolo();
		Jaime jaime = new Jaime();
		Maria maria = new Maria();
		Nicolas nicolas = new Nicolas();
		Migue migue = new Migue();
		AleD aleD = new AleD();
		AleS aleS = new AleS();
		Esther esther = new Esther();
		Lidia lidia = new Lidia();
		Ismael ismael  = new Ismael();
		Adri adri  = new Adri();
		Alvaro alvaro = new Alvaro();
		participantes.add(pablo);
		participantes.add (salva);
		participantes.add (migue);
		participantes.add (aleD);
		participantes.add (aleS);
		participantes.add (ruben);
		participantes.add (jaime);
		participantes.add (adri);
		participantes.add (ismael);
		participantes.add (esther);
		participantes.add (lidia);
		participantes.add (alvaro);
		participantes.add (lolo);
		participantes.add (maria);
		participantes.add (nicolas);

	}
	
	public void comprobarGanador() {
		Combate combate;
		Random rnd=new Random();
		int numero, i;
		final byte NUMRONDAS=5;
		Jugador jugador1, jugador2;
		
		numero = rnd.nextInt(participantes.size());
		jugador1 = participantes.get(numero);
		participantes.remove(numero);
		
		numero = rnd.nextInt(participantes.size());
		jugador2 = participantes.get(numero);
		participantes.remove(numero);
		
		combate = new Combate(jugador1, jugador2);
		
		ganadores.add(combate.comprobarGanadorCombate(NUMRONDAS));
		
		if (participantes.isEmpty()) {
			for (i = 0; i < ganadores.size(); i++) {
				participantes.add(ganadores.get(i));
			}
			ganadores.clear();
		} else if (participantes.size()==1 ){
			ganadores.add(participantes.get(0));
		}
		
	}
	
}
