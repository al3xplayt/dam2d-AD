package alumnos.principal;

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
		try {
			do {
                escribirMenu();
                int opcion = 0;
                opcion = Integer.parseInt(System.console().readLine());
                if (opcion == 0) {
                    break;
                }
                switch (opcion) {
                case 1:
                    // Insertar un alumno en el fichero.
                	
                    break;
                case 2:
                    // Actualizar el nombre, el apellido y la fecha de nacimiento de un alumno del fichero por código.
                    break;
                case 3:
                    // Actualizar la nota final 1 de los alumnos del fichero que están asignados a un grupo con una puntuación extra.
                    break;
                case 4:
                    // Eliminar un alumno del fichero por código.
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
