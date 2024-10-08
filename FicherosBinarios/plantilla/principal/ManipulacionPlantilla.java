package plantilla.principal;

import entrada.Teclado;
import plantilla.acceso.AccesoEmpleado;
import plantilla.modelo.Empleado;

public class ManipulacionPlantilla {

	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar empleado");
		System.out.println("2) Actualizar el nombre, el apellido y la fecha de alta de un empleado del fichero por código.");
		System.out.println("3) Eliminar un empleado del fichero por código.");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion, codigo;
		String nombre, apellidos, fechaAlta, departamento;
		double salario;

		try {
			do {
				escribirMenu();
				opcion = Integer.parseInt(Teclado.leerCadena("Introduce una opción: "));
				switch (opcion) {
				case 0:
					System.out.println("Fin del programa");
					break;
				case 1:
					// Inseertar empleado
					// int codigo, String nombre, String apellidos, String fechaAlta, String
					// departamento,double salario
					codigo = plantilla.acceso.AccesoCodigo.obtenerCodigo() + 1;
					nombre = Teclado.leerCadena("Introduce el nombre: ");
					apellidos = Teclado.leerCadena("Introduce los apellidos: ");
					fechaAlta = Teclado.leerCadena("Introduce la fecha de alta: ");
					departamento = Teclado.leerCadena("Introduce el departamento: ");
					salario = Teclado.leerReal("Introduce el salario: ");
					Empleado emple = new Empleado(codigo, nombre, apellidos, fechaAlta, departamento, salario);
					// Guardar empleado
					AccesoEmpleado.InsertarEmpleado(emple);
					plantilla.acceso.AccesoCodigo.guardarCodigo(codigo);
					System.out.println("Empleado insertado");
					break;
				case 2:
					// (2) Actualizar el nombre, el apellido y la fecha de alta de un empleado del
					// fichero por código.
					codigo = Teclado.leerEntero("¿Codigo del alumno? ");
					nombre = Teclado.leerCadena("Introduce el nombre: ");
					apellidos = Teclado.leerCadena("Introduce los apellidos: ");
					fechaAlta = Teclado.leerCadena("Introduce la fecha de alta: ");
					if (AccesoEmpleado.actualizarPorCodigo(codigo, nombre, apellidos, fechaAlta)) {
						System.out.println("Empleado actualizado");
					} else {
						System.out.println("Empleado no encontrado");
					}
					break;
				case 3:
					// (3) Eliminar un empleado del fichero por código.
					codigo = Teclado.leerEntero("¿Codigo del empleado? ");
					if (AccesoEmpleado.eliminarPorCodigo(codigo)) {
						System.out.println("Empleado eliminado");
					} else {
						System.out.println("Empleado no encontrado");
					}
					break;
				default:
					System.out.println("Opción incorrecta");
					break;
				}
			} while (opcion != 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
