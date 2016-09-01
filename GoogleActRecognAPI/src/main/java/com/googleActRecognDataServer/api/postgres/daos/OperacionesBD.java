package com.googleActRecognDataServer.api.postgres.daos;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.dao.DataAccessException;

import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

public class OperacionesBD {
	protected Logger logAccesoBBDD = Logger.getLogger("LogAccesoBBDD");  
	protected static FileHandler fh;  
	protected static final int TAM_MAX_LOG = 1024*1024;
	
	/* INSERCIONES */

	public void insertarNuevoIdCliente(IdsCliente ids) {
	}

	public void insertarNuevasActividades(IdsCliente ids, List<ActividadGoogle> actividades){
	}

	/* LECTURAS */
	public List<ActividadGoogle> cogerActividadesPorIDs(IdsCliente ids){
		return null;
	}

	public List<String> cogerTodosIdCliente(){
		return null;
	}
	
	public List<ActividadGoogle> cogerUltimaActividadPorIds(IdsCliente ids) throws DataAccessException {
		return null;
	}
}
