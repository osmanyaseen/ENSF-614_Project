package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import model.CreditStrategy;
import model.DebitStrategy;
import model.Movie;
import model.Payment;
import model.Receipt;
import model.RegisteredUser;
import model.Seat;
import model.Ticket;
import model.User;
import model.Voucher;
import view.HubManager;
import view.PaymentView;

/**
 * Controller for Payment Page
 * @author Osman Yaseen
 * @since December 7, 2021
 */
public class PaymentController {
	
	private ModelController modelController;
	private Payment payment;
	private PaymentView view;
	private HubManager hubManager;
	private ArrayList<Voucher> voucherList;
	
	/**
	 * Constructor method to set up connection between the model controller and respective component view
	 * @param modelController ModelController responsible for centralized information from database
	 * @param view respective view component
	 */
	public PaymentController(ModelController modelController, PaymentView view) {
		setModelController(modelController);
		setView(view);
		voucherList = modelController.getVoucherList();
		payment = new Payment();
		view.addPurchaseListener(new AddPurchaseListener ());
		view.addBackListener(new AddPurchaseListener ());
	}
	
	/**
	 * Validates inputs from user
	 * @param validationList Inputs from User
	 * @return String Error if required
	 */
	public String validateForm(String[] validationList) throws ParseException {
		for(int i = 0; i < 5; i++) {
			if(isEmpty(validationList[i])) {
				return "Error: one or more field is empty.";
			}
		}
		
		if(!isValidEmailAddress(validationList[1])) {
			return "Error: Please enter a valid email address";
		}
		
		if(!isValidCCNumber(validationList[2])) {
			return "Error: An invalid card number was input.";
		}
		
		if ((validationList[6].equals(null))) {
			return "Error: Select Debit or Credit";
		}
		
		if(!isValidExpiryDate(validationList[3])) {
			return "Error: Card has expired.";
		}
		
		if(!isValidCVV(validationList[4])) {
			return "Error: CVV is invalid.";
		}
		if (!isEmpty(validationList[5])) {
			if(!voucherValid(validationList[5])) {
				return "Error: Voucher is invalid";
			}
		}
		
		return null;
	}
	
	/**
	 * Sets seat availability
	 */
	public void setSeatAvailability() {
		for(Movie movie: modelController.getMovieList()) {
			for(Seat seat: movie.getSeatList()) {
				if(modelController.getTempSeat() == seat) {
					seat.setAvailable(false);
					modelController.getCont().updateSeat(seat);
				}
			}
		}
	}
	
