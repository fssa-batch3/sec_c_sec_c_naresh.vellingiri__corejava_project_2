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
				if(!UserDAO.isAvailableUser(user.getPhoneNumber())) {
					return UserDAO.addUser(user);

					
				}
				else {
					
					throw new InvalidInputException(UserDAOError.UNAVAILABLE_ACCOUNT);
				}

				
			}

		} catch (InvalidInputException | DAOException e) {

			e.getMessage();
			throw new ServiceException(e.getMessage());

		} 

		return false;
	}

	
	public User login(String email, String password) throws InvalidInputException, DAOException, SQLException {
		
        UserDAO userDAO = new UserDAO();

        if(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))
        	 
            return userDAO.logInUser(email, password);

        return null;
        
	}
	
}
