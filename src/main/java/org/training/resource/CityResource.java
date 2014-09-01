package org.training.resource;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.training.model.City;
import org.training.service.GeographyService;

@Service
@Path("city")
public class CityResource {

	private static final Logger LOG = Logger.getLogger( CityResource.class );
	
	private GeographyService service;
	public void setGeographyService( GeographyService service ) {
		this.service = service;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCities() {
		
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "Attempting to get list of cities." );
		}
		
		return "List of Cities!\n";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addCity( City city ) {
		
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "Attempting to add a city." );
		}
		
		service.addCity( city );
		return "City Added!\n";
	}
	
}
