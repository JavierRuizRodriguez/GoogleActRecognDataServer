package com.ActivityRecognition.api.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

/**
 * Main controller configuration class.
 * 
 * @author AceroDocs
 *
 */
@ApplicationPath("/api")
@Component
public class JerseyConfig extends ResourceConfig {

	/**
	 * Method that is used to register all packages and all classes that will
	 * composed controller.
	 */
	public JerseyConfig() {
		packages("com.ActivityRecognition.api.rest.controller");
		register(RequestContextFilter.class);
		register(JacksonFeature.class);
	}

}
