package com.laroche.git.pdl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {


	
	public static void main(String[] args) throws SQLException {
		// tente de se connecter a la bdd afin de verifier la connection
		UILogin log = new UILogin();
		UIEtudiant student = new UIEtudiant(3);
		//BDD db = new BDD();
		//System.out.println(db.isConnected());
		/*ArrayList<ArrayList<String>> result = db.request("SELECT * FROM UTILISATEUR",6);
		
		for(ArrayList<String> buff : result) {
			for(String buff2 : buff) {
				System.out.print(buff2 + "\t");
			}
			System.out.println();
		}
		
		
		while(!db.isConnected()) {
			System.out.println("Erreur de connection à la bdd");
			JOptionPane jopErreurBDD = new JOptionPane();			
			int option = jopErreurBDD.showConfirmDialog(null,"Essayer de se reconnecter ?","Erreur de connection à la BDD", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
						
			if(option != JOptionPane.YES_OPTION){
				System.exit(0);
			}
			db.reconnect();
		}*/

	}

}
