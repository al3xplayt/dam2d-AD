package libreria.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libreri.modelo.Escritor;

public class AccesoEscritor {
	public static List<Escritor> consultarTodos() throws SQLException, ClassNotFoundException {
		List<Escritor> escritores = new ArrayList<>();
		Connection conexion = null;
		Escritor escritor = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT * FROM escritor ORDER BY nombre ASC";

			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			ResultSet resultados = sentencia.executeQuery();
			while (resultados.next()) {
				int codigoEscritor = resultados.getInt("codigo");
				String nombre = resultados.getString("nombre");
				String nacionalidad = resultados.getString("nacionalidad");
				String fechaNacimiento = resultados.getString("fecha_nacimiento");
				String fechaFallecimiento = resultados.getString("fecha_fallecimiento");
				escritor = new Escritor(nombre, nacionalidad, codigoEscritor, fechaNacimiento, fechaFallecimiento);
				escritores.add(escritor);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}

		return escritores;
	}

	public static void escribirListaEscritores(List<Escritor> escritores) {
		for (Escritor escritor : escritores) {
			System.out.println(escritor.toString());
		}
	}

	public static boolean insertarEscritor(Escritor escritor) throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		boolean insertado = false;
		int filas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "INSERT INTO escritor (nombre, nacionalidad, fecha_nacimiento, fecha_fallecimiento) VALUES (?, ?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setString(1, escritor.getNombre());
			sentencia.setString(2, escritor.getNacionalidad());
			sentencia.setString(3, escritor.getFechaNacimiento().toString());
			if (escritor.getFecchaFallecimiento() == null) {
				sentencia.setString(4, null);
			} else {
				sentencia.setString(4, escritor.getFecchaFallecimiento().toString());
			}
			filas = sentencia.executeUpdate();
			insertado = filas > 0;
		} finally {
			ConfigBD.desconectar(conexion);
		}
		;
		return filas != 0;
	}

	public static void actualizarEscritor(Escritor escritor) throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "UPDATE escritor SET nombre = ?, nacionalidad = ?, fecha_nacimiento = ?, fecha_fallecimiento = ? WHERE codigo = ?";

			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setString(1, escritor.getNombre());
			sentencia.setString(2, escritor.getNacionalidad());
			sentencia.setString(3, escritor.getFechaNacimiento().toString());
			if (escritor.getFecchaFallecimiento() == null) {
				sentencia.setString(4, null);
			} else {
				sentencia.setString(4, escritor.getFecchaFallecimiento().toString());
			}
			sentencia.setInt(5, escritor.getCodigo());
			sentencia.executeUpdate();
		} finally {
			ConfigBD.desconectar(conexion);
		}
	}

	public static void eliminarEscritor(Escritor escritor) throws SQLException {
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "DELETE FROM escritor WHERE codigo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setInt(1, escritor.getCodigo());
			sentencia.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			ConfigBD.desconectar(conexion);
		}

	}

	public static int insertarEscritores(List<Escritor> escritores, String nacionalidad)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String nombre, fechaNacimiento, fechaFallecimiento;
		Connection conexion = null;
		int filas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			conexion.setAutoCommit(false);
			String codigoSQL = "INSERT INTO escritor (nombre, nacionalidad, fecha_nacimiento, fecha_fallecimiento) VALUES (?, ?, ?, ?)";
			for (Escritor esc : escritores) {
				nombre = esc.getNombre();
				fechaNacimiento = esc.getFechaNacimiento().toString();
				PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
				sentencia.setString(1, nombre);
				sentencia.setString(2, nacionalidad);
				sentencia.setString(3, fechaNacimiento);
				if (esc.getFecchaFallecimiento() == null) {
					sentencia.setString(4, null);
				} else {
					sentencia.setString(4, esc.getFecchaFallecimiento().toString());
				}

				filas = sentencia.executeUpdate();

			}
			conexion.commit();
		} catch (SQLException e) {
			if (conexion != null) {
				conexion.rollback();
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return filas;

	}
}
