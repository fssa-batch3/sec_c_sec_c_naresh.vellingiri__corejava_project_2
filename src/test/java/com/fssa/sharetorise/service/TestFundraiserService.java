package com.fssa.sharetorise.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.sharetorise.dao.ShareToRiseDAO;
import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.logger.Logger;
import com.fssa.sharetorise.model.Certificate;
import com.fssa.sharetorise.model.FundRaiser;
import com.fssa.sharetorise.model.Video;

 class TestFundraiserService {

	FundraiserService user = new FundraiserService();

	ShareToRiseDAO dao = new ShareToRiseDAO();

	Logger log = new Logger();

	// Test case for service layer
	

	@Test

	void testCreateFundraiser()  {

		FundRaiser fund = new FundRaiser();

//    	FundraisingValidator 

		fund.setTitle("Football Clubs");
		fund.setDescription("This fund is need for future player who needs fund for his financial ");
		fund.setFundEndingDate(LocalDate.of(2023, 9, 26));
		fund.setImageUrl("https://staticg.sportskeeda.com/Football+wallpapers/CR7/cr7.jpg");
		fund.setFundingGoal(10000);

		List<Certificate> certificates = new ArrayList<>();
		
		certificates.add(
				new Certificate("h369sjfn", "https://freeimghost.net/images/2023/05/21/certificate_1.jpeg"));
		certificates
				.add(new Certificate("h647jfn", "https://freeimghost.net/images/2023/05/21/certificate_3.jpeg"));
		
		
		
		fund.setCertificate(certificates);

		
		List<Video> video = new ArrayList<>();
		video.add(new Video("https://youtu.be/ED7Z6J2Y5T4?feature=shared"));
		video.add(new Video("https://youtu.be/hvSDbX790rI?feature=shared"));

		fund.setVideo(video);

		assertDoesNotThrow(() -> user.createFundraiser(fund));

	}

	@Test

	void testUpdateFundraiser() {

//    	FundraisingValidator
		FundRaiser fund = new FundRaiser();

		fund.setTitle("Football Clubs");
		fund.setDescription("This fund is need for future football player who needs fund for his financial ");
		fund.setFundEndingDate(LocalDate.of(2023, 9, 26));
		fund.setFundingGoal(20000);
		fund.setImageUrl("https://media.istockphoto.com/id/623472590/photo/portrait-of-young-man-gymnasts.jpg?");

		List<Certificate> certificates = new ArrayList<>();
		certificates.add(
				new Certificate("h369sjfn", "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png"));
		certificates
				.add(new Certificate("h647jfn", "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png"));

		fund.setCertificate(certificates);
		
		List<Video> video = new ArrayList<>();
		video.add(new Video("h"));
		fund.setVideo(video);
		
		assertDoesNotThrow(() -> user.updateFundraiser(fund, 47));

	}

	@Test
	void testReadAllFundraiser() {

		try {

			List<FundRaiser> listFund = user.readAllFundraiser();

			Assertions.assertFalse(listFund.isEmpty());

			for (FundRaiser ele : listFund) {

				log.info(ele);
			}

		} catch (DAOException e) {

			Assertions.fail(e.getMessage());
		}

	}

	

	
	@Test
	void testDeleteFundraiser() {
		
		//34
         int id=44;
         
		assertDoesNotThrow(() -> user.deleteVideoLinks(id));

		assertDoesNotThrow(() -> user.deleteCertificates(id));

		assertDoesNotThrow(() -> user.deleteFundRaiser(id));

	}

}
