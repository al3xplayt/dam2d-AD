package libreria.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import libreri.modelo.Tienda;

public class AccesoTienda {
	
	//3) Consultar una tienda de la base de datos.
	
	public static  Tienda  consultarTienda(int codigo) throws SQLException, ClassNotFoundException {
		Tienda tienda = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT * FROM tienda where codigo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setInt(1, codigo);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				String centroComercial = resultado.getString("centro_comercial");
				String direccion = resultado.getString("direccion");
				String localidad = resultado.getString("localidad");
				String telefono = resultado.getString("telefono");
				tienda = new Tienda(codigo, centroComercial, direccion, localidad, telefono);
			}

		} finally {
			ConfigBD.desconectar(conexion);
		}
		
		
		return tienda;
	}
}
