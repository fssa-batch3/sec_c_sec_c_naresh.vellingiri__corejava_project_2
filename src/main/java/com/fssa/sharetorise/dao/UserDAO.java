package com.fssa.sharetorise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.sharetorise.constants.ShareToRiseConstants;
import com.fssa.sharetorise.errors.UserDAOError;
import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.model.User;
import com.fssa.sharetorise.util.ConnectionUtil;

public class UserDAO {
	
	
	public UserDAO() {
//		private constructor 
	}

	

	public static boolean addUser(User user) throws DAOException {

		final String query = "INSERT INTO users (first_name,last_name,email,phone,password) VALUES (?,?,?,?,?)";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, user.getFirstName());
				pst.setString(2, user.getLastName());
				pst.setString(3, user.getEmail());
				pst.setLong(4, user.getPhoneNumber());
				pst.setString(5, user.getPassword());
				
				pst.executeUpdate();
			}
			

		}

		catch (SQLException e) {

			throw new DAOException(UserDAOError.INVALID_DATA);
		}

		return true;

	}

	/**
	 * Attempts to log in a customer using the provided credentials (phone, email,
	 * and password).
	 *
	 * @param phone    The phone number of the customer.
	 * @param email    The email address of the customer.
	 * @param password The password of the customer.
	 * @return {@code true} if the login is successful, {@code false} otherwise.
	 * @throws DAOException If there is an issue with the database operation during
	 *                      login.
	 * @throws SQLException If there is an issue with executing the SQL query.
	 */

	public static User logInUser(String email, String password) throws DAOException, SQLException {

		final String query = "SELECT * FROM users WHERE email = ? AND password=? ";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, email);
				pst.setString(2, password);

				
				try (ResultSet rs = pst.executeQuery()) {

					if (rs.next()) {

						User user= new User();
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setPhoneNumber(rs.getLong("phone"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("password"));
						user.setActive(rs.getBoolean("is_active"));
						user.setCustomerId(rs.getInt("customer_id"));
						
							return user;
					}
				}
			}
		}

		catch (SQLException e) {

			throw new DAOException(UserDAOError.INVALID_DATA);
		}
		return null;
		
		
	}
	
	

	public static boolean isAvailableUser(long phone) throws DAOException {

		final String query = "SELECT phone FROM users WHERE phone = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setLong(1, phone);

				try (ResultSet rs = pst.executeQuery()) {

					if (rs.next()) {

						return true;
					}
				}
			}
		} catch (SQLException e) {

			throw new DAOException(UserDAOError.UNAVAILABLE_ACCOUNT);
		}

		return false;
	}

	public static boolean isActive(long phone) throws DAOException {
		final String query = "SELECT COUNT(*) FROM users WHERE phone = ? AND is_active = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setLong(1, phone);
				pst.setBoolean(2, ShareToRiseConstants.STATIC_IS_ACTIVE_TRUE);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {

						return true;
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(UserDAOError.INVALID_USER);
		}
		return false;
	}

	
	public static List<User> getUserDetailsByPhoneNumber(long phone) throws DAOException {

		List<User> userList = new ArrayList<>();

		final String query = "SELECT customer_id,first_name,last_name,phone,email,password,is_active FROM users WHERE phone = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setLong(1, phone);
				try (ResultSet rs = pst.executeQuery()) {

					if (rs.next()) {

						User user = new User();
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setPhoneNumber(rs.getLong("phone"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("password"));
						user.setActive(rs.getBoolean("is_active"));
						user.setCustomerId(rs.getInt("customer_id"));
						userList.add(user);
					}
				}
			}
		}

		catch (SQLException e) {

			throw new DAOException(UserDAOError.INVALID_DATA);
		}

		return userList;

	}
}