package libreria.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libreri.modelo.Escritor;
import libreri.modelo.Libro;

public class AccesoLibro {
	public static List<Libro> consultarLibroPorEscritor(int codigoEscritor)
			throws SQLException, ClassNotFoundException {
		List<Libro> libros = new ArrayList<>();
		Connection conexion = null;
		Libro libro = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT * FROM libro WHERE codigo_escritor = ? ORDER BY precio DESC";

			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setInt(1, codigoEscritor);
			ResultSet resultados = sentencia.executeQuery();
			while (resultados.next()) {
				int codigoLibro = resultados.getInt("codigo");
				int codigoAutor = resultados.getInt("codigo_escritor");
				int agnoPublicacion = resultados.getInt("agno_publicacion");
				int numeroPaginas = resultados.getInt("numero_paginas");
				double precio = resultados.getDouble("precio");
				String titulo = resultados.getString("titulo");
				Escritor escritor = new Escritor(codigoAutor);
				libro = new Libro(codigoLibro, escritor, agnoPublicacion, numeroPaginas, precio, titulo);
				libros.add(libro);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}

		return libros;
	}

	public static void escribirListaLibros(List<Libro> libros) {
		for (Libro lib : libros) {
			System.out.println(lib.toString());
		}
	}

	public static List<Libro> consultarLibrosPorNombreAutor(String nombreAutor)
			throws SQLException, ClassNotFoundException {
		List<Libro> libros = new ArrayList<>();
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT l.* FROM libro as l " + "JOIN escritor as e "
					+ "ON l.codigo_escritor = e.codigo " + "WHERE e.nombre = ? ORDER by l.agno_publicacion ASC";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setString(1, nombreAutor);
			ResultSet resultados = sentencia.executeQuery();
			while (resultados.next()) {
				int codigoLibro = resultados.getInt("codigo");
				int codigoAutor = resultados.getInt("codigo_escritor");
				int agnoPublicacion = resultados.getInt("agno_publicacion");
				int numeroPaginas = resultados.getInt("numero_paginas");
				double precio = resultados.getDouble("precio");
				String titulo = resultados.getString("titulo");
				Escritor escritor = new Escritor(codigoAutor);
				Libro libro = new Libro(codigoLibro, escritor, agnoPublicacion, numeroPaginas, precio, titulo);
				libros.add(libro);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}

		return libros;
	}

	public static List<Libro> consultarLibrosNoDisponibles() throws ClassNotFoundException, SQLException {
		List<Libro> libros = new ArrayList<>();
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT lib.titulo, lib.agno_publicacion FROM libro"
					+ " as lib JOIN disponibilidad as disp on lib.codigo = disp.codigo_libro WHERE disp.cantidad = 0";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			ResultSet resultados = sentencia.executeQuery();
			while (resultados.next()) {
				String titulo = resultados.getString("titulo");
				int agnoPublicacion = resultados.getInt("agno_publicacion");
				Libro libro = new Libro(titulo, agnoPublicacion);
				libros.add(libro);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}

		return libros;
	}

	public static int insertarLibro(Libro lib) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		int filas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "INSERT INTO libro (codigo_escritor, titulo, agno_publicacion, numero_paginas, precio) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setInt(1, lib.getCodigoAutor());
			sentencia.setString(2, lib.getTitulo());
			sentencia.setInt(3, lib.getAgnoPublicacion());
			sentencia.setInt(4, lib.getNumeroPaginas());
			sentencia.setDouble(5, lib.getPrecio());
			filas = sentencia.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return filas;
	}

	public static int actualizarLibroEntreFechas(int fecha1, int fecha2, double descuento)
			throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		int filas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "UPDATE libro SET precio = precio - ((precio * ?)/100) "
					+ "WHERE agno_publicacion  BETWEEN ? AND ?";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setDouble(1, descuento);
			sentencia.setInt(2, fecha1);
			sentencia.setInt(3, fecha2);
			filas = sentencia.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return filas;
	}

	public static int eliminarLibrosEntrePrecios(double precio1, double precio2) throws SQLException, ClassNotFoundException {
		int filas = 0;
		Connection conexion = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "DELETE FROM libro WHERE precio BETWEEN ? AND ?";
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setDouble(1, precio1);
			sentencia.setDouble(2, precio2);
			filas = sentencia.executeUpdate();
		}  finally {
			ConfigBD.desconectar(conexion);
		}
		return filas;
	}

}
