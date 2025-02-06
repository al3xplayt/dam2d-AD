package inventario.acceso;

import java.util.ArrayList;
import java.util.List;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import inventario.modelo.Zona;

public class AccesoZona {
	public static Zona consultarPorCodigo(int codigo)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Zona zona = null;
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String consulta = "for $zona in /zonas/zona " + " where $zona/cod_zona = " + codigo + " return $zona";
			ResourceSet resultados = servicio.query(consulta);
			ResourceIterator iterador = resultados.getIterator();
			if (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String elemento = (String) recurso.getContent();
				zona = new Zona(elemento);
			}
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		return zona;
	}

	public static List<Zona> consultarPorDirector(String director)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		List<Zona> listaZonas = null;
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
			listaZonas = new ArrayList<Zona>();
			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String consulta = "for $zona in /zonas/zona " + " where $zona/director = '" + director + "'"
					+ " order by $zona/nombre" + " return $zona";
			ResourceSet resultados = servicio.query(consulta);
			ResourceIterator iterador = resultados.getIterator();
			while (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String elemento = (String) recurso.getContent();
				Zona zona = new Zona(elemento);
				listaZonas.add(zona);
			}
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		return listaZonas;
	}

	public static void eliminarPorDirector(String director)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		int filasAfectadas = 0;
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String consulta = "update delete " + "/zonas/zona[director = '" + director + "']";
			ResourceSet resultados = servicio.query(consulta);
		}
	}
}
