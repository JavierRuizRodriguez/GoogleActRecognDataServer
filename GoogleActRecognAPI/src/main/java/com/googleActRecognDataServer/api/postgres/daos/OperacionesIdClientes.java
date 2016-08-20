package com.googleActRecognDataServer.api.postgres.daos;

import java.util.List;

import org.postgresql.util.PSQLException;
import com.googleActRecognDataServer.api.postgres.PostgreSQL;

public class OperacionesIdClientes extends OperacionesBD {
		
	@Override
	public void insertarNuevoIdCliente(String idCliente) throws PSQLException {
		PostgreSQL.getPostgresInterface().nuevoIdCliente(idCliente);
	}

	@Override
	public List<String> cogerTodosIdCliente() throws PSQLException {
		return null;
	}

	@Override
	public String cogerIdCliente(String id) throws PSQLException {
		return null;
	}
}
