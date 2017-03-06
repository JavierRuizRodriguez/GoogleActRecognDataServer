package com.googleActRecognDataServer.api.pojos;

/**
 * POJO para la lectura sobre la base de datos de la tupla actividad y bandera.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class ActividadBandera {

	/**
	 * Cadena que representa la actividad realizada.
	 */
	public String actividad;
	/**
	 * Cadena que representa el lugar de la aplicación donde se realizó la
	 * actividad.
	 */
	public String bandera;

	/**
	 * Constructor de clase.
	 */
	public ActividadBandera() {
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

}
