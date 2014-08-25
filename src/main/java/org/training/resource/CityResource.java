package org.training.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("city")
public class CityResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String topLevelCity() {
		return "Example!";
	}
	
}
