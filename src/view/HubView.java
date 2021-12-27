package view;

import javax.swing.*;
import java.awt.*;

/**
 * GUI interface responsible for guiding the view through all component views
 * @author Deylin Yiao & Maliha Zakir
 * @since December 7, 2021
 */
@SuppressWarnings("serial")
public class HubView extends JFrame{
	
	private JLayeredPane jLayerPane;
	
	/**
	 * Initial build method to be called by hub manager, sets up the JFrame and background image
	 */
	public void initialBuild() {
		jLayerPane = new JLayeredPane();
		setTitle("Movie Theatre Application");
		setBounds(0, 0, 1200, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		drawBackground(jLayerPane);
		drawHeader(jLayerPane);
		add(jLayerPane);
		setVisible(true);
	}
	
	/**
	 * Constructs the header JPanel for the hub view
	 * @param jLayerPane JLayerPane Object
	 */
	public void drawHeader(JLayeredPane jLayerPane) {
		JPanel headerPanel = new JPanel();
		JLabel header = new JLabel("~ Movie Theatre Application ~");
		header.setFont(new Font("Serif", Font.BOLD, 35));
		header.setForeground(Color.WHITE);
		header.setBounds(400, 0, 1200, 50);
		headerPanel.setBounds(0, 0, 1200, 60);
		headerPanel.setLayout(null);
		headerPanel.setBackground(Color.BLACK);
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, Color.YELLOW));
		headerPanel.add(header);
		jLayerPane.add(headerPanel, JLayeredPane.PALETTE_LAYER);
	}
	
	
	/**
	 * Constructs the background JPanel for the hub view
	 * @param jLayerPane JLayerPane Object
	 */
	public void drawBackground(JLayeredPane jLayerPane) {
		JPanel backgroundPanel = new JPanel();
		JLabel background;
		backgroundPanel.setBounds(0, 0, 1200, 600);
		backgroundPanel.setLayout(null);
		ImageIcon img = new ImageIcon("asset/blackstar.jpg");
		background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0,0,1200,600);
		backgroundPanel.add(background);
		jLayerPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
	}
	
	/**
	 * Removes the previous view component
	 */
	public void refresh() {
		jLayerPane.remove(0);
	}
	
	/**
	 * Adds the following view component into hub view
	 * @param panel JPanel object of view component
	 */
	public void addComponent(JPanel panel) {
		jLayerPane.add(panel, JLayeredPane.MODAL_LAYER);
	}
	
	// Getter and setter methods
	public JLayeredPane getjLayerPane() {
		return jLayerPane;
	}

	public void setjLayerPane(JLayeredPane jLayerPane) {
		this.jLayerPane = jLayerPane;
	}
	
	
	
}
