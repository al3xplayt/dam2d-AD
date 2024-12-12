package musica.modelo;


public class Album {

	private int codigo;
	private Musico musico;
	private String titulo;
	private String tipo;
	private int agnoPublicacion;
	private double puntuacion;
	
	public Album(int codigo, Musico musico, String titulo, 
	             String tipo, int agnoPublicacion, double puntuacion) {
		this.codigo = codigo;
		this.musico = musico;
		this.titulo = titulo;
		this.tipo = tipo;
		this.agnoPublicacion = agnoPublicacion;
		this.puntuacion = puntuacion;
	}

	@Override
	public String toString() {
		return 
			"Álbum [Código = " + codigo + 
			", CódigoMúsico = " + musico.getCodigo() + 
			", Título = " + titulo + 
			", Tipo = " + tipo + 
			", AñoPublicación = " + agnoPublicacion + 
			", Puntuación = " + String.format("%.1f", puntuacion) + "]";
	}

	public int getCodigo() {
		return codigo;
	}
	
}
