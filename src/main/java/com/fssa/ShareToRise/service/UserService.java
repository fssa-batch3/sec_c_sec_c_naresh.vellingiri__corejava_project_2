package com.fssa.ShareToRise.service;

import com.fssa.ShareToRise.dao.ShareToRiseDao;
import com.fssa.ShareToRise.model.FundingRaiser;
import com.fssa.ShareToRise.validator.FundraisingValidator;

import day11.practice.DAOException;

public class UserService {


	public boolean createFundraiser(FundingRaiser fundraiser) throws DAOException {

		if (FundraisingValidator.validateFundingRaiser(fundraiser)) 
			return ShareToRiseDao.createTask(fundraiser); 
			return false; 
			
	}

	public boolean updateFundraiser(FundingRaiser fundraiser) throws DAOException {

		if (FundraisingValidator.validateFundingRaiser(fundraiser)) return ShareToRiseDao.updateTask(fundraiser);
		return false;
		
		
	}

}
