package com.fssa.sharetorise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.logger.Logger;
import com.fssa.sharetorise.model.FundingRaiser;
import com.fssa.sharetorise.util.ConnectionUtil;

public class ShareToRiseDao {

	Logger logger = new Logger();

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

				logger.info("Fundraiser inserted successfully!");

				return true;

			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the database.");
		}

	}

	public boolean updateUpdateFundraiser(FundingRaiser fundingRaiser) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "UPDATE  fundraiser SET  title=?,description=?,FundingGoal=?,FundEndingDate=?,NoOfDaysRequired=?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setObject(4, fundingRaiser.getFundEndingDate());
				preparedStatement.setInt(5, fundingRaiser.getNoOfDaysRequired());

				preparedStatement.executeUpdate();

				logger.info("Fundraiser updated successfully!");

				return true;

			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to update FundingRaiser into the database.");
		}

	}

}