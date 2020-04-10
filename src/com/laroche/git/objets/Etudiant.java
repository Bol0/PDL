package com.laroche.git.objets;

public class Etudiant extends Personne{

	private boolean filiere; //true --> classique, false --> alternance
	private int groupe,id;
	
	public Etudiant(String prenom, String nom, boolean filiere, int groupe) {
		super(nom, prenom);
		this.filiere = filiere;
		this.groupe = groupe;
		id = 0;
	}
	
	public Etudiant(String prenom, String nom) {
		super(nom,prenom);
		filiere = true;
		groupe = 0;
	}
	
	public boolean getFiliere() {
		return this.filiere;
	}
	
	public int getGroupe() {
		return this.groupe;
	}
	
	public void setFiliere(boolean filiere) {
		this.filiere = filiere;
	}
	
	public void setGroupe(int numero) {
		this.groupe = numero;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
}
