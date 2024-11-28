package filmoteca.acceso;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import filmoteca.modelo.Saga;

public class AccesoSaga {

	public static void insertarSaga(Saga saga) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.abrirSesion();
			transaction =  session.beginTransaction();
			session.save(saga);
			transaction.commit();
			
		} catch (HibernateException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
		
		finally {
			HibernateUtil.cerrarSesion(session);
		}
	}



}
