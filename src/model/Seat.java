package model;

/**
 * Entity containing the seat information for a movie screening, can be set availability status.
 * @author Maliha Zakir
 * @since December 7, 2021
 */
public class Seat {

	private int movieId;
	private int seatId;
	private String row;
	private int column;
	private boolean available;
	
	
	/**
	 * Constructor method for Seat Object
	 * @param id Seat ID
	 * @param r Character indicating the seat row
	 * @param c Number indicating the seat column
	 * @param a Seat availability
	 */
	public Seat(int movieId, int seatId, String r, int c, boolean a) {
		this.movieId = movieId;
		this.seatId = seatId;
		this.row = r;
		this.column = c;
		this.available = a;
	}
	
	/**
	 * Overridden toString method, returns the row and column indicator for seat
	 */
	@Override
    public String toString() {
    	return "Row: " + getRow() + "Column: " + getColumn();
    }
	
	// Getter and Setter Methods
	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
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
	public void setseatId(int seatId) {
		this.seatId = seatId;
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
