package com.eteration.sample.loadtestapp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
	
	public static void initDB(){
		Connection connection = ConnectionManager.getConntection();

		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet res = meta.getTables(null, null, "USERS", new String[] { "TABLE" });

			if (!res.next()) {
				connection.createStatement().execute("CREATE TABLE USERS(USERNAME varchar(255),EMAIL varchar(255),MESSAGE varchar(255))");
			} else {

				
					System.out.println("   " + res.getString("TABLE_CAT") + ", " + res.getString("TABLE_SCHEM") + ", "
							+ res.getString("TABLE_NAME") + ", " + res.getString("TABLE_TYPE") + ", "
							+ res.getString("REMARKS"));
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionManager.close(connection);
		}
	}

	public static void create(UserData userData) {
		

		Connection connection = null;
		try {
			
			connection  = ConnectionManager.getConntection();
			PreparedStatement stmt = ConnectionManager.getConntection().prepareStatement("INSERT INTO USERS VALUES(?,?,?)");
			
			System.out.println("Inserting "+userData);
			stmt.setString(1, userData.getName());

			stmt.setString(2, userData.getEmail());

			stmt.setString(3, userData.getMessage());
			
			stmt.executeUpdate();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(connection);
		}
		
		
		
	}

}
