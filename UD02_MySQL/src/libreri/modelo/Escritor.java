package libreri.modelo;

public class Escritor {
	private String nombre, nacionalidad;
	private int codigo;
	private Fecha fechaNacimiento, fechaFallecimiento;

	public Escritor(String nombre, String nacionalidad, int codigo, String fechaNacimiento,
			String fechaFallecimiento) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.codigo = codigo;
		this.fechaNacimiento = new Fecha(fechaNacimiento);
		if (fechaFallecimiento != null) {
			this.fechaFallecimiento = new Fecha(fechaFallecimiento);
		} else {
			this.fechaFallecimiento = null;
		}

	}
	

	public Escritor(int codigo) {
		super();
		this.codigo = codigo;
	}


	@Override
	public String toString() {
		if (this.fechaFallecimiento == null) {
			return "Escritor [nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", codigo=" + codigo
					+ ", fechaNacimiento=" + fechaNacimiento + "]";
		} else {
			return "Escritor [nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", codigo=" + codigo
					+ ", fechaNacimiento=" + fechaNacimiento + ", fecchaFallecimiento=" + fechaFallecimiento + "]";
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public int getCodigo() {
		return codigo;
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Fecha getFecchaFallecimiento() {
		return fechaFallecimiento;
	}

}
