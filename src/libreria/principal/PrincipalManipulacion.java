package libreria.principal;

import entrada.Teclado;
import libreri.modelo.Escritor;
import libreri.modelo.Fecha;
import libreria.acceso.AccesoEscritor;

public class PrincipalManipulacion {
		public static void escribirMenu() {
			System.out.println("0) Salir");
			System.out.println("1) Insertar un escritor en la base de datos.");
			System.out.println("2) Actualizar el nombre, la nacionalidad, la fecha de nacimiento y la fecha de fallecimiento de un escritor de la base de datos.");
			System.out.println("3) Eliminar un escritor de la base de datos.");
		}
		
		public static void main(String[] args) {
			try {
				int opcion = 0;
				int codigoEscritor;
				String nombreEscritor, nacionalidad;
				String fechaNacimiento, fechaFallecimiento;
				Fecha fecha1, fecha2;
				Escritor escritor = null;
				boolean vivo;
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
					}
					
					
				} while (opcion != 0);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
}
