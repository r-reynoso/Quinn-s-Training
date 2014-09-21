package org.training.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
