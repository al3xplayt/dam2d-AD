package libreria.acceso;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import libreria.modelo.Disponibilidad;

public class AccesoDisponibilidad {

	public static int actualizarPorLibroYTienda(Disponibilidad disponibilidad) {
		Session sesion = null;
		Transaction transaccion = null;
		int disponibilidadesActualizadas = 0;
		try {
			sesion = HibernateUtil.abrirSesion();
			transaccion = sesion.beginTransaction();
			String hql = "UPDATE Disponibilidad AS d " + 
			             "SET cantidad = ?1, fechaUltimaReposicion = sysdate() " +
					     "WHERE d.id.codigoLibro = ?2 AND d.id.codigoTienda = ?3";
			Query consulta = sesion.createQuery(hql);
			consulta.setParameter(1, disponibilidad.getCantidad());
			consulta.setParameter(2, disponibilidad.getId().getCodigoLibro());
			consulta.setParameter(3, disponibilidad.getId().getCodigoTienda());
			disponibilidadesActualizadas = consulta.executeUpdate();
			transaccion.commit();
		}
		catch (HibernateException he) {
			if (transaccion != null && transaccion.isActive()) {
				transaccion.rollback();
			}
			throw he;
		}
		finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return disponibilidadesActualizadas;
	}
	
}
