package com.fssa.sharetorise.errors;

public class FundraiserErrors {
   
	
	public static final String INVALID_OBJ ="Object can't be null.";

	public static final String INVALID_TITLE_NAME ="Title Name cannot be null or empty and its length should not be less than 20";
	
	public static final String INVALID_DESCRIPTION = "Description can't be null and it should contain minimum of 50 characters length.";
	
	public static final String INVALID_DAYS = "Days can't be past or today";
	
	public static final String INVALID_FUNDING_GOAL = "Amount can't be negative or less than minimal amount.";

	public static final String INVALID_FUND_ENDING_DATE = "Fund ending date cannot be null and it should be in future.";
	
	public static final String INVALID_CERTIFICATE_URL = "Certificate url's can't be null or empty.";

	public static final String INVALID_CERTIFICATE_NUM = "Certificate url's can't be null or empty.";


	public static final String INVALID_IMAGE_URL = "Image url's can't be null or empty.";

}
