package graphicalInterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import backend.Consumable;
import backend.GameState;
import backend.Ship;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class OutpostScreen {

	private JFrame frame;
	private JTextField countEntry;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutpostScreen window = new OutpostScreen();
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
	public OutpostScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<Consumable> consumables = GameState.getAllConsumable();
		ArrayList<Integer> costs = new ArrayList<Integer>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel DescriptionPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, DescriptionPanel, 1, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DescriptionPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, DescriptionPanel, 100, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, DescriptionPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(DescriptionPanel);
		DescriptionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDesciption = new JLabel("Welcome to Outpost 9!");
		lblDesciption.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDesciption.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblDesciption);
		
		JLabel lblCredits = new JLabel(String.format("Credits: %d", Ship.getMoney()));
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblCredits);
		
		JPanel itemsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, itemsPanel, 0, SpringLayout.NORTH, DescriptionPanel);
		springLayout.putConstraint(SpringLayout.WEST, itemsPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, itemsPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(itemsPanel);
		
		JPanel ButtonsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, ButtonsPanel, 420, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ButtonsPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, itemsPanel, 0, SpringLayout.NORTH, ButtonsPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		itemsPanel.add(scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, ButtonsPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ButtonsPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(ButtonsPanel);
		ButtonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setEnabled(false);
		ButtonsPanel.add(btnPurchase);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			MainScreen.callScreen();
			frame.dispose();
			}
		});
		ButtonsPanel.add(btnCancel);
		
		for (Consumable item : consumables) {
			JPanel BandagePanel = new JPanel();
			springLayout.putConstraint(SpringLayout.NORTH, BandagePanel, 44, SpringLayout.NORTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, BandagePanel, 0, SpringLayout.WEST, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, BandagePanel, 88, SpringLayout.NORTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, BandagePanel, 800, SpringLayout.WEST, frame.getContentPane());
			frame.getContentPane().add(BandagePanel);
			BandagePanel.setLayout(new GridLayout(1, 0, 0, 0));
			
			JLabel lblName = new JLabel("Bandage");
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			BandagePanel.add(lblName);
			
			JLabel lblCost = new JLabel("Cost: 25");
			lblCost.setHorizontalAlignment(SwingConstants.CENTER);
			BandagePanel.add(lblCost);
			
			JLabel lblMedical = new JLabel("Type: Medical");
			lblMedical.setHorizontalAlignment(SwingConstants.CENTER);
			BandagePanel.add(lblMedical);
			
			JLabel lblEffectiveness = new JLabel("Effectiveness: 25");
			lblEffectiveness.setHorizontalAlignment(SwingConstants.CENTER);
			BandagePanel.add(lblEffectiveness);
			
			
			JLabel lblHeld = new JLabel("Held: insert");
			lblHeld.setHorizontalAlignment(SwingConstants.CENTER);
			BandagePanel.add(lblHeld);
			
			countEntry = new JTextField();
			BandagePanel.add(countEntry);
			countEntry.setColumns(10);
		}
	}

}
