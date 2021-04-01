package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {

	//Metodi di acesso al DB
	//Chi crea la connessione:DBCOnnect
	//Quando creare la connessione: 
	
	
	public List<String> readShapes(){
		Connection con;
		try {
			con = DBConnect.getConnection();
		
		String sql="SELECT DISTINCT shape "
				+ "FROM sighting "
				+ "WHERE shape<>'' "
				+ "ORDER BY shape ASC ";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet res= st.executeQuery(sql);
		List<String> formeUFO = new ArrayList<>(); 
		while(res.next()) {
			String forma = res.getString("shape"); 
			formeUFO.add(forma);
		}
		st.close();
		con.close();
		return formeUFO;
		} catch (SQLException e) {
			throw new RuntimeException("Database error in readShapes",e);
		}
	}
	
	public int countByShape(String shape) {
		try {
			Connection con =DBConnect.getConnection();
			String sql2 = "SELECT COUNT(*) AS cnt "
					+ "FROM sighting "
					+ "WHERE shape=? "; //query parametrica
			
			PreparedStatement st2 = con.prepareStatement(sql2);	
			st2.setString(1,shape);
			ResultSet res2=st2.executeQuery();
			res2.first(); 
			int count = res2.getInt("cnt");
			st2.close();
			con.close();
			
			return count;

		}catch(SQLException e) {
			throw new RuntimeException("Database error in countByShape",e);
			}
		}
	
	
}
