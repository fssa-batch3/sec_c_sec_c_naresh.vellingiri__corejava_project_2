package com.fssa.sharetorise.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.model.FundingRaiser;
import com.fssa.sharetorise.util.ConnectionUtil;

public class ShareToRiseDao {


	public boolean createFundraiser(FundingRaiser fundingRaiser) throws DAOException {
		

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO fundraiser ( Title, Description, FundingGoal, FundEndingDate, NoOfDaysRequired) VALUES (?, ?, ?, ?, ?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setObject(4, fundingRaiser.getFundEndingDate());
				preparedStatement.setInt(5, fundingRaiser.getNoOfDaysRequired());

				preparedStatement.executeUpdate();


				return true;

			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the database.");
		}

	}
	
	
	    
	 


}
