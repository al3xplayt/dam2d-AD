package libreri.modelo;

public class Tienda {
	private int codigo;
	private String centroComercial, direccion, localidad, telefono;

	public Tienda(int codigo, String centroComercial, String direccion, String localidad, String telefono) {
		super();
		this.codigo = codigo;
		this.centroComercial = centroComercial;
		this.direccion = direccion;
		this.localidad = localidad;
		this.telefono = telefono;
	}

	public Tienda(int codigoTienda) {
		// TODO Auto-generated constructor stub
		codigo = codigoTienda;
	}

	@Override
	public String toString() {
		if (centroComercial == null) {
			return "Tienda [codigo=" + codigo + ", direccion=" + direccion + ", localidad=" + localidad + ", telefono="
					+ telefono + "]";
		} else {
			return "Tienda [codigo=" + codigo + ", centroComercial=" + centroComercial + ", direccion=" + direccion
					+ ", localidad=" + localidad + ", telefono=" + telefono + "]";
		}
	}

	public int getCodigo() {
		return codigo;
	}

	public String getCentroComercial() {
		return centroComercial;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getTelefono() {
		return telefono;
	}

}
