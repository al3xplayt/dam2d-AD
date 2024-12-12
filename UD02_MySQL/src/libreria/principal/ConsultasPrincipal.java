package libreria.principal;

import java.sql.SQLException;
import java.util.List;

import entrada.Teclado;
import libreri.modelo.Disponibilidad;
import libreri.modelo.Escritor;
import libreri.modelo.Libro;
import libreri.modelo.Tienda;
import libreria.acceso.AccesoDisponibilidad;
import libreria.acceso.AccesoEscritor;
import libreria.acceso.AccesoLibro;
import libreria.acceso.AccesoTienda;

public class ConsultasPrincipal {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Consultar todos los escritores de la base de datos, en orden por nombre ascendente.");
		System.out.println("2) Consultar todos los libros de un escritor.");
		System.out.println("3) Consultar una tienda de la base de datos.");
		System.out.println("4) Consultar la disponibilidad de un libro en una tienda de la base de datos.");
		System.out.println("5) Consultar los libros de un escritor de la base de datos, en orden por año de publicación ascendente.");
		System.out.println("6) Consultar el título y el año de publicación de los libros no disponibles de la base de datos.");
		System.out.println("7) Consultar el código, la dirección, la localidad, el número de libros disponibles y la cantidad total de libros disponibles de cada tienda de la base de datos.");
		System.out.println("8) Consultar las tiendas que tienen un libro y la cantidad disponible de la base de datos.");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int opcion = 0;
		int codigoEscritor = 0;
		List<Escritor> escritores = null;
		List<Libro> libros = null;
		Tienda tienda = null;
		Disponibilidad disponibilidad = null;
		try {
			do {
				escribirMenu();
				opcion = Teclado.leerEntero("Introduce una opción: ");
				switch (opcion) {
				case 0:
					System.out.println("Fin del programa.");
					break;
				case 1:
					// Consultar todos los escritores de la base de datos, en orden por nombre
					// ascendente.
					escritores = AccesoEscritor.consultarTodos();
					if (escritores.isEmpty()) {
						System.out.println("No hay escritores en la base de datos.");
					} else {
						AccesoEscritor.escribirListaEscritores(escritores);
						int cantidad = escritores.size();
						System.out.println("Cantidad de escritores: " + cantidad);
					}
					break;
				case 2:
					// Consultar todos los libros de un escritor.
					codigoEscritor = Teclado.leerEntero("Introduce el código del escritor: ");
					libros = AccesoLibro.consultarLibroPorEscritor(codigoEscritor);
					if (libros.isEmpty()) {
						System.out.println("No hay escritores en la base de datos.");
					} else {
						AccesoLibro.escribirListaLibros(libros);
					}
					break;
				default:
					System.out.println("Opción no válida.");
					break;
				case 3:
					// Consultar una tienda de la base de datos.
					int codigoTienda = Teclado.leerEntero("Introduce el código de la tienda: ");
					tienda = AccesoTienda.consultarTienda(codigoTienda);
					if (tienda == null) {
                        System.out.println("No hay tiendas en la base de datos.");
                    } else {
                        System.out.println(tienda.toString());
                    }
					break;
				case 4:
					//4) Consultar la disponibilidad de un libro en una tienda de la base de datos.
					int codigoLibro = Teclado.leerEntero("Introduce el código del libro: ");
					codigoTienda = Teclado.leerEntero("Introduce el código de la tienda: ");
					
					tienda = AccesoTienda.consultarTienda(codigoTienda);
					disponibilidad = AccesoDisponibilidad.consultarPorLibroTienda(codigoLibro, codigoTienda);
					if (disponibilidad == null) {
						System.out.println("No hay disponibilidad en la base de datos.");
					} else {
						System.out.println(disponibilidad.toString());
					}
					break;
				case 5:
					// 5) Consultar los libros de un escritor de la base de datos, en orden por año
					// de publicación ascendente.
					String nombre = Teclado.leerCadena("Introduce el nombre del escritor: ");
					libros = AccesoLibro.consultarLibrosPorNombreAutor(nombre);
					if (libros.isEmpty()) {
						System.out.println("No hay escritores en la base de datos.");
					} else {
						AccesoLibro.escribirListaLibros(libros);
					}
					break;
				case 6:
					// 6) Consultar el título y el año de publicación de los libros no disponibles
					// de la
					// base de datos.
					libros = AccesoLibro.consultarLibrosNoDisponibles();
					if (libros.isEmpty()) {
						System.out.println("No hay libros no disponibles en la base de datos.");
					} else {
						for (Libro lib : libros) {
							System.out.println("[Titulo = "+lib.getTitulo() + ",  Año publicación = " + lib.getAgnoPublicacion()+"]");
						}
					}
					break;	
				}
			} while (opcion != 0);
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
		}
	}
}
