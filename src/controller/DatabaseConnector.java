package controller;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

import model.Movie;
import model.RegisteredUser;
import model.Seat;
import model.Ticket;
import model.Voucher;
import model.Receipt;

/**
 * MySQL database interface class, Responsible for communicating with server to retrieve and update information
 * with the movie registration application.
 * Implements Singleton Design Pattern
 * @author Deylin Yiao & Osman Yaseen
 * @since December 7, 2021
 */
public class DatabaseConnector {
	
	private Connection myConnect;
	private Statement myStatement;
	private Statement myStatement2;
	private static DatabaseConnector onlyInstance;
	
	/**
	 * Establishes connection with database using the provided address, username, and password
	 */
	private DatabaseConnector() {
		try {
			// Establishing connection with database
			myConnect = DriverManager.getConnection("jdbc:mysql://localhost/movietheatre", "root", "MySQL123!");
			// SQl Query
			myStatement = myConnect.createStatement();
			myStatement2 = myConnect.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the only instance of database controller to prevent multiple
	 * connections to the MySQL database.
	 * @return the only instance of DatabaseConnector Object,
	 *         creates one if there non-existent
	 */
	public static DatabaseConnector getOnlyInstance() {
		if(onlyInstance == null) {
			onlyInstance = new DatabaseConnector();
		}
		return onlyInstance;
	}
	
	/**
	 * Sends a query to the MySQL database to retrieve an array list of registered user
	 * @return array list of registered user
	 */
	public ArrayList<RegisteredUser> retrieveRegisteredUserList() {
		try {
			// Execute Query
			ResultSet myRs = myStatement.executeQuery("SELECT * FROM REGISTEREDUSER");
			// Check result set
			ArrayList<RegisteredUser> registeredUserList = new ArrayList<RegisteredUser>();
			while(myRs.next()) {
				String n = myRs.getString("Name");
				String p = myRs.getString("Password");
				String e = myRs.getString("Email");
				String cardNumber = myRs.getString("CreditCardNumber");
				String cardDate = myRs.getString("creditCardDate");
				int CVV = myRs.getInt("CreditCardCVV");
				Instant annualFeeDate = myRs.getTimestamp("AnnualFeeDate").toInstant();
				boolean activeStatus = myRs.getBoolean("ActiveStatus");
				RegisteredUser user = new RegisteredUser(n, p, e, cardNumber, cardDate, CVV, annualFeeDate, activeStatus);
				registeredUserList.add(user);
			}
			return registeredUserList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Sends a query to MySQL database to add a registered user to the registered user table
	 * @param user RegisteredUser object
	 */
	public void addRegisteredUser(RegisteredUser user) {
		
		PreparedStatement addRegUser = null;
		String statement = "INSERT into REGISTEREDUSER" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			addRegUser = myConnect.prepareStatement(statement);
			addRegUser.setString(1, user.getName());
			addRegUser.setString(2, user.getPassword());
			addRegUser.setString(3, user.getEmail());
			addRegUser.setString(4, user.getCreditCardNumber());
			addRegUser.setString(5, user.getCreditCardDate());
			addRegUser.setInt(6, user.getCreditCardCVV());
			addRegUser.setTimestamp(7, Timestamp.from(user.getAnnualFeeDate()));
			addRegUser.setBoolean(8, user.isActiveStatus());
			addRegUser.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	/**
	 * Sends a query to the MySQL database to update a registered user in the registered user table
	 * @param user RegisteredUser object
	 */
	public void updateRegisteredUser(RegisteredUser user) {
		
		PreparedStatement updateRegUser = null;
		String statement = "UPDATE REGISTEREDUSER SET" +
				" Name = ? ," +
				" Password = ? ," +
				" Email = ? ," +
				" CreditCardNumber = ? ," +
				" CreditCardDate = ? ," +
				" CreditCardCVV = ? , " +
				" AnnualFeeDate = ? ," +
				" ActiveStatus = ? " +
				" WHERE Email = '" + user.getEmail() + "';";
		try {
			updateRegUser = myConnect.prepareStatement(statement);
			updateRegUser.setString(1, user.getName());
			updateRegUser.setString(2, user.getPassword());
			updateRegUser.setString(3, user.getEmail());
			updateRegUser.setString(4, user.getCreditCardNumber());
			updateRegUser.setString(5, user.getCreditCardDate());
			updateRegUser.setInt(6, user.getCreditCardCVV());
			updateRegUser.setTimestamp(7, Timestamp.from(user.getAnnualFeeDate()));
			updateRegUser.setBoolean(8, user.isActiveStatus());
			updateRegUser.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	/**
	 * Sends a query to the MySQL database to retrieve an array list of movies
	 * @return array list of movies
	 */
	public ArrayList<Movie> retrieveMovieList() {
		try {
			// Execute Query
			ResultSet myRs = myStatement.executeQuery("SELECT * FROM MOVIE");
			// Check result set
			ArrayList<Movie> movieList = new ArrayList<Movie>();
			while(myRs.next()) {
				int Id = myRs.getInt("movieID");
				String m = myRs.getString("MovieName");
				String t = myRs.getString("TheatreName");
				Instant sh = myRs.getTimestamp("Showtime").toInstant();
				int a = myRs.getInt("Auditorium");
				boolean isPublic = myRs.getBoolean("isPublic");
				ArrayList<Seat> seatList = retrieveSeatList(Id);
				Movie movie = new Movie(Id, m, t, sh, a, seatList, isPublic);
				movieList.add(movie);
			}
			return movieList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sends a query to the MySQL database to retrieve an array list of movie seats
	 * @return array list of seats
	 */
	public ArrayList<Seat> retrieveSeatList(int id) {
		try {
			// Execute Query
			ResultSet myRs2 = myStatement2.executeQuery("SELECT * FROM SEAT WHERE movieID = " + id);
			// Check result set
			ArrayList<Seat> seatList = new ArrayList<Seat>();
			while(myRs2.next()) {
				int movieId = myRs2.getInt("movieID");
				int seatId = myRs2.getInt("seatID");
				String row = myRs2.getString("RowLetter");
				int col = myRs2.getInt("ColumnNumber");
				boolean available = myRs2.getBoolean("Available");
				Seat seat = new Seat(movieId, seatId, row, col, available);
				seatList.add(seat);
			}
			return seatList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sends a query to the MySQL database to update a seat in the seat table
	 * @param seat Seat object
	 */
	public void updateSeat(Seat seat) {
		
		PreparedStatement updateTicket = null;
		String statement = "UPDATE SEAT SET" +
		" RowLetter = ?," +
		" ColumnNumber = ?," +
		" Available = ?" +
		" WHERE MovieID = " + seat.getMovieId() + " AND SeatID = " + seat.getSeatId() + ";";
		try {
			updateTicket = myConnect.prepareStatement(statement);
			updateTicket.setString(1, seat.getRow());
			updateTicket.setInt(2, seat.getColumn());
			updateTicket.setBoolean(3, seat.isAvailable());
			updateTicket.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	/**
	 * Sends a query to the MySQL database to retrieve an array list of purchased tickets
	 * @return array list of purchased tickets
	 */
	public ArrayList<Ticket> retrieveTicketList() {
		try {
			// Execute Query
			ResultSet myRs = myStatement.executeQuery("SELECT * FROM TICKET");
			// Check result set
			ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
			while(myRs.next()) {
				int tId = myRs.getInt("TicketID");
				int mId = myRs.getInt("MovieID");
				int sId = myRs.getInt("seatID");
				double price = myRs.getDouble("Price");
				Instant date = myRs.getTimestamp("Date").toInstant();
				boolean activeStatus = myRs.getBoolean("activeStatus");
				ResultSet myRs2 = myStatement2.executeQuery("SELECT * FROM MOVIE WHERE MovieID = " + mId + ";");
				myRs2.next();
				String movieName = myRs2.getString("MovieName");
				String theatreName = myRs2.getString("TheatreName");
				int auditorium = myRs2.getInt("Auditorium");
				Instant showtimeName = myRs2.getTimestamp("Showtime").toInstant();
				ResultSet myRs3 = myStatement2.executeQuery("SELECT * FROM SEAT WHERE MovieID = " + mId + " AND SeatID = " + sId + ";");
				myRs3.next();
				String seatRow = myRs3.getString("RowLetter");
				int seatCol = myRs3.getInt("ColumnNumber");
				String email = myRs.getString("email");
				Ticket ticket = new Ticket(tId, mId, sId, movieName, theatreName, showtimeName, auditorium,
						                 seatRow, seatCol, price, email, activeStatus, date);
				ticketList.add(ticket);
			}
			return ticketList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sends a query to MySQL database to add a ticket to the purchased ticket table
	 * @param ticket Ticket object
	 */
	public void addTicket(Ticket ticket) {
		
		PreparedStatement addTicket = null;
		String statement = "INSERT into TICKET(SeatID, MovieId, Price, Date, ActiveStatus, Email)" + " VALUES( ?, ?, ?, ?, ?, ?)";
		try {
			addTicket = myConnect.prepareStatement(statement);
			addTicket.setInt(1, ticket.getSeatId());
			addTicket.setInt(2, ticket.getMovieId());
			addTicket.setDouble(3, ticket.getPrice());
			addTicket.setTimestamp(4, Timestamp.from(ticket.getDate()));
			addTicket.setBoolean(5, ticket.isActiveStatus());
			addTicket.setString(6, ticket.getEmail());
			addTicket.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	/**
	 * Sends a query to the MySQL database to update a ticket in the ticket table
	 * @param seat Ticket object
	 */
	public void updateTicket(Ticket ticket) {
		
		PreparedStatement updateTicket = null;
		String statement = "UPDATE TICKET SET" +
		" SeatID = ?," +
		" MovieID = ?," +
		" Price = ?," +
		" Date = ?," +
		" ActiveStatus = ?, " +
		" Email = ?" +
		" WHERE TicketID = " + ticket.getTicketId() + ";";
		try {
			updateTicket = myConnect.prepareStatement(statement);
			updateTicket.setInt(1, ticket.getSeatId());
			updateTicket.setInt(2, ticket.getMovieId());
			updateTicket.setDouble(3, ticket.getPrice());
			updateTicket.setTimestamp(4, Timestamp.from(ticket.getDate()));
			updateTicket.setBoolean(5, ticket.isActiveStatus());
			updateTicket.setString(6, ticket.getEmail());
			updateTicket.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	/**
	 * Sends a query to the MySQL database to retrieve an array list of purchased tickets
	 * @return array list of purchased tickets
	 */
	public ArrayList<Voucher> retrieveVoucherList() {
		try {
			// Execute Query
			ResultSet myRs = myStatement.executeQuery("SELECT * FROM VOUCHER");
			// Check result set
			ArrayList<Voucher> voucherList = new ArrayList<Voucher>();
			while(myRs.next()) {
				int vId = myRs.getInt("VoucherID");
				int code = myRs.getInt("VoucherCode");
				double amount = myRs.getInt("Amount");
				Instant expiryDate = myRs.getTimestamp("ExpiryDate").toInstant();
				Voucher voucher = new Voucher(vId, code, amount, expiryDate);
				voucherList.add(voucher);
			}
			return voucherList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sends a query to MySQL database to add a voucher to the voucher table
	 * @param voucher Voucher object
	 */
	public void addVoucher(Voucher voucher) {
		
		PreparedStatement addVoucher = null;
		String statement = "INSERT into VOUCHER(VoucherCode, Amount, ExpiryDate)" + " VALUES(?, ?, ?)";
		try {
			addVoucher = myConnect.prepareStatement(statement);
			addVoucher.setInt(1, voucher.getVoucherCode());
			addVoucher.setDouble(2, voucher.getVoucherValue());
			addVoucher.setTimestamp(3, Timestamp.from(voucher.getExpiryDate()));
			addVoucher.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	
	/**
	 * Sends a query to MySQL database to delete a voucher with the corresponding voucher code in the voucher table
	 * @param voucherCode code to a corresponding voucher
	 */
	public void deleteVoucher(int voucherCode) {
		PreparedStatement deleteVoucher = null;
		String statement = "DELETE FROM VOUCHER WHERE VoucherCode = ?";
		try {
			deleteVoucher = myConnect.prepareStatement(statement);
			deleteVoucher.setInt(1, voucherCode);
			deleteVoucher.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Sends a query to MySQL database to retrieve an array list of receipts
	 * @return array list of receipts
	 */
	public ArrayList<Receipt> retrieveReceiptList() {
		try {
			// Execute Query
			ResultSet myRs = myStatement.executeQuery("SELECT * FROM RECEIPT");
			// Check result set
			ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
			while(myRs.next()) {
				int rID = myRs.getInt("ReceiptID");
				double price = myRs.getDouble("Price");
				Instant date = myRs.getTimestamp("Date").toInstant();
				Receipt receipt = new Receipt(rID, price, date);
				receiptList.add(receipt);
			}
			return receiptList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Sends a query to MySQL database to add a receipt to the receipt table
	 * @param receipt Receipt Object
	 */
	public void addReceipt(Receipt receipt) {
		
		PreparedStatement addReceipt = null;
		String statement = "INSERT into RECEIPT(Price, Date)" + " VALUES(?, ?)";
		try {
			addReceipt = myConnect.prepareStatement(statement);
			addReceipt.setDouble(1, receipt.getPrice());
			addReceipt.setTimestamp(2, Timestamp.from(receipt.getDate()));
			addReceipt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
}
