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

import com.googleActRecognDataServer.api.pojo.ActividadesGoogle;

@Path("/")
public class BaseController {

	@GET
	@Path("status")
	public Response status() {
		return Response.status(200).entity("OK").build();
	}

	@POST
	@Path("/data")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response nuevosDatos(ArrayList<ActividadesGoogle> actividades, @Context HttpServletRequest request) {
		if (actividades.size() != 0) {
			return Response.status(201).entity("Datos recibidos.").build();
		} else {
			return Response.status(400).entity("ERROR").build();
		}
	}
}
