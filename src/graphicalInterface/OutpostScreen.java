package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
		int[] costs = new int[consumables.size()];
		Arrays.fill(costs, 0);
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(800, 600));
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
		springLayout.putConstraint(SpringLayout.NORTH, itemsPanel, 0, SpringLayout.SOUTH, DescriptionPanel);
		springLayout.putConstraint(SpringLayout.WEST, itemsPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, itemsPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(itemsPanel);
		
		JPanel ButtonsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, ButtonsPanel, 420, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ButtonsPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, itemsPanel, 0, SpringLayout.NORTH, ButtonsPanel);
		itemsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel itemsInternal = new JPanel();
		itemsInternal.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane(itemsInternal);
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
		frame.pack();
		for (Consumable item : consumables) {
			System.out.println("Adding item: " + item.getName());
			JPanel itemPanel = new JPanel();
			itemPanel.setLayout(new GridLayout(1, 0, 0, 0));
			scrollPane.add(itemPanel);
			
			JLabel lblName = new JLabel(String.format("<html><p>%s</p></html>", item.getName()));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			itemPanel.add(lblName);
			
			JLabel lblCost = new JLabel(String.format("Cost: %d", item.getPrice()));
			lblCost.setHorizontalAlignment(SwingConstants.CENTER);
			itemPanel.add(lblCost);
			
			JLabel lblType = new JLabel(String.format("Type: %-10s", item.getItemType()));
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
			itemPanel.add(lblType);
			
			JLabel lblEffectiveness = new JLabel(String.format("Effectiveness: %d", item.getPrice()));
			lblEffectiveness.setHorizontalAlignment(SwingConstants.CENTER);
			itemPanel.add(lblEffectiveness);
			
			
			JLabel lblHeld = new JLabel(String.format("Held: %d", item.getHeld()));
			lblHeld.setHorizontalAlignment(SwingConstants.CENTER);
			itemPanel.add(lblHeld);
			
			countEntry = new JTextField();
			itemPanel.add(countEntry);
			countEntry.setColumns(10);
		}
		
		
	}

}
