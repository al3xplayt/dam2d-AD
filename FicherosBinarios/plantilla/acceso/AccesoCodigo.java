package plantilla.acceso;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AccesoCodigo {
	public static final String RUTA = "dataBinario/codigo.dat";
	public static int obtenerCodigo() throws IOException {
		DataInputStream flujoEntrada = null;
		int codigo = 0;
		try {
			File fichero = new File(RUTA);
			if (fichero.exists()) {
				flujoEntrada = new DataInputStream(new FileInputStream(fichero));
				codigo = flujoEntrada.readInt();
			}
		} finally {
			try {
				if (flujoEntrada != null) {
					flujoEntrada.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return codigo;
	}

	public static void guardarCodigo(int codigo) throws IOException {
		DataOutputStream flujoSalida = null;
		try {
			flujoSalida = new DataOutputStream(new FileOutputStream(RUTA));
			flujoSalida.writeInt(codigo);
		} finally {
			if (flujoSalida != null) {
				flujoSalida.close();
			}
		}
	}
}
