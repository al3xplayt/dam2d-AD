package filmoteca.acceso;

import org.hibernate.Session;

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
		try {
			sesion = HibernateUtil.abrirSesion();
			sesion.beginTransaction();
			sesion.update(actor);
			sesion.getTransaction().commit();
		} catch () {
			
		}
		finally {
			HibernateUtil.cerrarSesion(sesion);
		}
		
	}

}
