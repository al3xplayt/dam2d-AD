package filmoteca.modelo;
// Generated 26 nov 2024, 19:12:09 by Hibernate Tools 5.4.33.Final

/**
 * PersonajeId generated by hbm2java
 */
public class PersonajeId implements java.io.Serializable {

	private int codigoPelicula;
	private int codigoActor;

	public PersonajeId() {
	}

	public PersonajeId(int codigoPelicula, int codigoActor) {
		this.codigoPelicula = codigoPelicula;
		this.codigoActor = codigoActor;
	}

	public int getCodigoPelicula() {
		return this.codigoPelicula;
	}

	public void setCodigoPelicula(int codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}

	public int getCodigoActor() {
		return this.codigoActor;
	}

	public void setCodigoActor(int codigoActor) {
		this.codigoActor = codigoActor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PersonajeId))
			return false;
		PersonajeId castOther = (PersonajeId) other;

		return (this.getCodigoPelicula() == castOther.getCodigoPelicula())
				&& (this.getCodigoActor() == castOther.getCodigoActor());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoPelicula();
		result = 37 * result + this.getCodigoActor();
		return result;
	}

}
