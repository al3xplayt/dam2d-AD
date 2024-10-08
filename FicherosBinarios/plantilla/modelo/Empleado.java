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
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getDepartamento() {
		// TODO Auto-generated method stub
		return null;
	}
	public double getSalario() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
