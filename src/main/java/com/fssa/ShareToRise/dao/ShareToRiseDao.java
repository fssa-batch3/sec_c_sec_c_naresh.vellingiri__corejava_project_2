package com.fssa.ShareToRise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.fssa.ShareToRise.model.FundingRaiser;
import com.fssa.ShareToRise.service.UserService;
import com.fssa.ShareToRise.validator.FundraisingValidator;

import day11.practice.DAOException;

public class ShareToRiseDao {

	public static boolean createTask(FundingRaiser fundingRaiser) throws DAOException {
		

		if (fundingRaiser == null) {
			throw new DAOException("Fundraiser object cannot be null");

		}
		System.out.println("FundingRaiser inserted successfully!");

		String url = "jdbc:mysql://localhost:3306/sharetorise";
		String userName = "root";
		String password = "123456";

		try (Connection con = DriverManager.getConnection(url, userName, password)) {
			String sql = "INSERT INTO fundraiser (personID, Title, Description, FundingGoal, FundEndingDate, NoOfDaysRequired) VALUES (?, ?, ?, ?, ?, ?)";
 
			
			try {

				System.out.println("1!");

				PreparedStatement preparedStatement = con.prepareStatement(sql);
				
				System.out.println("FundingRaiser 2!");

				preparedStatement.setInt(1, fundingRaiser.getId());
				preparedStatement.setString(2, fundingRaiser.getTitle());
				preparedStatement.setString(3, fundingRaiser.getDescription());
				preparedStatement.setDouble(4, fundingRaiser.getFundingGoal());
				preparedStatement.setObject(5, fundingRaiser.getFundEndingDate()); 
				preparedStatement.setInt(6, fundingRaiser.getNoOfDaysRequired());
				
				System.out.println("FundingRaiser inserted 3!");

				preparedStatement.executeUpdate();
				
				System.out.println("FundingRaiser inserted 4!");

				System.out.println("FundingRaiser inserted successfully!");

			}

			catch (SQLException e) {
				System.err.println("Failed to insert FundingRaiser into the database.");
				e.printStackTrace();
			}
			return true;

		} catch (SQLException ex) {
			throw new DAOException("Connection is not created");
		}

	}
	
	
	public static boolean updateTask(FundingRaiser fundingRaiser) throws DAOException {

		if (fundingRaiser == null) {
			throw new DAOException("Fundraiser object cannot be null");

		}
 
		
		String url = "jdbc:mysql://localhost:3306/sharetorise";
		String userName = "root";
		String password = "123456";
//		Connection con = null;

		try (Connection con = DriverManager.getConnection(url, userName, password)) {
			String sql = "UPDATE  fundraiser SET  title=?,description=?,FundingGoal=?,FundEndingDate=?,NoOfDaysRequired=? WHERE personID = ?";
 
			try {

				PreparedStatement preparedStatement = con.prepareStatement(sql);

				
				
				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setObject(4, fundingRaiser.getFundEndingDate()); 
				preparedStatement.setInt(5, fundingRaiser.getNoOfDaysRequired());
				
				preparedStatement.setInt(6, fundingRaiser.getId());
				preparedStatement.executeUpdate();

				

			}

			catch (SQLException e) {
				System.err.println("Failed to update FundingRaiser into the database.");
				e.printStackTrace();
			}
			return true;

		} catch (SQLException ex) {
			throw new DAOException("Connection is not created");
		}

	}
	
	

	public static void main(String[] args) throws DAOException {
		FundingRaiser fundraiser = new FundingRaiser();
		
		fundraiser.setDescription("A great project description");
		fundraiser.setFundEndingDate(LocalDate.of(2023, 12, 31));
		fundraiser.setFundingGoal(5000.0);
		fundraiser.setNoOfDaysRequired(77);
		fundraiser.setTitle("Project XYZ");
		
		

	}
}

