package com.laroche.git.pdl;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeanceDAO {
	
	private ArrayList<Seance> seances;
	private int idCours;
	
	public SeanceDAO(int idCours) {
		this.idCours = idCours;
		seances = new ArrayList<Seance>();
		refreshSeances();
	}
	
	void refreshSeances() {
		seances.clear();
		try {
			BDD db = new BDD();
			ArrayList<ArrayList<String>> result = db.request("SELECT * FROM Seance WHERE  id_cours = "+idCours, 7);
			for(int i = 0; i < result.size(); i++) {
				String date = result.get(i).get(5);
				int heure = Integer.parseInt(result.get(i).get(3));
				String salle = result.get(i).get(1);
				String type = result.get(i).get(2);
				int duree = Integer.parseInt(result.get(i).get(4));
				int id = Integer.parseInt(result.get(i).get(0));
				int idCours = Integer.parseInt(result.get(i).get(6));
				seances.add(new Seance(date,heure,salle,type,duree,id,idCours));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Seance> getSeances() {
		return this.seances;
	}

}
