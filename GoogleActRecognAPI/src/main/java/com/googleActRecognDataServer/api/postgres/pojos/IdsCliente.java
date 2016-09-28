package com.googleActRecognDataServer.api.postgres.pojos;

/**
 * Clase que representa una tupla con los IDs que identifican cada dispositivo.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class IdsCliente {

	/**
	 * Cadena que representa el ID de anuncios.
	 */
	private String advIdCliente;
	/**
	 * Cadena que representa el ID de instancia.
	 */
	private String insIdCliente;

	/**
	 * Constructor de clase.
	 * 
	 * @param advIdCliente
	 *            Cadena que representa el ID de anuncios.
	 * @param insIdCliente
	 *            Cadena que representa el ID de instancia.
	 */
	public IdsCliente(String advIdCliente, String insIdCliente) {
		this.advIdCliente = advIdCliente;
		this.insIdCliente = insIdCliente;
	}

	public IdsCliente() {
	}

	public String getAdvIdCliente() {
		return advIdCliente;
	}

	public void setAdvIdCliente(String advIdCliente) {
		this.advIdCliente = advIdCliente;
	}

	public String getInsIdCliente() {
		return insIdCliente;
	}

	public void setInsIdCliente(String insIdCliente) {
		this.insIdCliente = insIdCliente;
	}

}
