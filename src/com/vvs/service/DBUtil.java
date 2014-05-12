package com.vvs.service;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBUtil {

	public static Connection getConnection() throws ClassNotFoundException,
	SQLException {
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
Connection connection = DriverManager.getConnection
("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");
/*Connection connection = DriverManager.getConnection
 ("jdbc:sqlserver://192.168.144.81;databaseName=vvs;user=sa;password=root123");*/
/* Connection connection = DriverManager.getConnection
		 ("jdbc:sqlserver://192.168.10.6;databaseName=SPEND_ANALYZER;user=sa;password=sa@SQL123");*/
return connection;
}
 
	public static void main(String[] argv) {
 
		System.out.println("-------- SQL JDBC Connection Testing ------");
 
		try {
 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your sql JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("SQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			/* connection = DriverManager.getConnection
					 ("jdbc:sqlserver://192.168.144.81;databaseName=vvs;user=sa;password=root123");*/

			 
			// To deploy in public ip uncomment  this
			connection = DriverManager.getConnection
						("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");

 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}
 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			 PreparedStatement ps=null;
			try {
				ps = connection.prepareStatement("Select * from VVSDetails");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				ResultSet rs=null;
				try {
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(rs != null ){
				try {
					while (rs.next()) {
						{
						System.out.println(rs.getString("body"));
						}
					}
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 	
			}else
			{
				
			}
		} else {
			System.out.println("Failed to make connection!");
		}
	}
 
}
