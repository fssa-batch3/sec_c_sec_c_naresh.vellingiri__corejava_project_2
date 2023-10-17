package com.fssa.sharetorise.model;

import java.time.LocalDate;
import java.util.List;

/*
 * 
 * multiple recievers
 * 
 * */
public class FundRaiser {

	private int userId;

	private int fundraiserId;// 

	private String title;// 

	private String description;

	private double fundingGoal;

	private LocalDate fundEndingDate;// 

	private String imageUrl;

	private List<Certificate> certificate;// create set 1-img1;2-img2

	private List<Video> video;
	
	private SportsCategories category;

	public SportsCategories getCategory() {
		return category;
	}

	public void setCategory(SportsCategories category) {
		this.category = category;
	}

	public List<Video> getVideo() {
		return video;
	}

	public double raised_amount;

	public double getRaised_amount() {
		return raised_amount;
	}

	public void setRaised_amount(double raised_amount) {
		this.raised_amount = raised_amount;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

	public FundRaiser() {

		// Private constructor
	}

	public int getFundraiserId() {
		return fundraiserId;
	}

	public void setFundraiserId(int fundraiserId) {
		this.fundraiserId = fundraiserId;
	}

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Certificate> getCertificate() {
		return certificate;
	}

	public void setCertificate(List<Certificate> certificate) {
		this.certificate = certificate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	@Override
	public String toString() {
		return "FundRaiser [userId=" + userId + ", fundraiserId=" + fundraiserId + ", title=" + title + ", description="
				+ description + ", fundingGoal=" + fundingGoal + ", fundEndingDate=" + fundEndingDate + ", imageUrl="
				+ imageUrl + ", certificate=" + certificate + ", video=" + video + ", category=" + category
				+ ", raised_amount=" + raised_amount + "]";
	}

	

}
