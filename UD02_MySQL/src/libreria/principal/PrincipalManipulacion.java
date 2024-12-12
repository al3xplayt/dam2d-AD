package libreria.principal;

import java.util.List;

import entrada.Teclado;
import libreri.modelo.Escritor;
import libreri.modelo.Fecha;
import libreri.modelo.Libro;
import libreria.acceso.AccesoEscritor;
import libreria.acceso.AccesoLibro;

public class PrincipalManipulacion {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar un escritor en la base de datos.");
		System.out.println(
				"2) Actualizar el nombre, la nacionalidad, la fecha de nacimiento y la fecha de fallecimiento de un escritor de la base de datos.");
		System.out.println("3) Eliminar un escritor de la base de datos.");
		System.out.println("4) Insertar un libro en la base de datos.");
		System.out.println("5) Actualizar el precio de los libros de la base de datos que se han editado "
				+ "entre dos años de publicación con un descuento. ⋆");
		System.out.println("6) Eliminar los libros de la base de datos que se venden entre dos precios.)");
	}

	public static void main(String[] args) {
		try {
			int opcion = 0;
			int codigoEscritor, codigoLibro, agno_publicacion;
			int numero_paginas;
			String nombreEscritor, nacionalidad;
			String fechaNacimiento, fechaFallecimiento;
			String titulo;
			double precio;
			int fecha1, fecha2;
			Escritor escritor = null;
			boolean vivo;
			List<Escritor> escritores = null;
			do {
				escribirMenu();
				opcion = Teclado.leerEntero("Introduzca una opción: ");
				switch (opcion) {
				case 0:
					System.out.println("Fin del programa.");
					break;
				case 1:
					// Insertar un escritor en la base de datos.
					nombreEscritor = Teclado.leerCadena("Introduzca el nombre del escritor: ");
					nacionalidad = Teclado.leerCadena("Introduzca la nacionalidad del escritor: ");
					fechaNacimiento = Teclado.leerCadena("Introduzca la fecha de nacimiento del escritor: ");
					vivo = Teclado.leerBooleano("¿Está vivo el escritor?");
					if (vivo) {
						fechaFallecimiento = null;
					} else {
						fechaFallecimiento = Teclado.leerCadena("Introduzca la fecha de fallecimiento del escritor: ");
					}
					// String nombre, String nacionalidad, int codigo, String fechaNacimiento,
					// String fecchaFallecimiento
					escritor = new Escritor(nombreEscritor, nacionalidad, 0, fechaNacimiento, fechaFallecimiento);
					if (AccesoEscritor.insertarEscritor(escritor)) {
						System.out.println("Escritor insertado correctamente.");
					} else {
						System.out.println("No se ha podido insertar el escritor.");
					}
					break;
				case 2:
					// Actualizar el nombre, la nacionalidad, la fecha de nacimiento y la fecha de
					// fallecimiento de un escritor de la base de datos.
					codigoEscritor = Teclado.leerEntero("Introduzca el código del escritor: ");
					nombreEscritor = Teclado.leerCadena("Introduzca el nombre del escritor: ");
					nacionalidad = Teclado.leerCadena("Introduzca la nacionalidad del escritor: ");
					fechaNacimiento = Teclado.leerCadena("Introduzca la fecha de nacimiento del escritor: ");
					fechaFallecimiento = Teclado.leerCadena("Introduzca la fecha de fallecimiento del escritor: ");

					escritor = new Escritor(nombreEscritor, nacionalidad, codigoEscritor, fechaNacimiento,
							fechaFallecimiento);
					AccesoEscritor.actualizarEscritor(escritor);
					break;
				case 3:
					// Eliminar un escritor de la base de datos.
					codigoEscritor = Teclado.leerEntero("Introduzca el código del escritor: ");
					escritor = new Escritor(codigoEscritor);
					AccesoEscritor.eliminarEscritor(escritor);
					break;
				case 4:
					// Insertar un libro en la base de datos.
					codigoEscritor = Teclado.leerEntero("Introduzca el código del escritor: ");
					escritores = AccesoEscritor.consultarTodos();
					for (Escritor esc : escritores) {
						int cod = esc.getCodigo();
						//int codigo, Escritor Autor, int agnoPublicacion, int numeroPaginas, double precio, String titul
						if (cod == codigoEscritor) {
							agno_publicacion = Teclado.leerEntero("Introduzca el año de publicación: ");
							numero_paginas = Teclado.leerEntero("Introduzca el número de páginas: ");
							precio = Teclado.leerReal("Introduzca el precio: ");
							titulo = Teclado.leerCadena("Introduzca el título: ");
							Libro libro = new Libro(0, esc, agno_publicacion, numero_paginas, precio, titulo);
							int filas = AccesoLibro.insertarLibro(libro);
							String mensaje = filas > 0 ? "Libro insertado correctamente." : "No se ha podido insertar el libro.";
							System.out.println(mensaje);
							break;
						}
					}
					break;
				  case 5:
					  // Actualizar el precio de los libros de la base de datos que se han editado entre dos años de publicación con un descuento.
					  fecha1 = Teclado.leerEntero("Introduzca la fecha de inicio: ");
					  fecha2 = Teclado.leerEntero("Introduzca la fecha de fin: ");
					  double descuento = Teclado.leerReal("Introduzca el descuento: ");
					  int filas = AccesoLibro.actualizarLibroEntreFechas(fecha1, fecha2, descuento);
					  String mensaje = filas > 0 ? "Se han actualizado un total de "+filas+"." : "No se ha podido actualizar ningun libro.";
					  System.out.println(mensaje);
					  break;
					
				case 6:
					// Eliminar los libros de la base de datos que se venden entre dos precios.
					double precio1 = Teclado.leerReal("Introduzca el precio mínimo: ");
					double precio2 = Teclado.leerReal("Introduzca el precio máximo: ");
					int filasEliminadas = AccesoLibro.eliminarLibrosEntrePrecios(precio1, precio2);
					String mensajeEliminacion = filasEliminadas > 0
							? "Se han eliminado un total de " + filasEliminadas + " libros."
							: "No se ha podido eliminar ningun libro.";
					System.out.println(mensajeEliminacion);
					break;
				}
			} while (opcion != 0);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
