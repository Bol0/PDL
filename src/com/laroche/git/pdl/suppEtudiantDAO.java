package com.laroche.git.pdl;

import java.sql.SQLException;
import java.util.ArrayList;

public class suppEtudiantDAO {

	public suppEtudiantDAO() {

	}
	
	public void deleteEtudiant(String email) {
		
		try {
			BDD bdd = new BDD();
			
			String result = bdd.request("SELECT ID_USER FROM Utilisateur WHERE email_user = '"+email+"' ", 1).get(0).get(0);
			
			bdd.post("DELETE FROM Etudiant WHERE ID_USER = '"+result+"' ");
			bdd.post("DELETE FROM Utilisateur WHERE EMAIL_USER = '"+email+"' ");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}