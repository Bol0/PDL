
package com.laroche.git.pdl;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuthentificationDAO {
	
	ArrayList<Authentification> listAuth;
	ArrayList<Personne> listPersonne;
	
	public AuthentificationDAO() {
		this.listAuth = new ArrayList<Authentification>();
		this.listPersonne = new ArrayList<Personne>();
		refreshAuth();
	}
	
	public void refreshAuth() {
		BDD bdd;
		
		try {
			bdd=new BDD();
			ArrayList<ArrayList<String>> result = bdd.request("SELECT * FROM Utilisateur", 6);
			listAuth.clear();
			listPersonne.clear();
			
			for(int i=0; i<result.size(); i++){
				Authentification ligne;
				Personne personne;
				
				String nom = result.get(i).get(4);
				String prenom = result.get(i).get(5);
				String email = result.get(i).get(1);
				String mdp = result.get(i).get(2);
				String statut = result.get(i).get(3);
				
				ligne = new Authentification(email, mdp, statut);
				personne = new Personne(nom, prenom);
				
				listAuth.add(ligne);
				listPersonne.add(personne);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<Authentification> getListAuth(){
		return this.listAuth;
	}
	
	public ArrayList<Personne> getListPersonne(){
		return this.listPersonne;
	}
}