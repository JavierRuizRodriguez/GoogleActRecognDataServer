package com.googleActRecognDataServer.api.postgres;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.googleActRecognDataServer.api.postgres.pojos.ActividadBandera;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

/**
 * Interfaz que define los métodos que se ejecutarán sobre la base de datos.
 * 
 * @author Javier Ruiz Rodríguez
 * 
 */
public interface ServicioPostgreSQL {

	/**
	 * Método para registrar nueva actividad.
	 * 
	 * @param ids
	 *            identificadores del cliente.
	 * @param actividad
	 *            actividad realizada por el cliente.
	 * @param bandera
	 *            cadena que representa el lugar de la aplicación donde se ha
	 *            realizado la actividad por el cliente.
	 */
	public void nuevaActividad(@Param("ids") IdsCliente ids, @Param("actividad") ActividadGoogle actividad,
			@Param("bandera") String bandera);

	/**
	 * Método para insertar un nuevo cliente.
	 * 
	 * @param ids
	 *            identificadores del cliente.
	 */
	public void nuevoIdCliente(@Param("ids") IdsCliente ids);

	/**
	 * Método para coger todas las actividades, junto con sus respectivas
	 * banderas, registradas en la base de datos durante el último día.
	 * 
	 * @return lista con un objeto que contiene, por cada entrada, la definición
	 *         de la actividad realizada y el lugar de la aplicación donde se
	 *         realizó.
	 */
	public List<ActividadBandera> cogerActividadesUltimoDia();

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
	public List<ActividadBandera> cogerActividadesUltimoDiaDeCliente(@Param("ids") IdsCliente ids);

	/**
	 * Método para coger todos los clientes existentes en la base de datos.
	 * 
	 * @return lista con todos los clientes.
	 */
	public ArrayList<IdsCliente> cogerTodosClientes();
}
