package com.fssa.sharetorise.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.exceptions.InvalidInputException;
import com.fssa.sharetorise.exceptions.ServiceException;
import com.fssa.sharetorise.model.User;

public class TestUserService {

	UserService userService = new UserService();

	/**
	 * Test case for validating the addition of a valid customer using the
	 * CustomerService's `addCustomer` method. It verifies whether a valid customer
	 * can be added successfully to the system.
	 *
	 * @throws DAOException       If there is an issue with the database operation
	 *                            during the test.
	 * @throws ValidatorException If there is an issue with validating the customer
	 *                            data during the test.
	 */
	@Test
	@Order(1)
	 void testValidAddUser() {

		User user = new User("Naresh", "v", 9442353110l, "dharun@gmail.com", "1234567890Dh@",
				"1234567890Dh@");

		try {
			Assertions.assertTrue(userService.addUser(user));
		} catch (ServiceException e) {
			Assertions.fail(e);
		}

	}

	@Test
	@Order(2)
	 void testLogin() {

		try {
			userService.login("dharun@gmail.com", "1234567890Dh@");
			System.out.println("Login success");
		} catch (InvalidInputException | DAOException | SQLException e) {
			// TODO Auto-generated catch block

			Assertions.fail("Login fails");
			e.printStackTrace();
		}

	}

}
