package com.fssa.sharetorise.service;

import java.sql.SQLException;

import com.fssa.sharetorise.dao.ShareToRiseDao;
import com.fssa.sharetorise.exceptions.DAOException;
import com.fssa.sharetorise.model.FundingRaiser;
import com.fssa.sharetorise.validator.FundraisingValidator;

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

