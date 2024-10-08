package alumnos.acceso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccesoCodigo {
	public static final String FicheroAccesoCodigo = "data/CodigoAlumno.txt";

	public static int obtenerCodigo() throws IOException {
		int codigo = 0;
		BufferedReader flujoLectura = null;
		try {
			File fichero = new File(FicheroAccesoCodigo);
			String line;
			if (fichero.exists()) {
				flujoLectura = new BufferedReader(new FileReader(fichero));
				line = flujoLectura.readLine();
				codigo = Integer.parseInt(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (flujoLectura != null) {

				flujoLectura.close();

			}
		}
		return codigo;
	}

	public static void EscribirCodigo(int codigo) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(FicheroAccesoCodigo));
			writer.write(String.format("%d", codigo));
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
