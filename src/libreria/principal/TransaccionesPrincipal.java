package libreria.principal;

import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;
import libreri.modelo.Escritor;
import libreria.acceso.AccesoEscritor;

public class TransaccionesPrincipal {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1)  Insertar varios escritores de una nacionalidad en la base de datos.");
		System.out.println("2) Actualizar el precio de los libros de un escritor de la base de datos según "
				+ "la extensión entre dos números de páginas con un descuento por tramos.");
		System.out.println("3) Eliminar una tienda y sus disponibilidades de la base de datos.");
		
	}
	
	public static void main(String[] args) {
		try {
			int opcion = 0;
			int codigoEscritor, codigoLibro, agno_publicacion, cant;
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
					// Insertar varios escritores de una nacionalidad en la base de datos.
					int cantidad = Teclado.leerEntero("Introduzca la cantidad de escritores: ");
                    nacionalidad = Teclado.leerCadena("Introduzca la nacionalidad de los escritores: ");
                    escritores = new ArrayList<>();
                    for (int i = 0; i < cantidad; i++) {
                        nombreEscritor = Teclado.leerCadena("Introduzca el nombre del escritor: ");
                        fechaNacimiento = Teclado.leerCadena("Introduzca la fecha de nacimiento del escritor: ");
                        vivo = Teclado.leerBooleano("El escritor está vivo? (true/false): ");
                        if (!vivo) {
                        	fechaFallecimiento = Teclado.leerCadena("Introduzca la fecha de fallecimiento del escritor (si aplica): ");
						} else {
							fechaFallecimiento = null;
						}
                        escritor = new Escritor(nombreEscritor, nacionalidad, 0, fechaNacimiento, fechaFallecimiento);
                        escritores.add(escritor);
                    }
                    cant = AccesoEscritor.insertarEscritores(escritores, nacionalidad);
                    System.out.println("Se han insertado " + cantidad + " escritores en la base de datos.");
                    break;
					
				case 2:
					// Actualizar el nombre, la nacionalidad, la fecha de nacimiento y la fecha de
					// fallecimiento de un escritor de la base de datos.
				
					break;
				case 3:
					
					break;
				// Add other cases as needed
				}
			} while (opcion != 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
