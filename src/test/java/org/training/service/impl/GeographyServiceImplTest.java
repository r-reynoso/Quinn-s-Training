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

import static org.junit.Assert.*;

public class GeographyServiceImplTest {

	private GeographyServiceImpl service;
	
	@Before
	public void init() {
		this.service = new GeographyServiceImpl();
		this.service.init();
	}
	
	@After
	public void destroy() {
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
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection( "jdbc:mysql://localhost/dqmc?user=dqmc&password=dqmc123" );
			PreparedStatement ps = conn.prepareStatement( "SELECT * FROM city WHERE name= ('" + uuid + "')" );
			ResultSet rs = ps.executeQuery();
			if ( ! rs.first() ) {				
				fail( "No valid rows in ResultSet, Hibernate failed." );
			} else {
				String uuidFromDb = rs.getString("name"); 
				assertTrue( uuid + " :NOT EQUAL TO: " + uuidFromDb, 
						uuidFromDb.equals( uuid ) );
				this.service.removeCity( city );
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			close( conn );
		}
	}

	private void close( Connection conn ) {
		try {
			if ( conn != null ) {
				conn.close();
			}
		} catch ( SQLException se ) {
			System.err.println( "Unresolvable SQL Connection error." );
		}
	}
}
