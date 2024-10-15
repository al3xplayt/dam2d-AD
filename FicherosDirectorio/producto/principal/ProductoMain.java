package producto.principal;

import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;
import producto.acceso.AccesoProducto;
import producto.modelo.Producto;

public class ProductoMain {
	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Insertar un producto en el fichero.");
		System.out.println("2) Consultar todos los productos del fichero.");
		System.out.println("3) Consultar un producto del fichero por c�digo.");
		System.out.println("4) Actualizar la fecha de modificaci�n, la cantidad y el precio de un producto del fichero por c�digo.");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    int opcion = 0;
	    String nombre, categoria, fechaModificacion;
	    int codigo, cantidad;
	    double precio;
	    List<Producto> listaProductos = new ArrayList<>();
	    Producto prod = null;
	    try {
	    	do {
	    		escribirMenu();
	    		opcion = Teclado.leerEntero("Inntroduce una opci�n:");
	    		switch (opcion) {
	    		case 0:
	    			
	    			break;
				case 1:
					//Insertar un producto en el fichero.
					//String nombre, String categoria, String fechaModificacion, int cantidad,
					//double precio
					nombre = Teclado.leerCadena("Introduce el nombre del producto:");
					categoria = Teclado.leerCadena("Introduce la categor�a del producto:");
					fechaModificacion = Teclado.leerCadena("Introduce la fecha de modificaci�n del producto:");
					cantidad = Teclado.leerEntero("Introduce la cantidad del producto:");
					precio = Teclado.leerReal("Introduce el precio del producto:");
					prod = new Producto(0, nombre, categoria, fechaModificacion, cantidad, precio);
					AccesoProducto.InsertarProducto(prod);
					break;
				case 2:
					// Consultar todos los productos del fichero.
					listaProductos = AccesoProducto.leerTodos();
					if (listaProductos.isEmpty()) {
						System.out.println("No hay productos en el fichero.");
					} else {
						AccesoProducto.escribirLista(listaProductos);
					}
					break;
				case 3:
					// Consultar un producto del fichero por c�digo.
					codigo = Teclado.leerEntero("Introduce el c�digo del producto:");
					prod = AccesoProducto.buscarProductoPorCodigo(codigo);
					if (prod == null) {
						System.out.println(
								"No se ha encontrado ning�n producto con c�digo " + codigo + " en el fichero.");
					} else {
						if (prod.getCodigo() < 0) {
							System.out.println(
									"El producto ha sido eliminado");
						} else {
							System.out.println(prod.toString());
						}
					}
					break;
				case 4:
					// Acualizar la fecha de modificaci�n, la cantidad y el precio de un producto del fichero por c�digo.
					codigo = Teclado.leerEntero("Introduce el c�digo del producto:");
					if (AccesoProducto.buscarProductoPorCodigo(codigo) == null) {
						System.out.println(
								"No se ha encontrado ning�n producto con c�digo " + codigo + " en el fichero.");
					} else {
						fechaModificacion = Teclado.leerCadena("Introduce la fecha de modificaci�n del producto:");
						cantidad = Teclado.leerEntero("Introduce la cantidad del producto:");
						precio = Teclado.leerReal("Introduce el precio del producto:");
						prod = new Producto(codigo, null, null, fechaModificacion, cantidad, precio);
						AccesoProducto.actualizarProductoPorCodigo(prod, codigo);
					}
					break;
					
				default:
					System.out.println("Opci�n no v�lida.");
					break;
	    		}
	    	} while (opcion != 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
