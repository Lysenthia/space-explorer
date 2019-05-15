package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.CrewMember;
import backend.GameState;
import backend.Planet;
import backend.Ship;
import backendGUIExtensions.CrewMemberExtended;
import backendGUIExtensions.PlanetExtended;

public class PilotScreen {

	private JFrame frmPleaseSelect;
	private int selectedCount = 0;
	private ArrayList<CrewMember> crew = Ship.getReadyCrew();
	
	private void pilotShip(CrewMember p1, CrewMember p2, Planet planet) {
		Ship.pilot(p1, p2, planet);
	}

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PilotScreen window = new PilotScreen();
					window.frmPleaseSelect.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PilotScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<JCheckBox> selectedCrew = new ArrayList<JCheckBox>();
		ArrayList<Planet> planetsList = GameState.getPlanets();
		planetsList.remove((PlanetExtended)Ship.getOrbiting());
		ButtonGroup radioButtons = new ButtonGroup();
		ArrayList<JLabel> lblImages = new ArrayList<JLabel>();
		
		frmPleaseSelect = new JFrame();
		frmPleaseSelect.setTitle("Please select 2 crew members and a destination");
		frmPleaseSelect.setResizable(false);
		frmPleaseSelect.getContentPane().setPreferredSize(new Dimension(800, 600));
		frmPleaseSelect.setPreferredSize(new Dimension(800, 600));
		frmPleaseSelect.setAlwaysOnTop(true);
		frmPleaseSelect.setBounds(100, 100, 800, 600);
		frmPleaseSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmPleaseSelect.getContentPane().setLayout(springLayout);
		
