package musica.modelo;


public class Lista {

	private int codigo;
	private String nombre;
	private Fecha fechaCreacion;
	private int cantidad;
	private Duracion duracionTotal;
	
	public Lista(int codigo, String nombre, String fechaCreacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = new Fecha(fechaCreacion);
	}

	public Lista(int codigo, String nombre, String fechaCreacion, 
	             int cantidad, String duracionTotal) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = new Fecha(fechaCreacion);
		this.cantidad = cantidad;
		this.duracionTotal = new Duracion(duracionTotal);
	}

	@Override
	public String toString() {
		String cadenaDuracionTotal = "";
		if (duracionTotal != null) {
			cadenaDuracionTotal = ", Duraci�nTotal = " + duracionTotal.toString();
		}
		return 
			"Lista [C�digo = " + codigo + 
			", Nombre = " + nombre + 
			", FechaCreaci�n = " + fechaCreacion.toString() + 
			", Cantidad = " + cantidad + 
			cadenaDuracionTotal + "]";
	}

	public int getCodigo() {
		return codigo;
	}
	
}
