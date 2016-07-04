package com.servidor.aceroDoc.rest.controlador;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.servidor.aceroDoc.rest.utilidades.*;

public class Aplicacion extends ResourceConfig{
	public Aplicacion(){
		register(RequestContextFilter.class);
		register(ControladorUsuario.class);
		register(JacksonFeature.class);	
		register(CORS_filtro.class);
		register(filtro_log.class);
	}	
}
