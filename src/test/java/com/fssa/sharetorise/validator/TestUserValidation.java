package com.fssa.sharetorise.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.errors.UserValidationErrors;
import com.fssa.sharetorise.exceptions.InvalidInputException;
import com.fssa.sharetorise.model.User;

public class TestUserValidation {

	User customer = new User();

	@Test

	 void testValidate() throws InvalidInputException {

		User customer = new User("Aravind", "Ram", 9080668509l, "aravind@gmail.com", "1234567890Dh@", "1234567890Dh@");

		Assertions.assertTrue(UserValidator.validate(customer));
	} 

	@Test

	 void testInvalidObject() throws InvalidInputException {

		try {

			UserValidator.validate(null);
		} catch (Exception e) {

			Assertions.assertEquals(UserValidationErrors.NULL_OBJECT, e.getMessage());
		}

	}

	@Test

	 void testValidFirstName() throws InvalidInputException {

		customer.setFirstName("Dharunraj");

		Assertions.assertTrue(UserValidator.validateFirstName(customer.getFirstName()));
	}

	@Test
	 void testNullFirstName() throws InvalidInputException {

		try {
			UserValidator.validateFirstName(null);

		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_NULL_FIRST_NAME, e.getMessage());
		}

	}

	@Test
	 void testEmptyFirstName() throws InvalidInputException {

		try {
			UserValidator.validateFirstName("");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_EMPTY_FIRST_NAME, e.getMessage());
		}

	}

	@Test

	 void testpatternFirstName() throws InvalidInputException {

		try {

			UserValidator.validateFirstName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	 void testLengthOfFirstName() throws InvalidInputException {

		try {

			UserValidator.validateFirstName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_LENGTH_FIRST_NAME, e.getMessage());
		}
	}

	@Test

	 void testValidLastName() throws InvalidInputException {

		customer.setLastName("Alagaruppu");

		Assertions.assertTrue(UserValidator.validateLastName(customer.getLastName()));
	}

	@Test
	 void testNullLastName() throws InvalidInputException {

		try {
			UserValidator.validateLastName(null);

		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_NULL_LAST_NAME, e.getMessage());
		}

	}

	@Test
	 void testEmptyLastName() throws InvalidInputException {

		try {
			UserValidator.validateLastName("");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_EMPTY_LAST_NAME, e.getMessage());
		}

	}

	@Test

	 void testpatternLastName() throws InvalidInputException {

		try {

			UserValidator.validateLastName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	 void testLengthOfLastName() throws InvalidInputException {

		try {

			UserValidator.validateLastName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_LENGTH_LAST_NAME, e.getMessage());
		}
	}

	@Test

	 void testValidEmail() throws InvalidInputException {

		customer.setEmail("dharun@gmail.com");

		Assertions.assertTrue(UserValidator.validateEmail(customer.getEmail()));
	}

	@Test
	 void testNullEmail() throws InvalidInputException {

		try {
			UserValidator.validateEmail(null);

		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_NULL_EMAIL, e.getMessage());
		}

	}

	@Test
	 void testEmptyEmail() throws InvalidInputException {

		try {
			UserValidator.validateEmail("");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_EMPTY_EMAIL, e.getMessage());
		}

	}

	@Test

	 void testpatternEmail() throws InvalidInputException {

		try {

			UserValidator.validateEmail("dharungmail");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_PATTERN_EMAIL, e.getMessage());
		}
	}

	@Test

	 void testValidPassword() throws InvalidInputException {

		customer.setPassword("1234567890Dh@");

		Assertions.assertTrue(UserValidator.validatePassword(customer.getPassword()));
	}

	@Test
	 void testNullPassword() throws InvalidInputException {

		try {
			UserValidator.validatePassword(null);

		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_NULL_PASSWORD, e.getMessage());
		}

	}

	@Test
	 void testEmptyPassword() throws InvalidInputException {

		try {
			UserValidator.validatePassword("");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_EMPTY_PASSWORD, e.getMessage());
		}

	}

	@Test

	 void testpatternPassword() throws InvalidInputException {

		try {

			UserValidator.validatePassword("Dharunraj@123");
		} catch (Exception e) {
			Assertions.assertEquals(UserValidationErrors.INVALID_PASSWORD, e.getMessage());
		}
	}

	@Test

	 void testNullComparePassword() throws InvalidInputException {

		try {

			UserValidator.validateComparePassword(null, null);
		} catch (Exception e) {

			Assertions.assertEquals(UserValidationErrors.INVALID_NULL_PASSWORD, e.getMessage());
		}
	}

	@Test

	 void testEmptyComparePassword() throws InvalidInputException {

		try {

			UserValidator.validateComparePassword("", "");
		} catch (Exception e) {

			Assertions.assertEquals(UserValidationErrors.INVALID_EMPTY_PASSWORD, e.getMessage());
		}
	}

	@Test

	 void testInvalidComparePassword() throws InvalidInputException {

		try {

			UserValidator.validateComparePassword("Dharunraj@123", "Dharunraj@124");
		} catch (Exception e) {

			Assertions.assertEquals(UserValidationErrors.WRONG_PASSWORD, e.getMessage());
		}
	}

	
}
