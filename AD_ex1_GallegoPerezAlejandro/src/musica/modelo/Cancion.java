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
			"Canción [Código = " + codigo + 
			", CódigoÁlbum = " + album.getCodigo() + 
			", Posición = " + posicion + 
			", Título = " + titulo + 
			", Duración = " + duracion.toString() + "]";
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
