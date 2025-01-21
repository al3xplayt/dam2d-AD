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

public class AccesoJugador {

	public static boolean insertarJugador(Jugador jugador, String nombreEquipo) {
		EntityManagerFactory emf = null;
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		Equipo equipo = null;
		boolean insertado = false;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			// Inicio transaccion
			transaccion = conexion.getTransaction();
			transaccion.begin();
			equipo = conexion.find(Equipo.class, nombreEquipo);
			if (equipo == null) {
				return insertado;
			}
			// Almaceno los objetos (persist)
			equipo.insertar(jugador);
			conexion.persist(jugador);

			// Confirm
			transaccion.commit();
			insertado = true;
		} catch (Exception e) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return insertado;

	}

	// SELECT J FROM Jugador J, Equipo E WHERE E.nombre = :nombreEquipo AND
	// e.jugadores.contains(j)
	public static List<Jugador> consultarJugadorPorEquipo(String nombreEquipo) {
		EntityManager conexion = null;
		List<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			conexion = ObjectDBUtil.abrirConexion();
			// Inicio transaccion
			String consulta = "SELECT J FROM Jugador J " + "WHERE J.equipo.nombre = ?1";
			TypedQuery<Jugador> query = conexion.createQuery(consulta, Jugador.class);
			query.setParameter(1, nombreEquipo);
			jugadores = query.getResultList();

		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return jugadores;
	}

	public static Jugador consultarJugadorPorCodigo(int codigo) {
		// TODO Auto-generated method stub
		EntityManager conexion = null;
		Jugador jugador = null;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			jugador = conexion.find(Jugador.class, codigo);
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return jugador;
	}

	public static boolean actualizarJugadorPorCodigo(int codigo, Jugador nuevoJugador) {
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		boolean actualizado = false;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			Jugador jugador = conexion.find(Jugador.class, codigo);
			if (jugador != null) {
				transaccion = conexion.getTransaction();
				transaccion.begin();
				// Actualizo el objeto
				jugador.setNombre(nuevoJugador.getNombre());
				jugador.setFechaNacimiento(nuevoJugador.getFechaNacimiento());
				transaccion.commit();
				actualizado = true;
			}
		} catch (Exception e) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return actualizado;

	}

	public static boolean actualizarJugadorPorCodigoV2(int codigo, Jugador nuevoJugador) {
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		boolean actualizado = false;
		int filas = 0;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			Jugador jugador = conexion.find(Jugador.class, codigo);
			String jpql = "UPDATE Jugador J SET J.nombre = ?1 WHERE J.codigo = ?2";
			Query query = conexion.createQuery(jpql);
			query.setParameter(1, nuevoJugador.getNombre());

			query.setParameter(2, codigo);
			transaccion = conexion.getTransaction();
			transaccion.begin();
			filas = query.executeUpdate();
			transaccion.commit();
		} catch (Exception e) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return filas > 0;
	}

	public static boolean eliminarJugadorPorCodigo(int codigo) {
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		boolean eliminado = false;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			Jugador jugador = conexion.find(Jugador.class, codigo);
			if (jugador != null) {
				transaccion = conexion.getTransaction();
				transaccion.begin();
				// Elimino el objeto
				Equipo equipo = jugador.getEquipo();
				equipo.eliminarJugador(jugador);
				conexion.remove(jugador);
				transaccion.commit();
				eliminado = true;
			}
		} catch (Exception e) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return eliminado;
	}
	public static int eliminarJugadorPorCodigoV2(int codigo) {
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		
		int filas = 0;
		try {
			conexion = ObjectDBUtil.abrirConexion();
			Jugador jugador = conexion.find(Jugador.class, codigo);
			if (jugador != null) {
				transaccion = conexion.getTransaction();
				transaccion.begin();
				// Elimino el objeto
				String jpql = "DELETE FROM Jugador J WHERE J.codigo = ?1";
				TypedQuery<Jugador> query = conexion.createQuery(jpql, Jugador.class);
				query.setParameter(1, codigo);
				filas = query.executeUpdate();
				Equipo equipo = jugador.getEquipo();
				equipo.eliminarJugador(jugador);
				conexion.remove(jugador);
				transaccion.commit();
			}
		} catch (Exception e) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw e;
		} finally {
			ObjectDBUtil.cerrarConexion(conexion);
		}
		return filas;
	}
}
