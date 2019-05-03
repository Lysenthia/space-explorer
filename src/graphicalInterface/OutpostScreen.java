package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import backend.Consumable;
import backend.GameState;
import backend.Ship;

public class OutpostScreen {

	private JFrame frmPleasePurchaseA;
	private int cost = 0;
	private JLabel lblCost;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutpostScreen window = new OutpostScreen();
					window.frmPleasePurchaseA.setVisible(true);
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

	private int sumCosts(HashMap<JTextField, Integer> costs) {
		int sum = 0;
		for (JTextField field : costs.keySet()) {
			sum += costs.get(field);
		}
		return sum;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList<Consumable> consumables = GameState.getAllConsumable();
		HashMap<JTextField, Integer> costs = new HashMap<JTextField, Integer>();
		HashMap<JTextField, Consumable> itemRelation = new HashMap<JTextField, Consumable>();
		ArrayList<JTextField> inputs = new ArrayList<JTextField>();
		
		frmPleasePurchaseA = new JFrame();
		frmPleasePurchaseA.setTitle("Please purchase a few items");
		frmPleasePurchaseA.setResizable(false);
		frmPleasePurchaseA.setPreferredSize(new Dimension(800, 600));
		frmPleasePurchaseA.setBounds(100, 100, 800, 600);
		frmPleasePurchaseA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmPleasePurchaseA.getContentPane().setLayout(springLayout);
		
		JPanel DescriptionPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, DescriptionPanel, 1, SpringLayout.NORTH, frmPleasePurchaseA.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DescriptionPanel, 0, SpringLayout.WEST, frmPleasePurchaseA.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, DescriptionPanel, 100, SpringLayout.NORTH, frmPleasePurchaseA.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, DescriptionPanel, 800, SpringLayout.WEST, frmPleasePurchaseA.getContentPane());
		frmPleasePurchaseA.getContentPane().add(DescriptionPanel);
		DescriptionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDesciption = new JLabel("Welcome to Outpost 9!");
		lblDesciption.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDesciption.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblDesciption);
		
		JLabel lblCredits = new JLabel(String.format("Credits: %d", Ship.getMoney()));
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblCredits);
		
		lblCost = new JLabel(String.format("Current Cost: %d", cost));
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblCost);
		
		JPanel itemsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, itemsPanel, 0, SpringLayout.SOUTH, DescriptionPanel);
		springLayout.putConstraint(SpringLayout.WEST, itemsPanel, 0, SpringLayout.WEST, frmPleasePurchaseA.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, itemsPanel, 800, SpringLayout.WEST, frmPleasePurchaseA.getContentPane());
		frmPleasePurchaseA.getContentPane().add(itemsPanel);
		
		JPanel ButtonsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, ButtonsPanel, 420, SpringLayout.NORTH, frmPleasePurchaseA.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ButtonsPanel, 0, SpringLayout.SOUTH, frmPleasePurchaseA.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, itemsPanel, 0, SpringLayout.NORTH, ButtonsPanel);
		itemsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel itemsInternal = new JPanel();
		itemsInternal.setBounds(new Rectangle(0, 0, 800, 0));
		itemsInternal.setPreferredSize(new Dimension(800, 10));
		itemsInternal.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane(itemsInternal);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		itemsPanel.add(scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, ButtonsPanel, 0, SpringLayout.WEST, frmPleasePurchaseA.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ButtonsPanel, 800, SpringLayout.WEST, frmPleasePurchaseA.getContentPane());
		frmPleasePurchaseA.getContentPane().add(ButtonsPanel);
		ButtonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setEnabled(false);
		ButtonsPanel.add(btnPurchase);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			MainScreen.callScreen();
			frmPleasePurchaseA.dispose();
			}
		});
		ButtonsPanel.add(btnCancel);
		frmPleasePurchaseA.pack();
		for (int i = 0; i < consumables.size(); i++) {
			Consumable item = consumables.get(i);
			JPanel itemSubPanel = new JPanel();
			itemSubPanel.setLayout(new GridLayout(1, 6, 0, 0));
			itemsInternal.add(itemSubPanel);
			
			JLabel lblName = new JLabel(String.format("<html><p style='text-align: center;'>%s</p></html>", item.getName()));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblName);
			
			JLabel lblPrice = new JLabel(String.format("Cost: %d", item.getPrice()));
			lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblPrice);
			
			JLabel lblType = new JLabel(String.format("<html><p style='text-align: center;'>Type: %s</p></html>", item.getItemType()));
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblType);
			
			JLabel lblEffectiveness = new JLabel(String.format("Effectiveness: %d", item.getPrice()));
			lblEffectiveness.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblEffectiveness);
			
			
			JLabel lblHeld = new JLabel(String.format("Held: %d", item.getHeld()));
			lblHeld.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblHeld);
			
			JTextField countEntry = new JTextField();
			itemSubPanel.add(countEntry);
			countEntry.setColumns(10);
			costs.put(countEntry, 0);
			itemRelation.put(countEntry, item);
			inputs.add(countEntry);
			countEntry.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
					//Not called
					
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					JTextField loc = countEntry;
					int price = itemRelation.get(loc).getPrice();
					String inString = (loc.getText());
					Scanner inputChecker = new Scanner(inString);
					if (inputChecker.hasNextInt()) {
						int value = inputChecker.nextInt();
						costs.put(loc, price * value);
						cost = sumCosts(costs);
						lblCost.setText(String.format("Current Cost: %d", cost));
						if (cost > Ship.getMoney()) {
							btnPurchase.setEnabled(false);
						} else {
							btnPurchase.setEnabled(true);
						}
					} else if (inString == "") {
						costs.put(loc, 0);
						cost = sumCosts(costs);
						if (cost > Ship.getMoney()) {
							btnPurchase.setEnabled(false);
						} else {
							btnPurchase.setEnabled(true);
						}
					} else {
						btnPurchase.setEnabled(false);
					}
					inputChecker.close();
					
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					JTextField loc = countEntry;
					costs.put(loc, 0);
					cost = sumCosts(costs);
					lblCost.setText(String.format("Current Cost: %d", cost));
					if (cost > Ship.getMoney()) {
						btnPurchase.setEnabled(false);
					} else {
						btnPurchase.setEnabled(true);
					}
				}
			});
			
		}
		
	}

}
