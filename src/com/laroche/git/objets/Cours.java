package com.laroche.git.objets;

import java.util.ArrayList;

/**
 * The "Cours" class is used to store classes data like : classes_id, matter
 * 
 * @author LAROCHE Corentin, LEBRUN Juliette
 * @version 1.0
 */
public class Cours {
	
	private String matiere;
	private int masseHoraire,idCours;
	private int tauxAbsence = 0;
	private ArrayList<Seance> seances;

	
	public Cours(String matiere, int masseHoraire,int idCours) {
		this.matiere = matiere;
		this.masseHoraire = masseHoraire;
		seances = new ArrayList<Seance>();
		this.idCours = idCours;
		
	}
	
	/**
	 * A Classes setter
	 * @param matiere
	 * 		set the matiere of the classes
	 */
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	
	/**
	 * A Classes setter
	 * @param masseHoraire
	 * 		set the total time of a matiere
	 */
	public void setMasseHoraire(int masseHoraire) {
		this.masseHoraire = masseHoraire;
	}
	
	/**
	 * A Classes setter
	 * @param tauxAbsence
	 * 		set the number of absences
	 */
	public void setTauxAbsence(int tauxAbsence) {
		this.tauxAbsence = tauxAbsence;
	}
	
	/**
	 * A Classes adder
	 * @param seance
	 * 		the seance to add to the list
	 */
	public void addSeance(Seance seance) {
		this.seances.add(seance);
	}
	
	/**
	 * A Classes remover
	 * @param seance
	 * 		the seance to remove to the list
	 */
	public void removeSeance(Seance seance) {
		this.seances.remove(seance);
	}
	
	/**
	 * A Classes setter
	 * @param seances
	 * 		set the seance list
	 */
	public void setSeances(ArrayList<Seance> seances) {
		this.seances = seances;
	}
	
	/**
	 * A Classes getter
	 * @return the matter linked to the classes
	 */
	public String getMatiere() {
		return this.matiere;
	}
	
	/**
	 * A Classes getter
	 * @return the number of hours per classes
	 */
	public int getMasseHoraire() {
		return this.masseHoraire;
	}
	
	/**
	 * A Classes getter
	 * @return the number of absences
	 */
	public int getTauxAbsences() {
		return this.tauxAbsence;
	}
	
	/**
	 * A Classes getter
	 * @return the seance list
	 */
	public ArrayList<Seance> getSeances(){
		return this.seances;
	}
	
	/**
	 * A Classes setter
	 * @param ID
	 * 		set the unique id of the classes
	 */
	public void setID(int ID) {
		this.idCours = ID;
	}
	
	/**
	 * A Classes getter
	 * @return the unique id of the classess
	 */
	public int getID() {
		return this.idCours;
	}

}
