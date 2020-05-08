package com.laroche.git.objets;

import java.sql.Blob;

/**
 * The "Absence" class is used to store absence data like : Student_id, Absence_id
 * 
 * @author LAROCHE Corentin, LEBRUN Juliette
 * @version 1.0
 */
public class Absences {
	
	private int idAbsence,idSeance,idEtudiant;
	private boolean etat;
	private Blob justificatif;
	private String nomJustificatif;
	

	public Absences(int idAbsence, int idEtudiant,int idSeance,boolean etat){
		this.idAbsence = idAbsence;
		this.idEtudiant = idEtudiant;
		this.idSeance = idSeance;
		this.etat = etat;
		justificatif = null;
		nomJustificatif = null;
	}

	/**
	 * An absence getter
	 * 
	 * @return the unique id of the absence
	 */
	public int getIdAbsence() {
		return idAbsence;
	}

	/**
	 * An Absence setter
	 * 
	 * @param idAbsence
	 * 		set the id of the seance
	 */
	public void setIdAbsence(int idAbsence) {
		this.idAbsence = idAbsence;
	}

	/**
	 * An absence getter
	 * 
	 * @return the id of the seance
	 */
	public int getIdSeance() {
		return idSeance;
	}

	/**
	 * An Absence setter
	 * 
	 * @param idSeance
	 * 		set the id of the seance
	 */
	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

	/**
	 * An Absence getter
	 * @return the id of the studient
	 */
	public int getIdEtudiant() {
		return idEtudiant;
	}

	/**
	 * An Absence setter
	 * @param idEtudiant
	 * 	set the id of the student
	 */
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	/**
	 * An Absence getter
	 * @return the state of the absence
	 */
	public boolean getEtat() {
		return etat;
	}

	/**
	 * An Absence setter
	 * @param etat
	 * 		set the state of the absence
	 */
	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	/**
	 * An Absence getter
	 * @return the proof
	 */
	public Blob getJustificatif() {
		return justificatif;
	}

	/**
	 * An Absence setter
	 * @param justificatif
	 * 			set the proof file
	 * @param nomJustificatif
	 * 			set the name of the proof file
	 */
	public void setJustificatif(Blob justificatif,String nomJustificatif) {
		this.justificatif = justificatif;
		this.nomJustificatif = nomJustificatif;
	}
	
	/**
	 * An Absence getter
	 * @return the name of the proof file
	 */
	public String getNomJustificatif() {
		return this.nomJustificatif;
	}
	

}
