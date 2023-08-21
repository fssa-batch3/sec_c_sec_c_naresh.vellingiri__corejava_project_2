package com.fssa.sharetorise.model;

import java.time.LocalDate;

/*
 * 
 * multiple recievers
 * 
 * */
public class FundingRaiser {
	
	private int fundraiserId;

	private String title;

	private String description;

	private double fundingGoal;

	private LocalDate fundEndingDate;

	private int noOfDaysRequired;
	
	
	public FundingRaiser() {
		
		
	}
	


	public FundingRaiser(int fundraiserId, String title, String description, double fundingGoal,
			LocalDate fundEndingDate) {
		super();
		this.fundraiserId = fundraiserId;
		this.title = title;
		this.description = description;
		this.fundingGoal = fundingGoal;
		this.fundEndingDate = fundEndingDate;
		this.noOfDaysRequired = noOfDaysRequired;
	}

	// creating Getters and Setters for this method
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFundingGoal() {
		return fundingGoal;
	}

	public void setFundingGoal(double fundingGoal) {
		this.fundingGoal = fundingGoal;
	}

	public LocalDate getFundEndingDate() {
		return fundEndingDate;
	}

	public void setFundEndingDate(LocalDate fundEndingDate) {
		this.fundEndingDate = fundEndingDate;
	}

	public int getNoOfDaysRequired() {
		return noOfDaysRequired;
	}

	



	public void setNoOfDaysRequired(int noOfDaysRequired) {
		this.noOfDaysRequired = noOfDaysRequired;
	}

	public int getFundraiserId() {
		return fundraiserId;
	}

	public void setFundraiserId(int fundraiserId) {
		this.fundraiserId = fundraiserId;
	}
	
	@Override
	public String toString() {
		return "FundingRaiser [fundraiserId=" + fundraiserId + ", title=" + title + ", description=" + description
				+ ", fundingGoal=" + fundingGoal + ", fundEndingDate=" + fundEndingDate + ", noOfDaysRequired="
				+ noOfDaysRequired + "]";
	}

}
