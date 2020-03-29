package com.laroche.git.pdl;

public class Authentification {

	private String email, mdp;
	private String statut = null;
	
	public Authentification(String email, String mdp) {
		this.email = email;
		this.mdp = mdp;
		
		//connection sql :
		connection();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setMDP(String mdp) {
		this.mdp = mdp;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getStatut() {
		return this.statut;
	}
	
	public void connection() {
		//se connecte a ka bdd et modifie le statut de l utilisateur 
		
	}

}
