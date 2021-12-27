package controller;
import model.Seat;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;

import model.*;
import view.*;


/**
 * Controller method for home page component of the movie registration application
 * @author Deylin Yiao & Maliha Zakir
 * @since December 7, 2021
 */
public class HomePageController {

	ModelController modelController;
	HomePageView homePageView;
	HubManager hubManager;
	private ArrayList<Seat> seatList;
	private ArrayList<Movie> movieList;
	private User user;
	private Ticket tempTicket;
	
	/**
	 * Constructor method to set up connection between the model controller and respective component view
	 * @param modelController ModelController responsible for centralized information from database
	 * @param homePageView respective view component
	 */
    public HomePageController(ModelController modelController, HomePageView homePageView) {
    	this.modelController = modelController;
    	this.homePageView = homePageView;
    	movieList = modelController.getMovieList();
    	this.user = modelController.getUser();
    	this.tempTicket = modelController.getTempTicket();
    	homePageView.addMovieListener(new AddMovieListener());
    	homePageView.addTheatreListener(new AddMovieListener());
    	homePageView.addShowtimeListener(new AddMovieListener());
    	homePageView.addA1Listener(new AddMovieListener());
    	homePageView.addA2Listener(new AddMovieListener());
    	homePageView.addA3Listener(new AddMovieListener());
    	homePageView.addA4Listener(new AddMovieListener());
    	homePageView.addB1Listener(new AddMovieListener());
    	homePageView.addB2Listener(new AddMovieListener());
    	homePageView.addB3Listener(new AddMovieListener());
    	homePageView.addB4Listener(new AddMovieListener());
    	homePageView.addC1Listener(new AddMovieListener());
    	homePageView.addC2Listener(new AddMovieListener());
    	homePageView.addC3Listener(new AddMovieListener());
    	homePageView.addC4Listener(new AddMovieListener());
    	homePageView.addCancelListener(new AddMovieListener());
    	homePageView.addPurchaseListener(new AddMovieListener());
    	initialized();
    	
    }
    
