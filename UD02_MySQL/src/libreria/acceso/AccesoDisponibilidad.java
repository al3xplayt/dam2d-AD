package libreria.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import libreri.modelo.Disponibilidad;
import libreri.modelo.Fecha;
import libreri.modelo.Libro;
import libreri.modelo.Tienda;

public class AccesoDisponibilidad {
	public static Disponibilidad consultarPorLibroTienda(int codigoLibro, int codigoTienda) throws ClassNotFoundException, SQLException {
		Disponibilidad disponibilidad = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT * FROM disponibilidad WHERE codigo_libro = ? AND codigo_tienda = ?";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setInt(1, codigoLibro);
			sentencia.setInt(2, codigoTienda);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				int cantidad = resultado.getInt("cantidad");
                String ultimaReposicion = resultado.getString("fecha_ultima_reposicion");
                Libro libro = new Libro(codigoLibro);
                Tienda tienda = new Tienda(codigoTienda);
                disponibilidad = new Disponibilidad(libro, tienda, cantidad, ultimaReposicion);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return disponibilidad;
	}
}
