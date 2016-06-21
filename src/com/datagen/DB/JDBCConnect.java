package com.datagen.DB;
import java.sql.Connection;
import java.sql.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnect {
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/learn@niit";

	   //  Database credentials
	   static final String USER = "guest";
	   static final String PASS = "guest";	
	   static Connection conn = null;
	   static Statement stmt = null;
	   
	   public static void connect(){

		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		   }
		   catch(SQLException se)
		   {
			 //Handle errors for JDBC
			      se.printStackTrace();
		   }
		   catch(ClassNotFoundException cnfe)
		   {
			 //Handle errors for JDBC
			      cnfe.printStackTrace();
		   }
	   }
	   
	   public static ResultSet execSQL(String sql)
	   {
		   connect();
		   ResultSet rs = null;
		   try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return rs;	
	   }
	   public static void main(String[] args)
	   {
		   System.out.println("Local execution -1");
		   ResultSet rs = execSQL("Select * from user");
		   if (rs != null)
		   {
			   try {
			   while(rs.next()){
			         //Retrieve by column name	   	
						System.out.print("ID -- " + rs.getInt("id"));
						System.out.print("Name -- " + rs.getString("name"));
						System.out.println(" --------- ");
			   }
			   rs.close();
			   } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }

	   }
	   
}
