package com.laroche.git.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.laroche.git.objets.Groupe;

public class listeGroupeDAO {
	
	private ArrayList<Groupe> listeGroupe;
	
	public listeGroupeDAO() {
		this.listeGroupe = new ArrayList<Groupe>();
	}
	
	public static ArrayList<Groupe> getList(){
		BDD db;
		ArrayList<Groupe> groupes = null;
		try {
			db = new BDD();
			ArrayList<ArrayList<String>> requete= db.request("SELECT numero_groupe FROM groupe", 1);
			groupes = new ArrayList<Groupe>();
			for(int i = 0; i < requete.size();i++) {
				groupes.add(new Groupe(Integer.parseInt(requete.get(i).get(0)),0));
			
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return groupes;
	}
}