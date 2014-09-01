package org.training.service.impl;

import org.apache.log4j.Logger;
import org.training.model.City;
import org.training.service.GeographyService;

public class GeographyServiceImpl implements GeographyService {

	private static final Logger LOG = Logger.getLogger( GeographyServiceImpl.class ); 
	
	@Override
	public void addCity( City city ) {
		LOG.debug( "Adding a city named '" + city.getName() + "' in the GeographyService!");
	}

}
