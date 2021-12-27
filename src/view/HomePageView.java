package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI interface for home page
 * @author Deylin Yiao & Maliha Zakir
 * @since December 7, 2021
 */
@SuppressWarnings("serial")
public class HomePageView extends JPanel{
	
	private JLabel title;
	private JComboBox movie;
	private JComboBox theatre;
	private JComboBox showtime;
	private JLabel movieLabel;
	private JLabel theatreLabel;
	private JLabel showtimeLabel;
	private JLabel seatselectionLabel;
	private JLabel screenLabel;
	private JRadioButton A1;
	private JRadioButton A2;
	private JRadioButton A3;
	private JRadioButton A4;
	private JRadioButton B1;
	private JRadioButton B2;
	private JRadioButton B3;
	private JRadioButton B4;
	private JRadioButton C1;
	private JRadioButton C2;
	private JRadioButton C3;
	private JRadioButton C4;
	private JButton cancel;
	private JButton purchase;
	private ButtonGroup group;

	
	/**
	 * Constuctor method for HomePageView Object, initially constructs and retrieves the page.
	 */
	public HomePageView() {
		retrievePage();
	}
	
	/**
	 * Adds an action listener for the movie combo box
	 * @param listener ActionListener object
	 */
	public void addMovieListener(ActionListener listener) {
		movie.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the theatre combo box
	 * @param listener ActionListener object
	 */
	public void addTheatreListener(ActionListener listener) {
		theatre.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the showtime combo box
	 * @param listener ActionListener object
	 */
	public void addShowtimeListener(ActionListener listener) {
		showtime.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat A1
	 * @param listener ActionListener object
	 */
	public void addA1Listener(ActionListener listener) {
		A1.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat A2
	 * @param listener ActionListener object
	 */
	public void addA2Listener(ActionListener listener) {
		A2.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat A3
	 * @param listener ActionListener object
	 */
	public void addA3Listener(ActionListener listener) {
		A3.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat A4
	 * @param listener ActionListener object
	 */
	public void addA4Listener(ActionListener listener) {
		A4.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat B1
	 * @param listener ActionListener object
	 */
	public void addB1Listener(ActionListener listener) {
		B1.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat B2
	 * @param listener ActionListener object
	 */
	public void addB2Listener(ActionListener listener) {
		B2.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat B3
	 * @param listener ActionListener object
	 */
	public void addB3Listener(ActionListener listener) {
		B3.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat B4
	 * @param listener ActionListener object
	 */
	public void addB4Listener(ActionListener listener) {
		B4.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat C1
	 * @param listener ActionListener object
	 */
	public void addC1Listener(ActionListener listener) {
		C1.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat C2
	 * @param listener ActionListener object
	 */
	public void addC2Listener(ActionListener listener) {
		C2.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat C3
	 * @param listener ActionListener object
	 */
	public void addC3Listener(ActionListener listener) {
		C3.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the radio button for seat C4
	 * @param listener ActionListener object
	 */
	public void addC4Listener(ActionListener listener) {
		C4.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the cancel ticket button
	 * @param listener ActionListener object
	 */
	public void addCancelListener(ActionListener listener) {
		cancel.addActionListener(listener);
	}
	
	/**
	 * Adds an action listener for the purchase ticket button 
	 * @param listener ActionListener object
	 */
	public void addPurchaseListener(ActionListener listener) {
		purchase.addActionListener(listener);
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
	    whiteSolid = new BasicStroke(3.0f);
	    graphics.setStroke(whiteSolid);
	    graphics.drawRoundRect(75, 185, 850, 225, arcs.width, arcs.height); //paint border
	}
	
	/**
	 * Constructs and retrieves the home page for movie theatre application
	 * @return Constructed home page in the form of a JPanel
	 */
	public JPanel retrievePage() {
		
		// Initial setup of page
		this.setLayout(null);
        this.setBounds(100, 75, 1000, 475);
		this.setOpaque(false);
		this.setBackground(Color.BLACK);
		
		// Implementation of title
		title = new JLabel("Home Page");
		title.setBounds(400, 20, 450, 40);
		title.setFont(new Font("Calibri", Font.BOLD, 40));
		title.setForeground(Color.WHITE);
		
		// Implementation of movie combo box label
	    movieLabel = new JLabel("Movie");
	    movieLabel.setBounds(75,70,250,20);
	    movieLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	    movieLabel.setForeground(Color.WHITE);
	    
	    // Implementation of movie combo box
	    movie = new JComboBox();
	    movie.setBounds(75,110,250,20);
	    movie.setEnabled(true);
	    
	    // Implementation of theatre combo box label
	    theatreLabel = new JLabel("Theatre");
	    theatreLabel.setBounds(375,70,250,20);
	    theatreLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	    theatreLabel.setForeground(Color.WHITE);
	    
	    // Implementation of theatre combo box
	    theatre = new JComboBox();
	    theatre.setBounds(375,110,250,20);
	    theatre.setEnabled(false);
	    
	    // Implementation of showtime combo box label
	    showtimeLabel = new JLabel("Showtime");
	    showtimeLabel.setBounds(675,70,250,20);
	    showtimeLabel.setFont(new Font("Calibri", Font.BOLD, 20));
	    showtimeLabel.setForeground(Color.WHITE);
	    
	    // Implementation of showtime combo box
	    showtime = new JComboBox();
	    showtime.setBounds(675,110,250,20);
	    showtime.setEnabled(false);
	    
	    // Implementation of seat selection label
	    seatselectionLabel = new JLabel("Seat Selection");
	    seatselectionLabel.setBounds(375,150,250,40);
	    seatselectionLabel.setFont(new Font("Calibri", Font.BOLD, 40));
	    seatselectionLabel.setForeground(Color.WHITE);
	    
	    // Implementation of screen label
	    screenLabel = new JLabel("| SCREEN |");
	    screenLabel.setBounds(425,200,250,30);
	    screenLabel.setFont(new Font("Calibri", Font.BOLD, 30));
	    screenLabel.setForeground(Color.WHITE);

	    // Implementation of radio button for seat A1
        A1 = new JRadioButton("A1");
        A1.setBounds(125,250,60,60);
        A1.setOpaque(false);
        A1.setForeground(Color.RED);
	    A1.setFont(new Font("Calibri", Font.BOLD, 30));
	    A1.setEnabled(false);
	    A1.setActionCommand("0"); // number corresponds to seat ID

	    // Implementation of radio button for seat A2
	    A2 = new JRadioButton("A2");
        A2.setBounds(350,250,60,60);
        A2.setOpaque(false);
        A2.setForeground(Color.RED);
	    A2.setFont(new Font("Calibri", Font.BOLD, 30));
	    A2.setEnabled(false);
	    A2.setActionCommand("1");
	    
	    // Implementation of radio button for seat A3	    
	    A3 = new JRadioButton("A3");
        A3.setBounds(575,250,60,60);
        A3.setOpaque(false);
        A3.setForeground(Color.RED);
	    A3.setFont(new Font("Calibri", Font.BOLD, 30));
	    A3.setEnabled(false);
	    A3.setActionCommand("2");

	    // Implementation of radio button for seat A4
	    A4 = new JRadioButton("A4");
        A4.setBounds(800,250,60,60);
        A4.setOpaque(false);
        A4.setForeground(Color.RED);
	    A4.setFont(new Font("Calibri", Font.BOLD, 30));
	    A4.setEnabled(false);
	    A4.setActionCommand("3");
	    
	    // Implementation of radio button for seat B1	    
	    B1 = new JRadioButton("B1");
        B1.setBounds(125,300,60,60);
        B1.setOpaque(false);
        B1.setForeground(Color.RED);
	    B1.setFont(new Font("Calibri", Font.BOLD, 30));
	    B1.setEnabled(false);
	    B1.setActionCommand("4");
	    
	    // Implementation of radio button for seat B2
	    B2 = new JRadioButton("B2");
        B2.setBounds(350,300,60,60);
        B2.setOpaque(false);
        B2.setForeground(Color.RED);
	    B2.setFont(new Font("Calibri", Font.BOLD, 30));
	    B2.setEnabled(false);
	    B2.setActionCommand("5");
	    
	    // Implementation of radio button for seat B3
	    B3 = new JRadioButton("B3");
        B3.setBounds(575,300,60,60);
        B3.setOpaque(false);
        B3.setForeground(Color.RED);
	    B3.setFont(new Font("Calibri", Font.BOLD, 30));
	    B3.setEnabled(false);
	    B3.setActionCommand("6");
	    
	    // Implementation of radio button for seat B4
	    B4 = new JRadioButton("B4");
        B4.setBounds(800,300,60,60);
        B4.setOpaque(false);
        B4.setForeground(Color.RED);
	    B4.setFont(new Font("Calibri", Font.BOLD, 30));
	    B4.setEnabled(false);
	    B4.setActionCommand("7");
	    
	    // Implementation of radio button for seat C1
	    C1 = new JRadioButton("C1");
        C1.setBounds(125,350,60,60);
        C1.setOpaque(false);
        C1.setForeground(Color.RED);
	    C1.setFont(new Font("Calibri", Font.BOLD, 30));
	    C1.setEnabled(false);
	    C1.setActionCommand("8");
	    
	    // Implementation of radio button for seat C2
	    C2 = new JRadioButton("C2");
        C2.setBounds(350,350,60,60);
        C2.setOpaque(false);
        C2.setForeground(Color.RED);
	    C2.setFont(new Font("Calibri", Font.BOLD, 30));
	    C2.setEnabled(false);
	    C2.setActionCommand("9");
	    
	    // Implementation of radio button for seat C3
	    C3 = new JRadioButton("C3");
        C3.setBounds(575,350,60,60);
        C3.setOpaque(false);
        C3.setForeground(Color.RED);
	    C3.setFont(new Font("Calibri", Font.BOLD, 30));
	    C3.setEnabled(false);
	    C3.setActionCommand("10");
	    
	    // Implementation of radio button for seat C4
	    C4 = new JRadioButton("C4");
        C4.setBounds(800,350,60,60);
        C4.setOpaque(false);
        C4.setForeground(Color.RED);
	    C4.setFont(new Font("Calibri", Font.BOLD, 30));
	    C4.setEnabled(false);
	    C4.setActionCommand("11");
	    
	    // Implementation of the cancel ticket button
	    cancel = new JButton("Cancel Ticket");
	    cancel.setBounds(800,20,150,30);
	    
	    // Implementation of the purchase ticket button
	    purchase = new JButton("Purchase Ticket");
	    purchase.setBounds(425,420,150,30);
	    
	    // Adding all seat radio buttons to a ButtonGroup
	    group = new ButtonGroup();
	    group.add(A1);
	    group.add(A2);
	    group.add(A3);
	    group.add(A4);
	    group.add(B1);
	    group.add(B2);
	    group.add(B3);
	    group.add(B4);
	    group.add(C1);
	    group.add(C2);
	    group.add(C3);
	    group.add(C4);
	    
	    // Adding all components to home page JPanel
		this.add(title);
        this.add(movieLabel);
        this.add(movie);
        this.add(theatreLabel);
        this.add(theatre);
        this.add(showtimeLabel);
        this.add(showtime);
        this.add(screenLabel);
        this.add(seatselectionLabel);
        this.add(A1);
        this.add(A2);
        this.add(A3);
        this.add(A4);
        this.add(B1);
        this.add(B2);
        this.add(B3);
        this.add(B4);
        this.add(C1);
        this.add(C2);
        this.add(C3);
        this.add(C4);
        this.add(cancel);
        this.add(purchase);
        
		return this;
	}
	
	
	
	// Getter and setter functions
	public JComboBox getMovie() {
		return movie;
	}



	public void setMovie(JComboBox movie) {
		this.movie = movie;
	}



	public JLabel getTitle() {
		return title;
	}


	public void setTitle(JLabel title) {
		this.title = title;
	}


	public JComboBox getTheatre() {
		return theatre;
	}


	public void setTheatre(JComboBox theatre) {
		this.theatre = theatre;
	}


	public JComboBox getShowtime() {
		return showtime;
	}


	public void setShowtime(JComboBox showtime) {
		this.showtime = showtime;
	}


	public JLabel getMovieLabel() {
		return movieLabel;
	}


	public void setMovieLabel(JLabel movieLabel) {
		this.movieLabel = movieLabel;
	}


	public JLabel getTheatreLabel() {
		return theatreLabel;
	}


	public void setTheatreLabel(JLabel theatreLabel) {
		this.theatreLabel = theatreLabel;
	}


	public JLabel getShowtimeLabel() {
		return showtimeLabel;
	}


	public void setShowtimeLabel(JLabel showtimeLabel) {
		this.showtimeLabel = showtimeLabel;
	}


	public JLabel getSeatselectionLabel() {
		return seatselectionLabel;
	}


	public void setSeatselectionLabel(JLabel seatselectionLabel) {
		this.seatselectionLabel = seatselectionLabel;
	}


	public JLabel getScreenLabel() {
		return screenLabel;
	}


	public void setScreenLabel(JLabel screenLabel) {
		this.screenLabel = screenLabel;
	}


	public JRadioButton getA1() {
		return A1;
	}


	public void setA1(JRadioButton a1) {
		A1 = a1;
	}


	public JRadioButton getA2() {
		return A2;
	}


	public void setA2(JRadioButton a2) {
		A2 = a2;
	}


	public JRadioButton getA3() {
		return A3;
	}


	public void setA3(JRadioButton a3) {
		A3 = a3;
	}


	public JRadioButton getA4() {
		return A4;
	}


	public void setA4(JRadioButton a4) {
		A4 = a4;
	}


	public JRadioButton getB1() {
		return B1;
	}


	public void setB1(JRadioButton b1) {
		B1 = b1;
	}


	public JRadioButton getB2() {
		return B2;
	}


	public void setB2(JRadioButton b2) {
		B2 = b2;
	}


	public JRadioButton getB3() {
		return B3;
	}


	public void setB3(JRadioButton b3) {
		B3 = b3;
	}


	public JRadioButton getB4() {
		return B4;
	}


	public void setB4(JRadioButton b4) {
		B4 = b4;
	}


	public JRadioButton getC1() {
		return C1;
	}


	public void setC1(JRadioButton c1) {
		C1 = c1;
	}


	public JRadioButton getC2() {
		return C2;
	}


	public void setC2(JRadioButton c2) {
		C2 = c2;
	}


	public JRadioButton getC3() {
		return C3;
	}


	public void setC3(JRadioButton c3) {
		C3 = c3;
	}


	public JRadioButton getC4() {
		return C4;
	}


	public void setC4(JRadioButton c4) {
		C4 = c4;
	}

	public JButton getCancel() {
		return cancel;
	}


	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}


	public JButton getPurchase() {
		return purchase;
	}


	public void setPurchase(JButton purchase) {
		this.purchase = purchase;
	}

    
	public ButtonGroup getGroup() {
		return group;
	}


	public void setGroup(ButtonGroup group) {
		this.group = group;
	}
	
}
