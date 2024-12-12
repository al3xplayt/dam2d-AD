package libreria.acceso;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import libreria.modelo.Tienda;

public class AccesoTienda {

	public static void insertar(Tienda tienda) {
		Session sesion = null;
		Transaction transaccion = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			transaccion = sesion.beginTransaction();
			sesion.save(tienda);
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
	}
	
}
