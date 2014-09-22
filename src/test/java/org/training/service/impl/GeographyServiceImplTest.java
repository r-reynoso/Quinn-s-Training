package org.training.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	private Connection conn;
	private GeographyServiceImpl service;
	
	@Before
	public void init() {
		this.service = new GeographyServiceImpl();
		this.service.init();
		try {
			this.conn = DriverManager.getConnection( "jdbc:mysql://localhost/dqmc?user=dqmc&password=dqmc123" );
		} catch (SQLException e) {
			throw new ExceptionInInitializerError( 
					"Problem initalizing GeographyService tests: " + e.getMessage() );
		} finally {
			SqlUtil.close( conn );
		}
	}
	
	@After
	public void destroy() {
		SqlUtil.close( conn );
		if ( this.service != null ) {
			this.service.destroy();
		}		
	}
	
	@Test
	public void addCityTest() {
		
		String uuid = UUID.randomUUID().toString();
		
		City city = new City();
		city.setName( uuid );
		this.service.addCity(city);

		ResultSet rs = SqlUtil.executeSqlQuery( "SELECT * FROM city WHERE name= ('" + uuid + "')", conn );

		if ( ! SqlUtil.hasResult( rs ) ) {				
			fail( "No valid rows in ResultSet, Hibernate failed." );
		} else {
			String uuidFromDb = SqlUtil.getString( rs, "name"); 
			assertTrue( uuid + " :NOT EQUAL TO: " + uuidFromDb, 
					uuidFromDb.equals( uuid ) );
			this.service.removeCity( city );
		}
	}

	@Test
	public void removeServiceTest() {
		
	}

}
