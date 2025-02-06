package inventario.acceso;

import java.util.ArrayList;
import java.util.List;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import inventario.modelo.Producto;

public class AccesoProducto {

	public static List<Producto> consultarTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Collection coleccion = null;
		List<Producto> listaProductos = null;
		try {
			listaProductos = new ArrayList<Producto>();
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = 
					(XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
		    String sentenciaConsultarTodos = "for $prod in /productos/produc "+
					" order by $prod/denominacion "+
		    		" return $prod";
			ResourceSet resultados = servicio.query(sentenciaConsultarTodos);
			ResourceIterator iterador = resultados.getIterator();
			while (iterador.hasMoreResources()) {
				Resource recurso = iterador.nextResource(); // Resource es un nodo del árbol XML
				String elemento = (String) recurso.getContent(); // Get content devuelve el contenido del nodo
				Producto producto = new Producto(elemento);
				listaProductos.add(producto);
			}
		} finally  {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		return listaProductos;
	}

	public static Producto consultarPorCodigo(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		Producto producto = null;
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
				String elemento = (String) recurso.getContent();
				producto = new Producto(elemento);
			}
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		return producto;
	}

	public static void insertarProducto(Producto producto) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		// TODO Auto-generated method stub
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String sentenciaInsertarProducto = 
					"update insert " +
						"<produc>" +
							"<cod_prod>" + producto.getCodigo() + "</cod_prod>" +
							"<denominacion>" + producto.getDenominacion() + "</denominacion>" +
							"<precio>" + String.format("%.2f", producto.getPrecio()) + "</precio>" +
							"<stock_actual>" + producto.getStockActual() + "</stock_actual>" +
							"<stock_minimo>" + producto.getStockMinimo() + "</stock_minimo>" +
							"<cod_zona>" + producto.getCodigoZona() + "</cod_zona>" +
						"</produc> " +
					"into /productos";
			servicio.query(sentenciaInsertarProducto);
			
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
	}

	public static void eliminarProducto(int codigo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		// TODO Auto-generated method stub
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			String sentenciaEliminarProducto = "update delete " + 
			"/productos/produc[cod_prod = " + codigo + "]";
			servicio.query(sentenciaEliminarProducto);
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
		
	}

	public static void actualizarProducto(Producto produc) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Collection coleccion = null;
		try {
			coleccion = ExistsDBUtil.abrirConexion();
			XPathQueryService servicio = (XPathQueryService) coleccion.getService("XPathQueryService", "1.0");
			
			String sentenciaActualizarProducto = 
					"update replace " +
					"/productos/produc[cod_prod = " + produc.getCodigo() + "] with " +
					"<produc>" +
						"<cod_prod>" + produc.getCodigo() + "</cod_prod>" +
						"<denominacion>" + produc.getDenominacion() + "</denominacion>" +
						"<precio>" + String.format("%.2f", produc.getPrecio()) + "</precio>" +
						"<stock_actual>" + produc.getStockActual() + "</stock_actual>" +
						"<stock_minimo>" + produc.getStockMinimo() + "</stock_minimo>" +
						"<cod_zona>" + produc.getCodigoZona() + "</cod_zona>" +
					"</produc>";	
			ResourceSet resultados2 = servicio.query(sentenciaActualizarProducto);
		} finally {
			ExistsDBUtil.cerrarConexion(coleccion);
		}
	}
	
}
