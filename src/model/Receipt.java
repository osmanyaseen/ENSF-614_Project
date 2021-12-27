package model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Entity storing the receipt information and to be stored in the database.
 * @author Peter Yuan
 * @since December 7, 2021
 */
public class Receipt {
	
	private int receiptID;
	private double price; 
	private Instant date;
	
	
	
	/**
	 * Constructor to create new receipt
	 * @param rID Receipt ID
	 * @param totalP Total price of ticket
	 * @param Date Purchase Date
	 */
	public Receipt(int rID, double totalP, Instant Date) { 
	
		setReceiptID(rID); 
		setPrice(totalP); 	
		setDate(Date); 
	}
	
	/**
	 * Overridden toString method for Receipt Object, primarily used for diagnostic purposes
	 */
	@Override
	public String toString() {
		return "ReceiptID: " + getReceiptID() + " Price: " + getPrice() + " Date Purchased: " + toDateFormat(getDate()); 
	}
	
	/**
	 * Formats the instant object to the following time format: yyyy-MM-dd
	 * @param instant - Instant time object
	 * @return Formatted time string
	 */
	public String toDateFormat(Instant instant) {
		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("yyyy-MM-dd")
								 .withLocale(Locale.CANADA)
								 .withZone(ZoneId.systemDefault());
		return formatter.format(instant);
	}
	
	// Getter and Setter Methods
	public int getReceiptID() {
		return receiptID; 
	}
	
	public void setReceiptID(int id) {
		receiptID = id; 
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double bought_price) {
		price = bought_price;
	}
	
	
	public Instant getDate() {
		return date;
	}
	
	public void setDate(Instant receiptdate) {
		date = receiptdate; 
	}
		
	
}


