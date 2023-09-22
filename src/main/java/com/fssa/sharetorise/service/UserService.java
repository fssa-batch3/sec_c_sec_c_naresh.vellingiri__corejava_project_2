package com.fssa.sharetorise.service;

import java.sql.SQLException;

import com.fssa.sharetorise.dao.UserDAO;
import com.fssa.sharetorise.errors.UserDAOError;
import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.exceptions.InvalidInputException;
import com.fssa.sharetorise.exceptions.ServiceException;
import com.fssa.sharetorise.model.User;
import com.fssa.sharetorise.validator.UserValidator;

public class UserService {

	public boolean addUser(User user) throws ServiceException {

		try {
			if (UserValidator.validate(user)) {
				if (!UserDAO.isAvailableUser(user.getEmail())) {
					return UserDAO.addUser(user);

				} else {

					throw new InvalidInputException(UserDAOError.ALREADY_AVAILABLE_ACCOUNT);
				}

			}

		} catch (InvalidInputException | DAOException e) {

			throw new ServiceException(e.getMessage());

		}

		return false;
	}

	public static void main(String[] args) throws InvalidInputException, DAOException, SQLException {
		User user = login("naresh@gmail.com", "Naresh@2000");
		System.out.println(user);

	}

	public static User login(String email, String password) throws InvalidInputException, DAOException, SQLException {

		UserDAO userDAO = new UserDAO();

		if (UserValidator.validateEmail(email) && UserValidator.validatePassword(password))

			return userDAO.logInUser(email, password);

		return null;

	}

}
