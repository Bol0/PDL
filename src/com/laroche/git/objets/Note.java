package com.laroche.git.objets;

public class Note {

	private Float note;
	private int idSeance, idEtudiant, idNote;
	
	public Note(int idSeance, int idEtudiant, float note) {
		this.idSeance = idSeance;
		this.idEtudiant = idEtudiant;
		this.note = note;
	}

	public Float getNote() {
		return note;
	}

	public void setNote(Float note) {
		this.note = note;
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
	
	public int getIdNote() {
		return idNote;
	}

	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}
	
}
