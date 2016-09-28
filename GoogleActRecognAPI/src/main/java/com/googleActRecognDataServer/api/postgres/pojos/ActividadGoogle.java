package com.googleActRecognDataServer.api.postgres.pojos;

/**
 * Clase que representa la actividad y el coeficiente de dicha actividad, para
 * la inserción en la base de datos.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class ActividadGoogle {
	/**
	 * Cadena que representa la actividad realizada.
	 */
	public String actividad;
	/**
	 * Entero que representa el coeficiente de acierto de dicha actividad.
	 */
	public int coeficiente;

	/**
	 * Constructor de clase.
	 */
	public ActividadGoogle() {
	}

	/**
	 * Constuctor de clase.
	 * 
	 * @param actividad
	 *            Cadena que representa la actividad realizada.
	 * @param coeficiente
	 *            Entero que representa el coeficiente de acierto de dicha
	 *            actividad.
	 */
	public ActividadGoogle(String actividad, int coeficiente) {
		this.actividad = actividad;
		this.coeficiente = coeficiente;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public int getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(int coeficiente) {
		this.coeficiente = coeficiente;
	}
}
