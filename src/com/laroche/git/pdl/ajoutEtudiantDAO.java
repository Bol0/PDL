package com.laroche.git.pdl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ajoutEtudiantDAO{

	private Etudiant etudiant;
	private Authentification authentification;
	private BDD db;
	
	public ajoutEtudiantDAO(Etudiant etudiant, Authentification authentification) {
		
		this.etudiant = etudiant;
		this.authentification = authentification;
		try {
			db = new BDD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateEtudiant() {
		
		//On définit la clé primaire id_user
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
		String nom = etudiant.getNom();
		String prenom = etudiant.getPrenom();
		
		db.post("INSERT INTO Utilisateur (ID_USER, EMAIL_USER, MDP_USER, STATUT_USER, NOM_USER, PRENOM_USER)" + 
				" VALUES ('"+id_utilisateur+"', '"+auth+"','"+mdp+"','"+stat+"','"+nom+"',' "+prenom+"' )");
		
		//On définit la clé primaire id_etudiant
		result = db.request("SELECT ID_ETUDIANT FROM Etudiant", 1); 
		int id_etudiant = 0;
		for(int i = 0; i < result.size(); i++) {
			int buff = Integer.parseInt(result.get(i).get(0));
			if(buff > id_etudiant) {
				id_etudiant = buff;
			}
		}
		id_etudiant ++;
		
		int filiere = 0;
		if (etudiant.getFiliere()) {
			filiere = 1;
		}
		int groupe = etudiant.getGroupe();
				
		db.post("INSERT INTO Etudiant (ID_ETUDIANT, FILIERE_ETUDIANT, ID_USER, GROUPE_ETUDIANT)" + 
				" VALUES ('"+id_etudiant+"',' "+filiere+"','"+id_utilisateur+"','"+groupe+"' )");
	}
	
	public boolean isEmailExisting(String email) {
		ArrayList<ArrayList<String>> result = db.request("SELECT id_user FROM utilisateur WHERE email_user = '"+email+"'", 1);
		return(result.size() != 0);
	}
}
