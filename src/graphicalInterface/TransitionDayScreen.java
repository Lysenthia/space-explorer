package graphicalInterface;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import backend.CrewMember;
import backend.GameState;
import backend.RandomEvent;
import backend.RandomEventOutput;

public class TransitionDayScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		ArrayList<CrewMember> dead = GameState.transitionDay();
		RandomEventOutput event = RandomEvent.activateRandomEvent();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
