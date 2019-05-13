package graphicalInterface;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.GameState;
import backend.Ship;

public class StartupScreen {

	private JFrame frmStartupScreen;
	private JTextField DaysTextField;
	private JTextField PartsTextField;
	private JTextField tfShipName;
	boolean nameClicked = false;

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
		frmStartupScreen.setPreferredSize(new Dimension(800, 600));
		frmStartupScreen.setResizable(false);
		frmStartupScreen.getContentPane().setBackground(UIManager.getColor("ColorChooser.background"));
		frmStartupScreen.setTitle("Startup Screen");
		frmStartupScreen.setBounds(100, 100, 800, 600);
		frmStartupScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblHelloAndWel = new JLabel("       Hello!      ");
		lblHelloAndWel.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAndWel.setFont(new Font("Dialog", Font.BOLD, 16));
		frmStartupScreen.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frmStartupScreen.getContentPane().add(lblHelloAndWel);
		
		JLabel lblWelcomeToSpace = new JLabel("  Welcome to Space Explorers!  ");
		lblWelcomeToSpace.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSpace.setFont(new Font("Dialog", Font.BOLD, 16));
		frmStartupScreen.getContentPane().add(lblWelcomeToSpace);
		
		JLabel lblDescription = new JLabel("<html><p>   Your spaceships has been broken and its pieces are scattered throughout the surrounding <br> planets. You will need to find the missing pieces of your spaceship so that you can repair it and get home.</p></html>");
		lblDescription.setBorder(new EmptyBorder(0, 10, 0, 10));
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		frmStartupScreen.getContentPane().add(lblDescription);
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the number of days you would like to play for!");
		lblPleaseSelectThe.setFont(new Font("Dialog", Font.BOLD, 15));
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
		DaysTextField.setToolTipText("Yeet");
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
		slider.setFont(new Font("Dialog", Font.BOLD, 14));
		
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
		
		frmStartupScreen.pack();
		
		JButton continueButton = new JButton("Continue");
		continueButton.setToolTipText("");
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfShipName.getText().replaceAll("\\s*","").equals("") || tfShipName.getText() == null) {
					Ship.setName("White Whale");
				} else {
					Ship.setName(tfShipName.getText());
				}
				CrewSelection.callScreen();
				frmStartupScreen.dispose();
			}
		});
		
		JPanel shipNamePanel = new JPanel();
		frmStartupScreen.getContentPane().add(shipNamePanel);
		shipNamePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblShipName = new JLabel("Please enter a ship name");
		lblShipName.setHorizontalAlignment(SwingConstants.CENTER);
		shipNamePanel.add(lblShipName);
		
		tfShipName = new JTextField();
		tfShipName.setHorizontalAlignment(SwingConstants.CENTER);
		tfShipName.setText("White Whale");
		lblShipName.setLabelFor(tfShipName);
		shipNamePanel.add(tfShipName);
		tfShipName.setColumns(10);
		frmStartupScreen.getContentPane().add(continueButton);
		
		GameState.setEndDay(3);
		DaysTextField.setText(Integer.toString(GameState.getEndDay()));
		PartsTextField.setText(Integer.toString(GameState.getPartsNeeded()));
		
		tfShipName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!nameClicked) {
					tfShipName.setText("");
					nameClicked = true;
				}
			}
		});
		
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
