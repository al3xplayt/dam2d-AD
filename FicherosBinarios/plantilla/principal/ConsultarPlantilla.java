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
