package com.fssa.sharetorise.validator;

/**
 * 
 * A class which holds the validator methods for the fundraiser object
 * 
 * @author NareshVellingiri
 */

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.sharetorise.errors.FundraiserErrors;
import com.fssa.sharetorise.exceptions.InvalidInputException;
import com.fssa.sharetorise.model.Certificate;
import com.fssa.sharetorise.model.FundRaiser;

public class FundraisingValidator {

	public boolean validateFundingRaiser(FundRaiser fund) throws InvalidInputException {

		if (fund == null) {

			throw new InvalidInputException(FundraiserErrors.INVALID_OBJ);
		}

		validateTitle(fund.getTitle());
		validateDescription(fund.getDescription());
		validateFundingGoal(fund.getFundingGoal());
		validateFundEndingDate(fund.getFundEndingDate());
		validateImageURL(fund.getImageUrl());
		return true;
	}
	

	/**
	 * Validates the title of a fundraiser.
	 * 
	 * @param title The title to validate.
	 * @return true if the title is valid.
	 * @throws InvalidInputException if the title is null, empty, or less than 30
	 *                                 characters long, or if it contains non-letter
	 *                                 characters.
	 */
	public boolean validateTitle(String title) throws InvalidInputException {
		// Check if the title is not null, not empty, and at least 30 characters long
		if (title == null || title.equals("") || title.length() < 10 || title.length() > 30) {
			throw new InvalidInputException(FundraiserErrors.INVALID_TITLE_NAME);
		}

		// Use a regular expression to check if the title contains only letters (no
		// numbers or special characters)
		String nameregex = "^[A-Za-z ]+$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(title);
		Boolean isMatch = matcher.matches();

		// If the title does not match the regular expression, it is considered invalid
		if (!isMatch) {
			throw new InvalidInputException(FundraiserErrors.INVALID_TITLE_NAME);
		}

		return true;
	}

	/**
	 * Validates the description of a fundraiser.
	 * 
	 * @param description The description to validate.
	 * @return true if the description is valid.
	 * @throws InvalidInputException if the description is null, empty, or less
	 *                                 than 300 characters long, or if it contains
	 *                                 non-letter characters.
	 */
	public boolean validateDescription(String description) throws InvalidInputException {
		// Check if the description is not null, not empty, and at least 300 characters
		// long
		if (description == null || description.trim().equals("") || description.length() < 30) {
			throw new InvalidInputException(FundraiserErrors.INVALID_DESCRIPTION);
		}

		// Use a regular expression to check if the description contains only letters
		// (no numbers or special characters)
		String nameregex = "^[A-Za-z ]+$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(description);
		Boolean isMatch = matcher.matches();

		// If the description matches the regular expression, it is considered invalid
		if (!isMatch) {
			throw new InvalidInputException(FundraiserErrors.INVALID_DESCRIPTION);
		}

		return true;
	}



	/**
	 * Validates the funding goal for a fundraiser.
	 * 
	 * @param fundingGoal The funding goal to validate.
	 * @return true if the funding goal is valid.
	 * @throws InvalidInputException if the funding goal is less than or equal to
	 *                                 2000.
	 */
	public boolean validateFundingGoal(double fundingGoal) throws InvalidInputException {
		// Check if the funding goal is greater than 2000
		if (fundingGoal <= 2000) {
			throw new InvalidInputException(FundraiserErrors.INVALID_FUNDING_GOAL);
		}
		return true;
	}

	
	public boolean validateFundEndingDate(LocalDate fundEndingDate) throws InvalidInputException {
		// Check if the ending date is not null and is not before the current date
		if (fundEndingDate == null) {
			throw new InvalidInputException(FundraiserErrors.INVALID_FUND_ENDING_DATE);
		}

		LocalDate today = LocalDate.now();
		if (fundEndingDate.isBefore(today)) {
			throw new InvalidInputException(FundraiserErrors.INVALID_FUND_ENDING_DATE);
		}

		return true;
	}
	
	
	public boolean validateCertficateObject(Certificate certificate) throws InvalidInputException {
	
		validateCertificateNumber(certificate.getCerNum());
	
		validateCertificateURL(certificate.getCerUrl());
		
		
		return true;
	}
	
	
	public boolean validateCertificateURL(String certificateUrl) throws InvalidInputException {
		// Check if the title is not null, not empty, and at least 30 characters long
		if (certificateUrl == null || certificateUrl.equals("") || certificateUrl.length()<=0) {
			throw new InvalidInputException(FundraiserErrors.INVALID_CERTIFICATE_URL);
		}

		// Use a regular expression to check if the title contains only letters (no
		// numbers or special characters)
		String nameregex = "\\b(?:https?|ftp)://\\S+\\.(?:jpg|jpeg|png|gif|bmp)\\b";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(certificateUrl);
		Boolean isMatch = matcher.matches();

		// If the title does not match the regular expression, it is considered invalid
		if (!isMatch) {
			throw new InvalidInputException(FundraiserErrors.INVALID_CERTIFICATE_URL);
		}

		return true;
	}
	
	
	
	
	public boolean validateCertificateNumber(String certificateNumber) throws InvalidInputException {
	    // Check if the certificate number is not null, not empty, and meets length requirements
	    if (certificateNumber == null || certificateNumber.isEmpty() || certificateNumber.length() < 8) {
	        throw new InvalidInputException(FundraiserErrors.INVALID_CERTIFICATE_NUM);
	    }

	    // Use a regular expression to check if the certificate number contains alphanumeric characters
	    String numberRegex = "^[a-zA-Z0-9]+$";
	    Pattern pattern = Pattern.compile(numberRegex);
	    Matcher matcher = pattern.matcher(certificateNumber);
	    boolean isMatch = matcher.matches();

	    // If the certificate number does not match the regular expression, it is considered invalid
	    if (!isMatch) {
	        throw new InvalidInputException(FundraiserErrors.INVALID_CERTIFICATE_NUM);
	    }

	    return true;
	}

	
	
	
	public boolean validateImageURL(String certificateUrl) throws InvalidInputException {
		// Check if the title is not null, not empty, and at least 30 characters long
		if (certificateUrl == null || certificateUrl.equals("") || certificateUrl.length()<=0) {
			throw new InvalidInputException(FundraiserErrors.INVALID_CERTIFICATE_URL);
		}

		// Use a regular expression to check if the title contains only letters (no
		// numbers or special characters)
//		String nameregex = "\\b(?:https?|ftp)://\\S+\\.(?:jpg|jpeg|png|gif|bmp)\\b";
//		Pattern pattern = Pattern.compile(nameregex);
//		Matcher matcher = pattern.matcher(certificateUrl);
//		Boolean isMatch = matcher.matches();
//
//		// If the title does not match the regular expression, it is considered invalid
//		if (!isMatch) {
//			throw new InvalidInputException(FundraiserErrors.INVALID_CERTIFICATE_URL);
//		}
//
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
