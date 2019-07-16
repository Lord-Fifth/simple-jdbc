package com.flytxt.sql;

import java.sql.*;

public class JdbcGetAll {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/demodb";

	   //  Database credentials
	   static final String USER = "aditya";
	   static final String PASS = "root123";
	   
	   public static void main(String[] args) {
		   
	   Connection conn = null;
	   Statement stmt = null;
	   int no;
       Date bdate,hdate;
       String first,last,gen;
	   try{
	      //Register JDBC driver
	      Class.forName("com.mysql.cj.jdbc.Driver");

	      //Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM employees";
	      ResultSet rs = stmt.executeQuery(sql);

	      //Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         no  = rs.getInt("emp_no");
	         bdate = rs.getDate("birth_date");
	         first = rs.getString("first_name");
	         last = rs.getString("last_name");
	         gen = rs.getString("gender");
	         hdate = rs.getDate("hire_date");

	         //Display values
	         System.out.print("ID: " + no);
	         System.out.print(", Birth-Date: " + bdate);
	         System.out.print(", First: " + first);
	         System.out.print(", Last: " + last);
	         System.out.print(", Gender: " + gen);
	         System.out.println(", Hire-Date: " + hdate);

	      }
	      //Clean-up environment
	      rs.close();
	      stmt.close();
	      
	      System.out.println();
	      
	      //Query to get all first names
	      stmt = conn.createStatement();
	      sql = "SELECT first_name FROM employees";
	      ResultSet fs = stmt.executeQuery(sql);
	      
	      while(fs.next()){
		         //Retrieve by column name
		         first = fs.getString("first_name");

		         //Display values
		         System.out.println("Name: " + first);
		  }
	      fs.close();
	      stmt.close();
	      
	      System.out.println();
	      
	      //Query to get details based on employee no.
	      stmt = conn.createStatement();
	      sql = "SELECT first_name,last_name,birth_date FROM employees WHERE emp_no = 101";
	      ResultSet cs = stmt.executeQuery(sql);
 
	      if(cs.next()) {
	      first = cs.getString("first_name");
          last = cs.getString("last_name");
          bdate = cs.getDate("birth_date");
 
          //Display values
	      System.out.print("First: " + first);
	      System.out.print(", Last: " + last);
	      System.out.println(", Birth-Date: " + bdate);

	      }
	      cs.close();
	      stmt.close();
	      conn.close();
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
	   System.out.println("Goodbye!");
	   
	   }
}
