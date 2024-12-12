package musica.modelo;


public class Musico {

	private int codigo;
	private String nombre;
	private String nacionalidad;
	private Fecha fechaNacimiento;
	private Fecha fechaFallecimiento;
	
	public Musico(int codigo, String nombre, String nacionalidad, 
	              String fechaNacimiento, String fechaFallecimiento) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = new Fecha(fechaNacimiento);
		if (fechaFallecimiento == null) {
			this.fechaFallecimiento = null;
		}
		else {
			this.fechaFallecimiento = new Fecha(fechaFallecimiento);
		}
	}
	
	@Override
	public String toString() {
		String cadenaFechaFallecimiento = "";
		if (fechaFallecimiento != null) {
			cadenaFechaFallecimiento = ", FechaFallecimiento = " + fechaFallecimiento.toString();
		}
		return 
			"Músico [Código = " + codigo + 
			", Nombre = " + nombre + 
			", Nacionalidad = " + nacionalidad + 
			", FechaNacimiento = " + fechaNacimiento.toString() + 
			cadenaFechaFallecimiento + "]";
	}
	
	public int getCodigo() {
		return codigo;
	}
	
}
