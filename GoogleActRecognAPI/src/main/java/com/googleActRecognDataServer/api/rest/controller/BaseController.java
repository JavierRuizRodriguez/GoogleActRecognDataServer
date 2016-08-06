package com.googleActRecognDataServer.api.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/")
public class BaseController {
	
	@GET
	@Path("status")
	public Response status() {
		return Response.status(200).entity("OK").build();
	}

}