    /**
     * Initializes the home page by enabling only the movie combo box and retrieving the movies
     * from the movie array list
     */
    public void initialized() {
    	ArrayList<String> movieNames = new ArrayList<String>();
    	for (Movie movie: movieList) {
    		if (movieNames.isEmpty()) {
    			movieNames.add(movie.getMovieName());
    		}
    		if (!movieNames.contains(movie.getMovieName())) {
    			movieNames.add(movie.getMovieName());
    		}
    	}
    	String dummy[] = movieNames.toArray(new String[movieNames.size()]);
        DefaultComboBoxModel dm = new DefaultComboBoxModel(dummy);
        homePageView.getMovie().setModel(dm);
     }
	

	
    /**
     * Retrieves an array list of the theatre names and adds the list to the theatre
     * combo box
     */
    public void getTheatres() {
    	ArrayList<String> theatreNames = new ArrayList<String>();
    	for (Movie movie: movieList) {
    		if (theatreNames.isEmpty()) {
    			theatreNames.add(movie.getTheatreName());
    		}
    		if (!theatreNames.contains(movie.getTheatreName())) {
    			theatreNames.add(movie.getTheatreName());
    		}
    	}
    	String dummy[] = theatreNames.toArray(new String[theatreNames.size()]);
        DefaultComboBoxModel dm = new DefaultComboBoxModel(dummy);
        homePageView.getTheatre().setEnabled(true);
        homePageView.getTheatre().setModel(dm);
     }
    
    
    /**
     * Retrieves an array list of show time for the selected movie and theatre
     * and adds the list to the showtime combo box
     * @param movieName Movie names
     * @param theatreName Theatre names
     */
    public void findShowTime(String movieName, String theatreName) {
    	ArrayList<String> showTimeList = new ArrayList<String>();
    	for (Movie movie: movieList) {
    		if (movie.getMovieName().equals(movieName) && movie.getTheatreName().equals(theatreName)) {
    			showTimeList.add(toDateTimeFormat(movie.getShowtime()));
    		}
    	}
    	String dummy[] = showTimeList.toArray(new String[showTimeList.size()]);
        DefaultComboBoxModel dm = new DefaultComboBoxModel(dummy);
        homePageView.getShowtime().setEnabled(true);
        homePageView.getShowtime().setModel(dm);
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
     * Retrieves an array list of seats for the selected movie, theatre, and showtime
     * @return array list of seats
     */
    public ArrayList<Seat> findAllSeats() {
    	String movieName = (String) homePageView.getMovie().getSelectedItem();
	    String theatreName = (String) homePageView.getTheatre().getSelectedItem();
	    String selectedShowtime = (String) homePageView.getShowtime().getSelectedItem();
	    for(Movie movie: movieList) {
	    	if(movie.verified(movieName, theatreName, selectedShowtime)) {	    		
	    		return movie.getSeatList();
	    	}
	    }
		return null;

    }
    
    
    /**
     * Finds and returns the movie object from the selected movie name, theatre and
     * showtime.
     * @return Corresponding Movie Object
     */
    public Movie findMovie() {
    	String movieName = (String) homePageView.getMovie().getSelectedItem();
	    String theatreName = (String) homePageView.getTheatre().getSelectedItem();
	    String selectedShowtime = (String) homePageView.getShowtime().getSelectedItem();
	    for(Movie movie: movieList) {
	    	if(movie.verified(movieName, theatreName, selectedShowtime)) {	    		
	    		return movie;
	    	}
	    }
		return null;

    }
    
    
    /**
     * Validates if the movie is a pre-order and whether or not the registered user
     * can pre-order dut to limited seats.
     * @param movie Movie object
     * @return error message if there were invalidations, otherwise null
     */
    public String preOrderValidation(Movie movie) {
    	if (!movie.isPublic()) {
    		if(!user.isRegistered()) {
    			return "This movie is available for pre-order only. Only registered user can pre-order.";
    		}
    		
    		int seatBought = 0;
    		double percentBought;
    		for(Seat seat: movie.getSeatList()) {
    			if (seat.isAvailable() == false) {
    				seatBought++;
    			}
    		}
    		percentBought = ((double)seatBought/12.0);
    		System.out.println(seatBought + " " +  percentBought);
    		if (percentBought > 0.1) {
    		return "Movie has reached maximum pre-order.";
    		}
    	}
    	return null;
    }
    
    
    
    /**
     * Changes the radio button group enabled status based on available seats
     * @param seatList array list of seats
     */
    public void flipAvailability(ArrayList<Seat> seatList) {
    	for(int i = 0; i< seatList.size(); i++) {
    		System.out.println(seatList.get(i).isAvailable());
    	}
    	if (seatList.get(0).isAvailable()) {
    		homePageView.getA1().setEnabled(true);
    		homePageView.getA1().setForeground(Color.WHITE);
    	} else {
    		homePageView.getA1().setEnabled(false);
    	}
    	if (seatList.get(1).isAvailable()) {
    		homePageView.getA2().setEnabled(true);
    		homePageView.getA2().setForeground(Color.WHITE);
    	} else {
    		homePageView.getA2().setEnabled(false);
    	}
    	if (seatList.get(2).isAvailable()) {
    		homePageView.getA3().setEnabled(true);
    		homePageView.getA3().setForeground(Color.WHITE);
    	} else {
    		homePageView.getA3().setEnabled(false);
    	}
    	if (seatList.get(3).isAvailable()) {
    		homePageView.getA4().setEnabled(true);
    		homePageView.getA4().setForeground(Color.WHITE);
    	} else {
    		homePageView.getA4().setEnabled(false);
    	}
    	if (seatList.get(4).isAvailable()) {
    		homePageView.getB1().setEnabled(true);
    		homePageView.getB1().setForeground(Color.WHITE);
    	} else {
    		homePageView.getB1().setEnabled(false);
    	}
    	if (seatList.get(5).isAvailable()) {
    		homePageView.getB2().setEnabled(true);
    		homePageView.getB2().setForeground(Color.WHITE);
    	} else {
    		homePageView.getB2().setEnabled(false);
    	}
    	if (seatList.get(6).isAvailable()) {
    		homePageView.getB3().setEnabled(true);
    		homePageView.getB3().setForeground(Color.WHITE);
    	} else {
    		homePageView.getB3().setEnabled(false);
    	}
    	if (seatList.get(7).isAvailable()) {
    		homePageView.getB4().setEnabled(true);
    		homePageView.getB4().setForeground(Color.WHITE);
    	} else {
    		homePageView.getB4().setEnabled(false);
    	}
    	if (seatList.get(8).isAvailable()) {
    		homePageView.getC1().setEnabled(true);
    		homePageView.getC1().setForeground(Color.WHITE);
    	} else {
    		homePageView.getC1().setEnabled(false);
    	} 	
    	if (seatList.get(9).isAvailable()) {
    		homePageView.getC2().setEnabled(true);
    		homePageView.getC2().setForeground(Color.WHITE);
    	} else {
    		homePageView.getC2().setEnabled(false);
    	}
    	if (seatList.get(10).isAvailable()) {
    		homePageView.getC3().setEnabled(true);
    		homePageView.getC3().setForeground(Color.WHITE);
    	} else {
    		homePageView.getC3().setEnabled(false);
    	}
    	if (seatList.get(11).isAvailable()) {
    		homePageView.getC4().setEnabled(true);
    		homePageView.getC4().setForeground(Color.WHITE);
    	} else {
    		homePageView.getC4().setEnabled(false);
    	}
    }
    
	/**
	 * Adds an action listener for the all combo boxes, radio buttons, other buttons
	 * @param listener ActionListener object
	 */
	class AddMovieListener implements ActionListener{
		public void actionPerformed(ActionEvent evt) {
			
			if (evt.getSource() == homePageView.getMovie()) {
				System.out.println("Activated");
				getTheatres();
				homePageView.getShowtime().setEnabled(false);
			} else if(evt.getSource() == homePageView.getTheatre()) {
				String movieName = (String) homePageView.getMovie().getSelectedItem();
			    String theatreName = (String) homePageView.getTheatre().getSelectedItem();
			    findShowTime(movieName, theatreName);
			}
			
			else if(evt.getSource() == homePageView.getShowtime()) {
				flipAvailability(findAllSeats());	
			}
			
			else if(evt.getSource() == homePageView.getCancel()) {
				hubManager.redirectToCancelView();
				
			}
			else if(evt.getSource() == homePageView.getPurchase()) {
				try {
				Movie m = findMovie();
				if(m != null) {
					String error = preOrderValidation(m);
					if(error == null) {
						int movieId = m.getMovieId();
						int seatId = Integer.parseInt(homePageView.getGroup().getSelection().getActionCommand());
						String movieName = m.getMovieName();
						String theatreName = m.getTheatreName();
						Instant showtime = m.getShowtime();
						int auditorium = m.getAuditorium();
			            String seatRow = m.getSeatList().get(seatId).getRow();
			            int seatColumn = m.getSeatList().get(seatId).getColumn();
			            modelController.setTempSeat(m.getSeatList().get(seatId));
		                modelController.setTempTicket( new Ticket(movieId, seatId, movieName, theatreName, showtime, auditorium, seatRow, seatColumn));
		                hubManager.redirectToPaymentView();
					} else {
						homePageView.displayErrorMessage(error);
					}
					
					} else {
					homePageView.displayErrorMessage("Invalid Movie Selection!");
					}
				} catch (Exception e) {
					homePageView.displayErrorMessage("Invalid Input!");
				}
			}
			else {
				ButtonModel btnmodel = homePageView.getGroup().getSelection();
			}
		}
	}
	
	// Getter and setter functions
	public HubManager getHubManager() {
		return hubManager;
	}

	public void setHubManager(HubManager hubManager) {
		this.hubManager = hubManager;
	}

	public HomePageView getHomePageView() {
		return homePageView;
	}
	
}
