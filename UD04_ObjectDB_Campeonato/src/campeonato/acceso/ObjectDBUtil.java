package campeonato.acceso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtil {
	
	private static final String NOMBRE_FICH = "data/campeonato.odb";
	
	public static EntityManager abrirConexion() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(NOMBRE_FICH);
		EntityManager conexion = emf.createEntityManager();
		return conexion;
	}
	
	public static void cerrarConexion(EntityManager conexion) {
		if (conexion != null && conexion.isOpen()) {
			conexion.close();
		}
		EntityManagerFactory emf = conexion.getEntityManagerFactory();
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
}
