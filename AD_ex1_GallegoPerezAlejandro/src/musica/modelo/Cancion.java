package musica.modelo;


public class Cancion {

	private int codigo;
	private Album album;
	private int posicion;
	private String titulo;
	private Duracion duracion;
	
	public Cancion(int codigo, Album album, int posicion, 
	               String titulo, String duracion) {
		this.codigo = codigo;
		this.album = album;
		this.posicion = posicion;
		this.titulo = titulo;
		this.duracion = new Duracion(duracion);
	}

	@Override
	public String toString() {
		return 
			"Canci�n [C�digo = " + codigo + 
			", C�digo�lbum = " + album.getCodigo() + 
			", Posici�n = " + posicion + 
			", T�tulo = " + titulo + 
			", Duraci�n = " + duracion.toString() + "]";
	}

	public int getCodigo() {
		return codigo;
	}

	public int getPosicion() {
		return posicion;
	}

	public String getTitulo() {
		return titulo;
	}

	public Duracion getDuracion() {
		return duracion;
	}
	
}
