package libreri.modelo;

public class Disponibilidad {
	private Libro libro;
	private Tienda tienda;
	private int cantidad;
	private Fecha ultimaReposicion;
	public Disponibilidad(Libro libro, Tienda tienda, int cantidad, Fecha ultimaReposicion) {
		super();
		this.libro = libro;
		this.tienda = tienda;
		this.cantidad = cantidad;
		this.ultimaReposicion = ultimaReposicion;
	}
	@Override
	public String toString() {
		return "Disponibilidd [libro=" + libro.getCodigo() + ", tienda=" + tienda.getCodigo() + ", cantidad=" + cantidad + ", ultimaReposicion="
				+ ultimaReposicion.toString() + "]";
	}

	
	
}
