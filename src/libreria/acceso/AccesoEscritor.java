package libreria.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import libreri.modelo.Escritor;

public class AccesoEscritor {
	public static List<Escritor> consultarTodos () throws SQLException, ClassNotFoundException{
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
}
