package ejemplos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Partido;

public class ActualizarPartido {
	public static void main(String[] args) {
		EntityManagerFactory fabricaConexiones = null;
		EntityManager conexion = null;
		EntityTransaction transaccion = null;
		try {
			boolean actualizado = false;
			fabricaConexiones = Persistence.createEntityManagerFactory("data/campeonato.odb");
			conexion = fabricaConexiones.createEntityManager();
			conexion = fabricaConexiones.createEntityManager();
			String sentenciaJPQL = "SELECT p FROM Partido p " +
					"WHERE p.equipoLocal.nombre = 'Azul' " +
					"AND p.equipoVisitante.nombre = 'Amarillo'"+
					"AND p.fecha = '04/06/2022'";
			TypedQuery<Partido> consulta = conexion.createQuery(sentenciaJPQL, Partido.class);
			List<Partido> listaPartidos = consulta.getResultList();
			Partido p = listaPartidos.get(0);
			System.out.println("Se actualizará el partido: " + p.toString());
			if (p != null) {
				transaccion = conexion.getTransaction();
				transaccion.begin(); 
				//Al actualizar los atributos y hacer el commit se almacena de manera persistente en la base de datos.
				p.setResultado("5-3");
				transaccion.commit();
				actualizado = true;
			}

			if(actualizado) {
				System.out.println("Se han actualizado el partido: " + p.toString());
			}else {
				System.err.println("No se ha podido actualizar el partido.");
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
