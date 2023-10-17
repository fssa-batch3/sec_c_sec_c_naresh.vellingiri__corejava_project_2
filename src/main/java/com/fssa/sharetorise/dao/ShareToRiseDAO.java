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
import com.fssa.sharetorise.model.SportsCategories;
import com.fssa.sharetorise.model.Video;
import com.fssa.sharetorise.util.ConnectionUtil;

public class ShareToRiseDAO {

	public boolean createFundraiser(FundRaiser fundingRaiser) throws DAOException {

		int genenratedFundraiserId = -1;

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO fundraiser ( title, description, funding_goal,fund_ending_date,image_url,raised_amount,user_id,sports_type ) VALUES (?, ?, ?, ?,?,?,?,?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));
				preparedStatement.setString(5, fundingRaiser.getImageUrl());
				preparedStatement.setDouble(6, 0);
				preparedStatement.setInt(7, fundingRaiser.getUserId());
				preparedStatement.setString(8, fundingRaiser.getCategory().toString());

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
			createVideos(fundingRaiser.getVideo(), genenratedFundraiserId);

			return true;

		} catch (SQLException ex) {
			throw new DAOException("Failed to createFundraiser into the database. " + ex.getMessage());
		}

	}

	public boolean createFundraiserWithCertificates(FundRaiser fundingRaiser) throws DAOException {

		int genenratedFundraiserId = -1;

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO fundraiser ( title, description, funding_goal,fund_ending_date,image_url,raised_amount,user_id,sports_type ) VALUES (?, ?, ?, ?,?,?,?,?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));
				preparedStatement.setString(5, fundingRaiser.getImageUrl());
				preparedStatement.setDouble(6, 0);
				preparedStatement.setInt(7, fundingRaiser.getUserId());
				preparedStatement.setString(8, fundingRaiser.getCategory().toString());
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

			return true;

		} catch (SQLException ex) {
			throw new DAOException("Failed to createFundraiserWithCertificates into the database. " + ex.getMessage());
		}

	}

	public boolean createFundraiserWithVideosOnly(FundRaiser fundingRaiser) throws DAOException {

		int genenratedFundraiserId = -1;

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO fundraiser ( title, description, funding_goal,fund_ending_date,image_url,raised_amount,user_id,sports_type ) VALUES (?, ?, ?, ?,?,?,?,?)";

			try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));
				preparedStatement.setString(5, fundingRaiser.getImageUrl());
				preparedStatement.setDouble(6, 0);
				preparedStatement.setInt(7, fundingRaiser.getUserId());
				preparedStatement.setString(8, fundingRaiser.getCategory().toString());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows > 0) {

					try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

						if (generatedKeys.next()) {

							genenratedFundraiserId = generatedKeys.getInt(1);
						}
					}
				}

			}

			createVideos(fundingRaiser.getVideo(), genenratedFundraiserId);

			return true;

		} catch (SQLException ex) {
			throw new DAOException("Failed to createFundraiserWithVideosOnly into the database. " + ex.getMessage());
		}

	}

	public boolean createFundraiserWithNone(FundRaiser fundingRaiser) throws DAOException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO fundraiser ( title, description, funding_goal,fund_ending_date,image_url,raised_amount,user_id,sports_type ) VALUES (?, ?, ?, ?,?,?,?,?)";
			;

			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

				preparedStatement.setString(1, fundingRaiser.getTitle());
				preparedStatement.setString(2, fundingRaiser.getDescription());
				preparedStatement.setDouble(3, fundingRaiser.getFundingGoal());
				preparedStatement.setDate(4, java.sql.Date.valueOf(fundingRaiser.getFundEndingDate()));
				preparedStatement.setString(5, fundingRaiser.getImageUrl());
				preparedStatement.setDouble(6, 0);
				preparedStatement.setInt(7, fundingRaiser.getUserId());
				preparedStatement.setString(8, fundingRaiser.getCategory().toString());

				int affectedRows = preparedStatement.executeUpdate();

				return affectedRows > 0;

			}

		} catch (SQLException ex) {
			throw new DAOException("Failed to createFundraiserWithNone into the database. " + ex.getMessage());
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

	public List<Video> getAllVideos(int id) throws DAOException {

		List<Video> allVideos = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT links FROM videolinks WHERE fundraiser_id = ?";

			try (PreparedStatement pst = con.prepareStatement(sql)) {

				pst.setInt(1, id);

				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						Video video = new Video();
						video.setVideoUrl(rs.getString("links"));
						allVideos.add(video);

					}

				}
			}
		} catch (SQLException e) {
			throw new DAOException("Failled to read all certificates." + e.getMessage());
		}

		return allVideos;
	}

	public List<FundRaiser> getAllFundraiser() throws DAOException {

		List<FundRaiser> allFundraiser = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT id,title,description,funding_goal,image_url,fund_ending_date,raised_amount FROM fundraiser";

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
						raiser.setRaised_amount(rs.getDouble("raised_amount"));

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

				deleteCertificates(id);
				deleteVideoLinks(id);

				int rows = pst.executeUpdate();

				if (rows == 0) {

					throw new DAOException("Something went wrong.");
				}
			}

		} catch (SQLException e) {

			throw new DAOException("Failed to delete the fundraiser." + e.getMessage());
		}

		return true;
	}

	public boolean deleteCertificates(int id) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM certificates WHERE fundraiser_id = ?";
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setInt(1, id);
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

				pst.setInt(1, id);

				pst.executeUpdate();

			}
		} catch (SQLException e) {
			throw new DAOException("Failed to delete the video links." + e.getMessage());
		}
		return true;
	}

	public FundRaiser getFundRaiserById(int id) throws SQLException {

		try (Connection con = ConnectionUtil.getConnection()) {

			String query = "SELECT id,title,description,funding_goal,image_url,fund_ending_date,raised_amount,sports_type FROM fundraiser WHERE id = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					FundRaiser fundraiser = new FundRaiser();

					fundraiser.setFundraiserId(id);
					fundraiser.setTitle(resultSet.getString("title"));
					fundraiser.setDescription(resultSet.getString("description"));
					fundraiser.setFundingGoal(resultSet.getDouble("funding_goal"));
					fundraiser.setImageUrl(resultSet.getString("image_url"));
					fundraiser.setCertificate(getAllCertificates(id));
					fundraiser.setVideo(getAllVideos(id));
					fundraiser.setFundEndingDate(resultSet.getDate("fund_ending_date").toLocalDate());
					fundraiser.setCategory(SportsCategories.valueOf(resultSet.getString("sports_type")));
					return fundraiser;

				}
			}

		}
		return null;

	}

	public List<FundRaiser> getAllFundraiserByUserId(int id) throws DAOException {

		List<FundRaiser> allFundraiserByUserId = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT id,title,description,funding_goal,image_url,fund_ending_date,raised_amount FROM fundraiser where user_id=?";

			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				stmt.setInt(1, id);
				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {

						FundRaiser raiser = new FundRaiser();

						raiser.setFundraiserId(rs.getInt("id"));
						raiser.setUserId(id);
						raiser.setTitle(rs.getString("title"));
						raiser.setDescription(rs.getString("description"));
						raiser.setFundEndingDate(rs.getDate("fund_ending_date").toLocalDate());
						raiser.setFundingGoal(rs.getDouble("funding_goal"));
						raiser.setImageUrl(rs.getString("image_url"));
						raiser.setRaised_amount(rs.getDouble("raised_amount"));

						raiser.setCertificate(getAllCertificates(rs.getInt("id")));

						allFundraiserByUserId.add(raiser);
					}
				}

			}
		} catch (SQLException e) {

			throw new DAOException("Failed to get all fundraiser." + e.getMessage());
		}

		return allFundraiserByUserId;
	}

	public static void main(String[] args) {
		ShareToRiseDAO d = new ShareToRiseDAO();
		System.out.println(d.getAllFundraiserByUserId(3));

	}

}
