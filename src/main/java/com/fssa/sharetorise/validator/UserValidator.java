package com.fssa.sharetorise.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.sharetorise.errors.UserValidationErrors;
import com.fssa.sharetorise.exceptions.InvalidInputException;
import com.fssa.sharetorise.logger.Logger;
import com.fssa.sharetorise.model.User;

public class UserValidator {

	private UserValidator() {

	}

	public static final int LENGTH_OF_FIRST_NAME = 30;

	public static final int LENGTH_OF_LAST_NAME = 30;


	/**
	 * Validates the provided customer object's data.
	 *
	 * @param customer The customer object to be validated
	 * @return True if the customer data is valid, false otherwise
	 * @throws CustomerInvalidInputException If any validation error occurs during the
	 *                                    process
	 * @throws AccountInvalidInputException  If an account validation error occurs
	 *                                    during the process
	 */

	
	public static boolean validate(User user) throws InvalidInputException {
		
		

		if (user == null) {

			throw new InvalidInputException(UserValidationErrors.NULL_OBJECT);
		}

		validateFirstName(user.getFirstName());
		validateLastName(user.getLastName());
		validateEmail(user.getEmail());
		validateComparePassword(user.getPassword(), user.getConfirmPassword());
		validatePhoneNumber(user.getPhoneNumber());

		return true;

	}

	
	
	
	/**
	 * Validates the first name of a customer.
	 *
	 * @param fName The first name to be validated
	 * @return True if the first name is valid, false otherwise
	 * @throws CustomerInvalidInputException If the first name is invalid
	 */

	public static boolean validateFirstName(String firstName) throws InvalidInputException {

		if (firstName == null) {

			throw new InvalidInputException(UserValidationErrors.INVALID_NULL_FIRST_NAME);
		}

		else if ("".equals(firstName.trim())) {

			throw new InvalidInputException(UserValidationErrors.INVALID_EMPTY_FIRST_NAME);
		}

		else if (firstName.trim().length() >= LENGTH_OF_FIRST_NAME) {

			throw new InvalidInputException(UserValidationErrors.INVALID_LENGTH_FIRST_NAME);
		}

		String regexFirstName = "^[A-Za-z\\s]+$";
		Pattern pattern = Pattern.compile(regexFirstName); // compiles the given pattern
		Matcher matcher = pattern.matcher(firstName); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (!isMatch) {

			throw new InvalidInputException(UserValidationErrors.INVALID_NAME);
		}
		return true;

	}

	/**
	 * Validates the last name of a customer.
	 *
	 * @param lName The last name to be validated
	 * @return True if the last name is valid, false otherwise
	 * @throws CustomerInvalidInputException If the last name is invalid
	 */

	public static boolean validateLastName(String lastName) throws InvalidInputException {

		if (lastName == null) {

			throw new InvalidInputException(UserValidationErrors.INVALID_NULL_LAST_NAME);
		}

		else if ("".equals(lastName.trim())) {

			throw new InvalidInputException(UserValidationErrors.INVALID_EMPTY_LAST_NAME);
		}

		else if (lastName.trim().length() >= LENGTH_OF_LAST_NAME) {

			throw new InvalidInputException(UserValidationErrors.INVALID_LENGTH_LAST_NAME);
		}

		String regexFirstName = "^[A-Za-z\\s]+$";
		Pattern pattern = Pattern.compile(regexFirstName); // compiles the given pattern
		Matcher matcher = pattern.matcher(lastName); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (!isMatch) {

			throw new InvalidInputException(UserValidationErrors.INVALID_NAME);
		}
		return true;

	}

	/**
	 * Validates the email address of a customer.
	 *
	 * @param email The email address to be validated
	 * @return True if the email address is valid, false otherwise
	 * @throws CustomerInvalidInputException If the email address is invalid
	 */

	public static boolean validateEmail(String email) throws InvalidInputException {

		if (email == null) {

			throw new InvalidInputException(UserValidationErrors.INVALID_NULL_EMAIL);
		}

		else if ("".equals(email.trim())) {
			throw new InvalidInputException(UserValidationErrors.INVALID_EMPTY_EMAIL);
		}
		return true;

	}

	/**
	 * Validates a customer's password against a strong password pattern.
	 *
	 * @param password The password to be validated
	 * @return True if the password is strong, false otherwise
	 * @throws CustomerInvalidInputException If the password is invalid
	 */

	public static boolean validatePassword(String password) throws InvalidInputException {

		if (password == null) {

			throw new InvalidInputException(UserValidationErrors.INVALID_NULL_PASSWORD);
		}

		else if ("".equals(password.trim())) {

			throw new InvalidInputException(UserValidationErrors.INVALID_EMPTY_PASSWORD);
		}

		String regexStrongPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(regexStrongPassword); // compiles the given pattern
		Matcher matcher = pattern.matcher(password); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (!isMatch) {

			throw new InvalidInputException(UserValidationErrors.INVALID_PATTERN_PASSWORD);
		}
		return true;

	}

	/**
	 * Validates if the provided password and confirmation password match and are
	 * strong.
	 *
	 * @param pass    The password to be validated
	 * @param confirm The confirmation password to be validated
	 * @return True if both passwords match and are strong, false otherwise
	 * @throws CustomerInvalidInputException If the passwords do not match or are
	 *                                    invalid
	 */

	public static boolean validateComparePassword(String pass, String confirm) throws InvalidInputException {

		if (pass == null || confirm == null) {

			throw new InvalidInputException(UserValidationErrors.INVALID_NULL_PASSWORD);
		}

		else if ("".equals(pass.trim()) || "".equals(confirm.trim())) {

			throw new InvalidInputException(UserValidationErrors.INVALID_EMPTY_PASSWORD);
		}

		boolean password = validatePassword(pass);
		boolean confirmPassword = validatePassword(confirm);

		if (!password || !confirmPassword || !pass.equals(confirm)) {

			throw new InvalidInputException(UserValidationErrors.WRONG_PASSWORD);
		}

		return true;

	}
	
	
	
	

	public static boolean validatePhoneNumber(long phoneNumber) throws InvalidInputException {

		String num = Long.toString(phoneNumber);
				
		String regexPhoneNumber = "^[0-9]{10}$"; // This the phone number regex pattern
		Pattern pattern = Pattern.compile(regexPhoneNumber); // this regex is validate if the string is valid or not
		Matcher matcher = pattern.matcher(num); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false

		if (!isMatch) {
			Logger.info("Valid mobilenumber");
			throw new InvalidInputException(UserValidationErrors.INVALID_PHONENUMBER);
		}
		return true;

	}
	
	
	public static boolean validateId(int id) throws InvalidInputException {

			if(id<0) {
				
				throw new InvalidInputException(UserValidationErrors.INVALID_ID);
			}	
	
		return true;

	}
}

