package view;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;


/**
 * GUI interface for cancellation page
 * @author Peter Yuan
 * @since December 7, 2021
 */
@SuppressWarnings("serial")
public class CancellationView extends JPanel{

	private JTextField ticketField;
	private JLabel title;
	private JLabel ticketLabel; 
    private JButton cancelTicketButton;
    private JButton returnButton;
    
    /**
	 * Constructor method for CancellationView Object, initially constructs and retrieves the page.
	 */
    public CancellationView() {
    	retrievePage();
    }
    
    /**
	 * Adds an action listener for the return to menu button
	 * @param listener ActionListener object
	 */
    public void addReturnToMenu(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }

    /**
	 * Adds an action listener for the cancel ticket button
	 * @param listener ActionListener object
	 */
    public void addCancelTicket (ActionListener actionListener) {
        cancelTicketButton.addActionListener(actionListener);
    }
    
    /**
	 * Displays a message box containing a message, primarily used for displaying error
	 * @param errorMessage Error message sent from the application
	 */
	public void displayMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message);
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
	 * Constructs and retrieves the cancellation page for movie theatre application
	 * @return Constructed cancellation page in the form of a JPanel
	 */
    public JPanel retrievePage() {
    	
    	// Initial setup of page
    	this.setLayout(null);
		this.setBounds(200, 80, 800, 450);
		this.setOpaque(false);
		
		// Implementation of title
		title = new JLabel("Cancel Ticket");
		title.setBounds(300, 30, 400, 40);
		title.setFont(new Font("Calibri", Font.BOLD, 40));
		title.setForeground(Color.WHITE);
		
		// Implementation of ticket label
		ticketLabel = new JLabel("Enter ticket ID:");
		ticketLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		ticketLabel.setForeground(Color.WHITE);
		ticketLabel.setBounds(150, 100, 300, 35);
		
		// Implementation of ticket input field
		ticketField = new JTextField(30);
		ticketField.setBounds(150, 150, 525, 30);
		ticketField.setFont(new Font("Calibri", Font.BOLD, 20));
		
		// Implementation of cancel button
		cancelTicketButton = new JButton("Cancel Ticket");
		cancelTicketButton.setBorder(null);
		cancelTicketButton.setFont(new Font("Calibri", Font.BOLD, 20));
		cancelTicketButton.setBounds(150, 200, 250, 35);
		
		// Implementation of return button
		returnButton = new JButton("Back");
		returnButton.setBorder(null);
		returnButton.setFont(new Font("Calibri", Font.BOLD, 20));
		returnButton.setBounds(425, 200, 250, 35);

		// Adding all components to cancellation page JPanel
		this.add(title);
		this.add(ticketLabel);
		this.add(ticketField);
		this.add(cancelTicketButton);
		this.add(returnButton);

		return this;
	}
    
    // Getter and setter methods
    public JButton getCancelButton() {
		return cancelTicketButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}
	
	public JTextField getTicketID() {
        return ticketField;
    }
	
	public JTextField getTicketField() {
		return ticketField;
	}
	
}
