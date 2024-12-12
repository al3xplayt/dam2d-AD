package examen.principal;
import java.util.List;

import centroformacion.acceso.AccesoCentroFormacion;
import centroformacion.modelo.Alumno;
import centroformacion.modelo.Ciclo;
import centroformacion.modelo.Modulo;
import concesionario.acceso.AccesoConcesionario;
import concesionario.modelo.Coche;
import entrada.Teclado;
import musica.acceso.AccesoMusica;
import musica.modelo.Album;
import musica.modelo.Cancion;

public class PrincipalPractica {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar un coche");
		System.out.println("2) Consultar todos los coches.");
		System.out.println("3) Consultar los albumes de un musico, por orden de puntuacion.");
		System.out.println("4) Insertar una cancion en la base de datos");
		System.out.println("5) Consultar todos los alumnos por orden de edad ascendente");
		System.out.println("6) Actualizar nombre y duración de un modulo por codigo");
		System.out.println("7) Eliminar las matriculas anuladas o que han sido creadas antes de un año academico");
	}
	public static void main(String[] args) {
		int opcion = 0, agnoFabricacion, codigoAlbum, posicion, filas;
		int codigoModulo, duracionModulo, agnoAcademico;
		String nombreModulo;
		String matricula, modelo, marca, mensaje, duracionString;
		String cantante, titulo;
		double precio;
		Coche coche;
		boolean bandera = false;
		List<Coche> coches;
		List<Album> albums = null;
		List<Alumno> alumnos = null;
		Cancion cancion;
		Album alb;
		Modulo modulo;
		do{
			escribirMenu();
			opcion = Teclado.leerEntero("¿Opcion? ");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Saliendo...");
					break;
				case 1: //1
					//Insertar un coche
					matricula = Teclado.leerCadena("Matricula del coche: ");		
					modelo = Teclado.leerCadena("Modelo del coche:");
					marca = Teclado.leerCadena("Marca del coche:");
					agnoFabricacion = Teclado.leerEntero("Año de fabricación del coche:");
					precio = Teclado.leerReal("Precio del coche:");
					//String matricula, String modelo, String marca, int agnoFabricacion, double precio
					coche = new Coche (matricula, modelo, marca, agnoFabricacion, precio);
					bandera = AccesoConcesionario.insertarCoche(coche);
					mensaje = bandera ? "Coche insertado" : "Ya existe un coche con la matricula "+matricula;
					System.out.println(mensaje);
					break;
				case 2: //1
					//Consultar todos los coches
					coches = AccesoConcesionario.consultarTodos();
					if (coches.isEmpty()) {
						System.out.println("No hay ningun coche registrado en el concesionario");
					} else {
						AccesoConcesionario.mostrarCoches(coches);
						System.out.println("Se han encontrado "+coches.size()+" coches registrados");
					}
					break;
				case 3: //0
					//Consultar los albumes de un musico, por orden de puntuacion.
					cantante = Teclado.leerCadena("Nombre del cantante: ");
					albums = AccesoMusica.consultarAlbumsPorArtista(cantante);
					if (albums.isEmpty()) {
						System.out.println("No existe ningun album del cangtante "+ cantante);
					} else {
						AccesoMusica.escribirListaAlbums(albums);
					}
					break;
				case 4:
					//Insertar una canción en la base de datos
					codigoAlbum  = Teclado.leerEntero("Codigo del album: ");
					/*int codigo, Musico musico, String titulo, 
	             String tipo, int agnoPublicacion, double puntuacion)*/
					alb = new Album(codigoAlbum, null, "", "", 0, 0);
					posicion = Teclado.leerEntero("Posicion de la cancion: ");
					duracionString = Teclado.leerCadena("Duración de la canción (formato mm:ss): ");
					titulo = Teclado.leerCadena("Titulo de la cancion: ");
					/*int codigo, Album album, int posicion, 
	               String titulo, String duracion*/
					cancion = new Cancion(codigoAlbum, alb, posicion, titulo, duracionString);
					filas = AccesoMusica.insertarCancion(cancion);
					if (filas != 0) {
						System.out.println("La cancion ha sido insertada correctamente");
					} else {
						System.out.println("No se ha podido insertar");
					}
					break;
				case 5:
					//Consultar todos los ALUMNOS de la bbd por edad asc 
					alumnos = AccesoCentroFormacion.consultarTodosLosAlumnos();
					if (alumnos.isEmpty()) {
						System.out.println("No hay ningun registro en la base de datos");
					} else {
						AccesoCentroFormacion.escribirAlumnos(alumnos);
						System.out.println("Se han encontrado "+alumnos.size()+" alumnos en la base de datos");
					}
					break;
				case 6:
					//Actualizar el nombre y duracion de un módulo por codigo de la bbd
					codigoModulo = Teclado.leerEntero("Codigo del modulo: ");
					nombreModulo = Teclado.leerCadena("Nuevo nombre del modulo: ");
					duracionModulo = Teclado.leerEntero("Nueva duracion del modulo: ");
					//int codigo, Ciclo ciclo, String nombre, String curso, int duracion, int creditos
					modulo = new Modulo(codigoModulo, null, nombreModulo, "", duracionModulo, 0);
					bandera = AccesoCentroFormacion.actualizarModulo(modulo);
					if (bandera) {
						System.out.println("Se ha realizado la actualizacion correctamente");
					} else {
						System.out.println("No existe ningun modulo con el codigo proporcionado");
					}
					break;
					
				case 7:
					//Eliminar las MATRICULAS de bbd anuladas o que han sido creadas antes de un año academico
					agnoAcademico = Teclado.leerEntero("Año academico: ");
					filas = AccesoCentroFormacion.eliminarMAtriculasPorAgnoEInactividad(agnoAcademico);
					mensaje = (filas != 0) ? "No se ha eliminado ningun registro." : "Se han eliminado "+filas+" registros";
					System.out.println(mensaje);
					break;
				default:
					System.out.println("Opcion invalida");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(opcion!=0);
	}
}
