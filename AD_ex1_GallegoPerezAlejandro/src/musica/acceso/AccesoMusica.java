package musica.acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import musica.modelo.Album;
import musica.modelo.Cancion;
import musica.modelo.Musico;

public class AccesoMusica {
	public static List<Album> consultarAlbumsPorArtista(String musico) throws SQLException, ClassNotFoundException {
		List<Album> albumes = new ArrayList<>();
		Connection conexion = null;
		Album album = null;
		Musico musicoNuevo = null;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "SELECT al.* FROM album as al JOIN musico as mu on al.codigo_musico = mu.codigo where mu.nombre = ? order by puntuacion";
			// SELECT * FROM album where album.codigo_musico in (SELECT codigo from musico where nombre = ?) order by puntuacion (con subconsulta)
			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setString(1, musico);
			ResultSet resultados = sentencia.executeQuery();
			while (resultados.next()) {
				int codigoAlbum = resultados.getInt("codigo");
				int codigoMusico = resultados.getInt("codigo_musico");
				String titulo = resultados.getString("titulo");
				String tipo = resultados.getString("tipo");
				int agnoPublicacion = resultados.getInt("agno_publicacion");
				double puntuacion = resultados.getDouble("puntuacion");

				//int codigo, String nombre, String nacionalidad, 
	            //String fechaNacimiento, String fechaFallecimiento
				musicoNuevo = new Musico (codigoMusico, "", "", "1111-11-11",null);
				//int codigo, Musico musico, String titulo, 
	            //String tipo, int agnoPublicacion, double puntuacion
				album = new Album(codigoAlbum, musicoNuevo, titulo, tipo, agnoPublicacion, puntuacion);
				albumes.add(album);
			}
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return albumes;
	}
	
	public static void escribirListaAlbums(List<Album> albums) {
		for (Album alb : albums) {
			System.out.println(alb.toString());
		}
	}

	public static int insertarCancion(Cancion cancion) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		boolean insertado = false;
		int filas = 0;
		try {
			conexion = ConfigBD.conectarseABD();
			String codigoSQL = "INSERT INTO cancion (codigo_album, posicion, titulo, duracion) VALUES (?, ?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(codigoSQL);
			sentencia.setInt(1, cancion.getCodigo());
			sentencia.setInt(2, cancion.getPosicion());
			sentencia.setString(3, cancion.getTitulo());
			sentencia.setString(4, cancion.getDuracion().toString());
			
			filas = sentencia.executeUpdate();
		} finally {
			ConfigBD.desconectar(conexion);
		}
		return filas;
	}

	
}
