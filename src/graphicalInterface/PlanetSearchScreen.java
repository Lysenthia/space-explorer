package graphicalInterface;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import backend.CrewMember;
import backend.GameState;
import backend.Planet;
import backend.PlanetSearchOutput;
import backend.Ship;
import backendGUIExtensions.CrewMemberExtended;

public class PlanetSearchScreen {

	private JFrame frame;
	private ArrayList<CrewMember> crew = Ship.getReadyCrew();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ArrayList<JLabel> crewMemberImages = new ArrayList<JLabel>();

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
		frame.setBounds(100, 100, 800, 600);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.getContentPane().setBackground(UIManager.getColor("ColorChooser.background"));
		frame.setTitle("Planet Search Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setTitle("Planet Search Screen");
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setPreferredSize(new Dimension(800, 600));
		selectionPanel.setBounds(100, 100, 800, 600);
		frame.getContentPane().add(selectionPanel, "name_10628420325591");
		SpringLayout sl_selection = new SpringLayout();
		selectionPanel.setLayout(sl_selection);
		
		JLabel lblDescription = new JLabel("Please select a crew member to search the planet");
		sl_selection.putConstraint(SpringLayout.NORTH, lblDescription, 0, SpringLayout.NORTH, selectionPanel);
		sl_selection.putConstraint(SpringLayout.WEST, lblDescription, 0, SpringLayout.WEST, selectionPanel);
		sl_selection.putConstraint(SpringLayout.SOUTH, lblDescription, 30, SpringLayout.NORTH, selectionPanel);
		sl_selection.putConstraint(SpringLayout.EAST, lblDescription, 0, SpringLayout.EAST, selectionPanel);
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		selectionPanel.add(lblDescription);
		
		JPanel crewMembersPanel = new JPanel();
		sl_selection.putConstraint(SpringLayout.NORTH, crewMembersPanel, 0, SpringLayout.SOUTH, lblDescription);
		sl_selection.putConstraint(SpringLayout.WEST, crewMembersPanel, 0, SpringLayout.WEST, selectionPanel);
		sl_selection.putConstraint(SpringLayout.SOUTH, crewMembersPanel, 400, SpringLayout.SOUTH, lblDescription);
		sl_selection.putConstraint(SpringLayout.EAST, crewMembersPanel, 0, SpringLayout.EAST, selectionPanel);
		selectionPanel.add(crewMembersPanel);
		SpringLayout sl_crewMembersPanel = new SpringLayout();
		crewMembersPanel.setLayout(sl_crewMembersPanel);
		
		JPanel buttonsPanel = new JPanel();
		sl_selection.putConstraint(SpringLayout.NORTH, buttonsPanel, 0, SpringLayout.SOUTH, crewMembersPanel);
		sl_selection.putConstraint(SpringLayout.WEST, buttonsPanel, 0, SpringLayout.WEST, selectionPanel);
		sl_selection.putConstraint(SpringLayout.SOUTH, buttonsPanel, 0, SpringLayout.SOUTH, selectionPanel);
		sl_selection.putConstraint(SpringLayout.EAST, buttonsPanel, 0, SpringLayout.EAST, selectionPanel);
		buttonsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		selectionPanel.add(buttonsPanel);
		
		JButton btnConfirm = new JButton("Confirm");
		buttonsPanel.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.callScreen();
				frame.dispose();
			}
		});
		buttonsPanel.add(btnCancel);
		
		JPanel searchResultsPanel = new JPanel();
		frame.getContentPane().add(searchResultsPanel, "name_10634435419587");
		SpringLayout sl_searchResultsPanel = new SpringLayout();
		searchResultsPanel.setLayout(sl_searchResultsPanel);
		
		JPanel resultPanel = new JPanel();
		sl_searchResultsPanel.putConstraint(SpringLayout.NORTH, resultPanel, 0, SpringLayout.NORTH, searchResultsPanel);
		sl_searchResultsPanel.putConstraint(SpringLayout.WEST, resultPanel, 0, SpringLayout.WEST, searchResultsPanel);
		sl_searchResultsPanel.putConstraint(SpringLayout.SOUTH, resultPanel, 400, SpringLayout.NORTH, searchResultsPanel);
		sl_searchResultsPanel.putConstraint(SpringLayout.EAST, resultPanel, 0, SpringLayout.EAST, searchResultsPanel);
		searchResultsPanel.add(resultPanel);
		
		JButton btnSearchConfirm = new JButton("Confirm");
		btnSearchConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlanetSearchScreen.callScreen();
				frame.dispose();
			}
		});
		sl_searchResultsPanel.putConstraint(SpringLayout.NORTH, btnSearchConfirm, 0, SpringLayout.SOUTH, resultPanel);
		resultPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTitle = new JLabel("Search Results:");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		resultPanel.add(lblTitle);
		
		JLabel lblFoundType = new JLabel("Found type");
		lblFoundType.setHorizontalAlignment(SwingConstants.CENTER);
		resultPanel.add(lblFoundType);
		
		JLabel lblFoundDescription = new JLabel("Found description");
		lblFoundDescription.setHorizontalAlignment(SwingConstants.CENTER);
		resultPanel.add(lblFoundDescription);
		sl_searchResultsPanel.putConstraint(SpringLayout.WEST, btnSearchConfirm, 0, SpringLayout.WEST, searchResultsPanel);
		sl_searchResultsPanel.putConstraint(SpringLayout.SOUTH, btnSearchConfirm, 0, SpringLayout.SOUTH, searchResultsPanel);
		sl_searchResultsPanel.putConstraint(SpringLayout.EAST, btnSearchConfirm, 0, SpringLayout.EAST, searchResultsPanel);
		searchResultsPanel.add(btnSearchConfirm);
		
		frame.pack();
		if (crew.size() == 0) {
			btnConfirm.setEnabled(false);
		}
		
		for (int i = 0; i < crew.size(); i++) {
			int widthMinor = i * (frame.getWidth() / crew.size());
			int widthMajor = (i + 1) * (frame.getWidth() / crew.size());
			int panelWidth = frame.getWidth() / crew.size();
			
			CrewMember member = crew.get(i);
			JPanel panel = new JPanel();
			crewMembersPanel.add(panel);
			SpringLayout sl_panel = new SpringLayout();
			panel.setLayout(sl_panel);
			sl_crewMembersPanel.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, crewMembersPanel);
			sl_crewMembersPanel.putConstraint(SpringLayout.WEST, panel, widthMinor, SpringLayout.WEST, crewMembersPanel);
			sl_crewMembersPanel.putConstraint(SpringLayout.SOUTH, panel, 400, SpringLayout.NORTH, crewMembersPanel);
			sl_crewMembersPanel.putConstraint(SpringLayout.EAST, panel, widthMajor, SpringLayout.WEST, crewMembersPanel);
			
			JLabel lblImage = new JLabel();
			sl_panel.putConstraint(SpringLayout.NORTH, lblImage, 0, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, lblImage, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblImage, 256, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.EAST, lblImage, panelWidth, SpringLayout.WEST, panel);
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			crewMemberImages.add(lblImage);
			panel.add(lblImage);
			
			JLabel lblName = new JLabel(String.format("Name: %s", member.getName()));
			sl_panel.putConstraint(SpringLayout.NORTH, lblName, 0, SpringLayout.SOUTH, lblImage);
			sl_panel.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblName, 35, SpringLayout.SOUTH, lblImage);
			sl_panel.putConstraint(SpringLayout.EAST, lblName, panelWidth, SpringLayout.WEST, panel);
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblName);
			
			JLabel lblClass = new JLabel(String.format("Class: %s", member.getMemberClass()));
			sl_panel.putConstraint(SpringLayout.NORTH, lblClass, 0, SpringLayout.SOUTH, lblName);
			sl_panel.putConstraint(SpringLayout.WEST, lblClass, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblClass, 35, SpringLayout.SOUTH, lblName);
			sl_panel.putConstraint(SpringLayout.EAST, lblClass, panelWidth, SpringLayout.WEST, panel);
			lblClass.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblClass);
			
			JLabel lblActionPoints = new JLabel(String.format("AP: %s", member.getActionPoints()));
			sl_panel.putConstraint(SpringLayout.NORTH, lblActionPoints, 0, SpringLayout.SOUTH, lblClass);
			sl_panel.putConstraint(SpringLayout.WEST, lblActionPoints, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, lblActionPoints, 35, SpringLayout.SOUTH, lblClass);
			sl_panel.putConstraint(SpringLayout.EAST, lblActionPoints, panelWidth, SpringLayout.WEST, panel);
			lblActionPoints.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblActionPoints);
			
			JRadioButton rdbtnSelected = new JRadioButton("");
			sl_panel.putConstraint(SpringLayout.NORTH, rdbtnSelected, 0, SpringLayout.SOUTH, lblActionPoints);
			sl_panel.putConstraint(SpringLayout.WEST, rdbtnSelected, 0, SpringLayout.WEST, panel);
			sl_panel.putConstraint(SpringLayout.SOUTH, rdbtnSelected, 35, SpringLayout.SOUTH, lblActionPoints);
			sl_panel.putConstraint(SpringLayout.EAST, rdbtnSelected, panelWidth, SpringLayout.WEST, panel);
			rdbtnSelected.setActionCommand(Integer.toString(i));
			buttonGroup.add(rdbtnSelected);
			rdbtnSelected.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(rdbtnSelected);
			if (i == 0) {
				rdbtnSelected.setSelected(true);
			}
		}
		frame.pack();
		for (int i = 0; i < crewMemberImages.size(); i++) {
			JLabel lblImage = crewMemberImages.get(i);
			lblImage.setIcon(new ImageIcon(((CrewMemberExtended)crew.get(i)).getImage().getContents(lblImage.getWidth(), lblImage.getHeight())));
		}
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO Complete implementation
				String selected = buttonGroup.getSelection().getActionCommand();
				Planet planet = Ship.getOrbiting();
				CrewMember member = crew.get(Integer.parseInt(selected));
				PlanetSearchOutput found = member.searchPlanet(planet);
				switch (found.FOUND) {
				case ITEM:
					lblFoundType.setText(String.format("%s found a %s", member.getName(), found.ITEM.getName()));
					lblFoundDescription.setText(String.format("The number of %s held is now %d", found.ITEM.getName(), found.ITEM.getHeld()));
					break;
				case MONEY:
					lblFoundType.setText(String.format("%s found %d credits", member.getName(), found.MONEY));
					lblFoundDescription.setText(String.format("The number of credits held is now %d", Ship.getMoney()));
					break;
				case NOTHING:
					lblFoundType.setText(String.format("%s found nothing", member.getName()));
					lblFoundDescription.setText("Now that's just plain sad");
					break;
				case PART:
					lblFoundType.setText(String.format("%s a part for the %s's hyperdrive!", member.getName(), Ship.getName()));
					int parts = GameState.getPartsNeeded() - GameState.getPartsFound();
					if (parts == 0) {
						lblFoundDescription.setText("Congratulations! You found all the parts needed to fix the hyperdrive!");
					} else {
						lblFoundDescription.setText(String.format("Remaining parts needed: %d", parts));
					}
					break;
				}
				CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
				layout.last(frame.getContentPane());
			}
		});
	}
}
