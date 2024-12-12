package libreria.principal;

import entrada.Teclado;
import libreria.acceso.AccesoTienda;
import libreria.modelo.Tienda;

public class InsertarTienda {

	public static void main(String[] args) {
		String direccion = Teclado.leerCadena("Direccion: ");
		String localidad = Teclado.leerCadena("Localidad: ");
		String telefono = Teclado.leerCadena("Telefono: ");
		Tienda tienda = null;
		if (Teclado.leerBooleano("Está en Centro Comercial? ")) {
			String centroComercial = Teclado.leerCadena("Centro Comercial: ");
			tienda = new Tienda(centroComercial, direccion, localidad, telefono, null);
		}
		else {
			tienda = new Tienda(direccion, localidad, telefono);
		}
		AccesoTienda.insertar(tienda);
		System.out.println("Se ha insertado una tienda.");
	}

}
