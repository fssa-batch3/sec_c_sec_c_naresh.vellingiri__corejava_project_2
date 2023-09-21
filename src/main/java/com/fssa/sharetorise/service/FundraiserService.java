package com.fssa.sharetorise.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.sharetorise.dao.DonateFundDao;
import com.fssa.sharetorise.dao.ShareToRiseDAO;
import com.fssa.sharetorise.exceptions.*;
import com.fssa.sharetorise.model.FundRaiser;
import com.fssa.sharetorise.validator.FundraisingValidator;

public class FundraiserService {

	FundraisingValidator validator = new FundraisingValidator();
	ShareToRiseDAO dao = new ShareToRiseDAO();
	DonateFundDao donateFundDao = new DonateFundDao();

	public boolean createFundraiser(FundRaiser fundraiser) throws DAOException {

		if (validator.validateFundingRaiser(fundraiser))
			return dao.createFundraiser(fundraiser);
		return false;
 
	}

	public boolean updateFundraiser(FundRaiser fundraiser, int id) throws DAOException {

		if (validator.validateFundingRaiser(fundraiser))
			return dao.updateFundraiser(fundraiser, id);

		return false;
	}
	
	

	public List<FundRaiser> readAllFundraiser() throws DAOException {

		return dao.getAllFundraiser();
	}

	public boolean deleteFundRaiser(int id) throws DAOException {

		return dao.deleteFundraiser(id);
	}
	
	public boolean deleteCertificates(int id) throws DAOException {

		return dao.deleteCertificates(id);
	}
	
	public boolean deleteVideoLinks(int id) throws DAOException {

		return dao.deleteVideoLinks(id);
	}
	
    public FundRaiser getFundraiserById(int id) throws DAOException {
        try {
        	
            return dao.getFundRaiserById(id);
            
        } catch (SQLException e) {
        	
            throw new DAOException(e.getMessage());
           
        }
    }
    
    public boolean donateFundint(double amount, int userId, int fundRaiserId) throws DAOException {
        
    	if(true) {
    		return donateFundDao.donateFund(amount, userId, fundRaiserId);
    	}
		return false;
    
    }
	

}
