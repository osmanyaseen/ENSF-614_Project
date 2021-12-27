package model;

/**
 * Object to send payment information to billing company, either via
 * debit or credit transaction
 * @author Osman Yaseen
 * @since December 7, 2021
 */
public class Payment {
	
	User user;
	boolean verifyStatus;
	PaymentStrategy paymentStrategy;
	
	/**
	 * Constructor method for payment Object.
	 */
	public Payment() {
		
	}
	
	/**
	 * Constructor method for payment Object.
	 * @param user User Object
	 */
	public Payment(User user) {
		this.user = user;
	}
	
	// Getter and Setter Methods
	public void setPaymentStrategy(PaymentStrategy ps) {
		this.paymentStrategy = ps;
	}
	
	public String performPayment() {
		return paymentStrategy.SendtoBillingCompany();
	}
	
	public boolean getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(boolean verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
}
