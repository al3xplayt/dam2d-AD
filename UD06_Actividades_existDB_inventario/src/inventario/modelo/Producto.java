package inventario.modelo;

public class Producto {
	private int codigo;
	private String denominacion;
	private double precio;
	private int stockActual;
	private int stockMinimo;
	private int codigoZona;

	public Producto(int codigo, String denominacion, double precio, int stockActual, int stockMinimo, int codigoZona) {
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.precio = precio;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.codigoZona = codigoZona;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", denominacion=" + denominacion + ", precio=" + precio + ", stockActual="
				+ stockActual + ", stockMinimo=" + stockMinimo + ", codigoZona=" + codigoZona + "]";
	}

}
