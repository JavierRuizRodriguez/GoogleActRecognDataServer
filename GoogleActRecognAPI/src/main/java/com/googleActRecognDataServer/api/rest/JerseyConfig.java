package com.googleActRecognDataServer.api.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

/**
 * Clase donde se registran las clases que conforman la API RESTful.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
@ApplicationPath("/api")
@Component
public class JerseyConfig extends ResourceConfig {

	/**
	 * Constructor de clase. Método donde se registran las librerías y las clases que conforman los
	 * controladores RESTful.
	 */
	public JerseyConfig() {
		packages("com.googleActRecognDataServer.api.rest.controller");
		register(RequestContextFilter.class);
		register(JacksonFeature.class);
	}

}
