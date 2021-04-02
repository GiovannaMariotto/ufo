package it.polito.tdp.ufo.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ufo.db.SightingDAO;



public class Model {

	//Userà la classe del DAO ---> memoriza cosa ha preso il DAO
	
	public List<String> formeUFO;
	
	public Model(List<String> lForme) {
		this.formeUFO=lForme;
	}
	
	public Model() {
		
	}
	

	public Integer countByShape(String shape) {
		SightingDAO dao = new SightingDAO();
		formeUFO = dao.readShapes();
		int count=0;
		
		for(String s : formeUFO ) {
			count++;
			
		}
		
		return count;
		
	}
	
	
	
}
