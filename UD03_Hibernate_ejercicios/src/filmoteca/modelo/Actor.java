package filmoteca.modelo;
// Generated 26 nov 2024, 19:12:09 by Hibernate Tools 5.4.33.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Actor generated by hbm2java
 */
public class Actor implements java.io.Serializable {

	private Integer codigo;
	private String nombre;
	private String nacionalidad;
	private Date fechaNacimiento;
	private Date fechaFallecimiento;
	private Set personajes = new HashSet(0);

	public Actor() {
	}

	public Actor(String nombre, String nacionalidad, Date fechaNacimiento) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Actor(String nombre, String nacionalidad, Date fechaNacimiento, Date fechaFallecimiento, Set personajes) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaFallecimiento = fechaFallecimiento;
		this.personajes = personajes;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaFallecimiento() {
		return this.fechaFallecimiento;
	}

	public void setFechaFallecimiento(Date fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}

	public Set getPersonajes() {
		return this.personajes;
	}

	public void setPersonajes(Set personajes) {
		this.personajes = personajes;
	}

	@Override
	public String toString() {
		return "Actor [codigo=" + codigo + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaFallecimiento=" + fechaFallecimiento + "]";
	}
	
	

}
