package controller;

import java.util.ArrayList;

import model.Movie;
import model.Receipt;
import model.RegisteredUser;
import model.Seat;
import model.Ticket;
import model.User;
import model.Voucher;

/**
 * Model Controller class
 * Implements Singleton Design Pattern
 * @author Deylin Yiao & Peter Yuan
 * @since December 7, 2021
 *
 */
public class ModelController {
	
	private DatabaseConnector cont;
	private User user;
	private Ticket tempTicket;
	private Seat tempSeat;
	private ArrayList<RegisteredUser> regUserList;
	private ArrayList<Ticket> ticketList;
	private ArrayList<Voucher> voucherList;
	private ArrayList<Movie> movieList;
	private ArrayList<Receipt> receiptList;
	private static ModelController onlyInstance;
	
	/**
	 * Constuctor method for Model Controller Object, initializes all the model class objects.
	 */
	private ModelController() {
		
		user = new User();
		cont = DatabaseConnector.getOnlyInstance();
		regUserList = cont.retrieveRegisteredUserList();
		ticketList = cont.retrieveTicketList();
		voucherList = cont.retrieveVoucherList();
		movieList = cont.retrieveMovieList();
		receiptList = cont.retrieveReceiptList();
		
	}

	/**
	 * Enforces only a single instance of model controller is created, 
	 * as model controller serves as a central controller for data management
	 * between the other controllers and database via DatabaseConnector.
	 * @return only instance of ModelController
	 */
	public static ModelController getOnlyInstance() {
		if(onlyInstance == null) {
			onlyInstance = new ModelController();
		}
		return onlyInstance;
	}
	
	// Getter and setter functions
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DatabaseConnector getCont() {
		return cont;
	}

	public void setCont(DatabaseConnector cont) {
		this.cont = cont;
	}

	public ArrayList<RegisteredUser> getRegUserList() {
		return regUserList;
	}

	public void setRegUserList(ArrayList<RegisteredUser> regUserList) {
		this.regUserList = regUserList;
	}

	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(ArrayList<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	public ArrayList<Voucher> getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(ArrayList<Voucher> voucherList) {
		this.voucherList = voucherList;
	}

	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public ArrayList<Receipt> getReceiptList() {
		return receiptList;
	}

	public void setReceiptList(ArrayList<Receipt> receiptList) {
		this.receiptList = receiptList;
	}

	public Ticket getTempTicket() {
		return tempTicket;
	}

	public void setTempTicket(Ticket tempTicket) {
		this.tempTicket = tempTicket;
	}
	
	public Seat getTempSeat() {
		return tempSeat;
	}

	public void setTempSeat(Seat tempSeat) {
		this.tempSeat = tempSeat;
	}

}
