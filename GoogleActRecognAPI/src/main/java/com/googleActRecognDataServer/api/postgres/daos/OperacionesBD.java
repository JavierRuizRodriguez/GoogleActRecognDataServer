package com.googleActRecognDataServer.api.postgres.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.dao.DataAccessException;

import com.googleActRecognDataServer.api.postgres.pojos.ActividadBandera;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

/**
 * Clase abstracta que define las operaciones que se podrán implementar para
 * ejecutar sobre la base de datos.
 * 
 * @author Javier Ruiz Rodríguez
 *
 *
 */
public abstract class OperacionesBD {

	/**
	 * Objeto para el log de acceso a la base de datos.
	 */
	protected Logger logAccesoBBDD = Logger.getLogger("LogAccesoBBDD");
	/**
	 * Manejador de archivos para el log.
	 */
	protected static FileHandler fh;
	/**
	 * Tamaño máximo del log.
	 */
	protected static final int TAM_MAX_LOG = 1024 * 1024;

	/**
	 * Método para insertar un nuevo cliente.
	 * 
	 * @param ids
	 *            identificadores del cliente.
	 */
	public void insertarNuevoIdCliente(IdsCliente ids) {
	}

	/**
	 * Método para registrar nueva actividad.
	 * 
	 * @param ids
	 *            identificadores del cliente.
	 * @param actividades
	 *            actividad realizada por el cliente.
	 * @param bandera
	 *            cadena que representa el lugar de la aplicación donde se ha
	 *            realizado la actividad por el cliente.
	 */
	public void insertarNuevasActividades(IdsCliente ids, List<ActividadGoogle> actividades, String bandera) {
	}

	/**
	 * Método para coger todas las actividades, junto con sus respectivas
	 * banderas, registradas en la base de datos durante el último día.
	 * 
	 * @return lista con un objeto que contiene, por cada entrada, la definición
	 *         de la actividad realizada y el lugar de la aplicación donde se
	 *         realizó.
	 */
	public List<ActividadBandera> cogerActividadesUltimoDia() {
		return null;
	}

	/**
	 * Método para coger todas las actividades, junto con sus respectivas
	 * banderas, registradas en la base de datos durante el último día, de un
	 * cliente en particular.
	 * 
	 * @param ids
	 *            identificadores del cliente.
	 * @return lista con un objeto que contiene, por cada entrada, la definición
	 *         de la actividad realizada y el lugar de la aplicación donde se
	 *         realizó.
	 */
	public List<ActividadBandera> cogerActividadesUltimoDiaDeCliente(IdsCliente ids) {
		return null;
	}

	/**
	 * Método para coger todos los clientes existentes en la base de datos.
	 * 
	 * @return lista con todos los clientes.
	 */
	public ArrayList<IdsCliente> cogerTodosClientes() {
		return null;
	}

}
