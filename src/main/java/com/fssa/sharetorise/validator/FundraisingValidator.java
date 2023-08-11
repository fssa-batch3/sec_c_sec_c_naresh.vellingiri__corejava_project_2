package com.fssa.sharetorise.validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.sharetorise.errors.FundraiserErrors;
import com.fssa.sharetorise.exceptions.FundraiserDataException;
import com.fssa.sharetorise.model.FundingRaiser;


public class FundraisingValidator { 

	public static boolean validateFundingRaiser(FundingRaiser fund) throws FundraiserDataException {

		if (fund == null) {

			throw new FundraiserDataException(FundraiserErrors.INVALID_ID);
		}

		validateTitle(fund.getTitle());
		validateDescription(fund.getDescription());
		validateFundingGoal(fund.getFundingGoal());
		validateFundEndingDate(fund.getFundEndingDate());
		validateNoOfDaysRequired(fund.getNoOfDaysRequired());
		
		return true; 
	}
	

	


	/**
	 * Validates the title of a fundraiser.
	 * 
	 * @param title The title to validate.
	 * @return true if the title is valid.
	 * @throws FundraiserDataException if the title is null, empty, or less than 30
	 *                                 characters long, or if it contains non-letter
	 *                                 characters.
	 */
	public static boolean validateTitle(String title) throws FundraiserDataException {
		// Check if the title is not null, not empty, and at least 30 characters long
		if (title == null || title.equals("") || title.length()<10 || title.length()>20) {
			throw new FundraiserDataException(FundraiserErrors.INVALID_TITLE_NAME);
		}

		// Use a regular expression to check if the title contains only letters (no
		// numbers or special characters)
		String nameregex = "^[A-Za-z ]+$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(title);
		Boolean isMatch = matcher.matches();

		// If the title does not match the regular expression, it is considered invalid
		if (!isMatch) {
			throw new FundraiserDataException(FundraiserErrors.INVALID_TITLE_NAME);
		}

		return true;
	}

	/**
	 * Validates the description of a fundraiser.
	 * 
	 * @param description The description to validate.
	 * @return true if the description is valid.
	 * @throws FundraiserDataException if the description is null, empty, or less
	 *                                 than 300 characters long, or if it contains
	 *                                 non-letter characters.
	 */
	public static boolean validateDescription(String description) throws FundraiserDataException {
		// Check if the description is not null, not empty, and at least 300 characters
		// long
		if (description == null || description.trim().equals("") || description.length()<30 ) {
			throw new FundraiserDataException(FundraiserErrors.INVALID_DESCRIPTION);
		}

		// Use a regular expression to check if the description contains only letters
		// (no numbers or special characters)
		String nameregex = "^[A-Za-z ]+$";
		Pattern pattern = Pattern.compile(nameregex);
		Matcher matcher = pattern.matcher(description);
		Boolean isMatch = matcher.matches();

		// If the description matches the regular expression, it is considered invalid
		if (!isMatch) {
			throw new FundraiserDataException(FundraiserErrors.INVALID_DESCRIPTION);
		}

		return true;
	}

	/**
	 * 
	 * Validates the number of days required for a fundraiser.
	 * 
	 * @param noOfDaysRequired The number of days required to validate.
	 * @return true if the number of days required is valid.
	 * @throws FundraiserDataException if the number of days required is less than
	 *                                 or equal to 0, or greater than or equal to
	 *                                 120.
	 */
	public static boolean validateNoOfDaysRequired(int noOfDaysRequired) throws FundraiserDataException {
		// Check if the number of days required is a positive integer less than 120
		if (noOfDaysRequired <= 0 || noOfDaysRequired >= 120) {
			throw new FundraiserDataException(FundraiserErrors.INVALID_DAYS);
		}
		return true;
	}

	/**
	 * Validates the funding goal for a fundraiser.
	 * 
	 * @param fundingGoal The funding goal to validate.
	 * @return true if the funding goal is valid.
	 * @throws FundraiserDataException if the funding goal is less than or equal to
	 *                                 2000.
	 */
	public static boolean validateFundingGoal(double fundingGoal) throws FundraiserDataException {
		// Check if the funding goal is greater than 2000
		if (fundingGoal <= 2000) {
			throw new FundraiserDataException(FundraiserErrors.Funding_Goal);
		}
		return true;
	}

	/**
	 * Validates the ending date for a fundraiser.
	 * 
	 * @param fundEndingDate The ending date to validate.
	 * @return true if the ending date is valid.
	 * @throws FundraiserDataException if the ending date is null or if it is before
	 *                                 the current date.
	 */
	public static boolean validateFundEndingDate(LocalDate fundEndingDate) throws FundraiserDataException {
		// Check if the ending date is not null and is not before the current date
		if (fundEndingDate == null) {
			throw new FundraiserDataException(FundraiserErrors.Fund_Ending_Date);
		}

		LocalDate today = LocalDate.now();
		if (fundEndingDate.isBefore(today)) {
			throw new FundraiserDataException(FundraiserErrors.Fund_Ending_Date);
		}

		return true;
	}
}
