package model;

/**
 * Debit Strategy to demonstrate how payment information is sent to a debit company.
 * Implements Strategy design pattern.
 * @author Osman Yaseen
 * @since December 7, 2021
 */
public class DebitStrategy implements PaymentStrategy {

	@Override
	public String SendtoBillingCompany() {
		return "Payment sent to Debit Card Company";
		
		
	}

}