package com.fssa.sharetorise.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.errors.UserValidationErrors;
import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.exceptions.ServiceException;
import com.fssa.sharetorise.model.User;

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
class TestUserService{
	
	
	
	@Test
	 void testValidAddUser() throws ServiceException{

		 UserService  userService = new UserService();
			User user = new User("Naresh","v",6383434544l,"naresh2356789@gmail.com","Naresh@2000","Naresh@2000");
		
			Assertions.assertTrue(userService.addUser(user));
		
	}
	
	@Test
	 void testInValidAddUser() throws ServiceException{

		 UserService  userService = new UserService();
			User user = new User("Naresh","v",6383434544l,"naresh@gmail.com","Naresh@2000","Naresh@2000");
			User user1 = new User();
			
			System.out.println(user1.toString());

			user1.setFirstName("Naresh");
			user1.setLastName("v");
			user1.setPassword("Naresh@2000");
			user1.setEmail("naresh@gmail.com");
			user1.setPhoneNumber(6383434544l);
			user1.setConfirmPassword("Naresh@2000");
			
			System.out.println(user1.toString());

		try {
			Assertions.assertTrue(userService.addUser(user));
		} catch (ServiceException | DAOException e) {
			Assertions.assertEquals("Add User is invalid", e.getMessage());
		}
	 }
	
	
	
	@Test
	void testLoginUserInvalid() throws ServiceException {
		UserService  userService = new UserService();
		try {
			userService.login("   ","  ");
		}
		catch(ServiceException e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_EMPTY_EMAIL,e.getMessage());
		}
		
	}
}


