package org.training.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.training.model.City;
import org.training.service.util.SqlUtil;

import static org.junit.Assert.*;

public class GeographyServiceImplTest {

	private static String randomCityName = UUID.randomUUID().toString();
	
	private GeographyServiceImpl service;
	private Connection conn;
	
	@Before
	public void init() {
		this.service = new GeographyServiceImpl();
		this.service.init();
		try {
			this.conn = DriverManager.getConnection( "jdbc:mysql://localhost/dqmc?user=dqmc&password=dqmc123" );
		} catch (SQLException e) {
			throw new ExceptionInInitializerError( 
					"Problem initalizing GeographyService tests: " + e.getMessage() );
		}
	}
	
	@After
	public void destroy() {
		if ( this.service != null ) {
			this.service.destroy();
		}
		
		SqlUtil.close( conn );
	}
	
	@Test
	public void addCityTest() {
		
		City city = new City();
		city.setName( randomCityName );
		this.service.addCity( city );

		ResultSet rs = SqlUtil.executeSqlQuery( "SELECT * FROM city WHERE name= ('" + randomCityName + "')", conn );

		assertTrue( "No valid rows in ResultSet, Hibernate failed.", SqlUtil.hasResult( rs ) );
		
		String uuidFromDb = SqlUtil.getString( rs, "name"); 
		assertTrue( randomCityName + " :NOT EQUAL TO: " + uuidFromDb, 
				uuidFromDb.equals( randomCityName ) );
		this.service.removeCity( city );
	}

	@Test
	public void removeCityTest() {
		
		int result = SqlUtil.executeSqlUpdate( "INSERT INTO city (name) values ('" + randomCityName + "')", conn );
		assertEquals( "Unable to insert row into database.", result, 1 );
		
		City city = this.service.getCityByName( randomCityName );
		assertNotNull( "Was unable to reterieve city: " + randomCityName, city );
		assertEquals( city.getName(), randomCityName, city.getName() );
	}

}
