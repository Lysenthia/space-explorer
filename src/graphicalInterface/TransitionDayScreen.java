package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.CrewClass;
import backend.CrewMember;
import backend.GameState;
import backend.PossibleEndings;
import backend.RandomEvent;
import backend.RandomEventOutput;
import backend.Ship;

public class TransitionDayScreen {

	private JFrame frame;
	private JLabel lblDeadCrewList;
	private JLabel lblRandomEvent;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransitionDayScreen window = new TransitionDayScreen();
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
	public TransitionDayScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//TODO
		RandomEventOutput event = null;
		ArrayList<CrewMember> deadCrew = GameState.transitionDay();
		if (Ship.getShipCrew().size() > 1) {
			event = RandomEvent.activateRandomEvent();
		}
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setTitle("Dawn of a new day");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel deadCrewPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, deadCrewPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, deadCrewPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, deadCrewPanel, 200, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, deadCrewPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(deadCrewPanel);
		deadCrewPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel randomEventPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, randomEventPanel, 0, SpringLayout.SOUTH, deadCrewPanel);
		springLayout.putConstraint(SpringLayout.WEST, randomEventPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, randomEventPanel, 300, SpringLayout.SOUTH, deadCrewPanel);
		
		JLabel lblDeadCrewFluff = new JLabel("<html><p style='text-align: center;'>Due to the effects of the space plague, the following crew have died:</p></html>");
		lblDeadCrewFluff.setBorder(new EmptyBorder(0, 10, 0, 10));
		lblDeadCrewFluff.setHorizontalAlignment(SwingConstants.CENTER);
		deadCrewPanel.add(lblDeadCrewFluff);
		
		lblDeadCrewList = new JLabel("Nobody");
		lblDeadCrewList.setHorizontalAlignment(SwingConstants.CENTER);
		deadCrewPanel.add(lblDeadCrewList);
		springLayout.putConstraint(SpringLayout.EAST, randomEventPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(randomEventPanel);
		randomEventPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel btnPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, btnPanel, 0, SpringLayout.SOUTH, randomEventPanel);
		
		lblRandomEvent = new JLabel("Random Event Here");
		lblRandomEvent.setBorder(new EmptyBorder(0, 30, 0, 30));
		lblRandomEvent.setHorizontalAlignment(SwingConstants.CENTER);
		randomEventPanel.add(lblRandomEvent);
		springLayout.putConstraint(SpringLayout.WEST, btnPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnPanel);
		btnPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainScreen.callScreen();
				frame.dispose();
			}
		});
		btnPanel.add(btnContinue);
		
		populateDead(deadCrew);
		parseEvent(event);
	}

	private void parseEvent(RandomEventOutput event) {
		String message = "<html><p style='text-align: center;'>";
		if (event == null && Ship.getShipCrew().size() == 1) {
			message += "Due to the death of the majority of the ships crew, it is left floating in space<br>";
		} else if (event == null && Ship.getShipCrew().size() == 0) {
			message += "Due to the death of the ships crew, it is left floating in space<br>";
		} else {
			switch (event.event) {
			case ASTEROID_BELT:
				message += "Whilst floating through space, your ship encountered a comically unrealistic asteroid belt<br>";
				message += "Impact with an asteroid caused your ship to sustain 50% damage to its shields<br>";
				if (Ship.getShields() <= 0) {
					GameState.setEnding(PossibleEndings.SHIP_DESTROYED);
				} else {
					message += String.format("The %s's shields are now at %d<br>", Ship.getName(), Ship.getShields());
				}
				break;
			case NOTHING:
				message += "Whilst floating in space, nothing of interest happened aboard the ship";
				break;
			case SPACE_PIRATES:
				message += "Whilst floating in space, a space pirate managed to sneak onboard your ship<br>";
				if (event.member == null) {
					message += "They managed to make off with two items before being found<br>";
				} else {
					if (event.member.getMemberClass() == CrewClass.GUARD) {
						message += String.format("Fortunately, your %s %s managed to find them, and dispatched of them before they could steal anything<br>", CrewClass.GUARD.getClassName().toLowerCase(),event.member.getName());
					} else {
						message += String.format("Fortunately %s managed to find them, but the pirate managed to make off with an item<br>", event.member.getName());
					}
				}
				break;
			case SPACE_PLAGUE:
				message += String.format("Whilst floating in space, %s contracted the space plague. This will decrease their health by 25 each day<br>", event.member.getName());
				message += "In order to cure them, head to the outpost and buy a cure<br>";
				break;		
			}
		}
		message += "</p></html>";
		lblRandomEvent.setText(message);	
	}

	private void populateDead(ArrayList<CrewMember> deadCrew) {
		if (deadCrew.size() > 0) {
			String deadCrewMessage = "";
			for (CrewMember dead: deadCrew) {
				deadCrewMessage += dead.getName() + "<br>";
			}
			lblDeadCrewList.setText(String.format("<html><p style='text-align: center;'>%s</p></html>", deadCrewMessage));
		}
		
	}

}
