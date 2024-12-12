package filmoteca.acceso;

import org.hibernate.Session;
import org.hibernate.Transaction;

import filmoteca.modelo.Actor;

public class AccesoActor {

	public static Actor consultarActor(int codigo) {
		// TODO Auto-generated method stub
		Actor actor = null;
		Session sesion = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			actor = sesion.get(Actor.class, codigo);
		} finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		return actor;
	}

	public static void actualizarActor(Actor actor) {
		Session sesion = null;
		Transaction transaction = null;
		Actor actorBD = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			transaction = sesion.beginTransaction();
			sesion.update(actor); //actualiza el objeto actor en la base de datos
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			throw e;
		}
		finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		
	}

}
