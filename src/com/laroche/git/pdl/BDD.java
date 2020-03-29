package com.laroche.git.pdl;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class BDD {
	
	  private final static String DB_URL= "jdbc:oracle:thin:@192.168.56.101:1521/orcl";
	  private final static String DB_USER = "hr";
	  private final static String DB_PASSWORD = "oracle";
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
		ArrayList<String> buff2 = new ArrayList<String>();
		if(isConnected()) {
		    try (Statement statement = connection.createStatement()) {      
		        try (ResultSet resultSet = statement
		            .executeQuery(request)) {
		        	
		          while (resultSet.next()) {
		        	  buff2.clear();
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
		}else {
			buff2.add("null");
			buff.add(buff2);
		}
		return buff;
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

}

