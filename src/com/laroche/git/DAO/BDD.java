package com.laroche.git.DAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.rowset.serial.SerialBlob;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class BDD {
	
	  private final static String DB_URL= "jdbc:oracle:thin:@127.0.0.1:1521/xe";
	  private final static String DB_USER = "SYSTEM";
	  private final static String DB_PASSWORD = "";
	  private Properties info;
	  private OracleDataSource ods;
	  private boolean connected = false;
	  private OracleConnection connection;
	  
	  
	
	public BDD() throws SQLException{
	    info = new Properties();
	    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
	    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
	    info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    
	  

	    ods = new OracleDataSource();
	    ods.setURL(DB_URL);    
	    ods.setConnectionProperties(info);
	    connection = null;
	    try{
	        // Get the JDBC driver name and version 
	    	connection = (OracleConnection) ods.getConnection();
	        DatabaseMetaData dbmd = connection.getMetaData();       
	        System.out.println("Driver Name: " + dbmd.getDriverName());
	        System.out.println("Driver Version: " + dbmd.getDriverVersion());
	        System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
	        System.out.println("Database Username is: " + connection.getUserName());
	        System.out.println();
	        connected = true;
	      } catch(SQLException e) {
	    	System.out.println("Erreur de connection a la BDD");
	    	System.out.println(e);
	    	connected = false;
	      }
	}
	
	public boolean isConnected() {
		if(connection != null) {
		try {
			connected = !connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			connected = false;
		}
		return connected;
	}
	
	public ArrayList<ArrayList<String>> request(String request,int row) {
		ArrayList<ArrayList<String>> buff = new ArrayList<ArrayList<String>>();
		if(isConnected()) {
		    try (Statement statement = connection.createStatement()) {      
		        try (ResultSet resultSet = statement
		            .executeQuery(request)) {
		        	
		          while (resultSet.next()) {
		        	  ArrayList<String> buff2 = new ArrayList<String>();
		          	for(int i = 1; i <= row; i++) {
		          		buff2.add(resultSet.getString(i));
		          	}
		          	buff.add(buff2);
		          }
		          
		          
		        }
		      } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		      }
		}
		return buff;
	}
	
	public void post(String request) {
		if(isConnected()) {
		    try (Statement statement = connection.createStatement()) {      
		        try (ResultSet resultSet = statement
		            .executeQuery(request)) {
		          }
		      } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		      }
		}
	}
	
	public Blob requestBlob(String request) {
		Blob fichier = null;
		if(isConnected()) {
			try (Statement statement = connection.createStatement()) {      
		        try (ResultSet resultSet = statement.executeQuery(request)) {
		        	resultSet.next();
		          	fichier = resultSet.getBlob(1);
		        }
		      } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		      }
		}
		return fichier;
	}
	

	public void postBlob(String path, int id_absence) {
		String updateSQL = "UPDATE absence " + "SET justificatif_absence = ? " + "WHERE id_absence=?";
		
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
 
            // set parameters
            pstmt.setBytes(1, readFile(path));
            pstmt.setInt(2, id_absence);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}


	public void reconnect() {
	    try{
	        // Get the JDBC driver name and version 
	    	connection = (OracleConnection) ods.getConnection();
	        DatabaseMetaData dbmd = connection.getMetaData();       
	        System.out.println("Driver Name: " + dbmd.getDriverName());
	        System.out.println("Driver Version: " + dbmd.getDriverVersion());
	        System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
	        System.out.println("Database Username is: " + connection.getUserName());
	        System.out.println();
	        connected = true;
	      } catch(SQLException e) {
	    	System.out.println("Erreur de connection a la BDD");
	    	System.out.println(e);
	    	connected = false;
	      }
	}
	
	
	 /**
     * Read the file and returns the byte array
     * @param file
     * @return the bytes of the file
     */
    private byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }

}

