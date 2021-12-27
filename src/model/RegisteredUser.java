package model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Entity to store information of a user registered in the
 * theater database.
 * @author Deylin Yiao
 * @since December 7, 2021
 */
public class RegisteredUser {
	
	private String name;
	private String password;
	private String email;
	private String creditCardNumber;
	private String creditCardDate;
	private int creditCardCVV;
	private Instant annualFeeDate;
	private boolean activeStatus;
	
	/**
	 * Comprehensive constructor method for RegisteredUser Object, primarily used to retrieve user from database
	 * @param n User's name
	 * @param p User's password
	 * @param e User's email address (Must be unique within the system)
	 * @param cardNumber User's credit card number
	 * @param cardDate User's credit card date (MM/yy)
	 * @param CVV User's Card verification value
	 * @param annualFeeDate Annual fee date when User needs to pay for membership
	 * @param ActiveStatus Active status of user's membership
	 */
	public RegisteredUser(String n, String p, String e, String cardNumber, String cardDate, int CVV, Instant annualFeeDate, boolean ActiveStatus) {
		setName(n);
		setPassword(p);
		setEmail(e);
		setCreditCardNumber(cardNumber);
		setCreditCardDate(cardDate);
		setCreditCardCVV(CVV);
		setAnnualFeeDate(annualFeeDate);
		setActiveStatus(ActiveStatus);
	}
	
	/**
	 * Robust constructor of RegisteredUser, primarily used for registering new users.
	 * @param n User's name
	 * @param p User's password
	 * @param e User's email address (Must be unique within the system)
	 * @param cardNumber User's credit card number
	 * @param cardDate User's credit card date (MM/yy)
	 * @param CVV User's Card verification value
	 */
	public RegisteredUser(String n, String p, String e, String cardNumber, String cardDate, int CVV) {
		setName(n);
		setPassword(p);
		setEmail(e);
		setCreditCardNumber(cardNumber);
		setCreditCardDate(cardDate);
		setCreditCardCVV(CVV);
		setAnnualFeeDate(Instant.now().plus(365, ChronoUnit.DAYS)); // set next annual fee date 1 year from when user registered today
		setActiveStatus(true);
	}
	
	/**
	 * Simulates renewing the account and updating the next annual fee pay date.
	 */
	public void pay() {
		System.out.println("Payment Received. Renewing the account...");
		Instant newDate = getAnnualFeeDate().plus(365, ChronoUnit.DAYS);
		setAnnualFeeDate(newDate);
		System.out.println("Success! " + getName() + "'s account has been " +
						   "successfully renewed.\nNew subscription date: " +
				           toDateFormat(getAnnualFeeDate()));
	}

	/**
	 * Simulates sending the movie news to the user's email.
	 */
	public void sendMovieNews() {
		System.out.println("Sending movie news to " + getEmail() + ". Check your inbox.");
	}
	
	/**
	 * Formats the instant object to the following time format: MM/yy
	 * @param instant - Instant time object
	 * @return Formatted time string
	 */
	public String toDateFormat(Instant instant) {
		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("MM/yy")
								 .withLocale(Locale.CANADA)
								 .withZone(ZoneId.systemDefault());
		return formatter.format(instant);
	}
	
	
	/**
	 * Overridden toString method for RegisteredUser Object, primarily used for diagnostic purposes
	 */
	@Override
	public String toString() {
		String result  = this.name + " " + this.password + " " + this.creditCardNumber + " " + this.creditCardDate + " " + this.creditCardCVV + " " + toDateFormat(this.annualFeeDate) + " " + this.activeStatus;
		return result;
	}
	
	// Getter and Setter Methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardDate() {
		return creditCardDate;
	}

	public void setCreditCardDate(String creditCardDate) {
		this.creditCardDate = creditCardDate;
	}

	public int getCreditCardCVV() {
		return creditCardCVV;
	}

	public void setCreditCardCVV(int creditCardCVV) {
		this.creditCardCVV = creditCardCVV;
	}

	public Instant getAnnualFeeDate() {
		return annualFeeDate;
	}

	public void setAnnualFeeDate(Instant annualFeeDate) {
		this.annualFeeDate = annualFeeDate;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

}
