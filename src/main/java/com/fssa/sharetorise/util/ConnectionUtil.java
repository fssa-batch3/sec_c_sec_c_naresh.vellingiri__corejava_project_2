package com.fssa.sharetorise.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.fssa.sharetorise.exceptions.DAOException;

import io.github.cdimascio.dotenv.Dotenv;


//naresh
public class ConnectionUtil {
	
	public static Connection getConnection() {
		Connection con = null;

		String url;
		String userName;
		String passWord;

		if(System.getenv("CI")!=null)
		{
			url = System.getenv("DATABASE_HOST");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		}else
		{
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOST");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
		}

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			
		}catch(Exception e)
		{
			throw new DAOException("Unable to connect to the database");
		}
		
		return con;
	}


	
}

