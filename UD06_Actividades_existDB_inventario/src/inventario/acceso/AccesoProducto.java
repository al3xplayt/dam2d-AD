package inventario.acceso;

import java.util.List;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class AccesoProducto {

	public static List<String> consultarTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		List<String> listaProductos = null;
		try {
			listaProductos = new java.util.ArrayList<String>();
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		    String sentenciaConsultarTodos = "for $prod in /productos/produc "+
					" order by $prod/denominacion "+
		    		" return $prod";
			ResourceSet resultados = servicio.query(sentenciaConsultarTodos);
			ResourceIterator iterador = resultados.getIterator();
			while (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				String producto = (String) recurso.getContent();
				listaProductos.add(producto);
			}
		} finally  {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		return listaProductos;
	}

	public static String consultarPorCodigo(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		String producto = null;
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String consulta = "for $prod in /productos/produc " +
					" where $prod/cod_prod = " + codigo +
					" return $prod";
			ResourceSet resultados = servicio.query(consulta);
			ResourceIterator iterador = resultados.getIterator();
			if (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource();
				producto = (String) recurso.getContent();
			}
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		return producto;
	}
	
}
