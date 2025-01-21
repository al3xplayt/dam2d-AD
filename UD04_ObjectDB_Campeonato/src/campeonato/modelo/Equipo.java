package campeonato.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Equipo {

	@Id
	private String nombre;
	private String ciudad;
	@OneToMany(fetch = FetchType.EAGER)
	// Para el borrado en cascada cascade = CascadeType.REMOVE)
            
	private List<Jugador> jugadores;
	
	public Equipo(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.jugadores = new LinkedList<Jugador>();
	}

	public void insertar(Jugador jugador) {
		jugadores.add(jugador);
		jugador.setEquipo(this);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	public void eliminarJugador(Jugador jugador) {
		jugadores.remove(jugador);
		jugador.setEquipo(null);
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	@Override
	public String toString() {
		String cadenaJugadores = "";
		if (jugadores != null && !jugadores.isEmpty()) {
				for (int posicion = 0 ; posicion < this.jugadores.size() ; posicion++) {
					Jugador jugador = this.jugadores.get(posicion);
					cadenaJugadores += cadenaJugadores + "\n\t" + jugador.toString();					
				}	
		}
		
		return 
			"Equipo [Nombre = " + nombre + 
			", Ciudad = " + ciudad + 
			", Jugadores = " + cadenaJugadores + 
			"]";
	}

	public void desvincularJugadores() {
		for (Jugador jugador : jugadores) {
			jugador.setEquipo(null);
		}
	}
	
}
