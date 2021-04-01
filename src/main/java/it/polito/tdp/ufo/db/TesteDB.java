package it.polito.tdp.ufo.db;

// importare sempre da java.sql oppure javax.sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TesteDB {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=gigika123";
		
		//DriverManager: prova a trovare qualche Driver che supporti l'interfaccia jdbc:mysql
		
		try {
			Connection con= DriverManager.getConnection(jdbcURL);
			Statement st = con.createStatement(); //creato un nuovo statament
			
			String sql="SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC ";
			//Al database legge come una riga sola!
			// Quindi: attenzione se la riga finisce con un spazio 
			//   ( per non concattenare di modo sbagliato)
			
			ResultSet res= st.executeQuery(sql);
			//Eseguire una query: mi ritorna un oggeto 'ResultSet'(interfaccia)
			
			List<String> formeUFO = new ArrayList<>();
			while(res.next()) {
				String forma = res.getString("shape");
				formeUFO.add(forma);
			}
			System.out.println(formeUFO);
			
			
			con.close(); //Devo sempre ricordare di chiudere la connezione (buona norma)
			//Se non la chiude: timeout de qualche minuto (si spegne automaticamente)
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
