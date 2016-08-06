package com.googleActRecognDataServer.api.postgres;

import java.util.List;

import com.googleActRecognDataServer.api.postgres.pojos.Prueba;

public interface PostgreSQLService {

	public List<Prueba> getPruebas();
	
	public Prueba getUnaPrueba(String nombre);

}
