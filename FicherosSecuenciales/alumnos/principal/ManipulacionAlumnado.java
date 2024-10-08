package alumnos.principal;

import java.util.ArrayList;
import java.util.List;

import alumnos.acceso.AccesoAlumno;
import alumnos.acceso.AccesoCodigo;
import alumnos.modelo.Alumno;
import entrada.Teclado;

public class ManipulacionAlumnado {
	public static void escribirMenu() {
		/* 
		 (1) Insertar un alumno en el fichero.
	(2) Actualizar el nombre, el apellido y la fecha de nacimiento de un alumno del fichero por código.
	(3) Actualizar la nota final 1 de los alumnos del fichero
	que están asignados a un grupo con una puntuación extra.
	(4) Eliminar un alumno del fichero por código.
	(5) Eliminar los alumnos del fichero que han nacido entre dos fechas. 
		 */
		System.out.println("0) Salir");
		System.out.println("1) Insertar un alumno en el fichero.");
		System.out.println("2) Actualizar el nombre, el apellido y la fecha de nacimiento de un alumno del fichero por código.");
		System.out.println("3) Actualizar la nota final 1 de los alumnos del fichero que están asignados a un grupo con una puntuación extra.");
		System.out.println("4) Eliminar un alumno del fichero por código.");
		System.out.println("5) Eliminar los alumnos del fichero que han nacido entre dos fechas.");
	}

	public static void main(String[] args) {
		Alumno alumno = null;
		int codigo = 0;
		String nombre, apellido, fechaNacimiento, grupo;
		double nota1, nota2;
		List<Alumno> alumnos  = new ArrayList<>();
		try {
			do {
                escribirMenu();
                int opcion = 0;
                opcion = Teclado.leerEntero("Introduce una opción: ");
                if (opcion == 0) {
                    break;
                }
                switch (opcion) {
                case 1:
                    // Insertar un alumno en el fichero.
                	//1;Carlos;Vazquez;31/10/1999;DAM1A;3,36;8,06
                	codigo = AccesoCodigo.obtenerCodigo() +1;
                	System.out.println ("Codigo: " + codigo);
                	nombre = Teclado.leerCadena("Introduce el nombre del alumno: ");
                    apellido = Teclado.leerCadena("Introduce el apellido del alumno: ");
                	fechaNacimiento = Teclado.leerCadena("Introduce la fecha de nacimiento del alumno: ");
                	grupo = Teclado.leerCadena("Introduce el grupo del alumno: ");
                	nota1 = Teclado.leerReal("Introduce la nota 1 del alumno: ");
                	nota2 = Teclado.leerReal("Introduce la nota 2 del alumno: ");
                	alumno = new Alumno(codigo, nombre, apellido, fechaNacimiento, grupo, nota1, nota2);
                	alumnos.add(alumno);
                	AccesoAlumno.insertar(alumno);
                	AccesoCodigo.EscribirCodigo(codigo);
                	System.out.println("Se ha insertado el alumno correctamente.");
                    break;
                case 2:
                    // Actualizar el nombre, el apellido y la fecha de nacimiento de un alumno del fichero por código.
                	codigo = Teclado.leerEntero("Introduce el código del alumno a modificar: ");
                	nombre = Teclado.leerCadena("Introduce el nombre del alumno: ");
                	apellido = Teclado.leerCadena("Introduce el apellido del alumno: ");
                	fechaNacimiento = Teclado.leerCadena("Introduce la fecha de nacimiento del alumno: ");
                	alumno = new Alumno(0, nombre, apellido, fechaNacimiento, null, 0, 0);
                	
                    break;
                case 3:
                    // Actualizar la nota final 1 de los alumnos del fichero que están asignados a un grupo con una puntuación extra.
                    break;
                case 4:
                    // Eliminar un alumno del fichero por código.
                	codigo = Teclado.leerEntero("Introduce el código del alumno a eliminar: ");
                	
                	if(AccesoAlumno.eliminarAlumnoPorCodigo(codigo))
                		System.out.println("Se ha eliminado el alumno correctamente.");
                	else
                		System.out.println("No se ha eliminado el alumno.");
                	
                    break;
                case 5:
                    // Eliminar los alumnos del fichero que han nacido entre dos fechas.
                	
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
                }
            } while (true);
		} catch (Exception e) {
            e.printStackTrace();
	}
	}
}
