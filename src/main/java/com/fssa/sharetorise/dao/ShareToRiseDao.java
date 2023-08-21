package com.fssa.sharetorise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.model.FundingRaiser;
import com.fssa.sharetorise.util.ConnectionUtil;

public class ShareToRiseDao {

	public boolean createFundraiser(FundingRaiser fundingRaiser) throws DAOException {

		try (Connection con = ConnectionUtil.getMyConnection()) {
			String sql = "INSERT INTO fundraiser ( title, description, funding_goal,fund_ending_date) VALUES (?, ?, ?, ?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));

				preparedStatement.executeUpdate();

				return true;

			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the database.");
		}

	}

	public boolean updateFundraiser(FundingRaiser fundingRaiser) throws DAOException {

		try (Connection con = ConnectionUtil.getMyConnection()) {
			String sql = "UPDATE fundraiser SET title = ?, description = ?, funding_goal = ?, fund_ending_date = ? WHERE fundraiser_id = ?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));
				preparedStatement.setInt(5, fundingRaiser.getFundraiserId()); // Assuming you have a method to get the
																				// fundraising_id

				int rowsAffected = preparedStatement.executeUpdate();

				return rowsAffected > 0; // Return true if at least one row was affected (updated)
			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to update the fundraiser in the database.");
		}
	}

	
	public List<FundingRaiser> readAllFundraisers() throws DAOException {
	    List<FundingRaiser> fundraisers = new ArrayList<>();

	    try (Connection con = ConnectionUtil.getMyConnection()) {
	        String sql = "SELECT * FROM fundraiser";

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    int fundraiserId = resultSet.getInt("fundraiser_id");
	                    String title = resultSet.getString("title");
	                    String description = resultSet.getString("description");
	                    double fundingGoal = resultSet.getDouble("funding_goal");
	                    LocalDate fundEndingDate = resultSet.getDate("fund_ending_date").toLocalDate();

	                    FundingRaiser fundingRaiser = new FundingRaiser(fundraiserId, title, description, fundingGoal, fundEndingDate);
	                    fundraisers.add(fundingRaiser);
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        throw new DAOException("Failed to retrieve all datas  from the database.");
	    }

	    return fundraisers;
	}
	
	
//	method for getting all Id's
	
	public List<Integer> getAllFundraiserIds() throws DAOException {
	    List<Integer> fundraiserIds = new ArrayList<>();

	    try (Connection con = ConnectionUtil.getMyConnection()) {
	        String sql = "SELECT fundraiser_id FROM fundraiser";

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    int fundraiserId = resultSet.getInt("fundraiser_id");
	                    fundraiserIds.add(fundraiserId);
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        throw new DAOException("Failed to retrieve fundraiser IDs from the database.");
	    }

	    return fundraiserIds;
	}


}
