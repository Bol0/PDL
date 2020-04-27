package com.laroche.git.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.laroche.git.objets.Note;

public class NotesDAO {

	private ArrayList<Note> notes;
	private BDD db;
	
	public NotesDAO() {
		notes = new ArrayList<Note>();
		try {
			db = new BDD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Note> getNotes() {
		return this.notes;
	}
	
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	
	public void refreshList(int idEtudiant) {
		this.notes.clear();
		if(db.isConnected()) {
			ArrayList<ArrayList<String>> result = db.request("SELECT * FROM Note WHERE id_etudiant_note = "+idEtudiant, 4);
			for(int ligne = 0; ligne < result.size(); ligne++) {
				int idSeance = Integer.parseInt(result.get(ligne).get(1));
				int idNote = Integer.parseInt(result.get(ligne).get(0));
				float valeurNote = Float.parseFloat(result.get(ligne).get(3));
				Note note = new Note(idSeance,idEtudiant,valeurNote);
				note.setIdNote(idNote);
				this.notes.add(note);
			}
		}else {
			System.out.println("La connection à la BDD a échoué");
		}
	}
	
	public void uploadList() {
		
	}
	
}
