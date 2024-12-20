package libreria.modelo;
// Generated 11 nov 2024, 17:29:00 by Hibernate Tools 5.4.33.Final

import java.util.Date;

/**
 * Disponibilidad generated by hbm2java
 */
public class Disponibilidad implements java.io.Serializable {

	private DisponibilidadId id;
	private Libro libro;
	private Tienda tienda;
	private int cantidad;
	private Date fechaUltimaReposicion;

	public Disponibilidad() {
	}

	public Disponibilidad(DisponibilidadId id, Libro libro, Tienda tienda, int cantidad, Date fechaUltimaReposicion) {
		this.id = id;
		this.libro = libro;
		this.tienda = tienda;
		this.cantidad = cantidad;
		this.fechaUltimaReposicion = fechaUltimaReposicion;
	}

	public DisponibilidadId getId() {
		return this.id;
	}

	public void setId(DisponibilidadId id) {
		this.id = id;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Tienda getTienda() {
		return this.tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaUltimaReposicion() {
		return this.fechaUltimaReposicion;
	}

	public void setFechaUltimaReposicion(Date fechaUltimaReposicion) {
		this.fechaUltimaReposicion = fechaUltimaReposicion;
	}

}
