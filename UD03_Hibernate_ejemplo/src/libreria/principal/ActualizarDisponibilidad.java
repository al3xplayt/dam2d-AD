package libreria.principal;

import entrada.Teclado;
import libreria.acceso.AccesoDisponibilidad;
import libreria.modelo.Disponibilidad;
import libreria.modelo.DisponibilidadId;

public class ActualizarDisponibilidad {

	public static void main(String[] args) {
		int codigoLibro = Teclado.leerEntero("Código de Libro: ");
		int codigoTienda = Teclado.leerEntero("Código de Tienda: ");
		int cantidad = Teclado.leerEntero("Cantidad: ");
		DisponibilidadId disponibilidadId = new DisponibilidadId(codigoLibro, codigoTienda);
		Disponibilidad disponibilidad = new Disponibilidad(disponibilidadId, null, null, cantidad, null);
		int disponibilidadesActualizadas = AccesoDisponibilidad.actualizarPorLibroYTienda(disponibilidad);
		if (disponibilidadesActualizadas > 0) {
			System.out.println("Se ha actualizado una disponibilidad.");
		}
		else {
			System.out.println("No se ha encontrado ningúna disponibilidad con el código de libro y el código de tienda.");
		}
	}

}
