package com.fssa.sharetorise.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.exceptions.FundraiserDataException;
import com.fssa.sharetorise.model.FundingRaiser;

public class TestUserService {

	FundraiserService user = new FundraiserService();

	FundingRaiser fundraiser = new FundingRaiser();

	// Test case for service layer

	@Test

	void testServiceLayerCreateValid() {

//    	FundraisingValidator

		fundraiser.setTitle("Fund for Cricket");
		fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
		fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 20));
		fundraiser.setFundingGoal(3000);
		fundraiser.setNoOfDaysRequired(90);

		assertDoesNotThrow(() -> user.createFundraiser(fundraiser));

	}

	@Test

	void testServiceLayerCreateInvalid() {


		fundraiser.setTitle("Fund for Footballcvhjgcjhchgfxgchvghkljghcgxfgchvgjkhgf");
		fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
		fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 20));
		fundraiser.setFundingGoal(300);
		fundraiser.setNoOfDaysRequired(90);

		assertThrows(FundraiserDataException.class, () -> user.createFundraiser(fundraiser));

	}
//
	@Test

	void testServiceLayerUpdateValid() {



		fundraiser.setTitle("Fund for Football");
		fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
		fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 20));
		fundraiser.setFundingGoal(8877);
		fundraiser.setNoOfDaysRequired(90);

		assertDoesNotThrow(() -> user.updateFundraiser(fundraiser));

	}

	
}
