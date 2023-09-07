package com.fssa.sharetorise.model;

public class Certificate {

	private String cerNum;
	private String cerUrl;
	
	public Certificate() {
		
	}
	
	public Certificate(String cerNum, String cerUrl) {
		super();
		this.cerNum = cerNum;
		this.cerUrl = cerUrl;
	}

	public String getCerNum() {
		return cerNum;
	}

	public void setCerNum(String cerNum) {
		this.cerNum = cerNum;
	}

	public String getCerUrl() {
		return cerUrl;
	}

	public void setCerUrl(String cerUrl) {
		this.cerUrl = cerUrl;
	}

	@Override
	public String toString() {
		return "Certificate [cerNum=" + cerNum + ", cerUrl=" + cerUrl + "]";
	}
	
	
	
	
}
