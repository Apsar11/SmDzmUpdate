package com.resource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DbConnection {
	public static Connection getConnection() {
		Properties props = new Properties();
		InputStream input = null;
		Connection conn = null;
		ModifiedDate md = new ModifiedDate();
		try {
			// Load the properties file from the classpath
	 		input = DbConnection.class.getClassLoader().getResourceAsStream("application.properties");
			if (input == null) {
				System.out.println("Sorry, unable to find appliction.properties");
				return null;
			}
			// Load properties from the file
			props.load(input);

			// Get the property values
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			String driver = props.getProperty("driver");

			// Load the JDBC driver
			Class.forName(driver);

			// Establish the connection
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return conn;
	}

}
