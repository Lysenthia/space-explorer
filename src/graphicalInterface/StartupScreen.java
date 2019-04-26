package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;

public class StartupScreen {

	private JFrame frmStartupScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartupScreen window = new StartupScreen();
					window.frmStartupScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartupScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStartupScreen = new JFrame();
		frmStartupScreen.setResizable(false);
		frmStartupScreen.setTitle("Startup Screen");
		frmStartupScreen.setBounds(100, 100, 800, 601);
		frmStartupScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStartupScreen.getContentPane().setLayout(null);
		
		JLabel lblHelloAndWel = new JLabel("Hello!");
		lblHelloAndWel.setBounds(363, 12, 66, 30);
		lblHelloAndWel.setFont(new Font("Dialog", Font.BOLD, 14));
		frmStartupScreen.getContentPane().add(lblHelloAndWel);
		
		JLabel lblWelcomeToSpace = new JLabel("Welcome to Space Explorers!");
		lblWelcomeToSpace.setBounds(276, 42, 216, 28);
		frmStartupScreen.getContentPane().add(lblWelcomeToSpace);
	}
}
