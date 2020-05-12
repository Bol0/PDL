
package com.laroche.git.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.laroche.git.objets.Absences;
import com.laroche.git.objets.Authentification;
import com.laroche.git.objets.Personne;
import com.laroche.git.objets.Etudiant;

public class AuthentificationDAO {
	
	ArrayList<Authentification> listAuth;
	ArrayList<Personne> listPersonne;
	ArrayList<Etudiant> listEtudiant;
	ArrayList<Absences> listAbsEtud;
	
	public AuthentificationDAO() {
		this.listAuth = new ArrayList<Authentification>();
		this.listPersonne = new ArrayList<Personne>();
		this.listEtudiant = new ArrayList<Etudiant>();
		this.listAbsEtud = new ArrayList<Absences>();
		refreshAuth();
	}
	
	public void refreshAuth() {
		BDD bdd;
		
		try {
			bdd=new BDD();
			ArrayList<ArrayList<String>> result = bdd.request("SELECT * FROM Utilisateur", 6);
			listAuth.clear();
			listPersonne.clear();
			listEtudiant.clear();
			
			
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
				
				if(statut.equals("etudiant"))
				{
					Etudiant etud;
					etud = new Etudiant(prenom, nom);
					ArrayList<ArrayList<String>> request = bdd.request("SELECT groupe_etudiant FROM etudiant WHERE id_user = "+result.get(i).get(0), 1);
					etud.setGroupe(Integer.parseInt(request.get(0).get(0)));
					
					//ArrayList<ArrayList<String>> request_abs = bdd.request("SELECT id_nom_cours, jour_seance, duree_seance FROM cours INNER JOIN seance INNER JOIN absence ON id_etudiant = "+this.id ", 1);
					//etud.setGroupe(Integer.parseInt(request.get(0).get(0)));
					
					listEtudiant.add(etud);
				}
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
	
	public ArrayList<Etudiant> getListEtudiant(){
		return this.listEtudiant;
	}
}