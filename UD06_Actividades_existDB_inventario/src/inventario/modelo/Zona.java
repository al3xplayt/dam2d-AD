package inventario.modelo;

public class Zona {
	private int codigo;
	private String nombre;
	private String director;
	public Zona(int codigo, String nombre, String director) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.director = director;
	}

	public Zona(String elemento) {
		this.codigo = Integer.parseInt(XMLUtil.extraerTexto(elemento, "cod_zona"));
		this.nombre = XMLUtil.extraerTexto(elemento, "nombre");
		this.director = XMLUtil.extraerTexto(elemento, "director");
	}

	@Override
	public String toString() {
		return "Zona [Codigo=" + codigo + ", Nombre=" + nombre + ", Director=" + director + "]";
	}
	
}
