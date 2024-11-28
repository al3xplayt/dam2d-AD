package filmoteca.acceso;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import filmoteca.modelo.Pelicula;
public class AccesoPeliculas {
	public static List<Pelicula> consultarPeliculas() {
		Session sesion = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			String hql = "SELECT pel FROM Pelicula AS pel ORDER BY titulo ASC";
			Query<Pelicula> consulta = sesion.createQuery(hql, Pelicula.class);
			List<Pelicula> listaPeliculas = consulta.list();
			return listaPeliculas;
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		
	}
	
	public static void verPeliculas(List<Pelicula> listaPeliculas) {
		for (Pelicula pel : listaPeliculas) {
			System.out.println(pel.toString());
		}
	}

	public static List<Pelicula> consultarPeliculasEntreAnios(int anio1, int anio2) {
		Session sesion = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			String hql = "SELECT pel FROM Pelicula AS pel "
					+ " WHERE agnoEstreno >= ?1 AND agnoEstreno <= ?2 "
					+ " ORDER BY recaudacion ASC";
			
			Query<Pelicula> consulta = sesion.createQuery(hql, Pelicula.class);
			consulta.setParameter(1, anio1);
			consulta.setParameter(2, anio2);
			List<Pelicula> listaPeliculas = consulta.list();
			return listaPeliculas;
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
	}


}
