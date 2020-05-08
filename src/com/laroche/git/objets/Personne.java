package com.laroche.git.objets;

/**
 * The "Personne" class is used to store user data like : first_name, last_name
 * 
 * @author LAROCHE Corentin, LEBRUN Juliette
 * @version 1.0
 */
public class Personne {

	protected String nom,prenom;
	
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
