package com.fssa.sharetorise.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.exceptions.FundraiserDataException;
import com.fssa.sharetorise.model.FundingRaiser;

public class TestFundraisingValidation {

	FundraisingValidator validator = new FundraisingValidator();

	FundingRaiser nm = new FundingRaiser(null, null, 0, null, 0);

	@Test
	void testValidFundingRaiser() {

		FundingRaiser fund = new FundingRaiser();

		fund.setTitle("Fund for Football");
		fund.setDescription("This fund is need for future football player who needs fund for his financial ");
		fund.setFundEndingDate(LocalDate.of(2023, 8, 20));
		fund.setFundingGoal(3000);
		fund.setNoOfDaysRequired(90);

		assertDoesNotThrow(() -> validator.validateFundingRaiser(fund));

	}

	@Test
	void testInvalidFundingRaiser() {

		FundingRaiser fund = null;

		assertThrows(FundraiserDataException.class, () -> validator.validateFundingRaiser(fund));

	}

//	    Test cases for validating title attribute

	@Test
	void testValidateTitleValid() {
		// Arrange
		String validTitle = "Fund for Football";

		// Act and Assert
		assertDoesNotThrow(() -> validator.validateTitle(validTitle));
	}

	@Test
	void testValidateTitleInvalidNull() {
		// Arrange
		String invalidTitle = null;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateTitle(invalidTitle));
	}

	@Test
	void testValidateTitleInvalidEmpty() {
		// Arrange
		String invalidTitle = "";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateTitle(invalidTitle));
	}

	@Test
	void testValidateTitleInvalidShort() {
		// Arrange
		String invalidTitle = "ShortTitl";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateTitle(invalidTitle));
	}

	@Test
	void testValidateTitleInvalidFormat() {
		// Arrange
		String invalidTitle = "Title with 123 numbers in it.";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateTitle(invalidTitle));
	}

	@Test
	void testValidateTitleInvalidEdgeCase() {
		// Arrange
		String invalidTitle = "A title with exactly 30 letters.";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateTitle(invalidTitle));
	}

//    Test cases for validating String Description attribute

	@Test
	void testValidateDescriptionValid() {
		// Arrange
		String validDescription = "A valdfghgfdgfhjgfdhjfdghjgid description with more than dhtdhtfdhtfdhgtfhfhycharacters";

		// Act and Assert
		assertDoesNotThrow(() -> validator.validateDescription(validDescription));
	}

	@Test
	void testValidateDescriptionInvalidNull() {
		// Arrange
		String invalidDescription = null;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidEmpty() {
		// Arrange
		String invalidDescription = "";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidShort() {
		// Arrange
		String invalidDescription = "Short description";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidLettersAndNumbers() {
		// Arrange
		String invalidDescription = "Description with 123 numbers in it.";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidLettersAndSpecialCharacters() {
		// Arrange
		String invalidDescription = "Description with special characters @#$%.";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidGap() {
		// Arrange
		String invalidDescription = "Descriptionwithspecialcharacters.";

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateDescription(invalidDescription));
	}

//	    //    Test cases for validating int Noofdays attribute

	@Test
	void testValidateNoOfDaysRequiredValid() {
		// Arrange
		int validNoOfDays = 5;

		// Act and Assert
		assertDoesNotThrow(() -> validator.validateNoOfDaysRequired(validNoOfDays));
	}

	@Test
	void testValidateNoOfDaysRequiredInvalidZero() {
		// Arrange
		int invalidNoOfDays = 0;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateNoOfDaysRequired(invalidNoOfDays));
	}

	@Test
	void testValidateNoOfDaysRequiredInvalidNegative() {
		// Arrange
		int invalidNoOfDays = -10;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateNoOfDaysRequired(invalidNoOfDays));
	}

	@Test
	void testValidateNoOfDaysRequiredInvalidTooLarge() {
		// Arrange
		int invalidNoOfDays = 150;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateNoOfDaysRequired(invalidNoOfDays));
	}

	@Test
	void testValidateNoOfDaysRequiredInvalidEdgeCase() {
		// Arrange
		int invalidNoOfDays = 120;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateNoOfDaysRequired(invalidNoOfDays));
	}

//	    Test case for this FundingGoal attribute

	@Test
	void testValidateFundingGoalValid() {
		// Arrange
		double validFundingGoal = 5000.0;

		// Act and Assert
		assertDoesNotThrow(() -> validator.validateFundingGoal(validFundingGoal));
	}

	@Test
	void testValidateFundingGoalInvalidEqual2000() {
		// Arrange
		double invalidFundingGoal = 2000.0;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateFundingGoal(invalidFundingGoal));
	}

	@Test
	void testValidateFundingGoalInvalidLessThan2000() {
		// Arrange
		double invalidFundingGoal = 1500.0;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateFundingGoal(invalidFundingGoal));
	}

//	    Test case for this LocalDate FundEndingDate attribute

	@Test
	void testValidateFundEndingDateValid() {
		// Arrange
		LocalDate validEndingDate = LocalDate.now().plusDays(7); // Set a valid ending date 7 days from today

		// Act and Assert
		assertDoesNotThrow(() -> validator.validateFundEndingDate(validEndingDate));
	}

	@Test
	void testValidateFundEndingDateInvalidNull() {
		// Arrange
		LocalDate invalidEndingDate = null;

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateFundEndingDate(invalidEndingDate));
	}

	@Test
	void testValidateFundEndingDateInvalidPastDate() {
		// Arrange
		LocalDate invalidEndingDate = LocalDate.now().minusDays(1); // Set an ending date in the past

		// Act and Assert
		assertThrows(FundraiserDataException.class, () -> validator.validateFundEndingDate(invalidEndingDate));
	}

}
