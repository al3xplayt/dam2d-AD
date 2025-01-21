package campeonato.principal;

import java.util.List;

import campeonato.acceso.AccesoJugador;
import campeonato.modelo.Jugador;
import entrada.Teclado;

public class GestionJugadores {

	public static void menu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar un jugador.");
		System.out.println("2) Consultar jugadores por equipo.");
		System.out.println("3) Consultar jugador por código.");
		System.out.println("4) Actualizar jugador por código.");
		System.out.println("5) Eliminar jugador por código.");
	}

	public static void escribirJugadores(List<Jugador> jugadores) {
		for (Jugador j : jugadores) {
			System.out.println(j.toString());
		}
	}

	public static void main(String[] args) {
		// TODO - implement GestionCampeonatos.main
		int opcion = 0;
		String nombreEquipo, nombreJugador, fechaNacimiento;
		List<Jugador> listaJugadores;
		do {
			menu();
			opcion = Teclado.leerEntero("¿Opcion? ");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Saliendo...");
					break;
				case 1:
					// Insertar un jugador y asignarlo a un equipo.
					nombreJugador = Teclado.leerCadena("Nombre del jugador: ");
					fechaNacimiento = Teclado.leerCadena("Fecha de nacimiento (formato aaaa-mm-dd): ");
					nombreEquipo = Teclado.leerCadena("Nombre del equipo: ");
					Jugador jugador = new Jugador(nombreJugador, fechaNacimiento);
					if (AccesoJugador.insertarJugador(jugador, nombreEquipo)) {
						System.out.println("Jugador insertado correctamente.");
					} else {
						System.out.println("No se ha podido insertar el jugador.");
					}
					break;
				case 2:
					// System.out.println("Consultar jugadores por equipo.");
					nombreEquipo = Teclado.leerCadena("Nombre del equipo: ");
					listaJugadores = AccesoJugador.consultarJugadorPorEquipo(nombreEquipo);
					if (listaJugadores.isEmpty()) {
						System.out.println("No se han encontrado jugadores en el equipo " + nombreEquipo + ".");
					} else {
						escribirJugadores(listaJugadores);
					}

					break;
				case 3:
					//Consultar jugador por código.
					int codigo = Teclado.leerEntero("Código del jugador: ");
					Jugador j = AccesoJugador.consultarJugadorPorCodigo(codigo);
					if (j != null) {
						System.out.println(j.toString());
					} else {
						System.out.println("No se ha encontrado el jugador con código " + codigo + ".");
					}
					break;
				default:
					System.out.println("Opción no válida.");
					break;
				case 4:
					//Actualizar jugador por código.
					codigo = Teclado.leerEntero("Código del jugador: ");
					nombreJugador = Teclado.leerCadena("Nombre del jugador: ");
					fechaNacimiento = Teclado.leerCadena("Fecha de nacimiento (formato aaaa-mm-dd): ");
					jugador = new Jugador(nombreJugador, fechaNacimiento);
					if (AccesoJugador.actualizarJugadorPorCodigo(codigo, jugador)) {
						System.out.println("El jugador se a actualizado correctamente.");
					} else {
						System.out.println("No se ha podido actualizar el jugador.");
					}
					break;
				case 5:
					// Eliminar jugador por código.
					codigo = Teclado.leerEntero("Código del jugador: ");
					if (AccesoJugador.eliminarJugadorPorCodigo(codigo)) {
						System.out.println("El jugador se ha eliminado correctamente.");
					} else {
						System.out.println("No se ha podido eliminar el jugador.");
					}
					break;
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}

		} while (opcion != 0);
	}

}
