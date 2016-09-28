package com.googleActRecognDataServer.api.postgres.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import com.googleActRecognDataServer.api.postgres.PostgreSQL;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadBandera;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

/**
 * Clase que define la implementación de los métodos que se ejecutarán sobre la
 * tabla 'actividad'.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
public class OperacionesActividades extends OperacionesBD {

	/**
	 * Constructor de clase que inicializa el log.
	 */
	public OperacionesActividades() {
		try {
			if (fh == null)
				fh = new FileHandler("logs/logAccesoBBDD.log", TAM_MAX_LOG, 10, true);
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
	public void insertarNuevasActividades(IdsCliente ids, List<ActividadGoogle> actividades, String bandera)
			throws DataAccessException {

		for (ActividadGoogle actividad : actividades) {
			try {
				PostgreSQL.getInterfazPostgreSQL().nuevaActividad(ids, actividad, bandera);
			} catch (DuplicateKeyException e) {
				logAccesoBBDD.info("PK duplicada actividad");
			} catch (DataAccessException e) {
				logAccesoBBDD.info("Error acceso BBDD");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ActividadBandera> cogerActividadesUltimoDiaDeCliente(IdsCliente ids) {
		return new ArrayList<ActividadBandera>(PostgreSQL.getInterfazPostgreSQL().cogerActividadesUltimoDiaDeCliente(ids));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ActividadBandera> cogerActividadesUltimoDia() {
		return new ArrayList<ActividadBandera>(PostgreSQL.getInterfazPostgreSQL().cogerActividadesUltimoDia());
	}
}
