package plantilla.modelo;

import java.io.Serializable;

public class Empleado implements Serializable{
	//Número de versión de la clase
	// Verifica que el escritor y el lector de objetos sean compatibles
	private static final long serialVersionUID = 1L;
	
	private int codigo; //Código del empleado
	private String nombre;
	private String apellidos;
	private String fechaAlta;
	private String departamento;
	private double salario; //Salario mensual en euros
	public Empleado(int codigo, String nombre, String apellidos, String fechaAlta, String departamento,
			double salario) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaAlta = fechaAlta;
		this.departamento = departamento;
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaAlta="
				+ fechaAlta + ", departamento=" + departamento + ", salario=" + salario + "]";
	}
	
	
}
