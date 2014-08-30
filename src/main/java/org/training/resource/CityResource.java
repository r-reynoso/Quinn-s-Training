package org.training.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.service.GeographyService;

@Service
@Path("city")
public class CityResource {

	private GeographyService service;
	
	@Autowired
	public void setGeographyService( GeographyService service ) {
		this.service = service;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String topLevelCity() {
		
		return "Example!";
	}
	
}
