package mssqlServerConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSQLServerConn {

	private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//private static final String dbURL = "jdbc:sqlserver://localhost:1433;databasename=SOMDB";
	private static final String dbURL = "jdbc:sqlserver://192.168.1.102:1433;databasename=SOMDB";
	
	
	private static final String user = "som_sa"; 
	private static final String pass = "password@12345";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Program started");
		MSSQLServerConn msserverCon = new MSSQLServerConn();
		msserverCon.dbConn(user, pass);

	}

	public String dbConn(String userName, String Password)
	{
	String returnMsg = "" ;
		System.out.println("Program started");
		returnMsg = returnMsg + "Program started"+ "/n";
	    try
	    {
	       Class.forName(jdbcDriver).newInstance();
	       System.out.println("JDBC driver loaded successfully 2!");
	       returnMsg = returnMsg + "JDBC driver loaded successfully!"+ "/n";
	    }
	    catch (Exception err)
	    {
	       System.err.println("Error loading JDBC driver");
	       returnMsg = returnMsg + "Error loading JDBC driver"+ "/n";
	       err.printStackTrace(System.err);
	       System.exit(0);
	    }
		
	    Connection databaseConnection= null;
	    try
	    {
	      //Connect to the database
	      databaseConnection = DriverManager.getConnection(dbURL,userName,Password);
	      System.out.println("Connected to the database successfully 2!");
	      System.out.println("Closing database connection");
	      returnMsg = returnMsg + "Error loading JDBC driver"+ "/n" + "Closing database connection";

	      //close the database connection
	      databaseConnection.close();
	    }
	    catch (SQLException err)
	    {
	       System.err.println("Error connecting to the database 1!");
	       returnMsg = "ERROR - " + returnMsg + "Error connecting to the database" + "/n";
	       err.printStackTrace(System.err);
	       return returnMsg;
	      // System.exit(0);
	    }
	    returnMsg = "SUCCESS";
	    System.out.println("Program finished");
	    return returnMsg;
	}
	
}
