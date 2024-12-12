package libreria.principal;

import java.util.List;
import libreria.acceso.AccesoLibro;
import libreria.modelo.Libro;

public class ConsultarLibros {

	public static void escribirListaLibros(List<Libro> lista) {
		for (Libro libro : lista) {
			System.out.println(libro.toString());
		}
		System.out.println(lista.size() + " libros consultados");
	}
	
	public static void main(String[] args) {
		List<Libro> listaLibros = AccesoLibro.consultarTodos();
		if (listaLibros.isEmpty()) {
			System.out.println("No se ha encontrado ningún libro.");
		}
		else {
			escribirListaLibros(listaLibros);
		}
	}

}
