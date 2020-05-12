package com.laroche.git.objets;

/**
 * The "Enseignant" class is used to store teacher data like : name, cellphone_number
 * 
 * @author LAROCHE Corentin, LEBRUN Juliette
 * @version 1.0
 */
public class Enseignant extends Personne{

	public int telephone;
	
	public Enseignant(String nom, String prenom, int telephone) {
		super(nom,prenom);
		this.telephone = telephone;
	}
	
	public int getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
}
