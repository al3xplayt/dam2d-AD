package inventario.principal;

import java.util.List;

import entrada.Teclado;
import inventario.acceso.AccesoProducto;

public class PrincipalProducto {

	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Consultar todos los productos de la base de datos.");
		System.out.println("2) Consultar un producto por código.");
	}
	
	public static void escribirlistaProductos(List<String> listaProductos) {
        for (String producto : listaProductos) {
            System.out.println(producto.toString());
        }
    }

	public static void main(String[] args) {
		int opcion, codigo;
		String producto;
		List<String> listaProductos = null;
		do {
			escribirMenu();
			opcion = Teclado.leerEntero("¿Opción? ");
			try {
				switch (opcion) {
				case 0:
					System.out.println("Fin del programa.");
					break;
				case 1:
					listaProductos = AccesoProducto.consultarTodos();
					if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos en la base de datos.");
					}else {
                        escribirlistaProductos(listaProductos);
					}
					break;
				case 2:
					codigo = Teclado.leerEntero("Introduce el código del producto: ");
					producto = AccesoProducto.consultarPorCodigo(codigo);
					if (producto == null) {
						System.out.println("No se ha encontrado el producto.");
					} else {
						System.out.println(producto);
					}
					break;
				default:
					System.out.println("Opción incorrecta.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		} while (opcion != 0);
	}
}
