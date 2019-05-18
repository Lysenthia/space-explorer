package graphicalInterface;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.CrewMember;
import backend.Ship;
import backendGUIExtensions.CrewMemberExtended;
import java.awt.Font;

/**
 * GUI for the repair shields screen
 * Allows the player to select a crew member to repair the ship's shields
 * @author hoo42
 * @author rvo16
 *
 */
public class RepairShields {

	private JFrame frmRepairShields;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RepairShields window = new RepairShields();
					window.frmRepairShields.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RepairShields() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<CrewMember> crew = Ship.getReadyCrew();
		ArrayList<JLabel> lblImages = new ArrayList<JLabel>();
		
		
		frmRepairShields = new JFrame();
		frmRepairShields.setResizable(false);
		frmRepairShields.setPreferredSize(new Dimension(800, 600));
		frmRepairShields.setTitle("Repair Shields Screen");
		frmRepairShields.setBounds(100, 100, 800, 600);
		frmRepairShields.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmRepairShields.getContentPane().setLayout(springLayout);
		
		JPanel InfoPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, InfoPanel, 0, SpringLayout.NORTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, InfoPanel, 0, SpringLayout.WEST, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, InfoPanel, -521, SpringLayout.SOUTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, InfoPanel, 0, SpringLayout.EAST, frmRepairShields.getContentPane());
		frmRepairShields.getContentPane().add(InfoPanel);
		InfoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel(String.format("Ship name: %s", Ship.getName()));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		InfoPanel.add(lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 53, SpringLayout.NORTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 86, SpringLayout.WEST, frmRepairShields.getContentPane());
		
		JLabel lblNewLabel_1 = new JLabel(String.format("Ship Shields: %d", Ship.getShields()));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		InfoPanel.add(lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 26, SpringLayout.NORTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 345, SpringLayout.WEST, frmRepairShields.getContentPane());
		
		JPanel CrewMemberPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, CrewMemberPanel, 51, SpringLayout.NORTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, CrewMemberPanel, 0, SpringLayout.WEST, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, CrewMemberPanel, 800, SpringLayout.WEST, frmRepairShields.getContentPane());
		frmRepairShields.getContentPane().add(CrewMemberPanel);
		SpringLayout sl_crewMember = new SpringLayout();
		CrewMemberPanel.setLayout(sl_crewMember);
		
		JPanel MenuPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, CrewMemberPanel, 0, SpringLayout.NORTH, MenuPanel);
		springLayout.putConstraint(SpringLayout.NORTH, MenuPanel, 400, SpringLayout.NORTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, MenuPanel, -1, SpringLayout.SOUTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, MenuPanel, 0, SpringLayout.WEST, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, MenuPanel, 800, SpringLayout.WEST, frmRepairShields.getContentPane());
		frmRepairShields.getContentPane().add(MenuPanel);
		MenuPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		MenuPanel.add(panel_4);
		springLayout.putConstraint(SpringLayout.NORTH, panel_4, 0, SpringLayout.SOUTH, CrewMemberPanel);
		springLayout.putConstraint(SpringLayout.WEST, panel_4, 0, SpringLayout.WEST, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_4, 381, SpringLayout.NORTH, frmRepairShields.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_4, 800, SpringLayout.WEST, frmRepairShields.getContentPane());
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
				
		JButton btnRepairShields = new JButton("Repair shields");
		//Disable button by default
		btnRepairShields.setEnabled(false);
		//Enable button when shield health < 100
		if (Ship.getShields() < 100) {
			btnRepairShields.setEnabled(true);
		}
		btnRepairShields.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selected = buttonGroup.getSelection().getActionCommand();
				CrewMember member = crew.get(Integer.parseInt(selected));
				if (Ship.getShields() < 100) {
					member.repairShields();
					//Dialog box to notify player of action
					JOptionPane.showMessageDialog(frmRepairShields, String.format("Crewmember %s has repaired the ship's shields!" , member.getName()));
				}
				RepairShields.callScreen();
				frmRepairShields.dispose();
			}
		});
		panel_4.add(btnRepairShields);
				
		//Cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatusScreen.callScreen();
				frmRepairShields.dispose();
			}
		});
		MenuPanel.add(btnCancel);
		
		frmRepairShields.pack();		
		
		for (int i = 0; i < crew.size(); i++) {
			int widthMinor = i * (frmRepairShields.getWidth() / crew.size());
			int widthMajor = (i + 1) * (frmRepairShields.getWidth() / crew.size());
			int panelWidth = frmRepairShields.getWidth() / crew.size();
			JPanel temp = new JPanel();
			sl_crewMember.putConstraint(SpringLayout.NORTH, temp, 0, SpringLayout.NORTH, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.WEST, temp, widthMinor, SpringLayout.WEST, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.SOUTH, temp, 400, SpringLayout.NORTH, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.EAST, temp, widthMajor, SpringLayout.WEST, CrewMemberPanel);
			CrewMemberPanel.add(temp);
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
			
			
			JRadioButton rdbtnSelected = new JRadioButton("");
			sl_temp.putConstraint(SpringLayout.NORTH, rdbtnSelected, 0, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.WEST, rdbtnSelected, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, rdbtnSelected, 35, SpringLayout.SOUTH, actionPoints);
			sl_temp.putConstraint(SpringLayout.EAST, rdbtnSelected, panelWidth, SpringLayout.WEST, temp);
			rdbtnSelected.setActionCommand(Integer.toString(i));
			buttonGroup.add(rdbtnSelected);
			rdbtnSelected.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(rdbtnSelected);
			if (i == 0) {
				rdbtnSelected.setSelected(true);
			}
			
			
		}
		frmRepairShields.pack();
		for (int i = 0; i < lblImages.size(); i++) {
			JLabel image = lblImages.get(i);
			image.setIcon(new ImageIcon(((CrewMemberExtended)crew.get(i)).getImage().getContents(image.getWidth(), image.getHeight())));
		}
	}
}
