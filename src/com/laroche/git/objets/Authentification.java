package com.laroche.git.objets;

public class Authentification {

	private String email, mdp;
	private String statut = null;
	
	public Authentification(String email, String mdp, String statut) {
		this.email = email;
		this.mdp = mdp;
		this.statut = statut;

		//connection sql :
		connection();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setMDP(String mdp) {
		this.mdp = mdp;
	}
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getMDP() {
		return this.mdp;
	}
	
	public String getStatut() {
		return this.statut;
	}
	
	public void connection() {
		//se connecte a ka bdd et modifie le statut de l utilisateur 
		
	}

}
