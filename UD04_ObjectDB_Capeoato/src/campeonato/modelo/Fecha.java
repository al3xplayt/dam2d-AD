package campeonato.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Fecha {

	private int agno;
	private int mes;
	private int dia;
	
	/**
	 * Crea una fecha a partir de un año, un mes y un día.
	 */
	public Fecha(int agno, int mes, int dia) {
		this.agno = agno;
		this.mes = mes;
		this.dia = dia;
	}
	
	/**
	 * Crea una fecha a partir de una cadena con formato AAAA-MM-DD 
	 * (estándar ISO 8601 de formato de fecha).
	 */
	public Fecha(String cadena) {
		String[] datos = cadena.split("-");
		this.agno = Integer.parseInt(datos[0]);
		this.mes = Integer.parseInt(datos[1]);
		this.dia = Integer.parseInt(datos[2]);
	}

	/**
	 * Devuelve una cadena con el estado de la fecha con formato AAAA-MM-DD 
	 * (estándar ISO 8601 de formato de fecha).
	 */
	@Override
	public String toString() {
		String fecha = String.format("%04d-%02d-%02d", agno, mes, dia);
		return fecha;
	}
	
	/**
	 * Devuelve verdadero si un año es bisiesto. 
	 * Devuelve falso en caso contrario.
	 * Un año es bisiesto si cumple dos condiciones: 
	 * - El año es divisible entre 4.
	 * - El año no es divisible entre 100 o el año es divisible entre 400.
	 */
	private static boolean esBisiesto(int agno) {
		boolean bisiesto = false;
		if (agno >= 1583) {
			if ((agno % 4 == 0) && (agno % 100 != 0 || agno % 400 == 0)) {
				bisiesto = true;
			}
		}
		return bisiesto;
	}

	/**
	 * Devuelve el número de días que tiene un mes de un año: 
	 * - Febrero tiene 28 días si el año no es bisiesto. 
	 * - Febrero tiene 29 días si el año es bisiesto.
	 * - Abril, Junio, Septiembre y Noviembre tienen 30 días. 
	 * - Enero, Marzo, Mayo, Julio, Agosto, Octubre y Diciembre tienen 31 días.
	 */
	private static int obtenerNumeroDiasDeMes(int agno, int mes) {
		int numeroDiasMes = 31;
		if (mes == 2) {
			if (esBisiesto(agno)) {
				numeroDiasMes = 29;
			} 
			else {
				numeroDiasMes = 28;
			}
		} 
		else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			numeroDiasMes = 30;
		}
		return numeroDiasMes;
	}

	/**
	 * Valida una fecha según las condiciones: 
	 * - Sigue el calendario gregoriano, que se basa en años bisiestos y años no bisiestos. 
	 * - El año debe ser igual o superior a 1583. 
	 * - El mes debe estar comprendido entre 1 y 12. 
	 * - El día debe estar comprendido entre 1 y el número de días del mes del año. 
	 * Devuelve verdadero si la fecha es válida. 
	 * Devuelve falso en caso contrario.
	 */
	public static boolean esValida(int agno, int mes, int dia) {
		boolean valida = true;
		if (agno < 1583) {
			valida = false;
		} 
		else if (mes < 1 || mes > 12) {
			valida = false;
		} 
		else if (dia < 1 || dia > obtenerNumeroDiasDeMes(agno, mes)) {
			valida = false;
		}
		return valida;
	}

	/**
	 * Devuelve el año de la fecha.
	 */
	public int getAgno() {
		return agno;
	}

	/**
	 * Devuelve el mes de la fecha.
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Devuelve el día de la fecha.
	 */
	public int getDia() {
		return dia;
	}
	
}
