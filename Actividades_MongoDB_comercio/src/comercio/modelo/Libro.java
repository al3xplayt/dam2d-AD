package comercio.modelo;

import org.bson.Document;

public class Libro {
	private int codigo;
	private String titulo;
	private String autor;
	private int agno;
	private String genero;
	private double precio;
	private int stock;

	public Libro(int codigo, String titulo, String autor, int agnoPublicacion, String genero, double precio) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.agno = agnoPublicacion;
		this.genero = genero;
		this.precio = precio;
	}

	public Libro(Document doc) {
		this.codigo = doc.getInteger("codigo");
		this.titulo = doc.getString("titulo");
		this.autor = doc.getString("autor");
		this.agno = doc.getInteger("agno");
		this.genero = doc.getString("genero");
		this.precio = doc.getDouble("precio");
		this.stock = doc.getInteger("stock");
	}
		

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", agnoPublicacion="
				+ agno + ", genero=" + genero + ", precio=" + precio + "]";
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public int getAgno() {
		return agno;
	}

	public String getGenero() {
		return genero;
	}

	public double getPrecio() {
		return precio;
	}

}