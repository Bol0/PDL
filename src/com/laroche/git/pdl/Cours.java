package com.laroche.git.pdl;

import java.util.ArrayList;

public class Cours {
	
	private String matiere;
	private int masseHoraire;
	private int tauxAbsence = 0;
	private ArrayList<Seance> seances;
	
	public Cours(String matiere, int masseHoraire) {
		this.matiere = matiere;
		this.masseHoraire = masseHoraire;
		seances = new ArrayList<Seance>();
		
	}
	
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	
	public void setMasseHoraire(int masseHoraire) {
		this.masseHoraire = masseHoraire;
	}
	
	public void setTauxAbsence(int tauxAbsence) {
		this.tauxAbsence = tauxAbsence;
	}
	
	public void addSeance(Seance seance) {
		this.seances.add(seance);
	}
	
	public void removeSeance(Seance seance) {
		this.seances.remove(seance);
	}
	
	public String getMatiere() {
		return this.matiere;
	}
	
	public int getMasseHoraire() {
		return this.masseHoraire;
	}
	
	public int getTauxAbsences() {
		return this.tauxAbsence;
	}
	
	public ArrayList<Seance> getSeances(){
		return this.seances;
	}

}
