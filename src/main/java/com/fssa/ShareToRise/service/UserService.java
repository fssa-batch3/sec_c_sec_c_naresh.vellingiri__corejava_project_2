package com.fssa.ShareToRise.service;

import com.fssa.ShareToRise.Exceptions.DAOException;
import com.fssa.ShareToRise.model.FundingRaiser;
import com.fssa.ShareToRise.validator.FundraisingValidator;
import com.fssa.sharetorise.dao.ShareToRiseDao;

import java.sql.SQLException;

public class UserService {


	public boolean createFundraiser(FundingRaiser fundraiser) throws SQLException, DAOException {

		if (FundraisingValidator.validateFundingRaiser(fundraiser)) 
			return ShareToRiseDao.createFundraiser(fundraiser); 
			return false; 
			 
	}

	public boolean updateFundraiser(FundingRaiser fundraiser) throws SQLException, DAOException {

		if (FundraisingValidator.validateFundingRaiser(fundraiser))
			return ShareToRiseDao.updateUpdateFundraiser(fundraiser);
		return false;
		
		
	}

}

