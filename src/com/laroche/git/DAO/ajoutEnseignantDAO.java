
package com.laroche.git.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.laroche.git.objets.Authentification;
import com.laroche.git.objets.Enseignant;

public class ajoutEnseignantDAO{

	private Enseignant enseignant;
	private Authentification authentification;
	private BDD db;
	
	public ajoutEnseignantDAO(Enseignant enseignant, Authentification authentification) {
		
		this.enseignant = enseignant;
		this.authentification = authentification;
		try {
			db = new BDD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateEnseignant() {
		
		//On definit la clee primaire id_user
		ArrayList<ArrayList<String>> result = db.request("SELECT ID_USER FROM Utilisateur", 1); 
		int id_utilisateur = 0;
		for(int i = 0; i < result.size(); i++) {
			int buff = Integer.parseInt(result.get(i).get(0));
			if(buff > id_utilisateur) {
				id_utilisateur = buff;
			}
		}
		id_utilisateur ++;
		
		String auth = authentification.getEmail();
		String mdp = authentification.getMDP();
		String stat = authentification.getStatut();
		String nom = enseignant.getNom();
		String prenom = enseignant.getPrenom();
		
		db.post("INSERT INTO Utilisateur (ID_USER, EMAIL_USER, MDP_USER, STATUT_USER, NOM_USER, PRENOM_USER)" + 
				" VALUES ('"+id_utilisateur+"', '"+auth+"','"+mdp+"','"+stat+"','"+nom+"',' "+prenom+"' )");
		
		//On definit la clee primaire id_enseignant
		result = db.request("SELECT ID_ENSEIGNANT FROM Enseignant", 1); 
		int id_enseignant = 0;
		for(int i = 0; i < result.size(); i++) {
			int buff = Integer.parseInt(result.get(i).get(0));
			if(buff > id_enseignant) {
				id_enseignant = buff;
			}
		}
		id_enseignant ++;
		
		int telephone = enseignant.getTelephone();
				
		db.post("INSERT INTO Enseignant (ID_ENSEIGNANT, TELEPHONE_ENSEIGNANT, ID_USER)" + 
				" VALUES ('"+id_enseignant+"',' "+telephone+"','"+id_utilisateur+"')");
	}
	
	public boolean isEmailExisting(String email) {
		ArrayList<ArrayList<String>> result = db.request("SELECT id_user FROM utilisateur WHERE email_user = '"+email+"'", 1);
		return(result.size() != 0);
	}
}