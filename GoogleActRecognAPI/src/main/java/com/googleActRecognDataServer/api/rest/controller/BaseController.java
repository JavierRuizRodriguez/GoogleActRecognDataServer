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
import org.rosuda.JRI.Rengine;

import com.googleActRecognDataServer.api.postgres.daos.OperacionesActividades;
import com.googleActRecognDataServer.api.postgres.daos.OperacionesIdClientes;
import com.googleActRecognDataServer.api.postgres.pojos.ActividadGoogle;
import com.googleActRecognDataServer.api.postgres.pojos.IdsCliente;
import com.googleActRecognDataServer.api.postgres.pojos.MotorR;
import com.googleActRecognDataServer.api.utilidades.CrearMatrizDatos;

@Path("/")
public class BaseController {

	private OperacionesActividades opActividades = new OperacionesActividades();
	private OperacionesIdClientes opIdCliente = new OperacionesIdClientes();
	private java.util.Date date = new java.util.Date();
	private Rengine motorR;
	
	@GET
	@Path("status")
	public Response status() {
		System.out.println("RECIBIDO status");
		
		CrearMatrizDatos cmd = new CrearMatrizDatos();
		cmd.montarMatrizDatos();
		
		/*String javaVector = "c(1,2,3,4,5)";

		motorR = MotorR.getInstacia().getMotorR();
		
		if (!motorR.waitForR())
        {
            System.out.println ("Cannot load R");
        }

		// The vector that was created in JAVA context is stored in 'rVector'
		// which is a variable in R context.
		motorR.eval("rVector=" + javaVector);

		// Calculate MEAN of vector using R syntax.
		motorR.eval("meanVal=mean(rVector)");

		// Retrieve MEAN value
		double mean = motorR.eval("meanVal").asDouble();

		// Print output values
		System.out.println("Mean of given vector is= " + mean);*/
		
		return Response.status(200).entity("OK").build();
	}

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

	@POST
	@Path("oldData")
	@Produces({ MediaType.TEXT_HTML })
	public Response nuevosDatos(@Context HttpServletRequest peticion) {

		System.out.println("----- SINCRO DATOS VIEJOS -----");
		mostrarDatosIdsEnConsola(peticion);

		IdsCliente ids = new IdsCliente(peticion.getHeader("advIdCliente"), peticion.getHeader("insIdCliente"));
		try {
			opActividades.insertarNuevasActividades(ids, opActividades.cogerUltimaActividadPorIds(ids), opActividades.cogerUltimaBanderaPorIds(ids));
			return Response.status(201).entity("Datos recibidos.").build();
		} catch (Exception e) {
			return Response.status(400).entity("ERROR").build();
		}
	}

	private void mostrarDatosIdsEnConsola(HttpServletRequest peticion) {
		System.out.println("RECIBIDO " + new Timestamp(date.getTime()));
		System.out.println("ID cliente --> " + peticion.getHeader("advIdCliente"));
		System.out.println("ID cliente --> " + peticion.getHeader("insIdCliente"));
	}
}
