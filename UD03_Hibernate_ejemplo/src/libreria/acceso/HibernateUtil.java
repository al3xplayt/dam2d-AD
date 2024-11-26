package libreria.acceso;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	// fábrica de sesiones de Hibernate
	private static final SessionFactory sessionFactory = crearFabricaSesiones();
	
	/**
	 * Crea una fábrica de sesiones de Hibernate con la base de datos relacional.
	 * Devuelve la fábrica de sesiones creada.
	 */
	private static SessionFactory crearFabricaSesiones() {
		try {
			// create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().configure().build()
			);
		}
		catch (Throwable ex) {
			// make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed: " + ex);
			throw new ExceptionInInitializerError(ex);
		} 
	}
	
	/**
	 * Recupera la fábrica de sesiones de Hibernate con la base de datos relacional.
	 * Devuelve la fábrica de sesiones creada.
	 */
	public static SessionFactory obtenerFabricaSesiones() {
		return sessionFactory;
	}
	
	/**
	 * Cierra la fábrica de sesiones de Hibernate con la base de datos relacional.
	 */
	public static void cerrarFabricaSesiones() {
		SessionFactory fabricaSesiones = obtenerFabricaSesiones();
		if (fabricaSesiones != null && fabricaSesiones.isOpen()) {
			fabricaSesiones.close();
		}
	}
	
	/**
	 * Abre una sesión de Hibernate con la base de datos relacional.
	 * Devuelve la sesión creada.
	 */
	public static Session abrirSesion() {
		SessionFactory fabricaSesiones = obtenerFabricaSesiones();
		Session sesion = fabricaSesiones.openSession();
		return sesion;
	}
	
	/**
	 * Cierra una sesión de Hibernate con la base de datos relacional.
	 */
	public static void cerrarSesion(Session sesion) {
		if (sesion != null && sesion.isOpen()) {
			sesion.close();
		}
	}
	
}
