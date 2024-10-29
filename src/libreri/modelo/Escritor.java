package libreri.modelo;

public class Escritor {
	private String nombre, nacionalidad;
	private int codigo;
	private Fecha fechaNacimiento, fecchaFallecimiento;

	public Escritor(String nombre, String nacionalidad, int codigo, String fechaNacimiento,
			String fecchaFallecimiento) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.codigo = codigo;
		this.fechaNacimiento = new Fecha(fechaNacimiento);
		if (fecchaFallecimiento != null) {
			this.fecchaFallecimiento = new Fecha(fecchaFallecimiento);
		} else {
			this.fecchaFallecimiento = null;
		}

	}
	

	public Escritor(int codigo) {
		super();
		this.codigo = codigo;
	}


	@Override
	public String toString() {
		if (this.fecchaFallecimiento == null) {
			return "Escritor [nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", codigo=" + codigo
					+ ", fechaNacimiento=" + fechaNacimiento + "]";
		} else {
			return "Escritor [nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", codigo=" + codigo
					+ ", fechaNacimiento=" + fechaNacimiento + ", fecchaFallecimiento=" + fecchaFallecimiento + "]";
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
		return fecchaFallecimiento;
	}

}
