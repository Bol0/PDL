package com.laroche.git.objets;

import java.util.ArrayList;

public class Groupe {
	private int capacite,numero;
	private ArrayList<Cours> cours;
	
	public Groupe(int numero, int capacite) {
		this.numero = numero;
		this.capacite = capacite;
		cours = new ArrayList<Cours>();
	}
	
	public int getCapacite() {
		return capacite;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public ArrayList<Cours> getCours(){
		return this.cours;
	}
	
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void addCours(Cours cours) {
		this.cours.add(cours);
	}
	
	public void removeCours(Cours cours) {
			this.cours.remove(cours);
	}
	
	public void clearCours() {
		this.cours.clear();
	}
}
