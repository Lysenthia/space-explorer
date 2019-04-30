package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import backend.CrewMember;
import backend.Ship;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatusScreen {

	private JFrame frmStatusscreen;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusScreen window = new StatusScreen();
					window.frmStatusscreen.setVisible(true);
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
		frmStatusscreen = new JFrame();
		frmStatusscreen.setTitle("StatusScreen");
		frmStatusscreen.setBounds(100, 100, 800, 600);
		frmStatusscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStatusscreen.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel CrewMemberPanel = new JPanel();
		frmStatusscreen.getContentPane().add(CrewMemberPanel);
		CrewMemberPanel.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel lblCrewMember = new JLabel("<html><p><center>Crew Member: <br>{name} <html><p> ");
		lblCrewMember.setHorizontalAlignment(SwingConstants.CENTER);
		CrewMemberPanel.add(lblCrewMember);
		
		JLabel lblHealth = new JLabel("Health: {000}");
		lblHealth.setHorizontalAlignment(SwingConstants.CENTER);
		CrewMemberPanel.add(lblHealth);
		
		JLabel lblActionPoints = new JLabel("Action Points: {0}");
		lblActionPoints.setHorizontalAlignment(SwingConstants.CENTER);
		CrewMemberPanel.add(lblActionPoints);
		
		JLabel lblHunger = new JLabel("Hunger: {000}");
		lblHunger.setHorizontalAlignment(SwingConstants.CENTER);
		CrewMemberPanel.add(lblHunger);
		
		JLabel lblTiredness = new JLabel("Tiredness: {000}");
		lblTiredness.setHorizontalAlignment(SwingConstants.CENTER);
		CrewMemberPanel.add(lblTiredness);
		
		JLabel lblNewLabel = new JLabel("<html><p><center> Space Plague Status: False <html><p><center>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CrewMemberPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		frmStatusscreen.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel label = new JLabel("<html><p><center>Crew Member: <br>{name} <html><p> ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Health: {000}");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Action Points: {0}");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Hunger: {000}");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Tiredness: {000}");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("<html><p><center> Space Plague Status: False <html><p><center>");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);
		
		JPanel panel_1 = new JPanel();
		frmStatusscreen.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel label_6 = new JLabel("<html><p><center>Crew Member: <br>{name} <html><p> ");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("Health: {000}");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("Action Points: {0}");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("Hunger: {000}");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_9);
		
		JLabel label_10 = new JLabel("Tiredness: {000}");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel("<html><p><center> Space Plague Status: False <html><p><center>");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_11);
		
		JPanel panel_2 = new JPanel();
		frmStatusscreen.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel label_12 = new JLabel("<html><p><center>Crew Member: <br>{name} <html><p> ");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_12);
		
		JLabel label_13 = new JLabel("Health: {000}");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_13);
		
		JLabel label_14 = new JLabel("Action Points: {0}");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_14);
		
		JLabel label_15 = new JLabel("Hunger: {000}");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_15);
		
		JLabel label_16 = new JLabel("Tiredness: {000}");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_16);
		
		JLabel label_17 = new JLabel("<html><p><center> Space Plague Status: False <html><p><center>");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_17);
		
		JPanel panel_4 = new JPanel();
		frmStatusscreen.getContentPane().add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Use an item");
		panel_4.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Rest a crew member");
		panel_4.add(btnNewButton_1);
		
		JButton btnRepairShields = new JButton("Repair shields");
		panel_4.add(btnRepairShields);
		
		JPanel panel_3 = new JPanel();
		frmStatusscreen.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmStatusscreen.dispose();
			}
		});
		panel_3.add(btnCancel);
	}

}
