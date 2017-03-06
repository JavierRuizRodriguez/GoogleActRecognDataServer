package com.googleActRecognDataServer.api.rest.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.googleActRecognDataServer.api.cassandra.daos.BalizaCrud;
import com.googleActRecognDataServer.api.cassandra.pojos.Baliza;
import com.googleActRecognDataServer.api.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.pojos.IdsCliente;

/**
 * Controlador principal de la API RESTFul.
 * 
 * @author Javier Ruiz Rodríguez
 *
 */
@Path("/")
public class BaseController {

	@Autowired
	private ApplicationContext applicationContext;
	
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
	@Produces({ MediaType.TEXT_HTML })
	public Response nuevosDatos(@Context HttpServletRequest peticion) {

		Date fecha = new Date();
		Baliza baliza = new Baliza(cogerFechaActual(),peticion.getHeader("advIdCliente"), peticion.getHeader("insIdCliente"),
				new Timestamp(fecha.getTime()), peticion.getHeader("actividad"), peticion.getHeader("bandera"),Long.parseLong(peticion.getHeader("duracion")));

		System.out.println("----- SINCRO DATOS NUEVOS -----");
		mostrarDatosIdsEnConsola(peticion);

		BalizaCrud balizaCrud = applicationContext.getBean(BalizaCrud.class);
		baliza = balizaCrud.añadirBaliza(baliza);

		return Response.status(201).entity("Datos recibidos.").build();

	}

	private String cogerFechaActual() {
	        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
	        Calendar calendario = Calendar.getInstance();
	        return formatoFecha.format(calendario.getTime());
	}
	

	/**
	 * Método para mostrar por la consola del servidor, los datos recibidos.
	 * 
	 * @param peticion
	 *            objeto con las cabeceras de la petición.
	 */
	private void mostrarDatosIdsEnConsola(HttpServletRequest peticion) {
		System.out.println("RECIBIDO " + new Timestamp(new java.util.Date().getTime()));
		System.out.println("ID adv cliente --> " + peticion.getHeader("advIdCliente"));
		System.out.println("ID ins cliente --> " + peticion.getHeader("insIdCliente"));
		System.out.println("-Actividad --> " + peticion.getHeader("actividad"));
		System.out.println("- Bandera --> " + peticion.getHeader("bandera"));
		System.out.println("- Duracion --> " + peticion.getHeader("duracion"));
	}
}
