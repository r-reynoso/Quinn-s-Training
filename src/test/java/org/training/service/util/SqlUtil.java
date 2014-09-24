package org.training.service.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SqlUtil {
	
	public static String getString( ResultSet rs, String fieldName ) {
		String result = null;
		
		try {
			result = rs.getString( fieldName );
		} catch ( SQLException se ) {
			se.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean hasResult( ResultSet rs ) {
		boolean hasRecord = false;
		try {
			hasRecord = rs.first();
		} catch ( SQLException se ) {
			se.printStackTrace();
			hasRecord = false;
		}
		
		return hasRecord;
	}
	
	public static ResultSet executeSqlQuery( String sql, Connection conn ) {
		
		ResultSet rs = null;
		try {
			PreparedStatement ps = conn.prepareStatement( sql );
			rs = ps.executeQuery();
		} catch ( SQLException se ) {
			se.printStackTrace();
		}
		
		return rs;
	}

	public static int executeSqlUpdate( String sql, Connection conn ) {
		
		int result = -1;
		try {
			PreparedStatement ps = conn.prepareStatement( sql );
			result = ps.executeUpdate();
		} catch ( SQLException se ) {
			se.printStackTrace();
		}
		
		return result;
	}
	
	public static void close( Connection conn ) {
		try {
			if ( conn != null ) {
				conn.close();
			}
		} catch ( SQLException se ) {
			System.err.println( "Unresolvable SQL Connection error." );
		}
	}
}
