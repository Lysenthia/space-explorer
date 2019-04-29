package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.GameState;
import backend.Ship;
import backendGUIExtensions.PlanetExtended;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame frame;
	private String orbiting = "<html><p>Currently Orbiting: %s</p></html>";

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setTitle("Please select an action");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel planetPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, planetPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, planetPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, planetPanel, 397, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, planetPanel, 794, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(planetPanel);
		planetPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel planetsImagePanel = new JPanel();
		planetPanel.add(planetsImagePanel);
		planetsImagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel planetImage = new JLabel("Planet Image Goes Here");
		planetImage.setPreferredSize(new Dimension(240, 240));
		planetImage.setMaximumSize(new Dimension(240, 240));
		planetImage.setMinimumSize(new Dimension(240, 240));
		planetImage.setHorizontalAlignment(SwingConstants.CENTER);
		planetsImagePanel.add(planetImage);
		
		JPanel planetInfoPanel = new JPanel();
		planetPanel.add(planetInfoPanel);
		planetInfoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel orbitalStatusPanel = new JPanel();
		planetInfoPanel.add(orbitalStatusPanel);
		orbitalStatusPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPlanetName = new JLabel("<html><p>Currently Orbiting: {Planet}</p></html>");
		lblPlanetName.setHorizontalAlignment(SwingConstants.CENTER);
		orbitalStatusPanel.add(lblPlanetName);
		lblPlanetName.setBorder(new EmptyBorder(0, 50, 0, 50));
		
		JLabel lblPlanetPartFound = new JLabel("Part Found: False");
		lblPlanetPartFound.setHorizontalAlignment(SwingConstants.CENTER);
		orbitalStatusPanel.add(lblPlanetPartFound);
		
		JLabel planetDescription = new JLabel();
		planetDescription.setHorizontalAlignment(SwingConstants.CENTER);
		planetDescription.setBorder(new EmptyBorder(0, 50, 0, 50));
		planetDescription.setText("The description of the planet will go here");
		planetInfoPanel.add(planetDescription);
		
		JPanel currentStatusPanel = new JPanel();
		planetInfoPanel.add(currentStatusPanel);
		currentStatusPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCurrentPartsFound = new JLabel(String.format("Parts found: %d", GameState.getPartsFound()));
		lblCurrentPartsFound.setHorizontalAlignment(SwingConstants.CENTER);
		currentStatusPanel.add(lblCurrentPartsFound);
		lblCurrentPartsFound.setBorder(new EmptyBorder(0, 50, 0, 50));
		
		JLabel lblCurrentDay = new JLabel("Current Day: 0");
		lblCurrentDay.setHorizontalAlignment(SwingConstants.CENTER);
		currentStatusPanel.add(lblCurrentDay);
		
		JPanel endStatusPanel = new JPanel();
		planetInfoPanel.add(endStatusPanel);
		endStatusPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPartsNeeded = new JLabel("Parts Needed: 2");
		lblPartsNeeded.setHorizontalAlignment(SwingConstants.CENTER);
		endStatusPanel.add(lblPartsNeeded);
		
		JLabel lblEndDay = new JLabel("End Day: 3");
		lblEndDay.setHorizontalAlignment(SwingConstants.CENTER);
		endStatusPanel.add(lblEndDay);
		
		JPanel choicesPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, choicesPanel, 397, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, choicesPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, choicesPanel, 572, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, choicesPanel, 794, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(choicesPanel);
		choicesPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel leftOptions = new JPanel();
		choicesPanel.add(leftOptions);
		leftOptions.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnPilot = new JButton("Travel to a new Planet");
		leftOptions.add(btnPilot);
		
		JButton btnOutpost = new JButton("Go to the outpost");
		leftOptions.add(btnOutpost);
		
		JButton btnDay = new JButton("Transition to a new day");
		leftOptions.add(btnDay);
		
		JPanel rightOptions = new JPanel();
		choicesPanel.add(rightOptions);
		rightOptions.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSearch = new JButton("Search the planet");
		btnSearch.setEnabled(false);
		rightOptions.add(btnSearch);
		
		JButton btnStatus = new JButton("View Crew Members/Access Inventory");
		rightOptions.add(btnStatus);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		rightOptions.add(btnQuit);
		
		frame.pack();
		
		if (Ship.getOrbiting() == null) {
			planetImage.setIcon(new ImageIcon(PlanetExtended.getDefaultImage().getContents(planetImage.getWidth(), planetImage.getHeight())));
			lblPlanetName.setText("<html><p>In a Heliocentric Orbit</p></html>");
			lblPlanetPartFound.setText("Good luck");
			planetDescription.setText(String.format("<html><p>The %s is in a high orbit above the star</html></p>", Ship.getName()));
			btnSearch.setEnabled(false);
		} else {
			planetImage.setIcon(new ImageIcon(((PlanetExtended)Ship.getOrbiting()).getImage().getContents(planetImage.getWidth(), planetImage.getHeight())));
			lblPlanetName.setText(String.format(orbiting, Ship.getOrbiting().getName()));
			lblPlanetPartFound.setText(String.format("Part Found: %s", Ship.getOrbiting().getPartFound()));
			planetDescription.setText(String.format("<html><p>%s</html></p>", Ship.getOrbiting().getDescription()));
			btnSearch.setEnabled(true);
		}
	}

}
