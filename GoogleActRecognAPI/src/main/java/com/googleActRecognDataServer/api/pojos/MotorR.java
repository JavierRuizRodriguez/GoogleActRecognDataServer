package com.googleActRecognDataServer.api.pojos;

import org.rosuda.JRI.Rengine;

/**
 * Clase implementada mediante el patrón Singleton que representa el motor de la
 * tecnología de análisis de datos R.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class MotorR {

	/**
	 * Instancia de la propia clase.
	 */
	private static MotorR instancia = null;
	/**
	 * Objeto que representa el motor R.
	 */
	private final Rengine motorR = new Rengine(new String[] { "-vanilla -no-save" }, false, null);
	/**
	 * Cadena que representa la función personalizada encargada de tratar los
	 * datos y convertirlos en las tablas necesarias.
	 */
	private static final String FUNCION_TRATAR_TABLA = "tratarTabla = function(tabla){tabla <- ddply(tabla,.(Actividad,Bandera,Duracion),nrow); tabla$ActBanDur <- paste(paste(tabla$Actividad,tabla$Bandera,sep=\"\n\"),tabla$Duracion,sep=\"\n\"); tabla$Actividad = NULL; tabla$Bandera = NULL; tabla$Duracion = NULL; colnames(tabla)[1] = \"Total\"; tabla <- tabla[order(tabla$Total, decreasing = TRUE),]}";
	/**
	 * Cadena que representa la función personalizada que, dependiendo de los
	 * datos, genera un diagrama de tallo y hojas de una escala determinada.
	 */
	private static final String FUNCION_DIAGRAMA_HOJAS = "dTalloHojas = function(x){ digitos = nchar(as.integer(mean(x$Total))); if(digitos <= 1){stem(x$Total, scale = 1)} else {stem(x$Total, digitos - 1)}}";

	/**
	 * Constructor de clase.
	 */
	protected MotorR() {
	}

	/**
	 * Método para coger la instancia de la clase. Si el parámetro 'instancia'
	 * no está inicializado, se inicializa.
	 * 
	 * @return instancia inicializada de esta clase.
	 */
	public static MotorR getInstacia() {
		if (instancia == null)
			inicializarMotorR();
		return instancia;
	}

	/**
	 * Método para la inicialización de la instancia. También definen en el
	 * sistema R las funciones personalizadas para el tratamiento de datos.
	 */
	private static void inicializarMotorR() {
		instancia = new MotorR();
		instancia.getMotorR().eval(FUNCION_DIAGRAMA_HOJAS);
		instancia.getMotorR().eval(FUNCION_TRATAR_TABLA);
	}

	/**
	 * Método para coger el motor R.
	 * 
	 * @return motor R.
	 */
	public Rengine getMotorR() {
		return motorR;
	}
}
