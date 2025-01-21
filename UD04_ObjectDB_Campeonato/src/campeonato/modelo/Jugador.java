package campeonato.modelo;

import java.util.Random;

import javax.jdo.annotations.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Indico que es una entidad de mi base de datos
@Entity
public class Jugador {

	//GeneratedValue -> ID autoincremental
	//Id -> Indico que es la clave primaria
	@Id @GeneratedValue
	private int codigo;
	private String nombre;
	
	
	@Embedded private Fecha fechaNacimiento;
	private Equipo equipo;
	public Jugador(String nombre) {
		Random aleatorio = new Random();
		
		int agno = aleatorio.nextInt(35) + 1970;
		int mes = aleatorio.nextInt(12) + 1;
		int dia = aleatorio.nextInt(28) + 1;
		
		this.nombre = nombre;
		this.fechaNacimiento = new Fecha(agno, mes , dia);
	}
	
	public Jugador(String nombre, String fecha) {
		this.nombre = nombre;
		this.fechaNacimiento = new Fecha(fecha);
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Fecha fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Equipo getEquipo() {
        return equipo;
	}
	@Override
	public String toString() {
		return 
			"Jugador [Código = " + codigo +
			", Nombre = " + nombre + 
			", FechaNacimiento = " + fechaNacimiento + 
			", Equipo = " + equipo.getNombre()+ 
			"]";
	}
	
}
