package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;

public class UseItemScreen {

	private JFrame frmUseItemScreen;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UseItemScreen window = new UseItemScreen();
					window.frmUseItemScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UseItemScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUseItemScreen = new JFrame();
		frmUseItemScreen.setTitle("Use Item Screen");
		frmUseItemScreen.setBounds(new Rectangle(100, 100, 800, 600));
		frmUseItemScreen.setPreferredSize(new Dimension(800, 600));
		frmUseItemScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
