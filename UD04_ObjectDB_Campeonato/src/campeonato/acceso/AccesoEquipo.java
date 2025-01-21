package campeonato.acceso;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import campeonato.modelo.Equipo;
import campeonato.modelo.Jugador;

public class AccesoEquipo {

	public static List<Equipo> consultarTodos() {
		// TODO Auto-generated method stub
		EntityManager conexion = null;
		List<Equipo> equipos = new ArrayList<Equipo>();
		try {
			conexion = ObjectDBUtil.abrirConexion();
			// Inicio transaccion
			String consulta = "SELECT E FROM Equipo E";
			TypedQuery<Equipo> query = conexion.createQuery(consulta, Equipo.class);
			equipos = query.getResultList();

		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}

		return equipos;
	}

	public static int eliminarEquipo(String nombreEquipo) {
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		List<Jugador> jugadores = new ArrayList<>();
		Equipo equipo = null;
		int eliminados = 0;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			equipo = conexion.find(Equipo.class, nombreEquipo);
			// Inicio transaccion
			if (equipo != null) {
				transaccion = conexion.getTransaction();
				transaccion.begin();
				equipo.desvincularJugadores();
				conexion.remove(equipo);
				transaccion.commit();
				eliminados++;
			}
			
		} catch (Exception e) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		// TODO Auto-generated method stub
		return eliminados;
	}

	public static void insertarEquipo(Equipo equipo) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = null;
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			// Inicio transaccion
			transaccion = conexion.getTransaction();
			transaccion.begin();

			// Almaceno los objetos (persist)
			conexion.persist(equipo);

			// Confirm
			transaccion.commit();
		} catch (Exception e) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
	}

	public static Equipo consultarEquipoPorNombre(String nombreEquipo) {
		// TODO Auto-generated method stub
		EntityManager conexion = null;
		Equipo equipo = null;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			// Inicio transaccion
			String consulta = "SELECT E FROM Equipo E WHERE E.nombre = ?1";
			TypedQuery<Equipo> query = conexion.createQuery(consulta, Equipo.class);
			query.setParameter(1, nombreEquipo);
			equipo = query.getSingleResult();
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return null;
	}

}
