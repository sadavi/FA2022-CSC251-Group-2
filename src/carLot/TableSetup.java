package sqlCarLot;

import java.sql.*;



public class TableSetup {
static final String DB_URL = "jdbc:mysql://localhost:3308";
static final String USER = "dbUser";
static final String PASS = "password123";
// change as needed for local machine

public static void main(String[] args) {
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
	   try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		      ) {		      
		         String sql = "CREATE DATABASE CARLOT";
		         stmt.executeUpdate(sql);
		         System.out.println("Database successfully created.");
		         Statement stmt3 = conn.createStatement();
		         String sql3 = "USE carlot;";
		         stmt3.executeUpdate(sql3);
		         Statement stmt2 = conn.createStatement();	      
		         String sql1 =
		        		 	"CREATE TABLE carlot(" +
		         			"id VARCHAR(50) NOT NULL," +
		     				"mileage INT NOT NULL,"  +
		     				"mpg INT," +
		     				"salesPrice INT," +
		     				"sold CHAR(1) DEFAULT 'N'," +
		     				"priceSold INT," +
		     				"profit INT NULL," +
		     				"PRIMARY KEY ( id ));";

		        stmt2.executeUpdate(sql1);
		        Statement stmt4 = conn.createStatement();
		         String sql4 = "INSERT INTO carlot VALUES ('Ford Mustang', 123, 14, 14999, 'N', 0, 0);";
		         String sql5 = "INSERT INTO carlot VALUES ('Chevy Camaro', 321, 15, 15999, 'N', 0, 0);";
		         String sql6 = "INSERT INTO carlot VALUES ('Dodge Stealth', 7888, 20, 20500, 'N', 0, 0);";
		         String sql7 = "INSERT INTO carlot VALUES ('Dodge Dart', 10000, 30, 21500, 'N', 0, 0);";
		         String sql8 = "INSERT INTO carlot VALUES ('Pontiac Thunderbird', 92548, 11, 69500, 'N', 0, 0);";
		         stmt4.executeUpdate(sql4);
		         stmt4.executeUpdate(sql5);
		         stmt4.executeUpdate(sql6);
		         stmt4.executeUpdate(sql7);
		         stmt4.executeUpdate(sql8);
		      }
		      
		      catch (SQLException e) {
		         e.printStackTrace();
		      }



	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
	   }
	}
}