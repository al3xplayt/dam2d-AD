package producto.acceso;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import producto.modelo.Producto;

public class AccesoProducto {
	public static final String RUTA = "dataBin/producto.dat";

	public static void InsertarProducto(Producto prod) throws IOException {
		/*
		 * Código Nombre Categoría Fecha Modificación Cantidad Precio 1 Zapatillas
		 * Calzado 20/02/2011 8 47,23
		 */
		RandomAccessFile flujoSalida = null;
		try {
			flujoSalida = new RandomAccessFile(RUTA, "rw");
			long tamagnoFichero = flujoSalida.length();
			int codigo = (int) (tamagnoFichero / Producto.tamagnoRegistro + 1);
			prod.setCodigo(codigo);
			// Ubicamos el puntero al final del fichero
			flujoSalida.seek(tamagnoFichero);
			escribirProducto(prod, flujoSalida);

		} finally {
			if (flujoSalida != null) {
				flujoSalida.close();
			}
		}

	}

	private static void escribirProducto(Producto prod, RandomAccessFile flujoSalida) {
		// TODO Auto-generated method stub
		try {
			flujoSalida.writeInt(prod.getCodigo());
			flujoSalida.writeChars(prod.getNombre());
			flujoSalida.writeChars(prod.getCategoria());
			flujoSalida.writeChars(prod.getFechaModificacion());
			flujoSalida.writeInt(prod.getCantidad());
			flujoSalida.writeDouble(prod.getPrecio());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Producto> leerTodos() throws IOException {
		RandomAccessFile flujoLectura = null;
		List<Producto> listaProductos = new ArrayList<>();
		try {
			flujoLectura = new RandomAccessFile(RUTA, "r");
			flujoLectura.seek(0);
			while (flujoLectura.getFilePointer() < flujoLectura.length()) {
				Producto prod = leerProducto(flujoLectura);
				if (prod.getCodigo() > 0) {
					listaProductos.add(prod);
				}
			}

		} finally {
			if (flujoLectura != null) {
				try {
					flujoLectura.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listaProductos;
	}

	public static Producto leerProducto(RandomAccessFile flujoLectura) throws IOException {
		int codigo = flujoLectura.readInt();
		/* Código Nombre Categoría Fecha Modificación Cantidad Precio */
		List<Producto> productos = new ArrayList<>();
		char[] vectorCaracteres = new char[Producto.LONGITUD_NOMBRE];
		for (int posicion = 0; posicion < vectorCaracteres.length; posicion++) {
			vectorCaracteres[posicion] = flujoLectura.readChar();
		}
		String nombre = new String(vectorCaracteres);
		vectorCaracteres = new char[Producto.LONGITUD_CATEGORIA];
		for (int posicion = 0; posicion < vectorCaracteres.length; posicion++) {
			vectorCaracteres[posicion] = flujoLectura.readChar();
		}
		String categoria = new String(vectorCaracteres);
		vectorCaracteres = new char[Producto.LONGITUD_FECHA];
		for (int posicion = 0; posicion < vectorCaracteres.length; posicion++) {
			vectorCaracteres[posicion] = flujoLectura.readChar();
		}
		String fechaModificacion = new String(vectorCaracteres);

		int cantidad = flujoLectura.readInt();
		double precio = flujoLectura.readDouble();
		Producto prod = new Producto(codigo, nombre, categoria, fechaModificacion, cantidad, precio);
		return prod;

	}
	public static Producto buscarProductoPorCodigo (int codigo) throws IOException {
		Producto prod = null;
		int posicion = (codigo - 1) * Producto.tamagnoRegistro;
		RandomAccessFile flujoLectura = new RandomAccessFile(RUTA, "r");
		if (posicion < 0 || posicion >= flujoLectura.length()) {
			return null;
		} else {
			try {
				flujoLectura.seek(posicion);
				prod = leerProducto(flujoLectura);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return prod;
	}
	public static void actualizarProductoPorCodigo (Producto prod, int codigo) throws IOException {
		int posicion = (codigo - 1) * Producto.tamagnoRegistro;
		RandomAccessFile flujoEscritura = null;
		if (posicion < 0 || posicion >= flujoEscritura.length()) {
			return;
		} else {
			try {
				flujoEscritura.seek(posicion);
				escribirProducto(prod, flujoEscritura);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void escribirLista(List<Producto> productos) {
		for (Producto prod : productos) {
			System.out.println(prod);
		}
	}

}
