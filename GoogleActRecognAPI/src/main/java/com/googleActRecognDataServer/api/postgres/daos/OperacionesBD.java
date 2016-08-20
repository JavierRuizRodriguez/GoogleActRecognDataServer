package com.googleActRecognDataServer.api.postgres.daos;

import java.util.List;
import org.postgresql.util.PSQLException;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;

public class OperacionesBD {
	/* INSERCIONES */

	public void insertarNuevoIdCliente(String idCliente) throws PSQLException {
	}

	public void insertarNuevasActividades(String idCliente, List<ActividadGoogle> actividades) throws PSQLException{
	}

	/* LECTURAS */
	public List<ActividadGoogle> cogerActividadesPorID(String idCliente) throws PSQLException{
		return null;
	}

	public List<String> cogerTodosIdCliente() throws PSQLException{
		return null;
	}

	public String cogerIdCliente(String id) throws PSQLException{
		return null;
	}
}
