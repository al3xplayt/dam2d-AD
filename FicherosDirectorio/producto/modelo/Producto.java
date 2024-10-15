package producto.modelo;

public class Producto {
	/* Código Nombre Categoría Fecha Modificación Cantidad Precio
1 Zapatillas Calzado 20/02/2011 8 47,23*/
	public static final String RUTA = "dataBin/producto.dat";
	
	//Longitudes de los campos
	public static final int LONGITUD_NOMBRE = 30;
	public static final int LONGITUD_CATEGORIA = 20;
	public static final int LONGITUD_FECHA = 10;
	
	//Tamaño del registro
	public static final int tamagnoRegistro = LONGITUD_NOMBRE * 2 
			+ LONGITUD_CATEGORIA * 2 + LONGITUD_FECHA * 2 + 16;
	
	private int codigo;
	private String nombre, categoria, fechaModificacion;
	private int cantidad;
	private double precio;
	
	public Producto(int codigo, String nombre, String categoria, String fechaModificacion, int cantidad,
			double precio) {
		super();
		this.codigo = codigo;
		StringBuffer sb = new StringBuffer(nombre);
		sb.setLength(LONGITUD_NOMBRE);
		this.nombre = sb.toString();
		sb = new StringBuffer(categoria);
		sb.setLength(LONGITUD_CATEGORIA);
		this.categoria = sb.toString();
		sb = new StringBuffer(fechaModificacion);
		sb.setLength(LONGITUD_FECHA);
		this.fechaModificacion = sb.toString();
		this.cantidad = cantidad;
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre.trim() + ", categoria=" + categoria.trim() + ", fechaModificacion="
				+ fechaModificacion.trim() + ", cantidad=" + cantidad + ", precio=" + String.format("%.2f", precio) + "]";
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	
}
