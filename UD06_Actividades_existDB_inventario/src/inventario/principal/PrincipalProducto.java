package inventario.principal;

import java.util.List;

import entrada.Teclado;
import inventario.acceso.AccesoProducto;
import inventario.acceso.AccesoZona;
import inventario.modelo.Producto;
import inventario.modelo.Zona;

public class PrincipalProducto {

	public static void escribirMenu() {
		System.out.println("0) Salir");
		System.out.println("1) Consultar todos los productos de la base de datos.");
		System.out.println("2) Consultar un producto por código.");
		System.out.println("3) Insertar un producto en la base de datos.");
		System.out.println("4) Eliminar un producto de la base de datos por codigo.");
		System.out.println("5) Actualizar un producto de la base de datos por codigo.");
	}
	
	public static void escribirlistaProductos(List<Producto> listaProductos) {
        for (Producto producto : listaProductos) {
            System.out.println(producto.toString());
        }
    }

	public static void main(String[] args) {
		int opcion, codigo, stock, stockMinimo, codigoZona;
		double precio;
		Zona zona;
		String denominacion;
		List<Producto> listaProductos = null;
		Producto produc = null;
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
					produc = AccesoProducto.consultarPorCodigo(codigo);
					if (produc == null) {
						System.out.println("No se ha encontrado el producto.");
					} else { 
						System.out.println(produc.toString());
					}
					break;
				case 3:
					codigo = Teclado.leerEntero("Introduce el código del producto: ");
					produc = AccesoProducto.consultarPorCodigo(codigo);
					if (produc != null) {
						System.out.println("Se ha encontrado el producto.");
						break;
					}
					codigoZona = Teclado.leerEntero("Introduce el código de la zona del producto: ");
					zona = AccesoZona.consultarPorCodigo(codigoZona);
					if (zona == null) {
                        System.out.println("No se ha encontrado ninguna zona con el código " + codigoZona + ".");
					}
					else {
						denominacion = Teclado.leerCadena("Introduce la denominación del producto: ");
						precio = Teclado.leerReal("Introduce el precio del producto: ");
						stock = Teclado.leerEntero("Introduce el stock actual del producto: ");
						stockMinimo = Teclado.leerEntero("Introduce el stock mínimo del producto: ");
						produc = new Producto(codigo, denominacion, precio, stock, stockMinimo, codigoZona);
						AccesoProducto.insertarProducto(produc);
						System.out.println("Se ha insertado un nuevo producto.");
					}
					break;
				case 4:
					codigo = Teclado.leerEntero("Introduce el código del producto: ");
					produc = AccesoProducto.consultarPorCodigo(codigo);
					if (produc == null) {
						System.out.println("No se ha encontrado el producto.");
					} else {
						AccesoProducto.eliminarProducto(codigo);
						System.out.println("Se ha eliminado el producto.");
					}
					break;
				case 5:
					codigo = Teclado.leerEntero("Introduce el código del producto: ");
					produc = AccesoProducto.consultarPorCodigo(codigo);
					if (produc == null) {
						System.out.println("No se ha encontrado el producto.");
						break;
                    } 
					
					codigoZona = Teclado.leerEntero("Introduce el código de la zona del producto: ");
                    zona = AccesoZona.consultarPorCodigo(codigoZona);
                    if (zona == null) {
                        System.out.println("No se ha encontrado ninguna zona con el código " + codigoZona + ".");
                    } else {
                        denominacion = Teclado.leerCadena("Introduce la denominación del producto: ");
                        precio = Teclado.leerReal("Introduce el precio del producto: ");
                        stock = Teclado.leerEntero("Introduce el stock actual del producto: ");
                        stockMinimo = Teclado.leerEntero("Introduce el stock mínimo del producto: ");
                        produc = new Producto(codigo, denominacion, precio, stock, stockMinimo, codigoZona);
                        AccesoProducto.actualizarProducto(produc);
                        System.out.println("Se ha actualizado el producto.");
                    }
					break;
				default:
					System.out.println("Opción incorrecta.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (opcion != 0);
	}
}
