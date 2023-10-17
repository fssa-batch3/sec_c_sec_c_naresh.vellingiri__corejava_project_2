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

				} 
				
				else {
					throw new ServiceException("Add User is invalid");
				}
			
			}

		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());

		}

		return false;
	}

	
	
	


	public User login(String email, String password) throws ServiceException {

		UserDAO userDAO = new UserDAO();
 
		
			try {
				if (UserValidator.validateEmail(email) && UserValidator.validatePassword(password))
				return userDAO.logInUser(email, password);
			} catch (DAOException | SQLException | InvalidInputException  e) {
				throw new ServiceException(e.getMessage());
			}
		
		return null;


	}

}
