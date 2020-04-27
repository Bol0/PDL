package com.laroche.git.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.laroche.git.objets.Cours;
import com.laroche.git.objets.Groupe;
import com.laroche.git.objets.Seance;

public class GroupeDAO {

	private int numero_groupe;
	private Groupe groupe;
	
	public GroupeDAO(int numero) {
		this.numero_groupe = numero;
		groupe = new Groupe(this.numero_groupe,0);
		refreshGroupe();
	}
	
	
	public void refreshGroupe() {
		try {
			BDD db = new BDD();
			ArrayList<ArrayList<String>> result = db.request("SELECT * FROM groupe WHERE numero_groupe = '"+numero_groupe+"'", 3);
			if(result.size() == 1) {
				groupe.setCapacite(Integer.parseInt(result.get(0).get(1)));
			}
			result.clear();
			
			//on ajoute tous les cours du groupe
			result = db.request("SELECT * FROM cours WHERE groupe_cours = "+this.numero_groupe, 5);
			this.groupe.clearCours();
			for(int i = 0; i < result.size(); i++){
				int idCours = Integer.parseInt(result.get(i).get(0));
				String matiere = result.get(i).get(1);
				int masseHoraire = Integer.parseInt(result.get(i).get(2));
				Cours cours = new Cours(matiere,masseHoraire, idCours);
				ArrayList<Seance> seances = new SeanceDAO(idCours).getSeances();
				cours.setSeances(seances);
				this.groupe.addCours(cours);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setNumero(int num) {
		this.numero_groupe = num;
	}
	
	public Groupe getGroupe() {
		return this.groupe;
	}
}
