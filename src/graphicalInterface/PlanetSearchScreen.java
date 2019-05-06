package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import backend.CrewMember;
import backend.Ship;

public class PlanetSearchScreen {

	private JFrame frame;
	private ArrayList<CrewMember> crew = Ship.getShipCrew();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanetSearchScreen window = new PlanetSearchScreen();
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
	public PlanetSearchScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 800, 600));
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.getContentPane().setBackground(UIManager.getColor("ColorChooser.background"));
		frame.setTitle("Planet Search Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setTitle("Planet Search Screen");
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblDescription = new JLabel("Please select a crew member to search the planet");
		springLayout.putConstraint(SpringLayout.SOUTH, lblDescription, 30, SpringLayout.NORTH, frame.getContentPane());
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblDescription, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDescription, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblDescription, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblDescription);
		
		JPanel crewMembersPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, crewMembersPanel, 0, SpringLayout.SOUTH, lblDescription);
		springLayout.putConstraint(SpringLayout.WEST, crewMembersPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, crewMembersPanel, 450, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, crewMembersPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(crewMembersPanel);
		crewMembersPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel buttonsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, buttonsPanel, 0, SpringLayout.SOUTH, crewMembersPanel);
		
		JPanel panel = new JPanel();
		crewMembersPanel.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JButton btnConfirm = new JButton("Confirm");
		buttonsPanel.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		buttonsPanel.add(btnCancel);
		
		frame.pack();
		for (CrewMember member : crew) {
			JLabel lblImage = new JLabel("Image");
			sl_panel.putConstraint(SpringLayout.NORTH, lblImage, 0, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, lblImage, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblImage, 256, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.EAST, lblImage, 800, SpringLayout.WEST, panel);
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblImage);
			
			JLabel lblName = new JLabel(String.format("Name: %s", member.getName()));
			sl_panel.putConstraint(SpringLayout.NORTH, lblName, 0, SpringLayout.SOUTH, lblImage);
			sl_panel.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblName, 40, SpringLayout.SOUTH, lblImage);
			sl_panel.putConstraint(SpringLayout.EAST, lblName, 800, SpringLayout.WEST, panel);
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblName);
			
			JLabel lblClass = new JLabel(String.format("Class: %s", member.getMemberClass()));
			sl_panel.putConstraint(SpringLayout.NORTH, lblClass, 0, SpringLayout.SOUTH, lblName);
			sl_panel.putConstraint(SpringLayout.WEST, lblClass, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblClass, 40, SpringLayout.SOUTH, lblName);
			sl_panel.putConstraint(SpringLayout.EAST, lblClass, 800, SpringLayout.WEST, panel);
			lblClass.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblClass);
			
			JLabel lblActionPoints = new JLabel("AP: 2");
			sl_panel.putConstraint(SpringLayout.NORTH, lblActionPoints, 0, SpringLayout.SOUTH, lblClass);
			sl_panel.putConstraint(SpringLayout.WEST, lblActionPoints, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblActionPoints, 40, SpringLayout.SOUTH, lblClass);
			sl_panel.putConstraint(SpringLayout.EAST, lblActionPoints, 800, SpringLayout.WEST, panel);
			lblActionPoints.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblActionPoints);
			
			JRadioButton rdbtnSelected = new JRadioButton("");
			sl_panel.putConstraint(SpringLayout.NORTH, rdbtnSelected, 0, SpringLayout.SOUTH, lblActionPoints);
			sl_panel.putConstraint(SpringLayout.WEST, rdbtnSelected, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, rdbtnSelected, 40, SpringLayout.SOUTH, lblActionPoints);
			sl_panel.putConstraint(SpringLayout.EAST, rdbtnSelected, 800, SpringLayout.WEST, panel);
			buttonGroup.add(rdbtnSelected);
			rdbtnSelected.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(rdbtnSelected);
			springLayout.putConstraint(SpringLayout.WEST, buttonsPanel, 0, SpringLayout.WEST, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, buttonsPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, buttonsPanel, 0, SpringLayout.EAST, frame.getContentPane());
			frame.getContentPane().add(buttonsPanel);
			buttonsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		}
	}
}
