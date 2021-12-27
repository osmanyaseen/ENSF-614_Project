package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI interface for login page
 * @author Deylin Yiao
 * @since December 7, 2021
 */
@SuppressWarnings("serial")
public class LoginView extends JPanel{
	
	private JLabel title;
	private JLabel emailLabel;
	private JTextField emailField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton registerButton;
	private JButton continueButton;
	
	/**
	 * Constructor method for LoginView Object, initially constructs and retrieves the page.
	 */
	public LoginView() {
		retrievePage();
	}
	
	/**
	 * Adds an action listener for the login button
	 * @param listener ActionListener object
	 */
	public void addLoginListener(ActionListener listener) {
		loginButton.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the register button
	 * @param listener ActionListener object
	 */
	public void addRegisterListener(ActionListener listener) {
		registerButton.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the continue button
	 * @param listener ActionListener object
	 */
	public void addContinueListener(ActionListener listener) {
		continueButton.addActionListener(listener);
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
	 * Constructs and retrieves the login page for movie theatre application
	 * @return Constructed login page in the form of a JPanel
	 */
	public JPanel retrievePage() {
		
		// Initial setup of page
		this.setLayout(null);
		this.setBounds(200, 80, 800, 450);
		this.setOpaque(false);
		
		// Implementation of title label
		title = new JLabel("Login Page");
		title.setBounds(325, 30, 400, 40);
		title.setFont(new Font("Calibri", Font.BOLD, 40));
		title.setForeground(Color.WHITE);
		
		// Implementation of email label
		emailLabel = new JLabel("Email Address");
		emailLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setBounds(150, 100, 300, 35);
		
		// Implementation of email input field
		emailField = new JTextField(30);
		emailField.setBounds(150, 140, 520, 30);
		emailField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of password label
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(150, 190, 300, 35);
		
		// Implementation of password input field
		passwordField = new JPasswordField(30);
		passwordField.setBounds(150, 220, 520, 30);
		passwordField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of login button
		loginButton = new JButton("Login");
		loginButton.setBorder(null);
		loginButton.setFont(new Font("Calibri", Font.BOLD, 20));
		loginButton.setBounds(150, 290, 250, 35);
		
		// Implementation of register button
		registerButton = new JButton("Register");
		registerButton.setBorder(null);
		registerButton.setFont(new Font("Calibri", Font.BOLD, 20));
		registerButton.setBounds(420, 290, 250, 35);
		
		// Implementation of continue button
		continueButton = new JButton("Continue as Ordinary User");
		continueButton.setBorder(null);
		continueButton.setFont(new Font("Calibri", Font.BOLD, 20));
		continueButton.setBounds(150, 340, 520, 35);
		
		// Adding all components to login page JPanel
		this.add(title);
		this.add(emailLabel);
		this.add(emailField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(loginButton);
		this.add(registerButton);
		this.add(continueButton);
		
		return this;
	}

	// Getter and setter methods
	public JPanel getView() {
		return this;
	}
	
	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public JButton getContinueButton() {
		return continueButton;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
}
