package com.googleActRecognDataServer.api.pojos;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase implementada mediante el patrón Singleton que sirve como contador
 * uniforme de informes.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class ContadorInformes {

	/**
	 * Instancia de la propia clase.
	 */
	private static ContadorInformes instancia;
	/**
	 * Contador atómico para mantener el ID de los informes.
	 */
	private static AtomicInteger contadorInforme = new AtomicInteger(0);

	/**
	 * Constructor de clase.
	 */
	private ContadorInformes() {
	}

	/**
	 * Método para coger la instacia de la clase. Si el parámetro 'instancia' no
	 * está inicializado, se inicializa.
	 * 
	 * @return instancia inicializada de esta clase.
	 */
	public static ContadorInformes getInstancia() {
		if (instancia == null)
			instancia = new ContadorInformes();
		return instancia;
	}

	/**
	 * Método para coger el contador atómico.
	 * 
	 * @return contador atómico.
	 */
	public int getIdContadorInformes() {
		return contadorInforme.incrementAndGet();
	}

}
