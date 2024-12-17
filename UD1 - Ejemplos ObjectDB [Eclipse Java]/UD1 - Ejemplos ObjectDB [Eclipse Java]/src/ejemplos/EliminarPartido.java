package ejemplos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Partido;

public class EliminarPartido {
	public static void main(String[] args) {
		EntityManagerFactory fabricaConexiones = null;
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		try {
			boolean eliminado = false;
			fabricaConexiones = Persistence.createEntityManagerFactory("data/campeonato.odb");
			conexion = fabricaConexiones.createEntityManager();
			conexion = fabricaConexiones.createEntityManager();
			String sentenciaJPQL = "SELECT p FROM Partido p " +
					"WHERE p.equipoLocal.nombre = 'Rojo' " +
					"AND p.equipoVisitante.nombre = 'Verde'"+
					"AND p.fecha = '04/06/2022'";
			TypedQuery<Partido> consulta = conexion.createQuery(sentenciaJPQL, Partido.class);
			List<Partido> listaPartidos = consulta.getResultList();
			Partido p = listaPartidos.get(0);
			if (p != null) {
				transaccion = conexion.getTransaction();
				transaccion.begin(); 
				//Método para eliminar.
				conexion.remove(p);
				transaccion.commit();
				eliminado = true;
			}

			if(eliminado) {
				System.out.println("Se han eliminado el partido: " + p.toString());
			}
		}
		catch (Exception e) {
			if (transaccion != null) {
				transaccion.rollback();
			}
			throw e;
		}
		finally {
			if (conexion != null) {
				conexion.close();
			}
			if (fabricaConexiones != null) {
				fabricaConexiones.close();
			}
		}
	}
}
