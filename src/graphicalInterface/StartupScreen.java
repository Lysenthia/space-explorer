package graphicalInterface;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.GameState;

public class StartupScreen {

	private JFrame frmStartupScreen;
	private JTextField DaysTextField;
	private JTextField PartsTextField;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
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
		frmStartupScreen.getContentPane().setBackground(UIManager.getColor("ColorChooser.background"));
		frmStartupScreen.setTitle("Startup Screen");
		frmStartupScreen.setBounds(20, 20, 903, 669);
		frmStartupScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblHelloAndWel = new JLabel("�ｿｽ�ｽｽ�ｽ｡�ｿｽ�ｽｽ�ｽ･:*:�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ貅ｪ�ｿｽ,�ｿｽ�ｽｽ�ｽ｡�ｿｽ�ｽｽ�ｽ･:*:�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ貅ｪ�ｿｽ       Hello!      �ｿｽ�ｽｽ�ｽ｡�ｿｽ�ｽｽ�ｽ･:*:�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ貅ｪ�ｿｽ,�ｿｽ�ｽｽ�ｽ｡�ｿｽ�ｽｽ�ｽ･:*:�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ貅ｪ�ｿｽ");
		lblHelloAndWel.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAndWel.setFont(new Font("Dialog", Font.BOLD, 16));
		frmStartupScreen.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frmStartupScreen.getContentPane().add(lblHelloAndWel);
		
		JLabel lblWelcomeToSpace = new JLabel("隨ｨ�ｽｧ�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ�ｿｽ: *隨ｨ�ｽｧ�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ�ｿｽ:*  Welcome to Space Explorers!  *:�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ貅ｪ謔ｸ*:�ｿｽ�ｽｽ�ｽ･�ｿｽ�ｽｾ貅ｪ謔ｸ");
		lblWelcomeToSpace.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSpace.setFont(new Font("Dialog", Font.BOLD, 16));
		frmStartupScreen.getContentPane().add(lblWelcomeToSpace);
		
		JTextPane descriptionTextArea = new JTextPane();
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setBackground(UIManager.getColor("CheckBoxMenuItem.background"));
		descriptionTextArea.setFont(new Font("Dialog", Font.PLAIN, 14));
		descriptionTextArea.setText("Your spaceships has been broken and its pieces are scattered throughout the surrounding planets. You will need to find the missing pieces of your spaceship so that you can repair it and get home.");
		frmStartupScreen.getContentPane().add(descriptionTextArea);
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the number of days you would like to play for!");
		lblPleaseSelectThe.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPleaseSelectThe.setHorizontalAlignment(SwingConstants.CENTER);
		frmStartupScreen.getContentPane().add(lblPleaseSelectThe);
		
		JPanel daysPanel = new JPanel();
		daysPanel.setBorder(new EmptyBorder(0, 100, 0, 100));
		frmStartupScreen.getContentPane().add(daysPanel);
		daysPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel daysLabel = new JLabel("Days:");
		daysPanel.add(daysLabel);
		daysLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		daysLabel.setLabelFor(DaysTextField);
		
		DaysTextField = new JTextField();
		DaysTextField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		DaysTextField.setHorizontalAlignment(SwingConstants.CENTER);
		DaysTextField.setText("Please select a day");
		daysPanel.add(DaysTextField);
		DaysTextField.setEditable(false);
		DaysTextField.setColumns(10);
		
		JPanel partsPanel = new JPanel();
		partsPanel.setBorder(new EmptyBorder(0, 100, 0, 100));
		frmStartupScreen.getContentPane().add(partsPanel);
		partsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel partsToFindLabel = new JLabel("Parts to Find:");
		partsPanel.add(partsToFindLabel);
		partsToFindLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		PartsTextField = new JTextField();
		PartsTextField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		PartsTextField.setText("Please select a day");
		PartsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		partsPanel.add(PartsTextField);
		PartsTextField.setEditable(false);
		PartsTextField.setColumns(10);
		partsToFindLabel.setLabelFor(PartsTextField);
		
		JSlider slider = new JSlider();
		
		slider.setBorder(new EmptyBorder(0, 50, 0, 50));
		frmStartupScreen.getContentPane().add(slider);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setValue(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setToolTipText("");
		slider.setSnapToTicks(true);
		slider.setMinimum(3);
		slider.setMaximum(10);
		
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrewSelection.callScreen();
				frmStartupScreen.dispose();
			}
		});
		frmStartupScreen.getContentPane().add(continueButton);
		
		GameState.setEndDay(3);
		DaysTextField.setText(Integer.toString(GameState.getEndDay()));
		PartsTextField.setText(Integer.toString(GameState.getPartsNeeded()));
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = slider.getValue();
				GameState.setEndDay(value);
				DaysTextField.setText(Integer.toString(GameState.getEndDay()));
				PartsTextField.setText(Integer.toString(GameState.getPartsNeeded()));
			}
		});
	}
}
