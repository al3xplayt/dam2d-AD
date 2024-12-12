package libreria.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigBD {
	//Esta clase debe hacer cosas
	private final static String CONECTOR_MYSQL ="com.mysql.cj.jdbc.Driver";
	private final static String ESQUEMABD ="libreria";
	private final static String URL_MYSQL_BD ="jdbc:mysql://localhost/"+ESQUEMABD;
	private final static String USUARIO ="root";
	private final static String CLAVE ="root";
	public static Connection conectarseABD() throws SQLException, ClassNotFoundException {
		//conexion
		Class.forName(CONECTOR_MYSQL);
		Connection conexion = DriverManager.getConnection(URL_MYSQL_BD, USUARIO, CLAVE);
		
		return conexion;
	}
	public static void desconectar(Connection conexion) throws SQLException {
		if (conexion != null && !conexion.isClosed()) {
			conexion.close();
		} 
	}
}
