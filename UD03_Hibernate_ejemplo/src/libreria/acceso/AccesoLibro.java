package libreria.acceso;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import libreria.modelo.Libro;

public class AccesoLibro {

	public static List<Libro> consultarTodos() {
		Session sesion = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			String hql = "SELECT lib FROM Libro AS lib ORDER BY precio DESC";
			Query<Libro> consulta = sesion.createQuery(hql, Libro.class);
			List<Libro> listaLibros = consulta.list();
			return listaLibros;
		}
		finally {
			HibernateUtil.cerrarSesion(sesion);
		}
	}
	
}
