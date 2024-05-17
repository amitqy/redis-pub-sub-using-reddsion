package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import config.AppProperties;

public class DBConnection{


	private static ServletContext servletContext;

	public DBConnection (ServletContext servletContext) {
		DBConnection.servletContext = servletContext;
	}

	public static Connection getDBConnection() {

		Connection connection = null;
		try {
			AppProperties  dbProperties   = (AppProperties)servletContext.getAttribute("dbProperties");
			// load the driver class
			System.out.println("driver: " + dbProperties.getDriverName());
			System.out.println("redisson client data: " + dbProperties.getRedissonClient());

			Class.forName(dbProperties.getDriverName());
			// get hold of the DriverManager
			connection = DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getUserName(),  dbProperties.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}


}