package graphicalInterface;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MainScreen {

	private JFrame frmPleaseSelectAn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmPleaseSelectAn.setVisible(true);
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
		frmPleaseSelectAn = new JFrame();
		frmPleaseSelectAn.setTitle("Please select an action");
		frmPleaseSelectAn.setResizable(false);
		frmPleaseSelectAn.setBounds(100, 100, 800, 600);
		frmPleaseSelectAn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPleaseSelectAn.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel planetPanel = new JPanel();
		frmPleaseSelectAn.getContentPane().add(planetPanel);
		planetPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel planetsImagePanel = new JPanel();
		planetPanel.add(planetsImagePanel);
		planetsImagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel planetImage = new JLabel("Planet Image Goes Here");
		planetsImagePanel.add(planetImage);
		
		JPanel planetInfoPanel = new JPanel();
		planetPanel.add(planetInfoPanel);
		planetInfoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblPlanetName = new JLabel("Currently Orbiting: {Planet}");
		lblPlanetName.setBorder(new EmptyBorder(0, 50, 0, 50));
		planetInfoPanel.add(lblPlanetName);
		
		JTextArea planetDescription = new JTextArea();
		planetDescription.setBorder(new EmptyBorder(0, 50, 0, 50));
		planetDescription.setText("The description of the planet will go here");
		planetDescription.setBackground(UIManager.getColor("windowBorder"));
		planetInfoPanel.add(planetDescription);
		
		JLabel lblPartFound = new JLabel("Part found: {Boolean}");
		lblPartFound.setBorder(new EmptyBorder(0, 50, 0, 50));
		planetInfoPanel.add(lblPartFound);
		
		JPanel choicesPanel = new JPanel();
		frmPleaseSelectAn.getContentPane().add(choicesPanel);
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
		rightOptions.add(btnSearch);
		
		JButton btnStatus = new JButton("View Crew Members/Access Inventory");
		rightOptions.add(btnStatus);
		
		JButton btnQuit = new JButton("Quit");
		rightOptions.add(btnQuit);
	}

}
