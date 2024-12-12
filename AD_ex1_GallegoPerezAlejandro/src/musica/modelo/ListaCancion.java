package musica.modelo;


public class ListaCancion {

	private Lista lista;
	private Cancion cancion;
	private int posicion;
	
	public ListaCancion(Lista lista, Cancion cancion, int posicion) {
		this.lista = lista;
		this.cancion = cancion;
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return 
			"ListaCanci�n [C�digoLista = " + lista.getCodigo() + 
			", C�digoCanci�n = " + cancion.getCodigo() + 
			", Posici�n = " + posicion + "]";
	}
	
}
