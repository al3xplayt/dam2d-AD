package plantilla.principal;

import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;
import plantilla.acceso.AccesoEmpleado;
import plantilla.modelo.Empleado;

public class ConsultarPlantilla {
	public static void EscrituraMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Consultar empleados");
		System.out.println("2) Consultar un empleado del fichero por código.");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		List<Empleado> empleados = new ArrayList<>();
		try {
			do {
				EscrituraMenu();
				opcion = Teclado.leerEntero("Introduce una opcion: ");
				switch (opcion) {
				case 0:
					break;
				case 1:
					// Consultar empleados
					empleados = AccesoEmpleado.consultarTodos();
					if (empleados.isEmpty()) {
						System.out.println("No hay empleados");
					} else {
						AccesoEmpleado.escribirEmpleados(empleados);
					}
					break;
				case 2:
					// Consultar un empleado del fichero por código
                    int codigo = Teclado.leerEntero("Introduce el código del empleado: ");
                    Empleado empleado = AccesoEmpleado.consultarPorCodigo(codigo);
                    if (empleado != null) {
                        System.out.println(empleado.toString());
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
