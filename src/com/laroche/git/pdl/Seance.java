package com.laroche.git.pdl;

public class Seance {
	
	private String date,salle,type;
	int duree,heure,id,idCours; //en heure
	
	public Seance(String date, int heure, String salle, String type, int duree,int id,int idCours){
		this.date = date;
		this.heure = heure;
		this.salle = salle;
		this.type = type;
		this.duree = duree;
		this.id = id;
		this.idCours = idCours;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getIDCours() {
		return this.idCours;
	}
	
	public void setIDCours(int id) {
		this.idCours = id;
	}
	
}
