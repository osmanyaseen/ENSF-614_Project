package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JPanel;

import model.RegisteredUser;
import view.HubManager;
import view.RegistrationView;

/**
 * Controller method for registration component of the movie registration application
 * @author Deylin Yiao & Peter Yuan
 * @since December 7, 2021
 */
public class RegistrationController {
	
	private ModelController modelController;
	private ArrayList<RegisteredUser> regList;
	private RegistrationView view;
	private HubManager hubManager;
	
	/**
	 * Constructor method to set up connection between the model controller and respective component view
	 * @param modelController ModelController responsible for centralized information from database
	 * @param view respective view component
	 */
	public RegistrationController(ModelController modelController, RegistrationView view) {
		setModelController(modelController);
		setView(view);
		regList = modelController.getRegUserList();
		view.addRegisterListener(new AddLoginListener());
		view.addCancelListener(new AddLoginListener());
	}
	
	
	/**
	 * Checks if an input field is empty
	 * @param field Input text field
	 * @return true if the field does not contain anything
	 */
	public boolean isEmpty(String field) {
		if(field.equals("")) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Checks if the input credit card number is 16 digits long and all integer
	 * @param field Credit card number
	 * @return true if credit card in valid format, false otherwise
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
	 * Checks if the email address is in a valid format
	 * @param email Email address
	 * @return true if email address in valid format, false otherwise
	 */
	public boolean isValidEmailAddress(String email) {
	    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	    return Pattern.compile(regexPattern).matcher(email).matches();
	}
	
	/**
	 * Checks if user input email is already existing in the database
	 * @param email Email Address
	 * @return true if email already exist in database, false otherwise
	 */
	public boolean dupeEmail(String email) {
		for(RegisteredUser user: regList) {
			if(user.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the credit card expiry date is valid
	 * @param field Credit card expiry date
	 * @return true if expiry date is later than today's date, false otherwise
	 * @throws ParseException If the date format is not possible to parse, throw ParseException
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
	 * Checks if the card verification value is in valid format
	 * @param field Card verification value
	 * @return true if CVV is in valid format, false otherwise
	 */
	public boolean isValidCVV(String field) {
		try {
			Integer.parseInt(field);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	
	/**
	 * Main function to check for registration form validity, will return a string if 
	 * @param validationList
	 * @return
	 * @throws ParseException
	 */
	public String validateForm(String[] validationList) throws ParseException {
		for(String field: validationList) {
			if(isEmpty(field)) {
				return "Error: one or more field is empty.";
			}
		}
		
		if(!isValidEmailAddress(validationList[2])) {
			return "Error: Please enter a valid email address";
		}
		
		if(dupeEmail(validationList[2])) {
			return "Error: Email Address already exist in Database, please log in instead";
		}
		
		if(!isValidCCNumber(validationList[3])) {
			return "Error: An invalid card number was input.";
		}
		
		if(!isValidExpiryDate(validationList[4])) {
			return "Error: Card has expired.";
		}
		
		if(!isValidCVV(validationList[5])) {
			return "Error: CVV is invalid.";
		}
		
		return null;
	}
	
	/**
	 * Adds an action listener for the Register and Cancel Buttons
	 * @param listener ActionListener object
	 */
	class AddLoginListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource() == view.getRegisterButton()) {
				try {
					String name = view.getNameField().getText();
					String email = view.getEmailField().getText();
					String password = view.getPasswordField().getText();
					String cardNumber = view.getCardNumField().getText();
					String expiry = view.getExpiryField().getText();
					String CVV = view.getCVVField().getText();
					String validationList[] = new String[] {name, password, email, cardNumber, expiry, CVV};
					String error = validateForm(validationList);
					if (error != null) {
						view.displayErrorMessage(error);
					} else {
						RegisteredUser newUser = new RegisteredUser(name, password, email, cardNumber, expiry, Integer.parseInt(CVV));
						regList.add(newUser);
						modelController.getCont().addRegisteredUser(newUser);
						view.displayErrorMessage("Registration Successful! Please try to log in.");
						hubManager.redirectToLoginView();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (evt.getSource() == view.getCancelButton()){
				hubManager.redirectToLoginView();
			}
		}
	}

	/**
	 * Returns Registration View
	 * @return view RegistrationView
	 */
	public JPanel sendRegistrationPage() {
		return view.retrievePage();
	}
	
	// Getter and setter functions
	public ModelController getModelController() {
		return modelController;
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}

	public ArrayList<RegisteredUser> getRegList() {
		return regList;
	}

	public void setRegList(ArrayList<RegisteredUser> regList) {
		this.regList = regList;
	}

	public RegistrationView getView() {
		return view;
	}

	public void setView(RegistrationView view) {
		this.view = view;
	}


	public HubManager getHubManager() {
		return hubManager;
	}


	public void setHubManager(HubManager hubManager) {
		this.hubManager = hubManager;
	}
	
	
	
}

