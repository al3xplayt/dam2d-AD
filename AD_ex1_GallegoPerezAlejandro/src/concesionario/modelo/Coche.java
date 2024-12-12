package concesionario.modelo;

import java.io.Serializable;

public class Coche implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String matricula, modelo, marca;
	private int agnoFabricacion;
	private double precio;
	
	public Coche(String matricula, String modelo, String marca, int agnoFabricacion, double precio) {
		super();
		this.matricula = matricula;
		this.modelo = modelo;
		this.marca = marca;
		this.agnoFabricacion = agnoFabricacion;
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + ", agnoFabricacion="
				+ agnoFabricacion + ", precio=" + precio + "]";
	}

	public String getMatricula() {
		return matricula;
	}
	
	
	
}
