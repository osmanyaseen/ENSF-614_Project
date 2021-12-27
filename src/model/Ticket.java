package model;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Entity containing the purchased ticket information, which includes the movie information, 
 * user's email, active status (whether the ticket was cancelled or not),
 * and purchase date. 
 * @author Osman Yaseen
 * @since December 7, 2021
 */
public class Ticket {

	private int ticketId;
	private int movieId;
	private int seatId;
	private String movieName;
	private String theatreName;
	private Instant showtimeName;
	private int auditorium;
	private String seatRow;
	private int seatCol;
	private double price;
	private String email;
	private boolean activeStatus;
	private Instant date;
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	/**
	 * Comprehensive Constructor method, used to retrieve ticket information from ticket database.
	 * @param tId Ticket ID
	 * @param mId Movie ID
	 * @param sId Showtime ID
	 * @param movie Movie Name
	 * @param theatre Theatre Name
	 * @param showtime Showtime date and time
	 * @param auditorium Auditorium Room #
	 * @param seatRow Seat row character
	 * @param seatCol Seat column number
	 * @param price Ticket price
	 * @param email User's email address to send ticket
	 * @param activeStatus Active Status of the ticket
	 * @param date Purchased date
	 */
	public Ticket (int tId, int mId, int sId, String movie, String theatre, Instant showtime, 
			int auditorium, String seatRow, int seatCol, double price, String email, 
			boolean activeStatus, Instant date) {
		this.ticketId = tId;
		this.movieId = mId;
		this.seatId = sId;
		this.movieName = movie;
		this.theatreName = theatre;
		this.showtimeName = showtime;
		this.auditorium = auditorium;
		this.seatRow = seatRow;
		this.seatCol = seatCol;
		this.price = price;
		this.email = email;
		this.activeStatus = activeStatus;
		this.date = date;
	}
	
	/**
	 * Robust version of Ticket Object, used mainly to send temporary ticket to payment page.
	 * @param mId Movie ID
	 * @param sId Showtime ID
	 * @param movie Movie name
	 * @param theatre Theatre name
	 * @param showtime Showtime date and time
	 * @param auditorium Auditorium Room #
	 * @param seatRow Seat row character
	 * @param seatColumn Seat column number
	 */
	public Ticket(int mId, int sId, String movie, String theatre, Instant showtime, int auditorium, String seatRow, int seatColumn) {
		this.movieId = mId;
		this.seatId = sId;
		this.movieName = movie;
		this.theatreName = theatre;
		this.showtimeName = showtime;
		this.auditorium = auditorium;
		this.seatRow = seatRow;
		this.seatCol = seatColumn;
	}
	
	
	/**
	 * Overridden toString method for displaying ticket information.
	 */
	@Override
	public String toString() {
		return  "Ticket ID: " + ticketId + "\n"
				+ "Movie Name: " + movieName + "\n"
				+ "Theatre Name: " + theatreName + "\n"
				+ "Showtime: " + toDateTimeFormat(showtimeName) + "\n"
				+ "Auditorium: " + auditorium + "\n"
				+ "Seat Row: " + seatRow + "\n"
				+ "Seat Column: " + seatCol + "\n"
				+ "Price: $" + df.format(price) + "\n"
				+ "Email: " + email + "\n"
				+ "Purchase Date: " + toDateFormat(date) + "\n";
	}

	/**
	 * Formats the instant object to the following time format: yyyy-MM-dd, HH:MM am/pm
	 * @param instant - Instant time object
	 * @return Formatted time string
	 */
	public String toDateTimeFormat(Instant instant) {
		DateTimeFormatter formatter =
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
								 .withLocale(Locale.CANADA)
								 .withZone(ZoneId.systemDefault());
		return formatter.format(instant);
	}
	
	/**
	 * Formats the instant object to the following time format: yyyy-MM-dd
	 * @param instant - Instant time object
	 * @return Formatted time string
	 */
	public String toDateFormat(Instant instant) {
		DateTimeFormatter formatter =
				DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
								 .withLocale(Locale.CANADA)
								 .withZone(ZoneId.systemDefault());
		return formatter.format(instant);
	}
	
	
	// Getter and Setter Methods
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Instant getShowtimeName() {
		return showtimeName;
	}

	public void setShowtimeName(Instant showtimeName) {
		this.showtimeName = showtimeName;
	}

	public int getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(int auditorium) {
		this.auditorium = auditorium;
	}

	public String getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatCol() {
		return seatCol;
	}

	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}
	
	
	
}