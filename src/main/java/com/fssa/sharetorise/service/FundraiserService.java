package com.fssa.sharetorise.service;

import com.fssa.sharetorise.dao.ShareToRiseDao;
import com.fssa.sharetorise.exceptions.*;
import com.fssa.sharetorise.model.FundingRaiser;
import com.fssa.sharetorise.validator.FundraisingValidator;

public class FundraiserService {

	FundraisingValidator validator = new FundraisingValidator();
	ShareToRiseDao dao = new ShareToRiseDao();

	public boolean createFundraiser(FundingRaiser fundraiser) throws FundraiserDataException, DAOException {

		if (validator.validateFundingRaiser(fundraiser))
			return dao.createFundraiser(fundraiser);
		return false;

	}

	public boolean updateFundraiser(FundingRaiser fundraiser) throws FundraiserDataException, DAOException {

		if (validator.validateFundingRaiser(fundraiser))
			return dao.updateUpdateFundraiser(fundraiser);
		return false;

	}

}
