package com.fssa.ShareToRise.TestUserService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fssa.ShareToRise.Exceptions.FundraiserDataException;
import com.fssa.ShareToRise.model.FundingRaiser;
import com.fssa.ShareToRise.service.UserService;

public class TestUserService {

	
	  //Test case for service layer


    @Test

    void testServiceLayerCreateValid() {
    	
//    	FundraisingValidator
    	
    	UserService user = new UserService();
    	
    	FundingRaiser fundraiser = new FundingRaiser();
    	
    	
    	fundraiser.setTitle("Fund for Football");
    	fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
    	fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 20));
    	fundraiser.setFundingGoal(3000);
    	fundraiser.setNoOfDaysRequired(90);
    	 
        assertDoesNotThrow(() -> user.createFundraiser(fundraiser));

    } 
    
    

    @Test

    void testServiceLayerCreateInvalid() {
    	
    	
//    	FundraisingValidator
    	
    	UserService user = new UserService();
    	
    	FundingRaiser fundraiser = new FundingRaiser();
    	
    	
    	fundraiser.setTitle("Fund for Footballcvhjgcjhchgfxgchvghkljghcgxfgchvgjkhgf");
    	fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
    	fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 20));
    	fundraiser.setFundingGoal(300);
    	fundraiser.setNoOfDaysRequired(90);
    	
    	assertThrows(FundraiserDataException.class,() -> user.createFundraiser(fundraiser));

    } 
     
    
    
    @Test

    void testServiceLayerUpdateValid() {
    	
//    	FundraisingValidator
    	
    	UserService user = new UserService();
    	
    	FundingRaiser fundraiser = new FundingRaiser();
    	
    	
    	fundraiser.setTitle("Fund for Football");
    	fundraiser.setDescription("This fund is need for future football player who needs fund for his financial ");
    	fundraiser.setFundEndingDate(LocalDate.of(2023, 8, 20));
    	fundraiser.setFundingGoal(3000);
    	fundraiser.setNoOfDaysRequired(90);
    	
        assertDoesNotThrow(() -> user.updateFundraiser(fundraiser));

    } 
    
}
