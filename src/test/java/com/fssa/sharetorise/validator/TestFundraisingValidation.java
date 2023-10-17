package com.fssa.sharetorise.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.exceptions.InvalidInputException;
import com.fssa.sharetorise.model.Certificate;
import com.fssa.sharetorise.model.FundRaiser;

public class TestFundraisingValidation {

	FundraisingValidator validator = new FundraisingValidator();

	FundRaiser nm = new FundRaiser();

	@Test
	void testValidFundingRaiser() {


		
		FundRaiser fund = new FundRaiser();
		

		fund.setTitle("Fund for Football");
		fund.setDescription("This fund is need for future football player who needs fund for his financial ");
		fund.setFundEndingDate(LocalDate.of(2023, 12, 26));
		fund.setFundingGoal(3000);
		fund.setImageUrl("https://staticg.sportskeeda.com/Football+wallpapers/CR7/cr7.jpg");

		
		
		
		
		List<Certificate>certificates = new ArrayList<>();
		certificates.add(new Certificate());
		certificates.add(new Certificate());
		
		
		
		fund.setCertificate(certificates);
		
	
		assertDoesNotThrow(() -> validator.validateFundingRaiser(fund)); 

	}

	@Test
	void testInvalidFundingRaiser() {
		

		FundRaiser fund = null;

		assertThrows(InvalidInputException.class, () -> validator.validateFundingRaiser(fund));

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
		assertThrows(InvalidInputException.class, () -> validator.validateTitle(invalidTitle));
	}

	
	
	
	@Test
	void testValidateTitleInvalidEmpty() {
		// Arrange
		String invalidTitle = "";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateTitle(invalidTitle));
	}

	@Test
	void testValidateTitleInvalidShort() {
		// Arrange
		String invalidTitle = "ShortTitl";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateTitle(invalidTitle));
	}

	@Test
	void testValidateTitleInvalidFormat() {
		// Arrange
		String invalidTitle = "Title with 123 numbers in it.";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateTitle(invalidTitle));
	}

	@Test
	void testValidateTitleInvalidEdgeCase() {
		// Arrange
		String invalidTitle = "A title with exactly 30 letters.";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateTitle(invalidTitle));
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
		assertThrows(InvalidInputException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidEmpty() {
		// Arrange
		String invalidDescription = "";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidShort() {
		// Arrange
		String invalidDescription = "Short description";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidLettersAndNumbers() {
		// Arrange
		String invalidDescription = "Description with 123 numbers in it.";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidLettersAndSpecialCharacters() {
		// Arrange
		String invalidDescription = "Description with special characters @#$%.";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateDescription(invalidDescription));
	}

	@Test
	void testValidateDescriptionInvalidGap() {
		// Arrange
		String invalidDescription = "Descriptionwithspecialcharacters.";

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateDescription(invalidDescription));
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
		assertThrows(InvalidInputException.class, () -> validator.validateFundingGoal(invalidFundingGoal));
	}

	@Test
	void testValidateFundingGoalInvalidLessThan2000() {
		// Arrange
		double invalidFundingGoal = 1500.0;

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateFundingGoal(invalidFundingGoal));
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
		assertThrows(InvalidInputException.class, () -> validator.validateFundEndingDate(invalidEndingDate));
	}

	@Test
	void testValidateFundEndingDateInvalidPastDate() {
		// Arrange
		LocalDate invalidEndingDate = LocalDate.now().minusDays(1); // Set an ending date in the past

		// Act and Assert
		assertThrows(InvalidInputException.class, () -> validator.validateFundEndingDate(invalidEndingDate));
	}
	
	
	
	
	@Test
	  
	void testCertificateObj() {
		
		FundraisingValidator fundraisingValidator = new FundraisingValidator();
		
		Certificate certificate = new Certificate();
		
		certificate.setCerNum("1wwert7hjk");
		certificate.setCerUrl("https://iili.io/HvSRHRS.jpg");
		
		
		assertDoesNotThrow(() -> fundraisingValidator.validateCertficateObject(certificate));

			
	}
	
	
	
	@Test
	void testImageUrlWithValid() {

		String[] validImageUrl = { "https://freeimghost.net/images/2023/03/01/apple-green.jpeg",
				"https://freeimghost.net/images/2023/03/01/avacado.png",
				"https://freeimghost.net/images/2023/03/01/blueberry.jpeg",
				"https://freeimghost.net/images/2023/03/01/dragon-fruit.png"
				
		 };

		for (String url : validImageUrl) {


				assertTrue(validator.validateImageURL(url));
			 
			

		}

	}

//	test the image url with differen null inputs
	@Test
	void testImageUrlWithNullInputs() {

		String[] invalidImageUrls = { null, "   ", ""};

		for (String url : invalidImageUrls) {

			try {

				validator.validateImageURL(url);

			} catch (InvalidInputException e) {

				assertThrows(InvalidInputException.class, () -> validator.validateImageURL(url));

			}

		}

	}

//	test the image url with different invalid inputs

	@Test
	void testImageUrlWithInvalid() {

		String[] invalidImageUrls = { "http://example.com/image", "https://example.com/image.",
				"ftp://example.com/image.pdf", "htp://example.com/image.jpg", "http:/example.com/image.jpg",
				"http://example.com/image.png?param=value", "https://example.com/image;param=value",
				"file:///C:/path/to/image.jpg", "https://example.com/image.mp3", "ftp://example.com/image_png" };

		for (String url : invalidImageUrls) {

			try {

				validator.validateImageURL(url);

			} catch (InvalidInputException e) {

				assertThrows(InvalidInputException.class, () -> validator.validateImageURL(url));

			}

		}

	}
	
	 



}
