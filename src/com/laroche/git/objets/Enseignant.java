package com.laroche.git.objets;

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
