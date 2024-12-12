package libreria.modelo;
// Generated 11 nov 2024, 17:29:00 by Hibernate Tools 5.4.33.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Tienda generated by hbm2java
 */
public class Tienda implements java.io.Serializable {

	private Integer codigo;
	private String centroComercial;
	private String direccion;
	private String localidad;
	private String telefono;
	private Set disponibilidads = new HashSet(0);

	public Tienda() {
	}

	public Tienda(String direccion, String localidad, String telefono) {
		this.direccion = direccion;
		this.localidad = localidad;
		this.telefono = telefono;
	}

	public Tienda(String centroComercial, String direccion, String localidad, String telefono, Set disponibilidads) {
		this.centroComercial = centroComercial;
		this.direccion = direccion;
		this.localidad = localidad;
		this.telefono = telefono;
		this.disponibilidads = disponibilidads;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCentroComercial() {
		return this.centroComercial;
	}

	public void setCentroComercial(String centroComercial) {
		this.centroComercial = centroComercial;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set getDisponibilidads() {
		return this.disponibilidads;
	}

	public void setDisponibilidads(Set disponibilidads) {
		this.disponibilidads = disponibilidads;
	}

}
