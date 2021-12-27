package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI interface for registration page
 * @author Deylin Yiao & Peter Yuan
 * @since December 7, 2021
 */
@SuppressWarnings("serial")
public class RegistrationView extends JPanel{
	
	private JLabel title;
	private JLabel registerLabel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel emailLabel;
	private JTextField emailField;
	private JLabel passwordLabel;
	private JTextField passwordField;
	private JLabel cardNumLabel;
	private JTextField cardNumField;
	private JLabel expiryLabel;
	private JTextField expiryField;
	private JLabel CVVLabel;
	private JTextField CVVField;
	private JButton registerButton;
	private JButton cancelButton;
	private JLabel receiveMovieNewsLabel;
	private JCheckBox receiveMovieNewsCheckBox;
	
	/**
	 * Constructor method for RegistrationView Object, initially constructs and retrieves the page.
	 */
	public RegistrationView() {
		retrievePage();
	}
	
	/**
	 * Adds an action listener for the register button
	 * @param listener ActionListener object
	 */
	public void addRegisterListener(ActionListener listener) {
		registerButton.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the cancel button
	 * @param listener ActionListener object
	 */
	public void addCancelListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}
	
	
	/**
	 * Function to create labels describing required input fields
	 * @param str Input field name
	 * @param height Height of label
	 * @return JLabel Object for input field label
	 */
	public JLabel createLabel(String str, int height) {
		JLabel label = new JLabel(str);
		label.setFont(new Font("Calibri", Font.BOLD, 25));
		label.setForeground(Color.WHITE);
		label.setBounds(100, height, 500, 35);
		this.add(label);
		return label;
	}
	
	/**
	 * Function to create text field describing required input fields
	 * @param height Height of field
	 * @return JTextField Object for input field
	 */
	public JTextField createField(int height) {
		JTextField field = new JTextField(30);
		field.setBounds(420, height, 300, 30);
		field.setFont(new Font("Calibri", Font.BOLD, 25));
		this.add(field);
		return field;
	}
	
	/**
	 * Displays a message box containing a message, primarily used for displaying error
	 * @param errorMessage Error message sent from the application
	 */
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	/**
	 * Overridden paintComponent method, draws a rounded white border on page JPanel
	 */
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15, 15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke whiteSolid = new BasicStroke(5.0f);
        graphics.setStroke(whiteSolid);
        //Draws the rounded panel with borders.
        graphics.setColor(getBackground());
        //graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(10, 10, width-20, height-20, arcs.width, arcs.height); //paint border
    }
	
	/**
	 * Constructs and retrieves the registration page for movie theatre application
	 * @return Constructed registration page in the form of a JPanel
	 */
	public JPanel retrievePage() {
		
		// Initial setup of page
		this.setLayout(null);
		this.setBounds(200, 80, 800, 475);
		this.setOpaque(false);
		
		// Implementation of title label
		title = new JLabel("Registration Form");
		title.setBounds(250, 30, 400, 40);
		title.setFont(new Font("Calibri", Font.BOLD, 40));
		title.setForeground(Color.WHITE);
		
		// Implementation of greeting label
		registerLabel = new JLabel("<html>Thank you for considering to be a member!<br/>Please fill out the following form:</html>");
		registerLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		registerLabel.setForeground(Color.WHITE);
		registerLabel.setBounds(225, 70, 800, 50);
		
		// Implementation of name input field
		nameLabel = createLabel("Name", 130);
		nameField = createField(130);
		
		// Implementation of email input field
		emailLabel = createLabel("Email", 170);
		emailField = createField(170);
		
		// Implementation of password input field
		passwordLabel = createLabel("Password", 210);
		passwordField = createField(210);
		
		// Implementation of credit card number input field
		cardNumLabel = createLabel("Credit/Debit Card Number", 250);
		cardNumField = createField(250);
		
		// Implementation of expiry date input field
		expiryLabel = createLabel("Expiry Date (MM/DD)", 290);
		expiryField = createField(290);
		
		// Implementation of card verification value input field
		CVVLabel = createLabel("CVV", 330);
		CVVField = createField(330);
		
		// Implementation of the register button
		registerButton = new JButton("Register");
		registerButton.setBorder(null);
		registerButton.setFont(new Font("Calibri", Font.BOLD, 20));
		registerButton.setBounds(100, 425, 275, 30);
		
		// Implementation of the cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.setBorder(null);
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 20));
		cancelButton.setBounds(445, 425, 275, 30);
		
		//Implementation of movieNews label
		receiveMovieNewsLabel = new JLabel("Yes I would like to receive movie news via email!");
		receiveMovieNewsLabel.setBounds(150, 380, 600, 25);
		receiveMovieNewsLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		receiveMovieNewsLabel.setForeground(Color.WHITE);
		
		//Implementation of movieNews check box
		receiveMovieNewsCheckBox = new JCheckBox();
		receiveMovieNewsCheckBox.setBounds(120, 380, 20, 20);
		receiveMovieNewsCheckBox.setOpaque(false);
		
		// Adding all components to registration page JPanel
		this.add(title);
		this.add(registerLabel);
		this.add(registerButton);
		this.add(cancelButton);
		this.add(receiveMovieNewsLabel);
		this.add(receiveMovieNewsCheckBox);
		
		return this;
	}
	
	// Getter and setter methods
	public JPanel getView() {
		return this;
	}
	
    public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}

	public JTextField getCardNumField() {
		return cardNumField;
	}

	public void setCardNumField(JTextField cardNumField) {
		this.cardNumField = cardNumField;
	}

	public JTextField getExpiryField() {
		return expiryField;
	}

	public void setExpiryField(JTextField expiryField) {
		this.expiryField = expiryField;
	}

	public JTextField getCVVField() {
		return CVVField;
	}

	public void setCVVField(JTextField cVVField) {
		CVVField = cVVField;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	
}
