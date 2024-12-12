package libreria.principal;

import java.util.List;
import entrada.Teclado;
import libreria.acceso.AccesoEscritor;
import libreria.modelo.Escritor;

public class ConsultarEscritoresPorFechaNacimiento {

	public static void escribirListaEscritores(List<Escritor> lista) {
		for (Escritor escritor : lista) {
			System.out.println(escritor.toString());
		}
		System.out.println(lista.size() + " escritores consultados");
	}
	
	public static void main(String[] args) {
		String fechaNac1 = Teclado.leerCadena("Fecha Nacimiento 1: ");
		String fechaNac2 = Teclado.leerCadena("Fecha Nacimiento 2: ");
		List<Escritor> listaEscritores = AccesoEscritor.consultarPorFechaNacimiento(fechaNac1, fechaNac2);
		if (listaEscritores.isEmpty()) {
			System.out.println("No se ha encontrado ningún escritores entre las fechas de nacimiento.");
		}
		else {
			escribirListaEscritores(listaEscritores);
		}
	}

}
