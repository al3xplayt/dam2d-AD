package inventario.acceso;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

public class ExistsDBUtil {
	private static final String COLECCION = "inventario";
	private static final String CONECTOR = "org.exist.xmldb.DatabaseImpl";
	private static final String PUERTO = "8080";
	private static final String URL_CONEXION = "xmldb:exist://localhost:" + PUERTO +
												"/exist/xmlrpc/db/" + COLECCION;
	private static final String USUARIO = "admin";
	private static final String CONTRASENA = "admin";
	
	public static Collection abrirConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException , 
			XMLDBException{
		Class cl = Class.forName(CONECTOR);
		Database database = (Database) cl.newInstance();
		DatabaseManager.registerDatabase(database);
		Collection coleccion = DatabaseManager.getCollection(URL_CONEXION, USUARIO, CONTRASENA);
		
		return coleccion;
	}

	public static void cerrarConexion(Collection coleccion) throws XMLDBException {
		if (coleccion != null && coleccion.isOpen()) {
			coleccion.close();
		}
	}
}
