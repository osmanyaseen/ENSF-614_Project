package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import model.*;
import view.*;

/**
 * Controller For Ticket Cancellation
 * @author Peter Yuan
 * @since December 7, 2021
 */
public class CancelController {
	private User user;
	private ModelController modelController; 
	private CancellationView cancellationView;
	private HubManager hubManager;
	
	/**
	 * Constuctor method for CancelController object, initially constructs, sets the view, and refreshes listeners.
	 * @param modelController ModelController object 
	 * @param cancellationView CancellationView object
	 */
	public CancelController(ModelController modelController, CancellationView cancellationView) {
		setModelController(modelController);
		setView(cancellationView);
		refreshListeners();
	}
	
	/**
	 * Refreshes listeners.
	 */
	public void refreshListeners() {
		cancellationView.addReturnToMenu(new AddCancelListener());
		cancellationView.addCancelTicket(new AddCancelListener());
	}
	
	/**
	 * Searches for ticket object using ticketNumber 
	 * @param ticketNumber ticketID for the desired ticket 
	 * @return ticket that contains input ticketID
	 */
	public Ticket searchForTicket(int ticketNumber) {
		Ticket myTicket = null; 
		for (Ticket t: modelController.getTicketList()) {
			if(t.getTicketId() == ticketNumber) {
				myTicket = t;
			}
		}
		return myTicket;
	}
	
	/**
	 * Sets a Ticket to inactive and removes it from database
	 * @param ticketToCancel Ticket object to be cancelled
	 */
	public void cancelTicket(Ticket ticketToCancel) {
		
		ticketToCancel.setActiveStatus(false);
		modelController.getCont().updateTicket(ticketToCancel);
	}
	
	/**
	 * Checks to see if the present time and date is more than 72 hours prior to Ticket showtime
	 * @param ticket ticketID of Ticket to be checked
	 * @return true if present time and date is more than 72 hours prior to showtime
	 * @return false otherwise
	 */
	public boolean within72Hours(int ticket) {
		
		Ticket t = searchForTicket(ticket); 
		
		Instant currentDate = Instant.now();
		Instant showTime = t.getShowtimeName().minus(Duration.ofDays(3));
		
		if(currentDate.isBefore(showTime)) {
			System.out.println(showTime);
			System.out.println(currentDate);
			return true;
			
		} else {
			
			return false;
		}
	}
	
	/**
	 * Checks to see if Ticket already exists in the database and is active
	 * @param ticket TicketID of ticket to be checked
	 * @return true if Ticket exists in the database and is active
	 * @return false otherwise
	 */
	public boolean matchingID(int ticket) {
		for(Ticket t: modelController.getTicketList()) {
			if(t.getTicketId() == ticket && t.isActiveStatus()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Validates the previous checking functions
	 * @param ticketID TicketID of ticket to be checked
	 * @return "Error: TicketID not found or cancelled already." if ticket is not found in system
	 * @return "Error: Cannot cancel ticket after 72 hours prior to showtime." if later than 72 hours before Ticket showtime
	 * @return null otherwise
	 */
	public String validateForm(int ticketID) {
		
		
		if (!matchingID(ticketID)) {
			return "Error: TicketID not found or cancelled already.";
		}
		
		else if(!within72Hours(ticketID)) {
			return "Error: Cannot cancel ticket after 72 hours prior to showtime.";
		}
	
		return null;
		
	}
	
	/**
	 * Implements ActionListener and ties outputs to user action
	 */
	class AddCancelListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			
			if(evt.getSource() == cancellationView.getCancelButton()) {
				
				String ticketID = cancellationView.getTicketField().getText();
				
				try {	
					
					int tixID = Integer.parseInt(ticketID);
					String error = validateForm(tixID);		
					
					if (error != null) { 
						cancellationView.displayMessage(error);
						
					} else {
						
						Ticket t = searchForTicket(tixID); 
						cancelTicket(t);
						Voucher vouch = createVoucher(tixID);

						modelController.getVoucherList().add(vouch); 
						modelController.getCont().addVoucher(vouch);
						String voucherInfo = "Ticket successfully cancelled. You have received a voucher: " + vouch.toString();
						cancellationView.displayMessage(voucherInfo);
					}
					
				}  catch (Exception e) {
					cancellationView.displayMessage("Error: Invalid input!");
				}
				
			} else if (evt.getSource() == cancellationView.getReturnButton()){
				hubManager.redirectToHomePageView();
			}
		}
	}
		
	/**
	* Checks to see if the user is registered
	* @return if user is registered or not
	*/	
	public boolean isUserRegistered () {
		user = modelController.getUser();
		return user.isRegistered();
	}
	
	/**
	* Gets the VoucherID of the last Voucher in VoucherList and increments it by 1
	* @return 0 if VoucherList is empty, VoucherID of the last Voucher in VoucherList incremented by 1 otherwise
	*/	
	public int getMaxVoucherID() {
		int index = (modelController.getVoucherList().size())-1; 
		
		if(index < 0) {
			
			return 0;
			
		} else {
		
			Voucher endVoucher = modelController.getVoucherList().get(index); 
				
			int maxID = endVoucher.getVoucherID() + 1;
			
			return maxID; 
		
		}
		
	}
	
	/**
	* Creates a new Voucher
	* @param ticketVoucher TicketID of cancelled Ticket
	* @return Voucher object with credit based on user registration status
	*/
	public Voucher createVoucher(int ticketVoucher) {
		
			
		double price;
		
		if(isUserRegistered()) {
			price = searchForTicket(ticketVoucher).getPrice();  
		} else {
			price = searchForTicket(ticketVoucher).getPrice()*0.85;
		}
				
		Instant voucherExpiry = Instant.now();

		int voucherID = getMaxVoucherID(); 
				
		int voucherCode = (int)Math.floor(Math.random()*(999999999-1+1)+1);
		Voucher voucher1 = new Voucher(voucherID, voucherCode, price, voucherExpiry.plus(365, ChronoUnit.DAYS));
		
		for (Voucher voucher : modelController.getVoucherList()){
			if (voucher.getVoucherCode() == voucher1.getVoucherCode()) {
			voucherCode = (int)Math.floor(Math.random()*(999999999-1+1)+1);
			voucher1 = new Voucher(voucherID, voucherCode, price, voucherExpiry.plus(365, ChronoUnit.DAYS));
		}
		}

		return voucher1;
	}
	
	// Getter and setter functions
	public ModelController getModelController() {
		return modelController;
	}
	
	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}
	
	public void setView(CancellationView cancellationView) {
		this.cancellationView = cancellationView;
	}
	
	public CancellationView getView() {
		return cancellationView;
	}
	
	public void setHubManager(HubManager hubManager) {
		this.hubManager = hubManager;
	}
	
}
	
