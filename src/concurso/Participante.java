package concurso;


public abstract class Participante {
	
	byte[] resultado;
	
	public void setResultado(byte[] a) {
		this.resultado = a;
	}
	
	public abstract byte[] crearCombPropuesta();

	public abstract byte[] crearCombSecreta();
}
