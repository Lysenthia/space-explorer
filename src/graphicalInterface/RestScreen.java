package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.CrewMember;
import backend.Planet;
import backend.Ship;
import backendGUIExtensions.CrewMemberExtended;

public class RestScreen {

	private JFrame frmRestScreen;
	private int selectedCount = 0;
	private ArrayList<CrewMember> crew = Ship.getReadyCrew();
	

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestScreen window = new RestScreen();
					window.frmRestScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RestScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<CrewMember> crew = Ship.getShipCrew();
		ArrayList<JLabel> lblImages = new ArrayList<JLabel>();
		ArrayList<JCheckBox> selectedCrew = new ArrayList<JCheckBox>();
		
		
		frmRestScreen = new JFrame();
		frmRestScreen.setResizable(false);
		frmRestScreen.setPreferredSize(new Dimension(800, 600));
		frmRestScreen.setTitle("Rest Screen");
		frmRestScreen.setBounds(100, 100, 800, 600);
		frmRestScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmRestScreen.getContentPane().setLayout(springLayout);
		
		JPanel CrewMemberPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, CrewMemberPanel, 1, SpringLayout.NORTH, frmRestScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, CrewMemberPanel, 0, SpringLayout.WEST, frmRestScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, CrewMemberPanel, 400, SpringLayout.NORTH, frmRestScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, CrewMemberPanel, 800, SpringLayout.WEST, frmRestScreen.getContentPane());
		frmRestScreen.getContentPane().add(CrewMemberPanel);
		SpringLayout sl_crewMember = new SpringLayout();
		CrewMemberPanel.setLayout(sl_crewMember);
		
		JPanel MenuPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, MenuPanel, 0, SpringLayout.SOUTH, CrewMemberPanel);
		springLayout.putConstraint(SpringLayout.WEST, MenuPanel, 0, SpringLayout.WEST, frmRestScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, MenuPanel, 571, SpringLayout.NORTH, frmRestScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, MenuPanel, 800, SpringLayout.WEST, frmRestScreen.getContentPane());
		frmRestScreen.getContentPane().add(MenuPanel);
		MenuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		MenuPanel.add(panel_4);
		springLayout.putConstraint(SpringLayout.NORTH, panel_4, 0, SpringLayout.SOUTH, CrewMemberPanel);
		springLayout.putConstraint(SpringLayout.WEST, panel_4, 0, SpringLayout.WEST, frmRestScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_4, 381, SpringLayout.NORTH, frmRestScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_4, 800, SpringLayout.WEST, frmRestScreen.getContentPane());
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
				
		JButton btnRest = new JButton("Rest a crew member");
		btnRest.setEnabled(false);
		btnRest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selectedCount == 1) {
					CrewMember member = null;
					for (int i = 0; i < selectedCrew.size(); i++) {
						JCheckBox check = selectedCrew.get(i);
						if (check.isSelected()) {
							if (member == null) {
								member = crew.get(i);
								member.sleep();
							}
						}
					}
					//Dialog box to notify player of action
					JOptionPane.showMessageDialog(frmRestScreen, String.format("Crewmember %s has rested and feels less tired!" , member.getName()));
					StatusScreen.callScreen();
					frmRestScreen.dispose();
				}
			}
		});
		panel_4.add(btnRest);
				
		//Cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatusScreen.callScreen();
				frmRestScreen.dispose();
			}
		});
		MenuPanel.add(btnCancel);
		
		frmRestScreen.pack();		
		
		for (int i = 0; i < crew.size(); i++) {
			int widthMinor = i * (frmRestScreen.getWidth() / crew.size());
			int widthMajor = (i + 1) * (frmRestScreen.getWidth() / crew.size());
			int panelWidth = frmRestScreen.getWidth() / crew.size();
			JPanel temp = new JPanel();
			sl_crewMember.putConstraint(SpringLayout.NORTH, temp, 0, SpringLayout.NORTH, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.WEST, temp, widthMinor, SpringLayout.WEST, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.SOUTH, temp, 400, SpringLayout.NORTH, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.EAST, temp, widthMajor, SpringLayout.WEST, CrewMemberPanel);
			CrewMemberPanel.add(temp);
			SpringLayout sl_temp = new SpringLayout();
			temp.setLayout(sl_temp);
			
			JLabel image = new JLabel("image");
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
			
			
			JLabel Tiredness = new JLabel("Tiredness");
			sl_temp.putConstraint(SpringLayout.NORTH, Tiredness, 0, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.WEST, Tiredness, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, Tiredness, 20, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.EAST, Tiredness, panelWidth, SpringLayout.WEST, temp);
			Tiredness.setText(String.format("Tiredness: %d", crew.get(i).getTiredness()));
			Tiredness.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(Tiredness);
			
			JCheckBox selected = new JCheckBox("Rest");
			sl_temp.putConstraint(SpringLayout.NORTH, selected, 0, SpringLayout.SOUTH, Tiredness);
			sl_temp.putConstraint(SpringLayout.WEST, selected, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, selected, 20, SpringLayout.SOUTH, Tiredness);
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
					if (selectedCount == 1) {
						btnRest.setEnabled(true);
					} else {
						btnRest.setEnabled(false);
					}
				}
			});
			temp.add(selected);
			
			
		}
		frmRestScreen.pack();
		for (int i = 0; i < lblImages.size(); i++) {
			JLabel image = lblImages.get(i);
			image.setIcon(new ImageIcon(((CrewMemberExtended)crew.get(i)).getImage().getContents(image.getWidth(), image.getHeight())));
		}
	}
}
