package libreria.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				libro = new Libro(codigoLibro, codigoAutor, agnoPublicacion, numeroPaginas, precio, titulo);
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
	
}
