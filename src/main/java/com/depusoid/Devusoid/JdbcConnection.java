package com.depusoid.Devusoid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	public static Connection getJdbcConnection() throws SQLException {
		Connection connection;
		String url = "jdbc:mysql://localhost:3306/giza?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "Polyesther9&";
		
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

}
