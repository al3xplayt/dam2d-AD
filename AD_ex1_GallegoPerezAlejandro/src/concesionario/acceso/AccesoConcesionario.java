package concesionario.acceso;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import concesionario.modelo.Coche;

public class AccesoConcesionario {
	public static final String RUTA = "data/coches.dat";


	public static List<Coche> consultarTodos() throws IOException, ClassNotFoundException {
		ObjectInputStream flujoLectura = null;
		List<Coche> coches = new ArrayList<>();
		try {
			flujoLectura = new ObjectInputStream(new FileInputStream(RUTA));
			boolean finalFichero = false;
			while (!finalFichero) {
				try {
					Coche coche = (Coche) flujoLectura.readObject();
					coches.add(coche);
				} catch (EOFException e) {
					finalFichero = true;
				}
			}
		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}
		return coches;
	}

	public static Coche consultarPorMatricula(String matricula) throws ClassNotFoundException, IOException {
		File fichero = new File(RUTA);
		ObjectInputStream flujoLectura = null;

		try {
			flujoLectura = new ObjectInputStream(new FileInputStream(fichero));
			boolean finalFichero = false;
			while (!finalFichero) {
				try {
					Coche coche = (Coche) flujoLectura.readObject();
					if (coche.getMatricula().equals(matricula)) {
						return coche;
					}
				} catch (EOFException e) {
					finalFichero = true;
				}
			}

		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}
		return null;
	}

	public static boolean insertarCoche(Coche cocheInsertar)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectOutputStream flujoSalida = null;
		MyObjectOutputStream flujosalida2 = null;
		Coche coche = null;
		boolean insertado = true;
		try {
			File fichero = new File(RUTA);
			if (fichero.exists()) {
				// Si el fichero ya existe, añadimos el coche al final del fichero sin crear
				// una nueva
				// cabecera de fichero
				coche = consultarPorMatricula(cocheInsertar.getMatricula());
				if (coche != null) {
					insertado = false;
				} else {
					flujosalida2 = new MyObjectOutputStream(new FileOutputStream(fichero, true));
					flujosalida2.writeObject(cocheInsertar);
				}

			} else {
				flujoSalida = new ObjectOutputStream(new FileOutputStream(fichero));
				flujoSalida.writeObject(cocheInsertar);

			}
		} finally {
			if (flujoSalida != null) {
				flujoSalida.close();
			}
			if (flujosalida2 != null) {
				flujosalida2.close();
			}
		}
		return insertado;
	}

	public static void mostrarCoches(List<Coche> coches) {
		// TODO Auto-generated method stub
		for (Coche coche : coches) {
			System.out.println(coche.toString());
		}
	}
}
