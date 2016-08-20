package com.googleActRecognDataServer.api.postgres.daos;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.postgresql.util.PSQLException;

import com.googleActRecognDataServer.api.postgres.PostgreSQL;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;

public class OperacionesActividades extends OperacionesBD {

	private Log log = LogFactory.getLog(OperacionesActividades.class);

	@Override
	public void insertarNuevasActividades(String idCliente, List<ActividadGoogle> actividades) throws PSQLException {

		for (ActividadGoogle actividad : actividades) {
			PostgreSQL.getPostgresInterface().nuevaActividad(idCliente, actividad);
		}
	}

	@Override
	public List<ActividadGoogle> cogerActividadesPorID(String idCliente) throws PSQLException {
		return null;
	}
}
