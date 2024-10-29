package libreri.modelo;

public class Disponibilidad {
	private Libro libro;
	private Tienda tienda;
	private int cantidad, codlib, codtienda;
	private Fecha ultimaReposicion;
	public Disponibilidad(Libro libro, Tienda tienda, int cantidad, String ultimaReposicion) {
		super();
		this.libro = libro;
		this.tienda = tienda;
		this.cantidad = cantidad;
		this.ultimaReposicion = new Fecha(ultimaReposicion);
	}
	public Disponibilidad(int libro2, int tienda2, int cantidad2, String fecha) {
		// TODO Auto-generated constructor stub
		this.codlib = libro2;
		this.codtienda = tienda2;
		this.cantidad = cantidad2;
		this.ultimaReposicion = new Fecha(fecha);
	}
	@Override
	public String toString() {
		return "Disponibilidd [libro=" + libro.getCodigo() + ", tienda=" + tienda.getCodigo() + ", cantidad=" + cantidad + ", ultimaReposicion="
				+ ultimaReposicion.toString() + "]";
	}

	
	
}
