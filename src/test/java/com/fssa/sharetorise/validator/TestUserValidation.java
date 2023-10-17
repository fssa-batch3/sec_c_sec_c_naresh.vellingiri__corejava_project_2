package com.fssa.sharetorise.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.errors.UserValidationErrors;
import com.fssa.sharetorise.exceptions.InvalidInputException;
import com.fssa.sharetorise.model.User;

public class TestUserValidation {
	
	User user = new User();
	User user1 = new User("Naresh", "v", 6383434544l, "naresh123@gmail.com", "Naresh@2000", "Naresh@2000");
	User user2 = new User("Naresh", "v", 63834544l, "naresh@gmail.com", "Naresh@2000", "Naresh@2000");

	@Test
	void testValidate() throws InvalidInputException {
		Assertions.assertTrue(UserValidator.validate(user1));
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
		user.setFirstName("Dharunraj");
		Assertions.assertTrue(UserValidator.validateFirstName(user.getFirstName()));
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
		user.setLastName("Alagaruppu");
		Assertions.assertTrue(UserValidator.validateLastName(user.getLastName()));
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
		user.setEmail("dharun@gmail.com");
		Assertions.assertTrue(UserValidator.validateEmail(user.getEmail()));
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
		user.setPassword("1234567890Dh@");
		Assertions.assertTrue(UserValidator.validatePassword(user.getPassword()));
	}

	@Test
	void testValidConfirmPassword() throws InvalidInputException {
		user.setConfirmPassword("1234567890Dh@");
		Assertions.assertTrue(UserValidator.validatePassword(user.getConfirmPassword()));
	}

	@Test
	void testActive() throws InvalidInputException {
		user.setActive(true);
		Assertions.assertTrue(user.isActive());
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
	
	
	@Test
	void testValidPhoneNumber() throws InvalidInputException {
		user.setPhoneNumber(6383434544l);
		Assertions.assertTrue(UserValidator.validatePhoneNumber(user.getPhoneNumber()));
	}
	
	@Test
	void testValidId() throws InvalidInputException {
		user.setUserId(1);
		Assertions.assertTrue(UserValidator.validateId(user.getUserId()));
	}

	@Test
	void testToString() {
		try {
			user.toString();
		} catch (Exception e) {
             System.out.println("to string method is not working");
		}
	}

}
