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
	public static List<Empleado> cargarDatos(){
		return null;
		
	}
	public static List<Empleado> consultarTodos() throws IOException, ClassNotFoundException {
		ObjectInputStream flujoLectura = null;
		List<Empleado> empleados = new ArrayList<>();
		try {
			flujoLectura = new ObjectInputStream(new FileInputStream(RUTA));
			boolean finalFichero = false;
			while (!finalFichero) {
				try {
					Empleado emple = (Empleado) flujoLectura.readObject();
					empleados.add(emple);
				} catch (EOFException e) {
					finalFichero = true;
				}
			}
		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}
		return empleados;
	}

	public static boolean actualizarPorCodigo(int codigo, String nombre, String apellido, String departamento)
			throws ClassNotFoundException, IOException {
		List<Empleado> empleados = consultarTodos();
		for (Empleado emple : empleados) {
			if (emple.getCodigo() == codigo) {
				emple.setNombre(nombre);
				emple.setApellidos(apellido);
				emple.setDepartamento(departamento);
			} else {
				return false;
			}
		}
	    sobreescribirTodos(empleados);
		return true;
	}

	public static Empleado consultarPorCodigo(int codigo) throws ClassNotFoundException, IOException {
        List<Empleado> empleados = consultarTodos();
        for (Empleado emple : empleados) {
            if (emple.getCodigo() == codigo) {
                return emple;
            }
        }
        return null;
    }

	public static Empleado consultarPorCodigoV2(int codigo) throws ClassNotFoundException, IOException {
        File fichero = new File(RUTA);
        ObjectInputStream flujoLectura = null;
		try {
			flujoLectura = new ObjectInputStream(new FileInputStream(fichero));
			boolean finalFichero = false;
			while (!finalFichero) {
				try {
					Empleado emple = (Empleado) flujoLectura.readObject();
					if (emple.getCodigo() == codigo) {
						return emple;
					}
				} catch (EOFException e) {
					finalFichero = true;
				}
			}
			System.out.println("Empleado no encontrado");
		} finally {
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}
		return null;
    }

	public static void sobreescribirTodos(List<Empleado> empleados) {
		File fichero = new File(RUTA);
		ObjectOutputStream flujoSalida = null;
		try {
			flujoSalida = new ObjectOutputStream(new FileOutputStream(fichero));
			for (Empleado emple : empleados) {
				flujoSalida.writeObject(emple);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flujoSalida != null) {
				try {
					flujoSalida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static boolean eliminarPorCodigo(int codigo) throws ClassNotFoundException, IOException {
		if (consultarPorCodigo(codigo) == null) {
			return false;
		}
		List<Empleado> empleadosAux = consultarTodos();
		List<Empleado> empleados = new ArrayList<>();
		for (Empleado emple : empleadosAux) {
			if (emple.getCodigo() != codigo) {
				empleados.add(emple);
			}
		}
		sobreescribirTodos(empleados);
		return true;
	}
	public static void eliminarPorCodigoV2() {
		
	}
}
