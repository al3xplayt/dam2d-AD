package inventario.acceso;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class AccesoZona {
	public static String consultarPorCodigo(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		String zona = null;
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String consulta = "for $zona in /zonas/zona " +
					" where $zona/cod_zona = " + codigo +
					" return $zona";
			ResourceSet resultados = servicio.query(consulta);
			ResourceIterator iterador = resultados.getIterator();
			if (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				zona = (String) recurso.getContent();
			}
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		return zona;
	}
}
