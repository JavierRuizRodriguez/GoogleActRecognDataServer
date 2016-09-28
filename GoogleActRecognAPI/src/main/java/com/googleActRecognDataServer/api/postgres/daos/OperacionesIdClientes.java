package com.googleActRecognDataServer.api.postgres.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import com.googleActRecognDataServer.api.postgres.PostgreSQL;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

/**
 * Clase que define la implementación de los métodos que se ejecutarán sobre la
 * tabla 'cliente'.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class OperacionesIdClientes extends OperacionesBD {
	
	/**
	 * Constructor de clase que inicializa el log.
	 */
	public OperacionesIdClientes() {
		try {
			if (fh == null)
				fh = new FileHandler("logAccesoBBDD.log", TAM_MAX_LOG, 10, true);
			logAccesoBBDD.addHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertarNuevoIdCliente(IdsCliente ids) {
		try {
			PostgreSQL.getInterfazPostgreSQL().nuevoIdCliente(ids);
		} catch (DuplicateKeyException e) {
			logAccesoBBDD.info("PK duplicada idCliente");
		} catch (DataAccessException e) {
			logAccesoBBDD.info("Error acceso BBDD");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<IdsCliente> cogerTodosClientes(){
		return PostgreSQL.getInterfazPostgreSQL().cogerTodosClientes();
	}
}
