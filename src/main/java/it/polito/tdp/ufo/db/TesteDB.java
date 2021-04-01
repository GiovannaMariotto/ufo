package it.polito.tdp.ufo.db;

// importare sempre da java.sql oppure javax.sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			//Statement st = con.createStatement(); //creato un nuovo statament
			//Usare Statement puo essere rischioso ( se c'è input dell'utente )
			

			String sql="SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC ";
			//Al database legge come una riga sola!
			// Quindi: attenzione se la riga finisce con un spazio 
			//   ( per non concattenare di modo sbagliato)
			
			
			PreparedStatement st = con.prepareStatement(sql); //Sempre usare PreparedStatement
			
			
			
			ResultSet res= st.executeQuery(sql);
			//Eseguire una query: mi ritorna un oggeto 'ResultSet'(interfaccia)
			
			
			//res.next() : ritorna un boolean ( nella riga sotto l'ultima riga del DB sarà false
			
			List<String> formeUFO = new ArrayList<>(); //lista
			while(res.next()) {
				String forma = res.getString("shape"); //shape=nome del campo nel database
				formeUFO.add(forma);
			}
			st.close(); //Ha senso se c'è molte statement nella connection
			System.out.println(formeUFO);
			
			String sql2 = "SELECT COUNT(*) AS cnt "
					+ "FROM sighting "
					+ "WHERE shape=? "; //query parametrica
			String shapeScelta ="circle";
			
			//obs: conection is always the same
			PreparedStatement st2 = con.prepareStatement(sql2);	
			st2.setString(1,shapeScelta);//impostare il parametro
			ResultSet res2=st2.executeQuery();
			res2.first(); //sappiamo che sara una sola riga
			int count = res2.getInt("cnt");
			st2.close();
			System.out.println("UFO di forma "+shapeScelta+" sono: "+count);
			
			
			con.close(); //Devo sempre ricordare di chiudere la connezione (buona norma)
			//Se non la chiude: timeout de qualche minuto (si spegne automaticamente)
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
