package musica.modelo;


public class Duracion {

	private int minutos;
	private int segundos;
	
	/**
	 * Crea una duración a partir de unos minutos y unos segundos.
	 */
	public Duracion(int minutos, int segundos) {
		this.minutos = minutos;
		this.segundos = segundos;
	}
	
	/**
	 * Crea una duración a partir de una cadena con formato mm:ss.
	 */
	public Duracion(String cadena) {
		String[] datos = cadena.split(":");
		this.minutos = Integer.parseInt(datos[0]);
		this.segundos = Integer.parseInt(datos[1]);
	}
	
	/**
	 * Devuelve una cadena con el estado de la duración con formato mm:ss. 
	 */
	@Override
	public String toString() {
		String duracion = String.format("%02d:%02d", minutos, segundos);
		return duracion;
	}
	
}
