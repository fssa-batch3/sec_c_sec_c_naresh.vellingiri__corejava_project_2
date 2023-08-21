package com.fssa.sharetorise.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.dao.ShareToRiseDao;
import com.fssa.sharetorise.logger.Logger;
import com.fssa.sharetorise.model.FundingRaiser;

public class TestUserService {

	FundraiserService user = new FundraiserService();

	FundingRaiser fundraiser = new FundingRaiser();
	
	ShareToRiseDao dao    = new ShareToRiseDao();
	
	Logger log = new Logger();

	// Test case for service layer

	@Test

	void testCreateFundraiser() {

//    	FundraisingValidator

		fundraiser.setTitle("Fund for harshini");
		fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
		fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 26));
		fundraiser.setFundingGoal(3000);
		


		assertDoesNotThrow(() -> user.createFundraiser(fundraiser));

	}
	
	
	
	
	@Test

	void testUpdateFundraiser() {

//    	FundraisingValidator

		fundraiser.setTitle("Fund for naresh");
		fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
		fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 26));
		fundraiser.setFundingGoal(4321);
		fundraiser.setFundraiserId(1);
		


		assertDoesNotThrow(() -> user.updateFundraiser(fundraiser));

	}
	
	
	@Test
	void testReadAllFundraisers() {
		
		List<FundingRaiser> fundingRaiserList = user.readAllFundraiser();
		
		assertNotNull(fundingRaiserList);
		assertFalse(fundingRaiserList.isEmpty());
		

		for(FundingRaiser fundRaiser: fundingRaiserList) {
			
			log.info(fundRaiser);
		}
		
	}

}
