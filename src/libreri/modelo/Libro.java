package libreri.modelo;

public class Libro {
	private int codigo,codigoAutor, agnoPublicacion, numeroPaginas;
	private double precio;
	private String titulo;
	private Escritor escritor;
	public Libro(int codigo, Escritor codigoAutor, int agnoPublicacion, int numeroPaginas, double precio, String titulo) {
		super();
		this.codigo = codigo;
		this.escritor = codigoAutor;
		this.agnoPublicacion = agnoPublicacion;
		this.numeroPaginas = numeroPaginas;
		this.precio = precio;
		this.titulo = titulo;
	}
	public Libro(int codigoLibro) {
		// TODO Auto-generated constructor stub
		codigo = codigoLibro;
	}
	public Libro(String titulo2, int agnoPublicacion2) {
		this.titulo = titulo2;
		this.agnoPublicacion = agnoPublicacion2;
	}
	
	
	
	public Libro(int codigoLibro, int codigoAutor, int agnoPublicacion2, int numeroPaginas2, double precio2,
			String titulo2) {
		super();
		this.codigo = codigoLibro;
		this.codigoAutor = codigoAutor;
		this.agnoPublicacion = agnoPublicacion2;
		this.numeroPaginas = numeroPaginas2;
		this.precio = precio2;
		this.titulo = titulo2;
	}
	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", codigoAutor=" + escritor.getCodigo() + ", agnoPublicacion=" + agnoPublicacion
				+ ", numeroPaginas=" + numeroPaginas + ", precio=" + precio + ", titulo=" + titulo + "]";
	}
	public int getCodigo() {
		return codigo;
	}
	public int getCodigoAutor() {
		return escritor.getCodigo();
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
