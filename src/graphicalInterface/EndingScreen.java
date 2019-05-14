package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class EndingScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndingScreen window = new EndingScreen();
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
	public EndingScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setSize(new Dimension(800, 600));
		frame.setSize(new Dimension(800, 600));
		frame.getContentPane().setBounds(new Rectangle(0, 0, 800, 600));
		frame.setBounds(new Rectangle(0, 0, 800, 800));
		frame.setPreferredSize(new Dimension(800, 600));
		frame.getContentPane().setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel splashPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, splashPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, splashPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, splashPanel, 200, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, splashPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(splashPanel);
		splashPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblWinLose = new JLabel("Win/Lose");
		lblWinLose.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblWinLose.setHorizontalAlignment(SwingConstants.CENTER);
		splashPanel.add(lblWinLose);
		
		JPanel descriptionPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, descriptionPanel, 0, SpringLayout.SOUTH, splashPanel);
		springLayout.putConstraint(SpringLayout.WEST, descriptionPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, descriptionPanel, 275, SpringLayout.SOUTH, splashPanel);
		springLayout.putConstraint(SpringLayout.EAST, descriptionPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(descriptionPanel);
		descriptionPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblDescription = new JLabel("Description goes here");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionPanel.add(lblDescription);
		
		JPanel buttonPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 0, SpringLayout.SOUTH, descriptionPanel);
		springLayout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnQuit = new JButton("Leave the game");
		buttonPanel.add(btnQuit);
	}

}
