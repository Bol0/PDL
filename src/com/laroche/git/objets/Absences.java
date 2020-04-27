package com.laroche.git.objets;

import java.sql.Blob;

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

	public int getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(int idAbsence) {
		this.idAbsence = idAbsence;
	}

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

	public int getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public boolean getEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Blob getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(Blob justificatif,String nomJustificatif) {
		this.justificatif = justificatif;
		this.nomJustificatif = nomJustificatif;
	}
	
	public String getNomJustificatif() {
		return this.nomJustificatif;
	}
	

}
