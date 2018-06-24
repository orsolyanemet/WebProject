package ro.edu.ubb.util;

import java.sql.Connection;

public class DataSource {
	static JdbcConnectionPool pool = new JdbcConnectionPool();

	public Connection createConnection() {
		Connection connection = pool.getConnectionFromPool();
		return connection;
	}

	public void closeConnection(Connection connection) {
		pool.returnConnectionToPool(connection);
	}
}
