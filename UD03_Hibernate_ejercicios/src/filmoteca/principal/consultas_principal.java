package filmoteca.principal;

import java.util.List;

import entrada.Teclado;
import filmoteca.modelo.Pelicula;

public class consultas_principal {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Consultar todas las sagas de la base de datos, en orden por nombre ascendente." + "");
		System.out.println("2) Consultar las peliculas entre dos años");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		List<Pelicula> listaPeliculas = null;
		
			do {
				escribirMenu();
				opcion = Teclado.leerEntero("Introduce una opcion: ");
				try {
				switch(opcion) {
				case 0:
					System.out.println("Saliendo...");
					break;
				case 1:
					// Consultar todas las sagas de la base de datos, en orden por nombre ascendente
					listaPeliculas = filmoteca.acceso.accesoPeliculas.consultarPeliculas();
					filmoteca.acceso.accesoPeliculas.verPeliculas(listaPeliculas);
					break;
				case 2:
					//Consultar las peliculas entre dos años
					int anio1 = Teclado.leerEntero("Introduce el año de inicio: ");
					int anio2 = Teclado.leerEntero("Introduce el año de fin: ");	
					listaPeliculas = filmoteca.acceso.accesoPeliculas.consultarPeliculasEntreAnios(anio1, anio2);
					if (listaPeliculas.isEmpty()) {
						System.out.println("No hay peliculas entre esos años");
					} else {
						filmoteca.acceso.accesoPeliculas.verPeliculas(listaPeliculas);
					}
					
					break;
			    default:
			    	System.out.println("Opcion no valida");
                	break;
				}
			
		}	catch (Exception e) {
			e.printStackTrace();
		}
				} while(opcion != 0);
	}

}
