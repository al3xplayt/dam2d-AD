package ejemplos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import modelo.Equipo;

public class ConsultarEquipoPorCodigo {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("data/campeonato.odb");
		EntityManager conexion = null;
		try {
			conexion = emf.createEntityManager();
			Equipo equipo = conexion.find(Equipo.class, "Azul");
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
