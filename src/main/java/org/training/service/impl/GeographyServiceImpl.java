package org.training.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.error.ImpossibleQueryResultException;
import org.training.model.City;
import org.training.service.GeographyService;
import org.training.service.util.DatabaseUtil;

public class GeographyServiceImpl implements GeographyService {

	private static final Logger LOG = Logger.getLogger( GeographyServiceImplTest.class ); 
	
	private Session session;
	
	public void init() {
		SessionFactory sessionFactory = DatabaseUtil.getInstance().getSessionFactory();
		this.session = sessionFactory.openSession();
	}
	
	public void destroy() {
		if ( this.session != null ) {
			this.session.close();
		}
	}

	public void addCity( City city ) {
		LOG.debug( "Adding a city, NAME: " + city.getName() );
		this.session.beginTransaction();
		this.session.save( city );
		this.session.getTransaction().commit();
	}
	
	public void removeCity( City city ) {
		LOG.debug( "Deleting a city, NAME: " + city.getName() );
		this.session.beginTransaction();
		this.session.delete( city );
		this.session.getTransaction().commit();
	}
	
	public City getCityByName( String cityName ) {
		
		LOG.debug( "Searching for city by name, NAME: " + cityName );
		
		Query cityQuery = session.createQuery( "FROM City where name = :name" );
		cityQuery.setParameter( "name", cityName );
		
		List<?> result = cityQuery.list();
		if ( result.size() > 1 ) {
			throw new ImpossibleQueryResultException( "Found more than one town by the name " 
					+ cityName + " in the database.  Application data is probably corrupt." );
		} else if ( result.size() < 1 ) {
			return null;
		}
		
		return ( ( City ) result.get( 0 ) );
	}
}
