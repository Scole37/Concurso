package concurso;

public class CodigoComprobarByN {
	
	public byte[] comprobarByN(byte combinacionOculta[],byte combinacionPropuesta[]){
		byte negrasBlancas[] = {0,0};
		byte contadorNegras = 0;
		byte contadorBlancas = 0;
		final byte NUM_CASILLAS = 8;
		boolean aparece;
		boolean comprobadaOculta[] = new boolean[NUM_CASILLAS];
		for (byte i=0;i<comprobadaOculta.length;i++)	//---- Esto sirve para que las casillas
			comprobadaOculta[i]=false;					// comprobadas no se vuelvan a comprobar.
		for (byte i=0;i<combinacionPropuesta.length;i++) {									//---- Compara las casillas de la
			aparece=false;																		// combinación propuesta por
			for (byte j=0;j<combinacionOculta.length&&!aparece;j++) {							// las casillas una a una de
				if (combinacionPropuesta[i]==combinacionOculta[j]&&!comprobadaOculta[j]) {		// la combinación original.
					if (combinacionPropuesta[i]==combinacionOculta[i]&&!comprobadaOculta[i]) {	// Al hacer esto se meten las
						contadorNegras++;														// casillas comprobadas de la
						comprobadaOculta[i]=true;												// combinación original en un
					}else if (combinacionPropuesta[j]==combinacionOculta[j]) {					// array para marcarlas y que
						contadorNegras++;														// no se comparen 2 veces. La
						comprobadaOculta[j]=true;												// ccombinación propuesta no
					}else {																		// lo necesita porque se
						contadorBlancas++;														// recorre de uno en uno. 
						comprobadaOculta[j]=true;												// Si el color de ficha de
					}																			// las 2 casillas es la misma,
					aparece=true;																// según la posición de ambas
				}																				// se marcará como ficha negra
			}																					// o ficha blanca.
		}
		negrasBlancas[0]=contadorNegras;
		negrasBlancas[1]=contadorBlancas;
		return negrasBlancas;
	}

}
