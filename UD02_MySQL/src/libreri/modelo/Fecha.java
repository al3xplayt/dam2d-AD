package libreri.modelo;

public class Fecha {

	private int agno;
	private int mes;
	private int dia;
	
	/**
	 * Crea una fecha a partir de un a�o, un mes y un d�a.
	 */
	public Fecha(int agno, int mes, int dia) {
		this.agno = agno;
		this.mes = mes;
		this.dia = dia;
	}
	
	/**
	 * Crea una fecha a partir de una cadena con formato AAAA-MM-DD 
	 * (est�ndar ISO 8601 de formato de fecha).
	 */
	public Fecha(String cadena) {
		String[] datos = cadena.split("-");
		this.agno = Integer.parseInt(datos[0]);
		this.mes = Integer.parseInt(datos[1]);
		this.dia = Integer.parseInt(datos[2]);
	}

	/**
	 * Devuelve una cadena con el estado de la fecha con formato AAAA-MM-DD 
	 * (est�ndar ISO 8601 de formato de fecha).
	 */
	@Override
	public String toString() {
		String fecha = String.format("%04d-%02d-%02d", agno, mes, dia);
		return fecha;
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
     * Valida una fecha seg�n las condiciones:
     * - Sigue el calendario gregoriano, que se basa en a�os bisiestos y a�os no bisiestos.
     * - El a�o debe ser igual o superior a 1583.
     * - El mes debe estar comprendido entre 1 y 12.
     * - El d�a debe estar comprendido entre 1 y el n�mero de d�as del mes del a�o.
     * Devuelve verdadero si la fecha es v�lida.
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
     * Devuelve el a�o de la fecha.
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
     * Devuelve el d�a de la fecha.
     */
	public int getDia() {
		return dia;
	}
	

}
