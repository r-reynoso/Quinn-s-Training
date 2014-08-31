package org.training.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.service.GeographyService;

@Service
@Path("city")
public class CityResource {

	private static final Logger LOG = Logger.getLogger( CityResource.class );
	
	private static GeographyService service;
	
	@Autowired
	public void setGeographyService( GeographyService service ) {
		CityResource.service = service;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String topLevelCity() {
		
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "Cities REST layer accessed, GeographyService: " + service );
		}
		
		return "Example!";
	}
	
}
