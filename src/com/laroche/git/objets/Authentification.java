package com.laroche.git.objets;

/**
 * The "Authentification" class is used to store login data like : mail, password
 * 
 * @author LAROCHE Corentin, LEBRUN Juliette
 * @version 1.0
 */
public class Authentification {

	private String email, mdp;
	private String statut = null;
	
	public Authentification(String email, String mdp, String statut) {
		this.email = email;
		this.mdp = mdp;
		this.statut = statut;
	}
	
	/**
	 * An Auth setter
	 * @param email
	 * 		set the mail for the auth
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * An Auth setter
	 * @param mdp
	 * 		set the password for the auth
	 */
	public void setMDP(String mdp) {
		this.mdp = mdp;
	}
	
	/**
	 * An Auth setter
	 * @param statut
	 * 		set the statut of the user
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	/**
	 * An Auth getter
	 * @return the mail
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * An Auth getter
	 * @return the password 
	 */
	public String getMDP() {
		return this.mdp;
	}
	
	/**
	 * An Auth getter
	 * @return the statut of the user
	 */
	public String getStatut() {
		return this.statut;
	}

}
