package campeonato.modelo;

import javax.jdo.annotations.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Partido {

	//Id compuesto por tres atributos.
	@Id
	private Equipo equipoLocal;
	@Id
	private Equipo equipoVisitante;
	@Id  @Embedded 
	private Fecha fecha;
	@Embedded private Resultado resultado;
	
	public Partido(Equipo equipoLocal, Equipo equipoVisitante, 
	               String fecha, String resultado) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fecha = new Fecha(fecha);
		this.resultado = new Resultado(resultado);
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}



	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return 
			"Partido [NombreEquipoLocal = " + equipoLocal.getNombre() + 
			", NombreEquipoVisitante = " + equipoVisitante.getNombre() + 
			", Fecha = " + fecha.toString() + 
			", Resultado = " + resultado.toString() + 
			"]";
	}
	
}
