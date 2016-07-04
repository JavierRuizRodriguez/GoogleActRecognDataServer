package com.ActivityRecognition.api.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/data")
public class DataController {
	@GET
	public Response home() {
		return Response.status(200).build();
	}
}
