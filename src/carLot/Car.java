package sqlCarLot;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Car {
		private String id;
		private int mileage;
		private int mpg;
		private double cost;
		private double salesPrice;
		private boolean sold;
		private double priceSold;
		private double profit;
		
		
   
public void getData() {
	try (Scanner scn = new Scanner(new File("Cars.txt"))){
		while(scn.hasNextLine()) {
			id = "";
			String line;
			line = scn.nextLine();
			
			try(Scanner data = new Scanner(line)) {
				while(!data.hasNextInt()) {
					id += data.next() + " ";
				}
				
				id = id.trim();
				
				
				if(data.hasNextInt()) {
					mileage = data.nextInt();
				}
				
				if(data.hasNextInt()) {
					mpg = data.nextInt();
				}
				
				if(data.hasNextDouble()) {
					cost = data.nextDouble();
				}
				
				if(data.hasNextDouble()) {
					salesPrice = data.nextDouble();
				}
				
				if(data.hasNextLine()) {
					 sold = false;
				}
				
				if(data.hasNextDouble()) {
					priceSold = data.nextDouble();
				}
				
				if(data.hasNextDouble()) {
					profit = data.nextDouble();
				}
			}
			
			System.out.println(id + "\t" + mileage + "\t" + mpg + "\t" + cost + "\t" + salesPrice + "\t" + sold + "\t" + priceSold + "\t" + profit);
			
			saveData();
		}
		
	} catch (IOException e) {
		System.out.println(e);
	}
}

private Connection connect() {
	try {
		Class.forName("com.mysql.cj.jbdc.Driver");
	
		return DriverManager.getConnection("jbdc:mysql://localhost:3308/Carlot", "dbUser", "password123");
	} catch(ClassNotFoundException | SQLException e) {
		System.out.println(e);
		return null;
	}
	
}

private void saveData() {
	try(Connection conn = connect(); 
			PreparedStatement stmnt = conn.prepareStatement("INSERT INTO Carlot VALUES(?, ?, ?, ?, ?, ?, ?)")) {
				stmnt.setString(1, id);
				stmnt.setInt(2, mileage);
				stmnt.setInt(3, mpg);
				stmnt.setDouble(4, cost);
				stmnt.setDouble(5, salesPrice);
				stmnt.setBoolean(6, sold);
				stmnt.setDouble(7, profit);
				
				stmnt.executeUpdate();
				
			} catch(SQLException e) {
				System.out.println(e);
			}
}


	public static void main(String[] args) {
		
		Car car = new Car(); 
		try {
			car.getData();
		} catch (Exception e) {
			System.out.println(e);
		}

   }
}

