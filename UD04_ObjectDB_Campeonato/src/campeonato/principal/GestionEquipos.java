package campeonato.principal;

import java.util.List;

import campeonato.acceso.AccesoEquipo;
import campeonato.modelo.Equipo;
import entrada.Teclado;

public class GestionEquipos {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar un equipo.");
		System.out.println("2) Consultar todos los equipo.");
		System.out.println("3) Consultar un equipo por nombre.");
		System.out.println("4) Actualizar un equipo por nombre.");
		System.out.println("5) Eliminar un equipo por nombre.");
	}

	public static void escribirEquipos(List<Equipo> equipos) {
        for (Equipo e : equipos) {
            System.out.println(e.toString());
        }
	}
	public static void main(String[] args) {
		// TODO - implement GestionEquipos.main
		int opcion = 0;
		List<Equipo> listaEquipos;
		String nombreEquipo, ciudad;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("¿Opcion? ");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Saliendo...");
					break;
				case 1:
					// Insertar un equipo.
					nombreEquipo = Teclado.leerCadena("Nombre del equipo: ");
					ciudad = Teclado.leerCadena("Ciudad del equipo: ");
					Equipo equipo = new Equipo(nombreEquipo, ciudad);
					AccesoEquipo.insertarEquipo(equipo);
					System.out.println("Equipo insertado correctamente.");
					break;
				case 2:
					// Consultar todos los equipo.
					listaEquipos = AccesoEquipo.consultarTodos();
					escribirEquipos(listaEquipos);
					break;
				case 3:
					// Consultar un equipo por nombre.
					nombreEquipo = Teclado.leerCadena("Nombre del equipo: ");
					Equipo equipoConsultado = AccesoEquipo.consultarEquipoPorNombre(nombreEquipo);
					if (equipoConsultado == null) {
						System.out.println("No se ha encontrado el equipo " + nombreEquipo + ".");
					} else {
						System.out.println(equipoConsultado.toString());
					}
					break;
				case 5:
					// Eliminar un equipo.
					nombreEquipo = Teclado.leerCadena("Nombre del equipo: ");
					int filas = AccesoEquipo.eliminarEquipo(nombreEquipo);
					if (filas > 0) {
						System.out.println("Equipo eliminado correctamente.");
					} else {
						System.out.println("No se ha podido eliminar el equipo.");
					}
					break;
				default:
					System.out.println("Opción no válida.");
					break;
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		} while (opcion != 0);
		System.out.println("Fin de la aplicación.");
	}
}
