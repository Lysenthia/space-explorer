package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import backendGUIExtensions.SaveGame;

/**
 * The initial screen displayed when the game is started
 * @author hoo42
 * @author rvo16
 *
 */
public class MainMenuScreen {

	private JFrame frame;
	
	private void loadGame() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Save files", "ses");
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		int selection = chooser.showOpenDialog(null);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			SaveGame save = new SaveGame(selectedFile.toPath());
			try {
				save.load();
				MainScreen.callScreen();
				frame.dispose();
			} catch (IOException e) {
				System.out.println(e);
				String ObjButtons[] = {"Continue"};
				JOptionPane.showOptionDialog(null,"There was an error while trying to load a save","Load Error",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[0]);
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuScreen window = new MainMenuScreen();
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
	public MainMenuScreen() {
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
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setTitle("Main Menu");
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTitle = new JLabel("Welcome to Space Explorers!");
		mainPanel.add(lblTitle);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 35));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel newGamePanel = new JPanel();
		mainPanel.add(newGamePanel);
		newGamePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewGame = new JButton("Start a new game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartupScreen.callScreen();
				frame.dispose();
			}
		});
		newGamePanel.add(btnNewGame);
		
		JPanel loadGamePanel = new JPanel();
		mainPanel.add(loadGamePanel);
		loadGamePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnLoad = new JButton("Load a saved game");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame();
			}
		});
		loadGamePanel.add(btnLoad);
		
		JPanel quitPanel = new JPanel();
		mainPanel.add(quitPanel);
		quitPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnQuite = new JButton("Quit");
		btnQuite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitPanel.add(btnQuite);
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
