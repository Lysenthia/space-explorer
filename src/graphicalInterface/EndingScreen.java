package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.CrewMember;
import backend.GameState;
import backend.PossibleEndings;
import backend.Score;
import backend.Ship;
import javax.swing.border.EmptyBorder;

/**
 * GUI to display the ending screen of the game
 * GUI displays ending screen message depending on type of end game state
 * @author hoo42
 * @author rvo16
 *
 */
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
		int score = Score.getScore();
		PossibleEndings ending = GameState.getEnding();
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
		
		JLabel lblWinLose = new JLabel("You lose");
		lblWinLose.setFont(new Font("Tahoma", Font.PLAIN, 70));
		lblWinLose.setHorizontalAlignment(SwingConstants.CENTER);
		splashPanel.add(lblWinLose);
		
		JPanel descriptionPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, descriptionPanel, 0, SpringLayout.WEST, frame.getContentPane());
		
		JPanel scorePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, descriptionPanel, 0, SpringLayout.SOUTH, scorePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, descriptionPanel, 175, SpringLayout.SOUTH, scorePanel);
		springLayout.putConstraint(SpringLayout.NORTH, scorePanel, 0, SpringLayout.SOUTH, splashPanel);
		springLayout.putConstraint(SpringLayout.WEST, scorePanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scorePanel, 100, SpringLayout.SOUTH, splashPanel);
		springLayout.putConstraint(SpringLayout.EAST, scorePanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(scorePanel);
		scorePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblScore = new JLabel("Final Score goes here");
		lblScore.setFont(new Font("Dialog", Font.BOLD, 16));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(lblScore);
		springLayout.putConstraint(SpringLayout.EAST, descriptionPanel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(descriptionPanel);
		descriptionPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblDescription = new JLabel("Description goes here");
		lblDescription.setBorder(new EmptyBorder(0, 20, 0, 20));
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
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonPanel.add(btnQuit);
		
		switch (ending) {
		case CREW_DEAD:
			lblScore.setText(String.format("Your final score was: %d", score));
			lblDescription.setText(String.format("<html><p>With the death of all the %s's crew, she is left floating through space, a desolate reminder of the perils of space travel...</html></p>", Ship.getName()));
			break;
		case LOST_IN_SPACE:
			lblScore.setText(String.format("Your final score was: %d", score));
			CrewMember lastCrew = Ship.getShipCrew().get(0);
			lblDescription.setText(String.format("<html><p style='text-align: center;'>With the death of all the %s's crew but %s, %s's future is uncertain, whether they will be rescued by another ship, starve to death, or choke and freeze as the ships life support system fails...</html></p>", Ship.getName(), lastCrew.getName(), lastCrew.getName()));
			break;
		case OUT_OF_TIME:
			lblScore.setText(String.format("Your final score was: %d", score));
			lblDescription.setText(String.format("<html><p style='text-align: center;'>With the crew unable to repair her Alcubierre drive in time, the %s's negative mass generator failed, causing her and her crew to vanish, never to be seen again...</html></p>", Ship.getName()));
			break;
		case QUIT:
			lblScore.setText("Your final score was: Nothing, you quitter");
			lblDescription.setText(String.format("<html><p style='text-align: center;'>In the face of impossible odds, the %s's crew promptly gave up, and decided it would be easier to jump out of the airlock into the vacuum of space</html></p>", Ship.getName()));
			break;
		case SHIP_DESTROYED:
			lblScore.setText(String.format("Your final score was: %d", score));
			lblDescription.setText(String.format("<html><p style='text-align: center;'>With the failure of the %s's shields system, her structural integrity failed, causing the ship to break up and expose her entire crew to the harsh void of space, killing all of them...</html></p>", Ship.getName()));
			break;
		case VICTORY:
			lblScore.setText(String.format("Your final score was: %d", score));
			lblWinLose.setText("Congratulations, you win!");
			lblDescription.setText(String.format("<html><p style='text-align: center;'>With the finding of the last part of her Alcubierre drive, the %s's crew were able to perform a patchwork fix, letting her crew escape back to more civilised systems where their ship could undergo permanent repairs</html></p>", Ship.getName()));
			break;
		}
	}

}
