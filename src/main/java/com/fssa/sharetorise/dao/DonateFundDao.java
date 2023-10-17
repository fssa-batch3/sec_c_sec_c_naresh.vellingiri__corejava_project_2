package com.fssa.sharetorise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fssa.sharetorise.exceptions.DAOException;

import com.fssa.sharetorise.util.ConnectionUtil;

public class DonateFundDao {

	public boolean donateFund(double amount, int userId, int fundRaiserId) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO donation (donation_amount, donated_user_id, fundraiser_id) VALUES (?, ?, ?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				preparedStatement.setDouble(1, amount);
				preparedStatement.setInt(2, userId);
				preparedStatement.setInt(3, fundRaiserId);

				int affectedRows = preparedStatement.executeUpdate();

				updateDonateFund(amount, fundRaiserId);
			}

			return true;

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the donated  database. " + ex.getMessage());
		}

	}

	public boolean updateDonateFund(double amount, int fundRaiserId) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "UPDATE fundraiser SET raised_amount=? WHERE id=?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				amount += getFundRaisedAmountById(fundRaiserId);
				preparedStatement.setDouble(1, amount);
				preparedStatement.setInt(2, fundRaiserId);

				int affectedRows = preparedStatement.executeUpdate();

			}

			return true;

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the database. " + ex.getMessage());
		}

	}

	public double getFundRaisedAmountById(int fundRaiserId) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT raised_amount FROM fundraiser  WHERE id=?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				preparedStatement.setDouble(1, fundRaiserId);

				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					double raisedAmount = rs.getDouble("raised_amount");
					return raisedAmount;
				}
			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the database. " + ex.getMessage());
		}
		return -1;

	}

	public double getFundGoalAmountById(int fundRaiserId) throws DAOException {

		System.out.println("hi");

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT raised_amount FROM fundraiser  WHERE id=?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				preparedStatement.setDouble(1, fundRaiserId);

				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					double raisedAmount = rs.getDouble("raised_amount");
					System.out.println(raisedAmount);
					return raisedAmount;

				}
			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the database. " + ex.getMessage());
		}
		return -1;

	}

	public int countDonationsByFundraiserId(int fundraiserId) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "SELECT COUNT(*) FROM donation WHERE fundraiser_id = ?";
			try (PreparedStatement statement = con.prepareStatement(sql)) {
				statement.setInt(1, fundraiserId);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						return resultSet.getInt(1);
					}
				}
			}
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage());
		}
		return fundraiserId = 0;
	}
}
