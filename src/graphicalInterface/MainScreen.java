package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.CrewMember;
import backend.GameState;
import backend.PossibleEndings;
import backend.Ship;
import backendGUIExtensions.PlanetExtended;
import backendGUIExtensions.SaveGame;

public class MainScreen {

	private JFrame frame;
	private String orbiting = "<html><p>Currently Orbiting: %s</p></html>";
	private ArrayList<CrewMember> readyCrew = Ship.getReadyCrew();

	private static void saveGame() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Save files", "ses");
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		int selection = chooser.showSaveDialog(null);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			SaveGame save = new SaveGame(selectedFile.toPath());
			try {
				save.save();
			} catch (IOException e) {
				String ObjButtons[] = {"Continue"};
				JOptionPane.showOptionDialog(null,"There was an error while trying to save the game","Save Error",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[0]);
			}
			finally {
				MainScreen.callScreen();
			}
		}
	}
	
	private static void loadGame() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Save files", "ses");
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		int selection = chooser.showOpenDialog(null);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			SaveGame save = new SaveGame(selectedFile.toPath());
			try {
				save.load();
			} catch (IOException e) {
				String ObjButtons[] = {"Continue"};
				JOptionPane.showOptionDialog(null,"There was an error while trying to load a save","Load Error",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[0]);
			}
			finally {
				MainScreen.callScreen();
			}
		}
	}
	
	private static boolean gameFinished() {
		boolean finished = false;
		 if (GameState.getPartsFound() == GameState.getPartsNeeded()) {
				GameState.setEnding(PossibleEndings.VICTORY);
				finished = true;
		 } else if (Ship.getShipCrew().size() == 0) {
			GameState.setEnding(PossibleEndings.CREW_DEAD);
			finished = true;
		} else if (Ship.getShipCrew().size() == 1) {
			GameState.setEnding(PossibleEndings.LOST_IN_SPACE);
			finished = true;
		} else if (Ship.getShields() <= 0) {
			GameState.setEnding(PossibleEndings.SHIP_DESTROYED);
			finished = true;
		}
		return finished;
	}
	
	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (gameFinished()) {
						EndingScreen.callScreen();
					} else {
						MainScreen window = new MainScreen();
						window.frame.setVisible(true);
					}
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
		//TODO Remove ability to select actions if no ready crew members
		
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
		
		JLabel planetImage = new JLabel("");
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
		
		JLabel lblPlanetPartFound = new JLabel("Part found on planet goes here");
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
		btnPilot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PilotScreen.callScreen();
				frame.dispose();
			}
		});
		leftOptions.add(btnPilot);
		
		JButton btnOutpost = new JButton("Go to the outpost");
		btnOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OutpostScreen.callScreen();
				frame.dispose();
			}
		});
		leftOptions.add(btnOutpost);
		
		JButton btnDay = new JButton("Transition to a new day");
		btnDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransitionDayScreen.callScreen();
				frame.dispose();
			}
		});
		
		JButton btnSave = new JButton("Save Game");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				saveGame();
			}
		});
		leftOptions.add(btnSave);
		leftOptions.add(btnDay);
		
		JPanel rightOptions = new JPanel();
		choicesPanel.add(rightOptions);
		rightOptions.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSearch = new JButton("Search the planet");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlanetSearchScreen.callScreen();
				frame.dispose();
			}
		});
		btnSearch.setEnabled(false);
		rightOptions.add(btnSearch);
		
		JButton btnStatus = new JButton("View Crew Members/Access Inventory");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatusScreen.callScreen();
				frame.dispose();
			}
		});
		rightOptions.add(btnStatus);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameState.setEnding(PossibleEndings.QUIT);
				EndingScreen.callScreen();
				frame.dispose();
			}
		});
		
		JButton btnLoad = new JButton("Load Game");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				loadGame();
			}
		});
		rightOptions.add(btnLoad);
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
		
		if (readyCrew.size() < 2) {
			btnPilot.setEnabled(false);
			btnPilot.setText("Not enough crew with AP to pilot the ship");
			if (readyCrew.size() < 1) {
				btnSearch.setEnabled(false);
				btnSearch.setText("Not enough crew with AP to search the planet");
			}
		}
	}

}
