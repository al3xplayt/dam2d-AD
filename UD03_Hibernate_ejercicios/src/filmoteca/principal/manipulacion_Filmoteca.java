package filmoteca.principal;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import entrada.Teclado;
import filmoteca.acceso.AccesoPeliculas;
import filmoteca.modelo.Actor;
import filmoteca.modelo.Saga;

public class manipulacion_Filmoteca {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar una saga");
		System.out.println("2) Actualizar un actor");
		System.out.println("3) Eliminar peliculas entre dos recaudaciones");
	}
	


	public static void main(String[] args) {
		int opcion = 0, codigo, contador;
		String nombre, descripcion, nacionalidad, fechaNacimiento, fechaFallecimiento;
		boolean vivo;
		Saga saga;
		Actor actor;
		Date fechaFallecimientoDate;
		BigDecimal recaudacion1, recaudacion2;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Introduce una opcion: ");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Saliendo...");
					break;
				case 1:
					// Insertar una saga
					nombre = Teclado.leerCadena("Introduce el nombre de la saga: ");
					descripcion = Teclado.leerCadena("Introduce el director de la saga: ");
					saga = new Saga();
					saga.setNombre(nombre);
					saga.setDescripcion(descripcion);
					filmoteca.acceso.AccesoSaga.insertarSaga(saga);
					System.out.println("Saga insertada correctamente");
					break;
				case 2:
					//Actualizar Actualizar el nombre, la nacionalidad, la fecha de nacimiento y la fecha
					//de fallecimiento de un actor de la base de datos por codigo
					codigo = Teclado.leerEntero("Introduce el codigo del actor a actualizar: ");
					actor = filmoteca.acceso.AccesoActor.consultarActor(codigo);
					if (actor == null) {
						System.out.println("No se ha encontrado al actor con codigo " + codigo);
					} else {
						actor.setCodigo(codigo);
						nombre = Teclado.leerCadena("Introduce el nombre del actor: ");
						actor.setNombre(nombre);
						nacionalidad = Teclado.leerCadena("Introduce la nacionalidad del actor: ");
						fechaNacimiento = Teclado.leerCadena("Introduce la fecha de nacimiento del actor: ");
						vivo = Teclado.leerBooleano("Introduce si el actor esta vivo: ");
						if(!vivo) {
							fechaFallecimiento = Teclado.leerCadena("Introduce la fecha de fallecimiento del actor: ");
							fechaFallecimientoDate = new SimpleDateFormat("yyyy-MM-DD").parse(fechaFallecimiento);
						} else {
							fechaFallecimiento = null;
							fechaFallecimientoDate = null;
						}
						Date fechaNacimientoDate = new SimpleDateFormat("yyyy-MM-DD").parse(fechaNacimiento);	
						
						actor.setNacionalidad(nacionalidad);
						actor.setFechaNacimiento(fechaNacimientoDate);
						actor.setFechaFallecimiento(fechaFallecimientoDate);
						
						
						filmoteca.acceso.AccesoActor.actualizarActor(actor);
						System.out.println("Actor actualizado correctamente");
					}
					break;
				//Eliminar registros de peliculas entre dos recaudaciones
				case 3:
					recaudacion1 = new BigDecimal(Teclado.leerReal("recaudacion 1: "));
					recaudacion2 = new BigDecimal(Teclado.leerReal("recaudacion 2: "));
					contador = AccesoPeliculas.eliminarPeliculasEntreRecaudaciones(recaudacion1, recaudacion2);
					String mensaje = (contador == 0) ? "No se ha eliminado ninguna pelicula" : "Se han eliminado " + contador + " peliculas";
					System.out.println(mensaje);
					break;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (opcion != 0);
	}
}
