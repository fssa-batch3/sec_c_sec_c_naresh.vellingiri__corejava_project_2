package com.fssa.sharetorise.service;

import java.util.List;

import com.fssa.sharetorise.dao.ShareToRiseDao;
import com.fssa.sharetorise.exceptions.*;
import com.fssa.sharetorise.model.FundingRaiser;
import com.fssa.sharetorise.validator.FundraisingValidator;

public class FundraiserService {

	FundraisingValidator validator = new FundraisingValidator();
	ShareToRiseDao dao = new ShareToRiseDao();

	public boolean createFundraiser(FundingRaiser fundraiser) throws  DAOException {

		if (validator.validateFundingRaiser(fundraiser))
			return dao.createFundraiser(fundraiser);
		return false;

	}

	
	public boolean updateFundraiser (FundingRaiser fundraiser) throws  DAOException {
		
		if(validator.validateFundingRaiser(fundraiser))
			return dao.updateFundraiser(fundraiser);

		return false;
	}
	
	
	public List<FundingRaiser> readAllFundraiser () throws  DAOException {
		
		return dao.readAllFundraisers();
	}

	
//	public boolean delete (int fundraiserId) throws  DAOException {
//
//	if(dao.getAllFundraiserIds().contains(fundraiserId)) {
//		return dao.readAllFundraisers(fundraiserId);
//	}
//	}
}
