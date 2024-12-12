package centroformacion.modelo;
// Generated 3 dic 2024, 19:02:01 by Hibernate Tools 5.4.33.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Alumno generated by hbm2java
 */
public class Alumno implements java.io.Serializable {

	private int codigo;
	private String nombre;
	private Date fechaNacimiento;
	private String domicilio;
	private String telefono;
	private String correo;
	private Set matriculas = new HashSet(0);

	public Alumno() {
	}

	public Alumno(int codigo, String nombre, Date fechaNacimiento, String domicilio, String telefono, String correo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.correo = correo;
	}

	public Alumno(int codigo, String nombre, Date fechaNacimiento, String domicilio, String telefono, String correo,
			Set matriculas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.correo = correo;
		this.matriculas = matriculas;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Set getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(Set matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public String toString() {
		return "Alumno [codigo=" + codigo + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento.toString()
				+ ", domicilio=" + domicilio + ", telefono=" + telefono + ", correo=" + correo + "]";
	}
	

}
