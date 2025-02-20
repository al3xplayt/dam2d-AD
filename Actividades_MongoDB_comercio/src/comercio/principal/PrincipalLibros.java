package comercio.principal;

import java.util.List;

import comercio.acceso.AccesoLibro;
import comercio.modelo.Libro;
import entrada.Teclado;

public class PrincipalLibros {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar un libro en la base de datos");
		System.out.println("2) Consultar los libros de un escritor de la base de datos en orden");
		System.out.println("3) Consultar un libro de la base de datos por codigo");
		System.out.println("4) Eliminar un libro de la base de datos por codigo");
		System.out.println("5) Modificar un libro de la base de datos por codigo");
		System.out.println("6) Consultar todos los libros de la base de datos publicado entre dos años");
		System.out.println("7) Añadir el campo stock a todos los libros de la base de datos");
		System.out.println("8) Simular una compra de un libro o varios libros");
	}

	public static void escribirListaLibros(List<Libro> listaLibros) {
		for (Libro libro : listaLibros) {
			System.out.println(libro);
		}
	}

	public static void escribirListaCadenas(List<String> listaCadenas) {
		for (String libro2 : listaCadenas) {
			System.out.println(libro2);
		}
	}

	public static void main(String[] args) {
		int opcion, codigo;
		String titulo, autor, genero;
		int agnoPublicacion;
		double precio;
		Libro libro;
		List<Libro> listaLibros;
		;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("Introduce una opción: ");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Hasta luego");
					break;
				case 1:
					// Insertar un libro en la base de datos
					// Estructura: int codigo, String titulo, String autor, int agnoPublicacion,
					// String genero, double precio
					codigo = Teclado.leerEntero("Introduce el código del libro: ");
					libro = AccesoLibro.consultarPorCodigo(codigo);
					if (libro == null) {
						titulo = Teclado.leerCadena("Introduce el título del libro: ");
						autor = Teclado.leerCadena("Introduce el autor del libro: ");
						agnoPublicacion = Teclado.leerEntero("Introduce el año de publicación del libro: ");
						genero = Teclado.leerCadena("Introduce el género del libro: ");
						precio = Teclado.leerReal("Introduce el precio del libro: ");
						libro = new Libro(codigo, titulo, autor, agnoPublicacion, genero, precio);
						AccesoLibro.insertarLibro(libro);
						System.out.println("Libro insertado correctamente");
					} else {
						System.out.println("Ya existe un libro con ese código");
					}
					break;
				case 2:
					autor = Teclado.leerCadena("Introduce el autor del libro: ");
					listaLibros = AccesoLibro.consultarPorAutor(autor);
					if (listaLibros.isEmpty()) {
						System.out.println("No hay libros de ese autor");
					} else {
						escribirListaLibros(listaLibros);
					}
					break;
				case 3:

					break;
				case 4:
					codigo = Teclado.leerEntero("Introduce el código del libro: ");
					if (AccesoLibro.eliminarLibro(codigo)) {
						System.out.println("Libro eliminado correctamente");
					} else {
						System.out.println("No se ha podido eliminar el libro");
					}
					break;
				case 5:
					codigo = Teclado.leerEntero("Introduce el código del libro: ");
					libro = AccesoLibro.consultarPorCodigo(codigo);
					if (libro == null) {
						System.out.println("No existe un libro con ese código");
					} else {
						titulo = Teclado.leerCadena("Introduce el título del libro: ");
						autor = Teclado.leerCadena("Introduce el autor del libro: ");
						agnoPublicacion = Teclado.leerEntero("Introduce el año de publicación del libro: ");
						genero = Teclado.leerCadena("Introduce el género del libro: ");
						precio = Teclado.leerReal("Introduce el precio del libro: ");
						libro = new Libro(codigo, titulo, autor, agnoPublicacion, genero, precio);
						if (AccesoLibro.modificarLibro(libro)) {
							System.out.println("Libro modificado correctamente");
						} else {
							System.out.println("No se ha podido modificar el libro");
						}
					}
					break;
				case 6:
					int agno1 = Teclado.leerEntero("Introduce el año de inicio: ");
					int agno2 = Teclado.leerEntero("Introduce el año de fin: ");
					listaLibros = AccesoLibro.consultarEntreAgnos(agno1, agno2);
					List<String> lista2 = AccesoLibro.consultarEntreAgnosV2(agno1, agno2);
					if (listaLibros.isEmpty()) {
						System.out.println("No hay libros publicados entre esos años");
					} else {
						escribirListaLibros(listaLibros);
					}
					if (lista2.isEmpty()) {
						System.out.println("No hay libros publicados entre esos años");
					} else {
						escribirListaCadenas(lista2);
					}
					break;
				case 7:
					// Añadir el campo stock a todos los libros de la base de datos
					int stock = Teclado.leerEntero("Introduce el stock de los libros: ");
					int afectados = AccesoLibro.anadirStock(stock);
					if (afectados > 0) {
						System.out.println("Se han actualizado " + afectados + " libros");
					} else {
						System.out.println("No se ha actualizado ningún libro");
					}
					break;
				case 8:
					codigo = Teclado.leerEntero("Introduce el código del libro: ");
					if (AccesoLibro.consultarPorCodigo(codigo) == null) {
						System.out.println("No existe un libro con ese código");
						break;
					}
					int cantidad = Teclado.leerEntero("Introduce la cantidad de libros a comprar: ");
					if (AccesoLibro.comprarLibro(codigo, cantidad)) {
						System.out.println("Compra realizada correctamente");
					} else {
						System.out.println("No se ha podido realizar la compra no hay suficiente stock");
					}
					break;
				default:
					System.out.println("Opción incorrecta");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (opcion != 0);
	}
}
