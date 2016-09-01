package com.googleActRecognDataServer.api.postgres.daos;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import com.googleActRecognDataServer.api.postgres.PostgreSQL;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

public class OperacionesIdClientes extends OperacionesBD {

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

	@Override
	public void insertarNuevoIdCliente(IdsCliente ids) {
		try {
			PostgreSQL.getPostgresInterface().nuevoIdCliente(ids);
		} catch (DuplicateKeyException e) {
			logAccesoBBDD.info("PK duplicada idCliente");
		} catch (DataAccessException e) {
			logAccesoBBDD.info("Error acceso BBDD");
		}
	}

	@Override
	public List<String> cogerTodosIdCliente() {
		return null;
	}
}
