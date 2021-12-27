package model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Voucher -- Entity created to store credit for the user, and can be redeemed for
 * future purchases on site for up to one year.
 * @author Peter Yuan
 * @since December 7, 2021
 */
public class Voucher {
	private int voucherID; 
	private int voucherCode; 
	private double amount; 
	private Instant expiryDate;
	
	
	//creates a new voucher
	public Voucher(int voucherID, int voucherCode, double amount, Instant expiryDate) { 
	
		setVoucherID(voucherID);
		setVoucherCode(voucherCode);
		setVoucherValue(amount); 
		setExpiryDate(expiryDate); 
	}
	
	/**
	 * Formats the instant object to the following time format: yyyy:MM:dd, HH:MM am/pm
	 * @param instant - Instant time object
	 * @return Formatted time string
	 */
	public String toDateTimeFormat(Instant instant) {
		DateTimeFormatter formatter =
				DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
								 .withLocale(Locale.CANADA)
								 .withZone(ZoneId.systemDefault());
		return formatter.format(instant);
	}
	
	
	/**
	 * Overridden toString method for Voucher Object, mainly for diagnostic purposes.
	 */
	@Override
	public String toString() {
		return "VoucherID: " + getVoucherID() +
				"   " + "Voucher Code: " + getVoucherCode() +
				"   " + "Voucher Value: $" + getVoucherValue() +
				"   " + "Expiry Date: " + toDateTimeFormat(getExpiryDate());
	}
	
	public int getVoucherID() {
		return voucherID; 
	}
	
	public void setVoucherID(int id) {
		voucherID = id; 
	}
	
	public int getVoucherCode() {
		return voucherCode;
	}
	
	public void setVoucherCode(int code) {
		voucherCode = code;
	}
	
	public double getVoucherValue() {
		return amount; 
	}
	
	public void setVoucherValue(double value) {
		amount = value;
	}
	
	public Instant getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Instant date) {
		expiryDate = date; 
	}
	
	
}
