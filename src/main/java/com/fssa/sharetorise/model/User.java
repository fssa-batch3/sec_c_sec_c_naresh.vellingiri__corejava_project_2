package com.fssa.sharetorise.model;


public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;
	private String password;
	private String confirmPassword;
	private boolean isActive;

	// No argument constructor
	public User() {
		// Empty constructor used for creating an instance without setting attributes
	}

	public User(String firstName, String lastName, long phoneNumber, String email, String password,
			String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public int getCustomerId() {
		return userId;
	}

	public void setCustomerId(int customerId) {
		this.userId = customerId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", isActive=" + isActive + "]";
	}

}