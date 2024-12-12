package centroformacion.acceso;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import centroformacion.modelo.Alumno;
import centroformacion.modelo.Modulo;

public class AccesoCentroFormacion {
	public static List<Alumno> consultarTodosLosAlumnos() {
		Session sesion = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			String hql = "SELECT al FROM Alumno AS al ORDER BY fecha_nacimiento ASC";//DESC
			Query<Alumno> consulta = sesion.createQuery(hql, Alumno.class);
			List<Alumno> listaAlumnos = consulta.list();
			return listaAlumnos;
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
	}

	public static void escribirAlumnos(List<Alumno> alumnos) {
		for (Alumno alu : alumnos) {
			System.out.println(alu.toString());
		}
	}

	public static boolean actualizarModulo(Modulo modulo) {
		Session sesion = null;
		Transaction transaction = null;
		Modulo moduloBD = null;
		boolean bandera = false;
		try {
			sesion = HibernateUtil.abrirSesion();
			moduloBD = sesion.get(Modulo.class, modulo.getCodigo());
			if (moduloBD == null) {
				bandera = false;
			} else {
				transaction = sesion.beginTransaction();
				// int codigo, Ciclo ciclo, String nombre, String curso, int duracion, int
				// creditos
				moduloBD.setNombre(modulo.getNombre());
				moduloBD.setDuracion(modulo.getDuracion());
				sesion.update(moduloBD);
				transaction.commit();
				bandera = true;
			}
			
		} catch (Exception e) {
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			throw e;
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return bandera;

	}

	public static int eliminarMAtriculasPorAgnoEInactividad(int agnoAcademico) {
		Session sesion = null;
		Transaction transaccion = null;
		int numFilas = 0;
		try {
			sesion = HibernateUtil.abrirSesion();
			transaccion = sesion.beginTransaction();
			String hql = "DELETE FROM Matricula AS mat WHERE agnoAcademico < ?1 OR estado = ?2 ";
			Query consulta = sesion.createQuery(hql);
			consulta.setParameter(1, agnoAcademico);
			consulta.setParameter(2, "anulada");
			numFilas = consulta.executeUpdate();
			transaccion.commit();
			
		} catch(HibernateException he){
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw he;
		
	}finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return numFilas;
	}
}
