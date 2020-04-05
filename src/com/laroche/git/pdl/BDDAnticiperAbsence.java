package com.laroche.git.pdl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDDAnticiperAbsence {
	
	private Blob fichier;
	private String nomFichier;
	private int id_seance,idEtudiant,id_absence;
	
	public BDDAnticiperAbsence(int id_seance,int id_etudiant,String directory,String nomFichier) {
		try {
			BDD db = new BDD();
			//on calcule la primary key
			ArrayList<ArrayList<String>> result = db.request("SELECT id_ABSENCE FROM ABSENCE", 1); 
			id_absence = 0;
			for(int i = 0; i < result.size(); i++) {
				int buff = Integer.parseInt(result.get(i).get(0));
				if(buff > id_absence) {
					id_absence = buff;
				}
			}
			id_absence ++;
			try {
				db.post("INSERT INTO \"HR\".\"ABSENCE\" (ETAT_ABSENCE, ID_ABSENCE, ID_SEANCE, ID_ETUDIANT, NOM_JUSTIFICATIF_ABSENCE, JUSTIFICATIF_ABSENCE) VALUES ('0', '"+id_absence+"', '"+id_seance+"', '"+id_etudiant+"', '"+nomFichier+"','"+Files.readAllBytes(Paths.get(directory))+"')");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//db.postBlob(directory,"UPDATE \"HR\".\"ABSENCE\" SET JUSTIFICATIF_ABSENCE=? WHERE ID_ABSENCE = "+id_absence);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
