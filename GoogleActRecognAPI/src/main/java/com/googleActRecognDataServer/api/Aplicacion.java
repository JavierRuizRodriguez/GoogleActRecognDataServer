package com.googleActRecognDataServer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

/**
 * Clase principal del servidor. Es la encargada de llamar a Spring Boot para
 * poner todos los componentes en marcha.
 * 
 * @author Javier Ruiz Rodríguez
 * 
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Aplicacion {

	/**
	 * Método principal del servidor. Es el encargado levantar el servidor.
	 * 
	 * @param args
	 *            cadenas de argumentos tomadas como parámetros de inicio.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Aplicacion.class, args);
	}

}
