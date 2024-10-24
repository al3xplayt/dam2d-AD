package libreri.modelo;

public class Libro {
	int codigo, codigoAutor, agnoPublicacion, numeroPaginas;
	double precio;
	String titulo;
	public Libro(int codigo, int codigoAutor, int agnoPublicacion, int numeroPaginas, double precio, String titulo) {
		super();
		this.codigo = codigo;
		this.codigoAutor = codigoAutor;
		this.agnoPublicacion = agnoPublicacion;
		this.numeroPaginas = numeroPaginas;
		this.precio = precio;
		this.titulo = titulo;
	}
	public Libro(int codigoLibro) {
		// TODO Auto-generated constructor stub
		codigo = codigoLibro;
	}
	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", codigoAutor=" + codigoAutor + ", agnoPublicacion=" + agnoPublicacion
				+ ", numeroPaginas=" + numeroPaginas + ", precio=" + precio + ", titulo=" + titulo + "]";
	}
	public int getCodigo() {
		return codigo;
	}
	public int getCodigoAutor() {
		return codigoAutor;
	}
	public int getAgnoPublicacion() {
		return agnoPublicacion;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	public double getPrecio() {
		return precio;
	}
	public String getTitulo() {
		return titulo;
	}
	
}
