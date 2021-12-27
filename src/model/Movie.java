package model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Entity to store the movie information for a specific theatre and showtime.
 * Can be set to allow only pre-ordering to be available for registered user.
 * @author Maliha Zakir
 * @since December 7, 2021
 */
public class Movie {

	private int movieId;
	private String movieName;
	private String theatreName;
	private Instant showtime;
	private int auditorium;
	private ArrayList<Seat> seatList;
	private boolean isPublic;

	/**
	 * Constructor method for Movie Object, primarily used to retrieve movie information from database
	 * @param id Movie ID
	 * @param m Movie name
	 * @param t Theatre name
	 * @param s Showtime date and time
	 * @param a Auditorium room number
	 * @param seatList Movie list of seats
	 * @param isPublic Status Movie public status, if false then seats are only available for pre-order
	 */
	public Movie(int id, String m, String t, Instant s, int a, ArrayList<Seat> seatList, boolean isPublic) {
		super();
		this.movieId = id;
		this.movieName = m;
		this.theatreName = t;
		this.showtime = s;
		this.auditorium = a;
		this.seatList = seatList;
		this.isPublic = isPublic;
	}

	/**
	 * Formats the instant object to the following time format: yyyy:MM:dd, HH:MM am/pm
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
	 * Verifies if a selected movie, theatre and showtime is present within the movie database
	 * @param movieName Movie name
	 * @param theatreName Theatre name
	 * @param showtime Showtime Date and time
	 * @return True if selection matches to a movie currently within the movie database, false otherwise
	 */
	public boolean verified(String movieName, String theatreName, String showtime) {
		if (getMovieName().equals(movieName) && getTheatreName().equals(theatreName) && toDateTimeFormat(getShowtime()).equals(showtime)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Overridden toString method for Movie Object. Primarily used to diagnostic purposes.
	 */
	@Override
	public String toString() {
		return getMovieName() + " " + getTheatreName() + " " + toDateTimeFormat(getShowtime()) + " " + getAuditorium() + " " + getSeatList().get(0).getRow() + " "+ isPublic();
	}

	// Getter and Setter Methods
	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
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

	public Instant getShowtime() {
		return showtime;
	}

	public void setShowtime(Instant showtime) {
		this.showtime = showtime;
	}

	public int getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(int auditorium) {
		this.auditorium = auditorium;
	}

	public ArrayList<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(ArrayList<Seat> seatList) {
		this.seatList = seatList;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

}