	/**
	 * Adds an action listener for the Purchase and Back Buttons
	 * @param listener ActionListener object
	 */
	class AddPurchaseListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource() == view.getPurchaseButton()) {
				try {
					
					
					String name = view.getNameField().getText();
					String email = view.getEmailField().getText();
					String card = view.getCardField().getText();
					String expiry = view.getExpiryField().getText();
					String cvv = view.getCVVField().getText();
					String voucher = view.getVoucherField().getText();
					String cd = (String) view.getcd().getSelectedItem();
					String validationList[] = new String[] {name, email, card, expiry, cvv, voucher, cd};
					String error = validateForm(validationList);
					if (error != null) {
						view.displayErrorMessage(error);
					}
					else {
						if (!isEmpty(voucher))
						{
							for (Voucher v: voucherList) {
								if (v.getVoucherCode() == Integer.parseInt(voucher)) {
									Receipt receipt = new Receipt(1, (20.00 - v.getVoucherValue()), Instant.now());
									modelController.getCont().addReceipt(receipt);
									modelController.getReceiptList().add(receipt);
									modelController.getCont().deleteVoucher(v.getVoucherCode());
									
									
									if (cd.equals("Debit")) {
										payment.setPaymentStrategy(new DebitStrategy());
									}
									
									if (cd.equals("Credit")) {
										payment.setPaymentStrategy(new CreditStrategy());
									}
									Ticket ticket = modelController.getTempTicket();
									ticket.setTicketId(modelController.getCont().retrieveTicketList().size()+1);
									ticket.setPrice(20.00 - v.getVoucherValue());
									ticket.setEmail(email);
									ticket.setActiveStatus(true);
									ticket.setDate(Instant.now());
									modelController.getCont().addTicket(ticket);
									modelController.getTicketList().add(ticket);
									modelController.getTempSeat().setAvailable(false);
									
									String ticketString = ticket.toString();
									view.ticketSuccess("*****TicketReceipt Summary*****" + "\n" + ticketString + "\n" + payment.performPayment());
									
								}
							}
						}
						else {
							if (cd.equals("Debit")) {
								payment.setPaymentStrategy(new DebitStrategy());
							}
							
							if (cd.equals("Credit")) {
								payment.setPaymentStrategy(new CreditStrategy());
							}
							
							Receipt receipt = new Receipt(1, 20.00, Instant.now());
							modelController.getCont().addReceipt(receipt);
							modelController.getReceiptList().add(receipt);
							Ticket ticket = modelController.getTempTicket();
							ticket.setTicketId(modelController.getCont().retrieveTicketList().size()+1);
							ticket.setPrice(20.00);
							ticket.setEmail(email);
							ticket.setActiveStatus(true);
							ticket.setDate(Instant.now());
							setSeatAvailability();
							modelController.getCont().addTicket(ticket);
							modelController.getTicketList().add(ticket);
							String ticketString = ticket.toString();
							view.ticketSuccess(("*****TicketReceipt Summary*****" + "\n" + ticketString + "\n" + payment.performPayment()));
						}
					}
						
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			else if (evt.getSource() == view.getBackButton()) {
				try {
					hubManager.redirectToHomePageView();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	/**
	 * Checks if voucher valid 
	 * @param voucher
	 * @return true if valid
	 */
	public boolean voucherValid(String voucher) {
		for (Voucher v: voucherList) {
			if (v.getVoucherCode() == Integer.parseInt(voucher)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns Payment View
	 * @return view PaymentView
	 */
	public JPanel sendPaymentPage() {
		return view.retrievePage();
	}
	
	/**
	 * Checks if String is empty
	 * @param field String object
	 * @return true if empty
	 */
	public boolean isEmpty(String field) {
		if(field.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if Credit/Debit Card Number is Valid
	 * @param field String object
	 * @return true if valid
	 */
	public boolean isValidCCNumber(String field) {
		if(field.length() != 16) {
			return false;
		}
		for(int i = 0; i < field.length(); i++) {
			if(field.charAt(i) < '0' || field.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if Credit/Debit Card Expiry Date is Valid
	 * @param field String object
	 * @return true if valid
	 */
	public boolean isValidExpiryDate(String field) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		simpleDateFormat.setLenient(false);
		Date expiry = simpleDateFormat.parse(field);
		System.out.println(expiry);
		System.out.println(new Date());
		boolean valid = expiry.after(new Date());
		return valid;
	}
	
	/**
	 * Checks if Credit/Debit Card CVV is Valid
	 * @param field String object
	 * @return true if valid
	 */
	public boolean isValidCVV(String field) {
		try {
			Integer.parseInt(field);
			if (field.length() == 3)
				return true;
			else
				return false;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Checks if Email is Valid
	 * @param field String object
	 * @return true if valid
	 */
	public boolean isValidEmailAddress(String email) {
	    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	    return Pattern.compile(regexPattern).matcher(email).matches();
	}
	
	// Getter and setter functions
	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}

	public PaymentView getView() {
		return view;
	}

	public void setView(PaymentView view) {
		this.view = view;
	}


	public HubManager getHubManager() {
		return hubManager;
	}


	public void setHubManager(HubManager hubManager) {
		this.hubManager = hubManager;
	}
	
	public ModelController getModelController() {
		return modelController;
	}
}

