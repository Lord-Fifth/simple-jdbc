package com.flytxt.sql;

import java.io.*;
import java.sql.*;

public class JdbcUpdateAll {

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
	   Date bdate,hdate;
	   String first,last,gen;
	   try{
		      //Register JDBC driver
		      Class.forName("com.mysql.cj.jdbc.Driver");

		      //Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql = "UPDATE employees SET gender = 'F' WHERE emp_no in ('101', '102')";
		      stmt.executeUpdate(sql);

		      // Now you can extract all the records to see the updated records
		      sql = "SELECT emp_no, first_name, last_name, gender FROM employees";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while(rs.next()){
		    	//Retrieve by column name
		    	no  = rs.getInt("emp_no");
			    first = rs.getString("first_name");
			    last = rs.getString("last_name");
			    gen = rs.getString("gender");

			    //Display values
			    System.out.print("ID: " + no);
			    System.out.print(", First: " + first);
			    System.out.print(", Last: " + last);
			    System.out.println(", Gender: " + gen);
		      }
		      rs.close();
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
