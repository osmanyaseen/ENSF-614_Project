package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI interface for payment page
 * @author Osman Yaseen
 * @since December 7, 2021
 */
@SuppressWarnings("serial")
public class PaymentView extends JPanel{
	
	private JLabel title;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel emailLabel;
	private JTextField emailField;
	private JLabel cardLabel;
	private JTextField cardField;
	private JLabel expiryLabel;
	private JTextField expiryField;
	private JLabel CVVLabel;
	private JTextField CVVField;
	private JLabel voucherLabel;
	private JTextField voucherField;
	private JButton purchaseButton;
	private JButton backButton;
	private JComboBox cd;

	/**
	 * Constructor method for PaymentView Object, initially constructs and retrieves the page.
	 */
	public PaymentView() {
		retrievePage();
	}
	
	/** gets the payment view for hub view
	 * @return PaymentView JPanel
	 */
	public JPanel getView() {
		return this;
	}
	
	/**
	 * Adds an action listener for the purchase button
	 * @param listener ActionListener object
	 */
	public void addPurchaseListener(ActionListener listener) {
		purchaseButton.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the back button
	 * @param listener ActionListener object
	 */
	public void addBackListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}
	
	/**
	 * Displays a message box containing a message, primarily used for displaying error
	 * @param errorMessage Error message sent from the application
	 */
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	/**
	 * Adds the credit and debit options into card combo box
	 */
	public void cb() {
    	String dummy[] = {"Debit", "Credit"};
        DefaultComboBoxModel dm = new DefaultComboBoxModel(dummy);
        getcd().setModel(dm);
     }

	/**
	 * Displays to user the ticket and payment method
	 * @param ticketString - string with ticket information
	 */
	public void ticketSuccess(String ticketString) {
		JOptionPane.showMessageDialog(this, ticketString);
		
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
	 * Constructs and retrieves the payment page for movie theatre application
	 * @return Constructed payment page in the form of a JPanel
	 */
	public JPanel retrievePage() {
		
		// Initial setup of page
		this.setLayout(null);
		this.setBounds(200, 80, 800, 450);
		this.setOpaque(false);
		
		// Implementation of title
		title = new JLabel("Payment Page");
		title.setBounds(250, 30, 400, 40);
		title.setFont(new Font("Calibri", Font.BOLD, 40));
		title.setForeground(Color.WHITE);
		
		// Implementation of name label
		nameLabel = new JLabel("Full Name");
		nameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(100, 130, 500, 35);
		
		// Implementation of name input field
		nameField = new JTextField(30);
		nameField.setBounds(420, 130, 300, 30);
		nameField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of email label
		emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setBounds(100, 170, 500, 35);
		
		// Implementation of email input field
		emailField = new JTextField(30);
		emailField.setBounds(420, 170, 300, 30);
		emailField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of credit card number label
		cardLabel = new JLabel("Credit/Debit Card Number");
		cardLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		cardLabel.setForeground(Color.WHITE);
		cardLabel.setBounds(100, 210, 500, 35);
		
		// Implementation of credit card number input field
		cardField = new JTextField(30);
		cardField.setBounds(420, 210, 300, 30);
		cardField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of card combo box
		cd = new JComboBox();
	    cd.setBounds(335,215,75,20);
	    cd.setEnabled(true);
	    cb();
		
	    // Implementation of credit card expiry date label
		expiryLabel = new JLabel("Expiry Date (MM/YY)");
		expiryLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		expiryLabel.setForeground(Color.WHITE);
		expiryLabel.setBounds(100, 250, 500, 35);
		
		// Implementation of credit card expiry date input field
		expiryField = new JTextField(30);
		expiryField.setBounds(420, 250, 300, 30);
		expiryField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of card verification value label
		CVVLabel = new JLabel("CVV");
		CVVLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		CVVLabel.setForeground(Color.WHITE);
		CVVLabel.setBounds(100, 290, 500, 35);
		
		// Implementation of card verification value input field
		CVVField = new JTextField(30);
		CVVField.setBounds(420, 290, 300, 30);
		CVVField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of voucher label
		voucherLabel = new JLabel("Voucher");
		voucherLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		voucherLabel.setForeground(Color.WHITE);
		voucherLabel.setBounds(100, 330, 500, 35);
		
		// Implementation of voucher input field
		voucherField = new JTextField(30);
		voucherField.setBounds(420, 330, 300, 30);
		voucherField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of the purchase button
		purchaseButton = new JButton("Purchase");
		purchaseButton.setBorder(null);
		purchaseButton.setFont(new Font("Calibri", Font.BOLD, 20));
		purchaseButton.setBounds(100, 380, 275, 30);
		
		// Implementation of the back button
		backButton = new JButton("Back");
		backButton.setBorder(null);
		backButton.setFont(new Font("Calibri", Font.BOLD, 20));
		backButton.setBounds(445, 380, 275, 30);
		
		// Adding all components to payment page JPanel
		this.add(title);
		this.add(nameLabel);
		this.add(nameField);
		this.add(emailLabel);
		this.add(emailField);
		this.add(cardLabel);
		this.add(cardField);
		this.add(cd);
		this.add(expiryLabel);
		this.add(expiryField);
		this.add(CVVLabel);
		this.add(CVVField);
		this.add(voucherLabel);
		this.add(voucherField);
		this.add(purchaseButton);
		this.add(backButton);

		return this;
	}

	// Getter and setter functions
	public JButton getPurchaseButton() {
		return purchaseButton;
	}
	
	public JButton getBackButton() {
		return backButton;
	}

	public JTextField getNameField() {
		return nameField;
	}
	public JTextField getEmailField() {
		return emailField;
	}
	
	public JTextField getCardField() {
		return cardField;
	}
	
	public JTextField getExpiryField() {
		return expiryField;
	}
	
	public JTextField getCVVField() {
		return CVVField;
	}
	
	public JTextField getVoucherField() {
		return voucherField;
	}
	
	public void setNameField(String nameField) {
		this.nameField.setText(nameField);
	}

	public void setEmailField(String emailField) {
		this.emailField.setText(emailField);
	}

	public void setCardField(String cardField) {
		this.cardField.setText(cardField);
	}

	public void setExpiryField(String expiryField) {
		this.expiryField.setText(expiryField);
	}

	public void setCVVField(String cVVField) {
		this.CVVField.setText(cVVField);
	}

	public void setVoucherField(String voucherField) {
		this.voucherField.setText(voucherField);
	}
	
	public JComboBox getcd() {
		return cd;
	}

	public void setcd(JComboBox cd) {
		this.cd = cd;
	}
	
}
