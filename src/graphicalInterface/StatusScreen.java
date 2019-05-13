package graphicalInterface;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.CrewMember;
import backend.Ship;
import backendGUIExtensions.CrewMemberExtended;
import java.awt.Dimension;

public class StatusScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusScreen window = new StatusScreen();
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
	public StatusScreen() {
		initialize();
		//ArrayList<CrewMember> crew = Ship.getShipCrew();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<CrewMember> crew = Ship.getShipCrew();
		ArrayList<JLabel> lblImages = new ArrayList<JLabel>();
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setTitle("StatusScreen");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel CrewMemberPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, CrewMemberPanel, 1, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, CrewMemberPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, CrewMemberPanel, 400, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, CrewMemberPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(CrewMemberPanel);
		SpringLayout sl_crewMember = new SpringLayout();
		CrewMemberPanel.setLayout(sl_crewMember);
		
		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 0, SpringLayout.SOUTH, CrewMemberPanel);
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, 571, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_3, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		springLayout.putConstraint(SpringLayout.NORTH, panel_4, 0, SpringLayout.SOUTH, CrewMemberPanel);
		springLayout.putConstraint(SpringLayout.WEST, panel_4, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_4, 381, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_4, 800, SpringLayout.WEST, frame.getContentPane());
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
				
		JButton btnNewButton = new JButton("Use an item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UseItemScreen.callScreen();
				frame.dispose();
			}
		});
		panel_4.add(btnNewButton);
				
		JButton btnNewButton_1 = new JButton("Rest a crew member");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RestScreen.callScreen();
				frame.dispose();
			}
		});
		panel_4.add(btnNewButton_1);
			
		JButton btnRepairShields = new JButton("Repair shields");
		btnRepairShields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RepairShields.callScreen();
				frame.dispose();
			}
		});
		panel_4.add(btnRepairShields);
				
		//Cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainScreen.callScreen();
				frame.dispose();
			}
		});
		panel_3.add(btnCancel);
		
		frame.pack();		
		
		for (int i = 0; i < crew.size(); i++) {
			int widthMinor = i * (frame.getWidth() / crew.size());
			int widthMajor = (i + 1) * (frame.getWidth() / crew.size());
			int panelWidth = frame.getWidth() / crew.size();
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
			
			JLabel health = new JLabel("health");
			sl_temp.putConstraint(SpringLayout.NORTH, health, 0, SpringLayout.SOUTH, name);
			sl_temp.putConstraint(SpringLayout.WEST, health, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, health, 20, SpringLayout.SOUTH, name);
			sl_temp.putConstraint(SpringLayout.EAST, health, panelWidth, SpringLayout.WEST, temp);
			health.setText(String.format("Health: %d", crew.get(i).getHealth()));
			health.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(health);
			
			JLabel actionPoints = new JLabel("actionPoints");
			sl_temp.putConstraint(SpringLayout.NORTH, actionPoints, 0, SpringLayout.SOUTH, health);
			sl_temp.putConstraint(SpringLayout.WEST, actionPoints, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, actionPoints, 20, SpringLayout.SOUTH, health);
			sl_temp.putConstraint(SpringLayout.EAST, actionPoints, panelWidth, SpringLayout.WEST, temp);
			actionPoints.setText(String.format("AP: %d", crew.get(i).getActionPoints()));
			actionPoints.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(actionPoints);
			
			JLabel Hunger = new JLabel("Hunger");
			sl_temp.putConstraint(SpringLayout.NORTH, Hunger, 0, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.WEST, Hunger, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, Hunger, 20, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.EAST, Hunger, panelWidth, SpringLayout.WEST, temp);
			Hunger.setText(String.format("Hunger: %d", crew.get(i).getHunger()));
			Hunger.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(Hunger);
			
			JLabel Tiredness = new JLabel("Tiredness");
			sl_temp.putConstraint(SpringLayout.NORTH, Tiredness, 0, SpringLayout.SOUTH, Hunger);
			sl_temp.putConstraint(SpringLayout.WEST, Tiredness, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, Tiredness, 20, SpringLayout.SOUTH, Hunger);
			sl_temp.putConstraint(SpringLayout.EAST, Tiredness, panelWidth, SpringLayout.WEST, temp);
			Tiredness.setText(String.format("Tiredness: %d", crew.get(i).getTiredness()));
			Tiredness.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(Tiredness);
			
			JLabel SpacePlagueStatus = new JLabel("SpacePlagueStatus");
			sl_temp.putConstraint(SpringLayout.NORTH, SpacePlagueStatus, 0, SpringLayout.SOUTH, Tiredness);
			sl_temp.putConstraint(SpringLayout.WEST, SpacePlagueStatus, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, SpacePlagueStatus, 20, SpringLayout.SOUTH, Tiredness);
			sl_temp.putConstraint(SpringLayout.EAST, SpacePlagueStatus, panelWidth, SpringLayout.WEST, temp);
			SpacePlagueStatus.setText(String.format("Has Space Plague: %b", crew.get(i).hasSpacePlague()));
			SpacePlagueStatus.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(SpacePlagueStatus);
			
		}
		frame.pack();
		for (int i = 0; i < lblImages.size(); i++) {
			JLabel image = lblImages.get(i);
			image.setIcon(new ImageIcon(((CrewMemberExtended)crew.get(i)).getImage().getContents(image.getHeight())));
		}
	}
	
	
	

}
