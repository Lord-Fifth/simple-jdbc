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
	         int no  = rs.getInt("emp_no");
	         Date bdate = rs.getDate("birth_date");
	         String first = rs.getString("first_name");
	         String last = rs.getString("last_name");
	         String gen = rs.getString("gender");
	         Date hdate = rs.getDate("hire_date");

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
