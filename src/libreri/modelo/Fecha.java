package libreri.modelo;

public class Fecha {

	private int dia;
	private int mes;
	private int agno;

	/**
	 * Crea una fecha a partir de un día, un mes y un a�o.
	 */
	public Fecha(int dia, int mes, int agno) {
		this.dia = dia;
		this.mes = mes;
		this.agno = agno;
	}

	/**
	 * Crea una fecha a partir de una cadena con formato DD/MM/AAAA.
	 */
	public Fecha(String cadena) {
		String[] datos = cadena.split("-");
		this.dia = Integer.parseInt(datos[0]);
		this.mes = Integer.parseInt(datos[1]);
		this.agno = Integer.parseInt(datos[2]);
	}

	/**
	 * Devuelve verdadero si un a�o es bisiesto. 
	 * Devuelve falso en caso contrario.
	 * Un a�o es bisiesto si cumple dos condiciones: 
	 * - El a�o es divisible entre 4.
	 * - El a�o no es divisible entre 100 o el a�o es divisible entre 400.
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
	 * Devuelve el n�mero de d�as que tiene un mes de un a�o: 
	 * - Febrero tiene 28 d�as si el a�o no es bisiesto. 
	 * - Febrero tiene 29 d�as si el a�o es bisiesto.
	 * - Abril, Junio, Septiembre y Noviembre tienen 30 d�as. 
	 * - Enero, Marzo, Mayo, Julio, Agosto, Octubre y Diciembre tienen 31 d�as.
	 */
	private static int obtenerNumeroDiasDeMes(int mes, int agno) {
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
	 * Valida una fecha seg�n las condiciones: 
	 * - Sigue el calendario gregoriano, que se basa en a�os bisiestos y a�os no bisiestos. 
	 * - El a�o debe ser igual o superior a 1583. 
	 * - El mes debe estar comprendido entre 1 y 12. 
	 * - El d�a debe estar comprendido entre 1 y el n�mero de d�as del mes del a�o. 
	 * Devuelve verdadero si la fecha es v�lida. 
	 * Devuelve falso en caso contrario.
	 */
	public static boolean esValida(int dia, int mes, int agno) {
		boolean valida = true;
		if (agno < 1583) {
			valida = false;
		} 
		else if (mes < 1 || mes > 12) {
			valida = false;
		} 
		else if (dia < 1 || dia > obtenerNumeroDiasDeMes(mes, agno)) {
			valida = false;
		}
		return valida;
	}

	/**
	 * Devuelve una cadena de caracteres con el estado de la fecha con formato DD/MM/AAAA.
	 */
	@Override
	public String toString() {
		return String.format("%02d/%02d/%04d", dia, mes, agno);
	}

	/**
	 * Devuelve el d�a de la fecha.
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * Devuelve el mes de la fecha.
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Devuelve el a�o de la fecha.
	 */
	public int getAgno() {
		return agno;
	}

	/**
	 * Devuelve verdadero si la fecha es igual que otra fecha. 
	 * Devuelve falso en caso contrario.
	 */
	public boolean esIgualQue(Fecha otra) {
		if (this.agno == otra.agno && this.mes == otra.mes && this.dia == otra.dia) {
			return true;
		} 
		else {
			return false;
		}
	}

	/**
	 * Devuelve verdadero si la fecha es menor o igual que otra fecha. 
	 * Devuelve falso en caso contrario.
	 */
	public boolean esMenorIgualQue(Fecha otra) {
		int esteNumero = this.agno * 10000 + this.mes * 100 + this.dia;
		int otroNumero = otra.agno * 10000 + otra.mes * 100 + otra.dia;
		return esteNumero <= otroNumero;
	}

	/**
	 * Devuelve verdadero si la fecha es mayor o igual que otra fecha. 
	 * Devuelve falso en caso contrario.
	 */
	public boolean esMayorIgualQue(Fecha otra) {
		int esteNumero = this.agno * 10000 + this.mes * 100 + this.dia;
		int otroNumero = otra.agno * 10000 + otra.mes * 100 + otra.dia;
		return esteNumero >= otroNumero;
	}

}
