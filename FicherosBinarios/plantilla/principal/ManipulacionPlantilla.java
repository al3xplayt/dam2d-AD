package plantilla.principal;

import alumnos.acceso.AccesoCodigo;
import entrada.Teclado;
import plantilla.acceso.AccesoEmpleado;
import plantilla.modelo.Empleado;

public class ManipulacionPlantilla {

	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar empleado");

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
					//Inseertar empleado
					//int codigo, String nombre, String apellidos, String fechaAlta, String departamento,double salario	
					codigo = AccesoCodigo.obtenerCodigo()+1;
					nombre = Teclado.leerCadena("Introduce el nombre: ");
					apellidos = Teclado.leerCadena("Introduce los apellidos: ");
					fechaAlta = Teclado.leerCadena("Introduce la fecha de alta: ");
					departamento = Teclado.leerCadena("Introduce el departamento: ");
					salario = Teclado.leerReal("Introduce el salario: ");
					Empleado emple = new Empleado(codigo, nombre, apellidos, fechaAlta, departamento, salario);
					//Guardar empleado
					AccesoEmpleado.InsertarEmpleado(emple);
					System.out.println("Empleado insertado");
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
