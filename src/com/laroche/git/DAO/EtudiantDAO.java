package com.laroche.git.DAO;

/**
 * The "EtudiantDao" is used to retrieve student data stored in the sql database
 * 
 * @author LAROCHE Corentin, LEBRUN Juliette
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.ArrayList;

import com.laroche.git.objets.Etudiant;

public class EtudiantDAO {

	private int userID,studentID;
	private Etudiant etudiant;
	
	public EtudiantDAO(int userID) {
		this.userID = userID;
		etudiant = new Etudiant("nom","prenom",true,0);
		refreshEtudiant();
	}
	
	public void refreshEtudiant() {
		try {
			BDD db = new BDD();
			ArrayList<ArrayList<String>> result = db.request("SELECT * FROM etudiant WHERE id_user = '"+this.userID+"'", 4);
			if(result.size()==1) {
				this.studentID = Integer.parseInt(result.get(0).get(0));
				this.etudiant.setGroupe(Integer.parseInt(result.get(0).get(3)));
				this.etudiant.setID(this.studentID);
				if(Integer.parseInt(result.get(0).get(1)) == 0) {
					this.etudiant.setFiliere(false);
				}else {
					this.etudiant.setFiliere(true);
				}
			}
			result.clear();
			result = db.request("SELECT * FROM utilisateur WHERE id_user = '"+this.userID+"'", 6);
			if(result.size() == 1) {
				this.etudiant.setNom(result.get(0).get(4));
				this.etudiant.setPrenom(result.get(0).get(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Etudiant getEtudiant() {
		return this.etudiant;
	}
	
	public int getStudentID() {
		return this.studentID;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public void setUserID(int id) {
		this.userID = id;
	}

	
}
