package com.fssa.sharetorise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.model.Certificate;
import com.fssa.sharetorise.model.FundRaiser;
import com.fssa.sharetorise.model.Video;
import com.fssa.sharetorise.util.ConnectionUtil;

public class ShareToRiseDAO {

	public boolean createFundraiser(FundRaiser fundingRaiser) throws DAOException {

		int genenratedFundraiserId = -1;

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO fundraiser ( title, description, funding_goal,fund_ending_date,image_url) VALUES (?, ?, ?, ?,?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));
				preparedStatement.setString(5, fundingRaiser.getImageUrl());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows > 0) {

					try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

						if (generatedKeys.next()) {

							genenratedFundraiserId = generatedKeys.getInt(1);
						}
					}
				}

			}

			createCertificates(fundingRaiser.getCertificate(), genenratedFundraiserId);
			createVideos(fundingRaiser.getVideo(),genenratedFundraiserId);

			return true;

		} catch (SQLException ex) {
			throw new DAOException("Failed to insert the fundraiser into the database. " + ex.getMessage());
		}

	}

	public boolean updateFundraiser(FundRaiser fundingRaiser, int id) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "UPDATE fundraiser SET title = ?, description = ?, funding_goal = ?, fund_ending_date = ?, image_url=? WHERE id = ?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));
				preparedStatement.setString(5, fundingRaiser.getImageUrl());
				preparedStatement.setInt(6, id);

				// Assuming you have a method to get the
				// fundraising_id
				preparedStatement.executeUpdate();

			}

			updateCertificates(fundingRaiser.getCertificate(), id);

			return true;
		} catch (SQLException ex) {
			throw new DAOException("Failed to update the fundraiser in the database." + ex.getMessage());
		}
	}

	public boolean createCertificates(List<Certificate> certificate, int fundraiser_id) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO certificates(fundraiser_id,cert_number,cert_image_url) VALUES(?,?,?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				for (Certificate ele : certificate) {

					preparedStatement.setInt(1, fundraiser_id);
					preparedStatement.setString(2, ele.getCerNum());
					preparedStatement.setString(3, ele.getCerUrl());

					preparedStatement.addBatch();

				}

				int[] affectedRows = preparedStatement.executeBatch();
				for (int rowsAffected : affectedRows) {

					if (rowsAffected <= 0) {

						throw new DAOException("Creation failed for some certificates.");
					}
				}

			}

		} catch (SQLException ex) {
			throw new DAOException("Error while updating certificates" + ex.getMessage());
		}

		return true;
	}
	
	public boolean createVideos(List<Video> videos, int fundraiser_id) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO videolinks(fundraiser_id,links) VALUES(?,?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				for (Video ele : videos) {

					preparedStatement.setInt(1, fundraiser_id);
					preparedStatement.setString(2, ele.getVideoUrl());

					preparedStatement.addBatch();

				}

				int[] affectedRows = preparedStatement.executeBatch();
				for (int rowsAffected : affectedRows) {

					if (rowsAffected <= 0) {

						throw new DAOException("Creation failed for some video links.");
					}
				}

			}

		} catch (SQLException ex) {
			throw new DAOException("Error while updating certificates" + ex.getMessage());
		}

		return true;
	}


	public boolean updateCertificates(List<Certificate> certificates, int fundraiser_id) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "UPDATE certificates SET cert_number = ?, cert_image_url = ? WHERE fundraiser_id = ?";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				for (Certificate certificate : certificates) {
					preparedStatement.setString(1, certificate.getCerNum());
					preparedStatement.setString(2, certificate.getCerUrl());
					preparedStatement.setInt(3, fundraiser_id);
					preparedStatement.addBatch();
				}

				int[] affectedRows = preparedStatement.executeBatch();
				for (int rowsAffected : affectedRows) {
					if (rowsAffected <= 0) {
						throw new DAOException("Update failed for some certificates.");
					}
				}
			}

		} catch (SQLException ex) {
			throw new DAOException("Error while updating certificates." + ex.getMessage());
		}

		return true;
	}

	public List<Certificate> getAllCertificates(int id) throws DAOException {

		List<Certificate> allCert = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT cert_number,cert_image_url FROM certificates WHERE fundraiser_id = ?";

			try (PreparedStatement pst = con.prepareStatement(sql)) {

				pst.setInt(1, id);

				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						Certificate cer = new Certificate();
						cer.setCerNum(rs.getString("cert_number"));
						cer.setCerUrl(rs.getString("cert_image_url"));
						allCert.add(cer);

					}

				}
			}
		} catch (SQLException e) {
			throw new DAOException("Failled to read all certificates." + e.getMessage());
		}

		return allCert;
	}

	public List<FundRaiser> getAllFundraiser() throws DAOException {

		List<FundRaiser> allFundraiser = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT id,title,description,funding_goal,image_url,fund_ending_date FROM fundraiser";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {

						FundRaiser raiser = new FundRaiser();

						raiser.setFundraiserId(rs.getInt("id"));
						raiser.setTitle(rs.getString("title"));
						raiser.setDescription(rs.getString("description"));
						raiser.setFundEndingDate(rs.getDate("fund_ending_date").toLocalDate());
						raiser.setFundingGoal(rs.getDouble("funding_goal"));
						raiser.setImageUrl(rs.getString("image_url"));

						raiser.setCertificate(getAllCertificates(rs.getInt("id")));

						allFundraiser.add(raiser);
					}
				}

			}
		} catch (SQLException e) {

			throw new DAOException("Failed to get all fundraiser." + e.getMessage());
		}

		return allFundraiser;
	}

	public boolean deleteFundraiser(int id) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM fundraiser WHERE id = ?";

			try (PreparedStatement pst = con.prepareStatement(sql)) {

				pst.setInt(1, id);

				int rows = pst.executeUpdate();

				if (rows == 0) {

					throw new DAOException("Something went wrong.");
				}
			}

			deleteCertificates(id);
			deleteVideoLinks(id);
		} catch (SQLException e) {

			throw new DAOException("Failed to delete the fundraiser." + e.getMessage());
		}

		return true;
	}

	public boolean deleteCertificates(int id) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM certificates WHERE fundraiser_id = ?";
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				System.out.println(sql);
				System.out.println(id);
				pst.setInt(1, id);
				System.out.println(sql);

				pst.executeUpdate();

			}
		} catch (SQLException e) {
			throw new DAOException("Failed to delete the certificates." + e.getMessage());
		}
		return true;
	}
	
	public boolean deleteVideoLinks(int id) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM videolinks WHERE fundraiser_id = ?";
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				System.out.println(sql);
				System.out.println(id);
				pst.setInt(1, id);
				System.out.println(sql);

				pst.executeUpdate();

			}
		} catch (SQLException e) {
			throw new DAOException("Failed to delete the video links." + e.getMessage());
		}
		return true;
	}

	public FundRaiser getFundRaiserById(int id) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "SELECT title, description, funding_goal, image_url, fund_ending_date FROM fundraiser WHERE id = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					FundRaiser fundraiser = new FundRaiser();
					fundraiser.setTitle(resultSet.getString("title"));
					fundraiser.setDescription(resultSet.getString("description"));
					fundraiser.setFundingGoal(resultSet.getDouble("funding_goal"));
					fundraiser.setImageUrl(resultSet.getString("image_url"));
					fundraiser.setFundEndingDate(resultSet.getDate("fund_ending_date").toLocalDate());
					return fundraiser;

				}
			}

		}
		return null;

	}

}
