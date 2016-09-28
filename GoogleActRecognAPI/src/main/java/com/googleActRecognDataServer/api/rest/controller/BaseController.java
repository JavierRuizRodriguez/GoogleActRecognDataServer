package com.googleActRecognDataServer.api.rest.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.postgresql.util.PSQLException;

import com.googleActRecognDataServer.api.postgres.daos.OperacionesActividades;
import com.googleActRecognDataServer.api.postgres.daos.OperacionesIdClientes;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;

/**
 * Controlador principal de la API RESTFul.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
@Path("/")
public class BaseController {

	/**
	 * Objeto utilizado para la ejecución de scrpits la tabla 'actividad'.
	 */
	private OperacionesActividades opActividades = new OperacionesActividades();
	/**
	 * Objeto utilizado para la ejecución de scrpits la tabla 'cliente'.
	 */
	private OperacionesIdClientes opIdCliente = new OperacionesIdClientes();

	/**
	 * Endpoint GET que indica el estado de la API. Sirve para comprobar si la
	 * API está o no levantada.
	 * 
	 * @return respuesta HTTP.
	 */
	@GET
	@Path("status")
	public Response status() {
		System.out.println("RECIBIDO status");

		return Response.status(200).entity("OK").build();
	}

	/**
	 * Endpoint POST donde se realizan las peticiones desde la librería AAR para
	 * la inserción de nuevos datos en la base de datos.
	 * 
	 * @param actividades
	 *            representa las actividades capturadas.
	 * @param peticion
	 *            objeto que contiene las cabeceras de la petición.
	 * @return respuesta HTTP.
	 * @throws PSQLException
	 */
	@POST
	@Path("data")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response nuevosDatos(ArrayList<ActividadGoogle> actividades, @Context HttpServletRequest peticion)
			throws PSQLException {

		System.out.println("----- SINCRO DATOS NUEVOS -----");
		mostrarDatosIdsEnConsola(peticion);
		System.out.println("BANDERA --> " + peticion.getHeader("Bandera"));
		IdsCliente ids = new IdsCliente(peticion.getHeader("advIdCliente"), peticion.getHeader("insIdCliente"));

		if (actividades.size() != 0) {

			opIdCliente.insertarNuevoIdCliente(ids);
			opActividades.insertarNuevasActividades(ids, actividades, peticion.getHeader("Bandera"));

			return Response.status(201).entity("Datos recibidos.").build();

		} else {
			return Response.status(400).entity("ERROR").build();
		}

	}

	/**
	 * Método para mostrar por la consola del servidor, los datos recibidos.
	 * 
	 * @param peticion
	 *            objeto con las cabeceras de la petición.
	 */
	private void mostrarDatosIdsEnConsola(HttpServletRequest peticion) {
		System.out.println("RECIBIDO " + new Timestamp(new java.util.Date().getTime()));
		System.out.println("ID cliente --> " + peticion.getHeader("advIdCliente"));
		System.out.println("ID cliente --> " + peticion.getHeader("insIdCliente"));
	}
}
