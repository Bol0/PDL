package com.laroche.git.pdl;

public class Seance {
	
	private String date,heure,salle,type;
	int duree; //en minutes
	
	public Seance(String date, String heure, String salle, String type, int duree){
		this.date = date;
		this.heure = heure;
		this.salle = salle;
		this.type = type;
		this.duree = duree;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
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

	
}
