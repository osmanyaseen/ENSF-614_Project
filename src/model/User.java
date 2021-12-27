package model;

/**
 * Entity created for the movie registration application, will be primarily
 * used for movie reservation, such as filling out information automatically
 * and as a check for pre-ordering.
 * @author Deylin Yiao
 * @since December 7, 2021
 */
public class User {
	
	private String name;
	private String email;
	private String creditCardNumber;
	private String creditCardDate;
	private int creditCardCVV;
	private boolean isRegistered;
	
	/**
	 * Sets a user class for ordinary user, no information is required.
	 */
	public User () {
		setRegistered(false);
	}
	
	/**
	 * If user had logged into the website, the corresponding registeredUser will
	 * be used to fill out the necessary information for reserving movie and set
	 * the isRegistered condition to true to allow user to pre-order movies and
	 * receive full credit for ticket cancellation.
	 * @param ru RegisteredUser Object, user's information
	 */
	public void setRegisteredUser (RegisteredUser ru) {
		setName(ru.getName());
		setEmail(ru.getEmail());
		setCreditCardNumber(ru.getCreditCardNumber());
		setCreditCardDate(ru.getCreditCardDate());
		setCreditCardCVV(ru.getCreditCardCVV());
		setRegistered(true);
	}

	
	// Getter and Setter Methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
}
