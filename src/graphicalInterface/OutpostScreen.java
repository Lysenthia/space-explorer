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
import backend.Outpost;
import backend.Ship;

public class OutpostScreen {

	private JFrame frame;
	private int cost = 0;
	private JLabel lblCost;
	private JLabel lblCredits;
	private JButton btnPurchase;
	private HashMap<JTextField, HashMap<String, Object>> itemData = new HashMap<JTextField, HashMap<String, Object>>();
	private Outpost outpost = StartApplication.getOutpost();
	private ArrayList<Consumable> consumables = outpost.getStock();

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

	private void updateCost() {
		cost = sumCosts();
		lblCost.setText(String.format("Current Cost: %d", cost));
		if (cost > Ship.getMoney() || Ship.getMoney() == 0) {
			btnPurchase.setEnabled(false);
		} else {
			btnPurchase.setEnabled(true);
		}
	}
	
	private int sumCosts() {
		int sum = 0;
		for (JTextField field : itemData.keySet()) {
			sum += (int) itemData.get(field).get("cost");
		}
		return sum;
	}

	protected void purchaseItems() {
		int prePurchase = Ship.getMoney();
		for (JTextField field : itemData.keySet()) {
			HashMap<String, Object> item = itemData.get(field);
			outpost.purchaseItem((Consumable) item.get("item"), (int) item.get("wanted"));
			field.setText("0");
			((JLabel) item.get("heldLbl")).setText(String.format("Held: %d", ((Consumable) item.get("item")).getHeld()));
		}
		if (prePurchase != Ship.getMoney()) {
			lblCost.setText("Items purchased!");
			lblCredits.setText(String.format("Credits: %d", Ship.getMoney()));
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int itemsPanelSize = 45 * consumables.size();
		
		frame = new JFrame();
		frame.setTitle("Please purchase a few items");
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel DescriptionPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, DescriptionPanel, 1, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DescriptionPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, DescriptionPanel, 80, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, DescriptionPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(DescriptionPanel);
		DescriptionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDesciption = new JLabel(String.format("Welcome to %s!", outpost.getName()));
		lblDesciption.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDesciption.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblDesciption);
		
		lblCredits = new JLabel(String.format("Credits: %d", Ship.getMoney()));
		lblCredits.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblCredits);
		
		lblCost = new JLabel(String.format("Current Cost: %d", cost));
		lblCost.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblCost);
		
		JPanel itemsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, itemsPanel, 0, SpringLayout.SOUTH, DescriptionPanel);
		springLayout.putConstraint(SpringLayout.WEST, itemsPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, itemsPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(itemsPanel);
		
		JPanel ButtonsPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, itemsPanel, 0, SpringLayout.NORTH, ButtonsPanel);
		springLayout.putConstraint(SpringLayout.NORTH, ButtonsPanel, 420, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ButtonsPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		itemsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel itemsInternal = new JPanel();
		itemsInternal.setBounds(new Rectangle(0, 0, 750, itemsPanelSize));
		itemsInternal.setPreferredSize(new Dimension(750, itemsPanelSize));
		itemsInternal.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane(itemsInternal);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		itemsPanel.add(scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, ButtonsPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ButtonsPanel, 800, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(ButtonsPanel);
		ButtonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		
		btnPurchase = new JButton("Purchase");
		btnPurchase.setEnabled(false);
		ButtonsPanel.add(btnPurchase);
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			purchaseItems();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			MainScreen.callScreen();
			frame.dispose();
			}
		});
		ButtonsPanel.add(btnCancel);
		frame.pack();
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
			
			HashMap<String, Object> lblMapping = new HashMap<String,Object>();
			lblMapping.put("item", item);
			lblMapping.put("wanted", 0);
			lblMapping.put("cost", 0);
			lblMapping.put("heldLbl", lblHeld);
			itemData.put(countEntry, lblMapping);
			
			countEntry.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
					//Not called
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					JTextField loc = countEntry;
					HashMap<String, Object> lblMapping = itemData.get(loc);
					int price = ((Consumable) lblMapping.get("item")).getPrice();
					String inString = (loc.getText());
					Scanner inputChecker = new Scanner(inString);
					if (inputChecker.hasNextInt()) {
						int wanted = inputChecker.nextInt();
						lblMapping.put("cost", price * wanted);
						lblMapping.put("wanted", wanted);
						updateCost();
					} else {
						btnPurchase.setEnabled(false);
					}
					inputChecker.close();
					
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					JTextField loc = countEntry;
					HashMap<String, Object> lblMapping = itemData.get(loc);
					int price = ((Consumable) lblMapping.get("item")).getPrice();
					String inString = (loc.getText());
					Scanner inputChecker = new Scanner(inString);
					if (inputChecker.hasNextInt()) {
						int wanted = inputChecker.nextInt();
						lblMapping.put("cost", price * wanted);
						lblMapping.put("wanted", wanted);
						updateCost();
					} else if (inString.equals("")) {
						lblMapping.put("cost", 0);
						lblMapping.put("wanted", 0);
						updateCost();
					} else {
						btnPurchase.setEnabled(false);
					}
					inputChecker.close();
				}
			});
			
		}
		
	}
}
