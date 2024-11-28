package filmoteca.principal;

import java.text.SimpleDateFormat;
import java.util.Date;

import entrada.Teclado;
import filmoteca.modelo.Actor;
import filmoteca.modelo.Saga;

public class manipulacion_Filmoteca {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar una saga");
		System.out.println("2) Actualizar un actor");
		System.out.println("3) Eliminar un actor");
	}
	
	public static Date leerFecha(String mensaje) {
		String cadena = Teclado.leerCadena(mensaje);
		String [] datos = cadena.split("-");
		
		return new Date(Integer.parseInt(datos[0])-1900, Integer.parseInt(datos[1]), Integer.parseInt(datos[2])); // [1]
	}

	public static void main(String[] args) {
		int opcion = 0, codigo;
		String nombre, descripcion, nacionalidad, fechaNacimiento, fechaFallecimiento;
		boolean vivo;
		Saga saga;
		Actor actor;
		Date fechaFallecimientoDate;
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
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (opcion != 0);
	}
}
