package libreria.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import libreri.modelo.Tienda;

public class AccesoTienda {

	// 3) Consultar una tienda de la base de datos.

	public static Tienda consultarTienda(int codigo) throws SQLException, ClassNotFoundException {
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

	public static int[] eliminarTiendaDisponibilidades(int codigoTienda) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection conexion = null;
		int eliminaciones[] = new int[2]; // [0] -> disponibilidad, [1] -> tienda]
		int cantidadEliminaciones = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			conexion.setAutoCommit(false);
			
			String codigoSQL1 = "DELETE FROM disponibilidad WHERE codigo_tienda = ?";
			PreparedStatement sentencia1 = conexion.prepareStatement(codigoSQL1);
			sentencia1.setInt(1, codigoTienda);
			eliminaciones[0] = sentencia1.executeUpdate(); // [0] -> disponibilidad, [1] -> tienda]
			
			String codigoSQL2 = "DELETE FROM tienda WHERE codigo = ?";
			PreparedStatement sentencia2 = conexion.prepareStatement(codigoSQL2);
			sentencia2.setInt(1, codigoTienda);
			eliminaciones[1] = sentencia2.executeUpdate(); // [0] -> disponibilidad, [1] -> tienda]
			
			
			conexion.commit();
		} catch (SQLException e) {
			if (conexion != null) {
				conexion.rollback();
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return eliminaciones;
	}
}
