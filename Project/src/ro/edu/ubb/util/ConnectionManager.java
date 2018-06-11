package ro.edu.ubb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Interface which contains constants for database connection.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class ConnectionManager {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:3366/webproj";
	private static final String USERNAME = "root";
	private static final String PASS = "11235813";
	private Connection connection;
	private static ConnectionManager cm;

	private ConnectionManager() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new UtilException("An error occured while loading driver.");
		}
	}

	public static ConnectionManager getInstance() {
		if (cm == null) {
			cm = new ConnectionManager();
		}
		return cm;
	}
	
	public Connection createConnection() {
		try {
			connection = DriverManager.getConnection(CONNECTION, USERNAME, PASS);
		} catch (SQLException e) {
			throw new UtilException("An error occured while creating database connection.");
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new UtilException("An error occured while closing database connection.");
		}
	}
}
