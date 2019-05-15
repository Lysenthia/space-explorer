package graphicalInterface;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import backendGUIExtensions.SaveGame;

public class SaveGameScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveGameScreen window = new SaveGameScreen();
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
	public SaveGameScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JFileChooser chooser = new JFileChooser();
		int selection = chooser.showSaveDialog(null);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			SaveGame save = new SaveGame(selectedFile.toPath());
			try {
				save.save();
			} catch (IOException e) {
				String ObjButtons[] = {"Continue"};
				JOptionPane.showOptionDialog(null,"There was an error while trying to save the game","Save Error",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[0]);
			}
			finally {
				MainScreen.callScreen();
			}
		}

	}

}
