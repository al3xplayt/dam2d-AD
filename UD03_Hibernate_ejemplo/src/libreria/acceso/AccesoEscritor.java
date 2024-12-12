package libreria.acceso;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import libreria.modelo.Escritor;

public class AccesoEscritor {

	public static List<Escritor> consultarPorFechaNacimiento(String fechaNac1, String fechaNac2) {
		Session sesion = null;
		try {
			sesion = HibernateUtil.abrirSesion();
			String hql = "SELECT esc FROM Escritor AS esc " +
			             "WHERE fechaNacimiento BETWEEN '" + fechaNac1 + "' AND '" + fechaNac2 + "'";
			TypedQuery<Escritor> consulta = sesion.createQuery(hql, Escritor.class);
			List<Escritor> listaEscritores = consulta.getResultList();
			return listaEscritores;
		}
		finally {
			HibernateUtil.cerrarSesion(sesion);
		}
	}
	
}
