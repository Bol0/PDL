package com.laroche.git.pdl;

/**
 * Classe d'accès à la BDD
 * @author Binome 10
 *
 */
public class ConnectionDAO {

	protected static String URL = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
	protected static String USER = "SYSTEM";
	protected static String PASSWORD = "Invisible92";
	
	public ConnectionDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}
		catch(ClassNotFoundException e){
			System.err.println("Erreur connection");
		}
	}
	
}
