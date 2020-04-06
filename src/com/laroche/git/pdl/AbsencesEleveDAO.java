package com.laroche.git.pdl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class AbsencesEleveDAO {
	
	private ArrayList<Absences> absences;
	private int id;
	
	public AbsencesEleveDAO(int id) {
		this.id = id;
		absences = new ArrayList<Absences>();
		refresh();
	}
	
	public void refresh() {
		
		try {
			BDD db = new BDD();
			absences.clear();
			ArrayList<ArrayList<String>> result = db.request("SELECT id_absence,etat_absence,id_seance,nom_justificatif_absence,id_etudiant FROM absence WHERE id_etudiant = "+this.id, 5);
			for(int i = 0; i < result.size(); i++) {
				ArrayList<String> ligne = result.get(i);
				int id_absence = Integer.parseInt(ligne.get(0));
				int id_etudiant = Integer.parseInt(ligne.get(4)) ;
				int id_seance = Integer.parseInt(ligne.get(2));
				String nomJustificatif = ligne.get(3);
				boolean etat = (Integer.parseInt(ligne.get(1)) == 1);
				System.out.println("SELECT justificatif_absence FROM absence WHERE (id_etudiant = "+this.id+" AND id_absence = "+id_absence+")");
				Blob justificatif = db.requestBlob("SELECT justificatif_absence FROM absence WHERE (id_etudiant = "+this.id+" AND id_absence = "+id_absence+")");
				Absences buff = new Absences(id_absence,id_etudiant,id_seance,etat);
				buff.setJustificatif(justificatif,nomJustificatif);
				absences.add(buff);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Absences> getAbsences() {
		return this.absences;
	}

}
