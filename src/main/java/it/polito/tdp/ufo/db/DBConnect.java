package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	//Fa la connessione
	
	public static Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=gigika123";
		
		return DriverManager.getConnection(jdbcURL);
	}
	//Useremo la classe DataSource invece di DriverManager
	//Lei gestisce l'apertura e chiusura di connessioni
	
	
	
}
