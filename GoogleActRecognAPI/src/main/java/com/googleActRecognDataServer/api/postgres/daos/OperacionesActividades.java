package com.googleActRecognDataServer.api.postgres.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import com.googleActRecognDataServer.api.postgres.PostgreSQL;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

public class OperacionesActividades extends OperacionesBD {

	public OperacionesActividades() {
		try {
			if(fh == null)
				fh = new FileHandler("logAccesoBBDD.log", TAM_MAX_LOG, 10, true);
			logAccesoBBDD.addHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertarNuevasActividades(IdsCliente ids, List<ActividadGoogle> actividades)
			throws DataAccessException {

		for (ActividadGoogle actividad : actividades) {
			try {
				PostgreSQL.getPostgresInterface().nuevaActividad(ids, actividad);
			} catch (DuplicateKeyException e) {
				logAccesoBBDD.info("PK duplicada actividad");
			} catch (DataAccessException e) {
				logAccesoBBDD.info("Error acceso BBDD");
			}
		}
	}

	@Override
	public List<ActividadGoogle> cogerActividadesPorIDs(IdsCliente ids) throws DataAccessException {
		return null;
	}
	
	@Override
	public List<ActividadGoogle> cogerUltimaActividadPorIds(IdsCliente ids) throws DataAccessException {
		return new ArrayList<ActividadGoogle>(PostgreSQL.getPostgresInterface().cogerUltimaActividadPorIds(ids));
	}
}
