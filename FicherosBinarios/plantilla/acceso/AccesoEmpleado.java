package plantilla.acceso;

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

import plantilla.modelo.Empleado;

public class AccesoEmpleado {

	public static final String RUTA = "dataBin/empleado.dat";

	public static void InsertarEmpleado(Empleado emple) throws FileNotFoundException, IOException {
		ObjectOutputStream flujoSalida = null;
		MyObjectOutputStream flujosalida2 = null;

		try {
			File fichero = new File(RUTA);
			if (fichero.exists()) {
				// Si el fichero ya existe, añadimos el empleado al final del fichero sin crear
				// una nueva
				// cabecera de fichero
				System.out.println("Existe");
				flujosalida2 = new MyObjectOutputStream(new FileOutputStream(fichero, true));
				flujosalida2.writeObject(emple); 
			} else {
				flujoSalida = new ObjectOutputStream(new FileOutputStream(fichero));
				flujoSalida.writeObject(emple);
			}
		} finally {
			if (flujoSalida != null) {
				flujoSalida.close();
			}
			if (flujosalida2 != null) {
				flujosalida2.close();
			}
		}
	}

	public static void escribirEmpleados(List<Empleado> empleados) {
		for (Empleado emple : empleados) {
			System.out.println(emple.toString());
		}
	}

	public static List<Empleado> consultarTodos() throws  IOException, ClassNotFoundException {
		ObjectInputStream flujoSalida = null;
		List<Empleado> empleados = new ArrayList<>();
		try {
			flujoSalida = new ObjectInputStream(new FileInputStream(RUTA));
			boolean finalFichero = false;
			while (!finalFichero) {
				try {
					Empleado emple = (Empleado) flujoSalida.readObject();
					empleados.add(emple);
				} catch (EOFException e) {
					finalFichero = true;
				}
			}
		} finally {
			if (flujoSalida != null) {
                flujoSalida.close();
			}
		}
		return empleados;
	}
}
