package com.googleActRecognDataServer.api.rest.controller;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.postgresql.util.PSQLException;

import com.googleActRecognDataServer.api.postgres.daos.OperacionesActividades;
import com.googleActRecognDataServer.api.postgres.daos.OperacionesIdClientes;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.sun.jmx.snmp.Timestamp;

@Path("/")
public class BaseController {

	private OperacionesActividades opActividades = new OperacionesActividades();
	private OperacionesIdClientes opIdCliente = new OperacionesIdClientes();
	private java.util.Date date = new java.util.Date();

	@GET
	@Path("status")
	public Response status() {
		System.out.println("RECIBIDO status");
		return Response.status(200).entity("OK").build();
	}

	@POST
	@Path("data")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response nuevosDatos(ArrayList<ActividadGoogle> actividades, @Context HttpServletRequest peticion) throws PSQLException {

		System.out.println("RECIBIDO " + new Timestamp(date.getTime()));
		System.out.println("BANDERA --> " + peticion.getHeader("Bandera"));
		System.out.println("ID cliente --> " + peticion.getHeader("IDcliente"));

		String id = peticion.getHeader("IDcliente");

		if (actividades.size() != 0) {

			//pIdCliente.insertarNuevoIdCliente(id);
			opActividades.insertarNuevasActividades(id, actividades);

			return Response.status(201).entity("Datos recibidos.").build();

		} else {
			return Response.status(400).entity("ERROR").build();
		}

	}

	@GET
	@Path("oldData")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response nuevosDatos(@Context HttpServletRequest peticion) {
		System.out.println("ID cliente --> " + peticion.getHeader("IDcliente"));

		// coger ultima bandera introducida para el idCliente y replicarla con
		// un nuevo timestamp

		return Response.status(201).entity("Datos recibidos.").build();

	}
}
