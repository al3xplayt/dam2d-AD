package ejemplos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import modelo.Equipo;

public class ConsultarEquipoPorJugador {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		try {
			Equipo equipo = null;
			conexion = emf.createEntityManager();
			//.contains() -> El objeto contiene el jugador j además el jugador j tiene el código 1
			String sentenciaJPQL = "SELECT e FROM Equipo e, Jugador j " +
			                       "WHERE e.jugadores.contains(j) " + 
			                       "AND j.codigo = " + 1;
			TypedQuery<Equipo> consulta = conexion.createQuery(sentenciaJPQL, Equipo.class);
			List<Equipo> listaEquipos = consulta.getResultList();
			if (listaEquipos.size() > 0) {
				equipo = listaEquipos.get(0);
			}
			System.out.println(equipo.toString());
		}
		catch (PersistenceException e) {
			System.err.println("La base de datos data/campeonato.odb no existe.");
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
		}
	}
}
