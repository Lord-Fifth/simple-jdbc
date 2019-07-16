package com.flytxt.sql;

import java.sql.*;
import java.io.*;

public class JdbcInsertAll {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/demodb";
	   			
	   //  Database credentials
	   static final String USER = "aditya";
	   static final String PASS = "root123";
	   
	   public static void main(String[] args)throws IOException {
	   BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	   
	   Connection conn = null;
	   Statement stmt = null;
	   int no;
       String bdate,hdate;
       String first,last,gen;
	   try{
		      //Register JDBC driver
		      Class.forName("com.mysql.cj.jdbc.Driver");

		      //Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //Retrieving data from user
		      System.out.println("Enter Employee Details(ID,Birth-Date,First-Name,Last-Name,Gender,Hire-Date)");
		      no = Integer.parseInt(br.readLine());
		      bdate = br.readLine();
		      first = br.readLine();
		      last = br.readLine();
		      gen = br.readLine();
		      hdate = br.readLine();
		      
		      //Execute a query
		      System.out.println("Inserting records into the table...");
		      stmt = conn.createStatement();
		      
		      String sql = "INSERT INTO employees " + "VALUES('" + no + "', '" + bdate + "', '" + first + "', '" + last +"', '" + gen + "', '" + hdate + "')";
		      stmt.executeUpdate(sql);
		      System.out.println("Inserted records into the table...");
	   }
	   catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }
	   catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	   finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   System.out.println("\nGoodbye!");
	   
	   }
}
