package com.fssa.sharetorise.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.sharetorise.exceptions.DAOException;

//naresh
public class ConnectionUtil {

	public static Connection getConnection() {
		Connection con = null;

		String url;
		String userName;
		String passWord;

		url = System.getenv("LOCAL_DATABASE_HOST");
		userName = System.getenv("LOCAL_DATABASE_USERNAME");
		passWord = System.getenv("LOCAL_DATABASE_PASSWORD");
		
		
//		url = System.getenv("CLOUD_DATABASE_HOST");
//		userName = System.getenv("CLOUD_DATABASE_USERNAME");
//		passWord = System.getenv("CLOUD_DATABASE_PASSWORD");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);


		} catch (Exception e) {
			throw new DAOException("Unable to connect to the database(connection util)");
		} 

		return con;
	}
	
	


	
	


}
