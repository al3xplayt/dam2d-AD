package campeonato.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Resultado {
	private int puntuacionLocal;
	private int puntuacionVisitante;
	public Resultado(int puntuacionLocal, int puntuacionVisitante) {
		super();
		this.puntuacionLocal = puntuacionLocal;
		this.puntuacionVisitante = puntuacionVisitante;
	}
	
	public Resultado(String cadena) {
        super();
        String[] puntuaciones = cadena.split("-");
        this.puntuacionLocal = Integer.parseInt(puntuaciones[0]);
        this.puntuacionVisitante = Integer.parseInt(puntuaciones[1]);
	}
	public int getPuntuacionLocal() {
		return puntuacionLocal;
	}
	public void setPuntuacionLocal(int puntuacionLocal) {
		this.puntuacionLocal = puntuacionLocal;
	}
	public int getPuntuacionVisitante() {
		return puntuacionVisitante;
	}
	public void setPuntuacionVisitante(int puntuacionVisitante) {
		this.puntuacionVisitante = puntuacionVisitante;
	}
	@Override
	public String toString() {
		String resultados = String.format("%d-%d", puntuacionLocal, puntuacionVisitante);
		return resultados;
	}
	
	
}
