package com.laroche.git.pdl;

public class Etudiant extends Personne{

	private boolean filiere; //true --> classique, false --> alternance 
	
	public Etudiant(String prenom, String nom, boolean filiere) {
		super(nom, prenom);
		this.filiere = filiere;
	}
	
	public boolean getFiliere() {
		return this.filiere;
	}
	
	public void setFiliere(boolean filiere) {
		this.filiere = filiere;
	}
	
}
