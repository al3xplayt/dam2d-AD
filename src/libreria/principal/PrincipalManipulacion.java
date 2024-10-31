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
			System.out.println("2) Actualizar el nombre, la nacionalidad, la fecha de nacimiento y la fecha de fallecimiento de un escritor de la base de datos.");
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
				Fecha fecha1, fecha2;
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
							fechaFallecimiento = Teclado
									.leerCadena("Introduzca la fecha de fallecimiento del escritor: ");
						}
						//String nombre, String nacionalidad, int codigo, String fechaNacimiento,
						//String fecchaFallecimiento
						escritor = new Escritor(nombreEscritor, nacionalidad, 0, fechaNacimiento, fechaFallecimiento);
						if (AccesoEscritor.insertarEscritor(escritor)) {
							System.out.println("Escritor insertado correctamente.");
						} else {
							System.out.println("No se ha podido insertar el escritor.");
						}
						break;
					case 2:
						// Actualizar el nombre, la nacionalidad, la fecha de nacimiento y la fecha de fallecimiento de un escritor de la base de datos.
						codigoEscritor = Teclado.leerEntero("Introduzca el código del escritor: ");
						nombreEscritor = Teclado.leerCadena("Introduzca el nombre del escritor: ");
						nacionalidad = Teclado.leerCadena("Introduzca la nacionalidad del escritor: ");
						fechaNacimiento = Teclado.leerCadena("Introduzca la fecha de nacimiento del escritor: ");
						fechaFallecimiento = Teclado.leerCadena("Introduzca la fecha de fallecimiento del escritor: ");
						
						escritor = new Escritor(nombreEscritor, nacionalidad, codigoEscritor, fechaNacimiento, fechaFallecimiento);
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
							System.out.println(esc.getCodigo() + " "+esc.getNombre());
							int cod = esc.getCodigo();
							if (cod == codigoEscritor) {
								
								titulo = Teclado.leerCadena("Introduzca el título del libro: ");
								agno_publicacion = Teclado.leerEntero("Introduzca el año de publicación del libro: ");
								numero_paginas = Teclado.leerEntero("Introduzca el número de páginas del libro: ");
								precio = Teclado.leerReal("Introduzca el precio del libro: ");
								//int, Escritor, int, int, double, String
								Libro lib = new Libro(0, esc, agno_publicacion,numero_paginas, precio, titulo);
								int filas =AccesoLibro.insertarLibro(lib);
								String mensaje = filas > 0 ? "Libro insertado correctamente." : "No se ha podido insertar el libro.";
								System.out.println(mensaje);
								break;
							} else {
								System.out.println("El escritor no existe.");
							}
						}
						

						
						break;
					}
					
					
				} while (opcion != 0);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
}
