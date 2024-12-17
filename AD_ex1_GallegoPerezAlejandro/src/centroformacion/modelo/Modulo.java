package centroformacion.modelo;
// Generated 3 dic 2024, 19:02:01 by Hibernate Tools 5.4.33.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Modulo generated by hbm2java
 */
public class Modulo implements java.io.Serializable {

	private int codigo;
	private Ciclo ciclo;
	private String nombre;
	private String curso;
	private int duracion;
	private int creditos;
	private Set lineaMatriculas = new HashSet(0);

	public Modulo() {
	}

	public Modulo(int codigo, Ciclo ciclo, String nombre, String curso, int duracion, int creditos) {
		this.codigo = codigo;
		this.ciclo = ciclo;
		this.nombre = nombre;
		this.curso = curso;
		this.duracion = duracion;
		this.creditos = creditos;
	}

	public Modulo(int codigo, Ciclo ciclo, String nombre, String curso, int duracion, int creditos,
			Set lineaMatriculas) {
		this.codigo = codigo;
		this.ciclo = ciclo;
		this.nombre = nombre;
		this.curso = curso;
		this.duracion = duracion;
		this.creditos = creditos;
		this.lineaMatriculas = lineaMatriculas;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Ciclo getCiclo() {
		return this.ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCreditos() {
		return this.creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public Set getLineaMatriculas() {
		return this.lineaMatriculas;
	}

	public void setLineaMatriculas(Set lineaMatriculas) {
		this.lineaMatriculas = lineaMatriculas;
	}

}