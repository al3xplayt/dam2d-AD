package alumnos.modelo;

public class Alumno {
	private int codigo;
	private String nombre, apellido, fechaNacimiento, grupo;
	private double nota1, nota2;
	private static final String separador = ";";
	public static int poblacion= 0;
	
	public Alumno(int codigo, String nombre, String apellido, String fechaNacimiento, String grupo, double nota1,
			double nota2) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.grupo = grupo;
		this.nota1 = nota1;
		this.nota2 = nota2;
	}
	
	//1;Carlos;Vázquez;31/10/1999;DAM1A;3,36;8,06
	public Alumno (String linea) {
		String [] atribute = linea.split(separador);
		this.codigo = Integer.parseInt(atribute[0]);
		this.nombre = atribute[1];
		this.apellido = atribute[2];
		this.fechaNacimiento = atribute[3];
		this.grupo = atribute[4];
		this.nota1 = Double.parseDouble(atribute[5].replace(',', '.'));
		this.nota2 = Double.parseDouble(atribute[6].replace(',', '.'));
	}
	
	@Override
	public String toString() {
		return "Alumno [codigo=" + codigo + 
				", nombre=" + nombre + ", apellido=" + apellido + 
				", fechaNacimiento=" + fechaNacimiento + ", grupo=" + grupo +
				", nota1=" + String.format("%.2f", nota1)  + ", nota2=" + String.format("%.2f", nota2)
				+ "]";
	}
	
	public String toCSV() {
		return codigo + separador + nombre + separador + apellido + separador + fechaNacimiento + separador + grupo
				+ separador + String.format("%.2f", nota1) + separador + String.format("%.2f", nota2);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		codigo = poblacion;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public double getNota1() {
		return nota1;
	}

	public double getNota2() {
		return nota2;
	}

}
