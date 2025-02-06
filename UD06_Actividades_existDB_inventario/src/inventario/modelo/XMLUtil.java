package inventario.modelo;

public class XMLUtil {
	public static String extraerTexto (String elemento, String etiqueta) {
		String marcaInicio = "<" + etiqueta + ">";
		String marcaFin = "</" + etiqueta + ">";
		int inicio = elemento.indexOf(marcaInicio) + marcaInicio.length(); // + marcaInicio.length() para que no incluya la marca de inicio
		int fin = elemento.indexOf(marcaFin);
		String texto = elemento.substring(inicio, fin);
		return texto;
	}
}
