package inventario.principal;

import java.util.List;

import entrada.Teclado;
import inventario.acceso.AccesoZona;
import inventario.modelo.Zona;

public class PrincipalZona {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("*1) Consultar todas las zona de un director en orden ascendente.");
		System.out.println("2) Consultar una zona por código.");
		System.out.println("3) Insertar una zona en la base de datos.");
		System.out.println("*4) Eliminar una zona de la base de datos por director.");
		System.out.println("5) Actualizar un producto de la base de datos por codigo.");
		System.out.println();
	}

	public static void main(String[] args) {
		int opcion, codigo, filasAfectadas;
		String nombre, director;
		List<Zona> listaZonas = null;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("¿Opción? ");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Fin del programa.");
					break;
				case 1:
					//Consulta todas las zonas de un director en orden ascendente
					director = Teclado.leerCadena("Introduce el director de la zona: ");
					listaZonas = AccesoZona.consultarPorDirector(director);
					if (listaZonas.isEmpty()) {
						System.out.println("No hay zonas en la base de datos.");
					} else {
						escribirlistaZonas(listaZonas);
					}
					
					break;
				case 2:
					System.out.println("Opción no implementada.");
					break;
				case 3:
					System.out.println("Opción no implementada.");
					break;
				case 4:
					director = Teclado.leerCadena("Introduce el director de la zona: ");
					AccesoZona.eliminarPorDirector(director);
					System.out.println("Se han eliminado zonas.");
					break;
				case 5:
					System.out.println("Opción no implementada.");
					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (opcion != 0);
	}

	private static void escribirlistaZonas(List<Zona> listaZonas) {
		// TODO Auto-generated method stub
		for (Zona zona : listaZonas) {
			System.out.println(zona.toString());
		}
		
	}
}