		JPanel pilots = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pilots, 1, SpringLayout.NORTH, frmPleaseSelect.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pilots, 0, SpringLayout.WEST, frmPleaseSelect.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pilots, 316, SpringLayout.NORTH, frmPleaseSelect.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pilots, 800, SpringLayout.WEST, frmPleaseSelect.getContentPane());
		frmPleaseSelect.getContentPane().add(pilots);
		SpringLayout sl_pilots = new SpringLayout();
		pilots.setLayout(sl_pilots);
		
		
		
		JPanel planets = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, planets, 0, SpringLayout.SOUTH, pilots);
		springLayout.putConstraint(SpringLayout.WEST, planets, 0, SpringLayout.WEST, frmPleaseSelect.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, planets, 150, SpringLayout.SOUTH, pilots);
		springLayout.putConstraint(SpringLayout.EAST, planets, 800, SpringLayout.WEST, frmPleaseSelect.getContentPane());
		frmPleaseSelect.getContentPane().add(planets);
		planets.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel buttonPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 0, SpringLayout.SOUTH, planets);
		
		JPanel planetsInternal = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(planetsInternal);
		planetsInternal.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		planets.add(scrollPane);
		
		springLayout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, frmPleaseSelect.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonPanel, 0, SpringLayout.SOUTH, frmPleaseSelect.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonPanel, 800, SpringLayout.WEST, frmPleaseSelect.getContentPane());
		frmPleaseSelect.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedCount == 2) {
					CrewMember pilot1 = null;
					CrewMember pilot2 = null;
					for (int i = 0; i < selectedCrew.size(); i++) {
						JCheckBox check = selectedCrew.get(i);
						if (check.isSelected()) {
							if (pilot1 == null) {
								pilot1 = crew.get(i);
							} else if (pilot2 == null) {
								pilot2 = crew.get(i);
							}
						}
					}
					String selection = radioButtons.getSelection().getActionCommand();
					Planet planet = planetsList.get(Integer.parseInt(selection));
					pilotShip(pilot1, pilot2, planet);
					MainScreen.callScreen();
					frmPleaseSelect.dispose();
				}
			}
		});
		buttonPanel.add(confirm);
		confirm.setEnabled(false);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.callScreen();
				frmPleaseSelect.dispose();
			}
		});
		buttonPanel.add(cancel);

		frmPleaseSelect.pack();
		
		//Populate Crew Members
		for (int i = 0; i < crew.size(); i++) {
			int widthMinor = i * (frmPleaseSelect.getWidth() / crew.size());
			int widthMajor = (i + 1) * (frmPleaseSelect.getWidth() / crew.size());
			int panelWidth = frmPleaseSelect.getWidth() / crew.size();
			JPanel temp = new JPanel();
			sl_pilots.putConstraint(SpringLayout.NORTH, temp, 0, SpringLayout.NORTH, pilots);
			sl_pilots.putConstraint(SpringLayout.WEST, temp, widthMinor, SpringLayout.WEST, pilots);
			sl_pilots.putConstraint(SpringLayout.SOUTH, temp, 316, SpringLayout.NORTH, pilots);
			sl_pilots.putConstraint(SpringLayout.EAST, temp, widthMajor, SpringLayout.WEST, pilots);
			pilots.add(temp);
			SpringLayout sl_temp = new SpringLayout();
			temp.setLayout(sl_temp);
			
			JLabel image = new JLabel();
			sl_temp.putConstraint(SpringLayout.NORTH, image, 1, SpringLayout.NORTH, temp);
			sl_temp.putConstraint(SpringLayout.WEST, image, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, image, 256, SpringLayout.NORTH, temp);
			sl_temp.putConstraint(SpringLayout.EAST, image, panelWidth, SpringLayout.WEST, temp);
			image.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(image);
			lblImages.add(image);
			
			JLabel name = new JLabel("name");
			sl_temp.putConstraint(SpringLayout.NORTH, name, 0, SpringLayout.SOUTH, image);
			sl_temp.putConstraint(SpringLayout.WEST, name, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, name, 20, SpringLayout.SOUTH, image);
			sl_temp.putConstraint(SpringLayout.EAST, name, panelWidth, SpringLayout.WEST, temp);
			name.setHorizontalAlignment(SwingConstants.CENTER);
			name.setText(String.format("Name: %s", crew.get(i).getName()));
			temp.add(name);
			
			JLabel actionPoints = new JLabel("actionPoints");
			sl_temp.putConstraint(SpringLayout.NORTH, actionPoints, 0, SpringLayout.SOUTH, name);
			sl_temp.putConstraint(SpringLayout.WEST, actionPoints, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, actionPoints, 20, SpringLayout.SOUTH, name);
			sl_temp.putConstraint(SpringLayout.EAST, actionPoints, panelWidth, SpringLayout.WEST, temp);
			actionPoints.setText(String.format("AP: %d", crew.get(i).getActionPoints()));
			actionPoints.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(actionPoints);
			
			JCheckBox selected = new JCheckBox("Pilot");
			sl_temp.putConstraint(SpringLayout.NORTH, selected, 0, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.WEST, selected, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, selected, 20, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.EAST, selected, panelWidth, SpringLayout.WEST, temp);
			selected.setHorizontalAlignment(SwingConstants.CENTER);
			selectedCrew.add(selected);
			selected.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if (event.getStateChange() == ItemEvent.SELECTED) {
						selectedCount += 1;
					} else if (event.getStateChange() == ItemEvent.DESELECTED) {
						selectedCount -= 1;
					}
					if (selectedCount == 2) {
						confirm.setEnabled(true);
					} else {
						confirm.setEnabled(false);
					}
				}
			});
			temp.add(selected);
		}
		frmPleaseSelect.pack();
		for (int i = 0; i < lblImages.size(); i++) {
			JLabel image = lblImages.get(i);
			image.setIcon(new ImageIcon(((CrewMemberExtended)crew.get(i)).getImage().getContents(image.getWidth(), -1)));
		}
		
		//Populate planets
		for (int i = 0; i < planetsList.size(); i++) {
			Planet planet = planetsList.get(i);
			String message = String.format("Name: %-20s Part found: %b", planet.getName(), planet.getPartFound());
			JRadioButton planetRadio = new JRadioButton(message);
			planetRadio.setHorizontalAlignment(SwingConstants.CENTER);
			planetRadio.setActionCommand(Integer.toString(i));
			if (i == 0) {
				planetRadio.setSelected(true);
			}
			planetsInternal.add(planetRadio);
			radioButtons.add(planetRadio);
		}
		frmPleaseSelect.pack();
		
	}
}
