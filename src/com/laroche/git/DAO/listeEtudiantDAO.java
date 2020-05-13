package com.laroche.git.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.laroche.git.objets.Etudiant;

public class listeEtudiantDAO {
	
	private ArrayList<Etudiant> listeEtudiant;
	
	public listeEtudiantDAO() {
		this.listeEtudiant = new ArrayList<Etudiant>();
	}
	
	public static ArrayList<Etudiant> getList(){
		BDD db;
		ArrayList<Etudiant> etudiants = null;
		try {
			db = new BDD();
			ArrayList<ArrayList<String>> requete_prenom= db.request("SELECT prenom_user,id_user FROM utilisateur WHERE statut_user = 'etudiant'", 2);
			ArrayList<ArrayList<String>> requete_nom= db.request("SELECT nom_user FROM utilisateur WHERE statut_user = 'etudiant'", 1);
			etudiants = new ArrayList<Etudiant>();
			
			for(int i = 0; i < requete_prenom.size();i++) {
				Etudiant pipo = new Etudiant(requete_prenom.get(i).get(0),requete_nom.get(i).get(0));
				String id_user = requete_prenom.get(i).get(1);
				ArrayList<ArrayList<String>> requete_id= db.request("SELECT id_etudiant FROM etudiant WHERE id_user = "+id_user, 1);
				pipo.setID(Integer.parseInt(requete_id.get(0).get(0)));
				etudiants.add(pipo);
				
			
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return etudiants;
	}
}