package alumnos.principal;

import java.util.List;

import alumnos.acceso.AccesoAlumno;
import alumnos.modelo.Alumno;
import entrada.Teclado;

public class ConsultaAlumnado {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Consultar todos los alumnos.");
		System.out.println("2) Consultar un alumno por código");
		System.out.println("3) Consultar los alumnos por nombre o apellido.");
		System.out.println("4) Consultar los alumnos que han aprobado.");
	}

	public static void main(String[] args) {
		int opcion;
		List<Alumno> alumnos;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("¿Opcion? ");
			try {
				switch (opcion) {
				case 0:
					// Salir del programa
					break;
				case 1:
					// Consultar todos los alumnos
					alumnos = AccesoAlumno.ConsultarAlumnos();
					if (alumnos.isEmpty()) {
						System.out.println("No hay alumnos en el fichero.");
					} else {
						escribirLista(alumnos);
					}
					break;

				case 2:
					// Consultar un alumno por código
					int codigo = Teclado.leerEntero("Introduce el código del alumno: ");
					alumnos = AccesoAlumno.consultarAlumnoPorCodigo(codigo);
					if (alumnos.isEmpty()) {
						System.out.println("No se ha encontrado el alumno con código " + codigo);
					} else {
						escribirLista(alumnos);
					}
					break;
				case 3:
					// Consultar los alumnos por nombre o apellido.
					String nombre = Teclado.leerCadena("Introduce el nombre o apellido del alumno: ");
					alumnos = AccesoAlumno.consultarAlumnoPorNombre(nombre);
					if (alumnos.isEmpty()) {
						System.out.println("No se ha encontrado el alumno con nombre o apellido " + nombre);
					} else {
						escribirLista(alumnos);
					}
					break;
				case 4:
					// Consultar los alumnos que han aprobado.
					alumnos = AccesoAlumno.consultarAlumnosAprobados();
					if (alumnos.isEmpty()) {
						System.out.println("No se ha encontrado alumnos aprobados.");
					} else {
						escribirLista(alumnos);
					}
					break;

				default:
					System.out.println("Opcion invalida, opciones validas (0-4)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (opcion != 0);

	}

	private static void escribirLista(List<Alumno> alumnos) {
		for (Alumno alumno : alumnos) {
			System.out.println(alumno.toString());
		}
	}
}
