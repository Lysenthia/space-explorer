package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OutpostScreen {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(13, 1, 0, 0));
		
		JPanel DescriptionPanel = new JPanel();
		frame.getContentPane().add(DescriptionPanel);
		DescriptionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDesciption = new JLabel("Welcome to Outpost 9!");
		lblDesciption.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDesciption.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblDesciption);
		
		JLabel lblCredits = new JLabel("Credits : {Credits go here}");
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(lblCredits);
		
		JPanel BandagePanel = new JPanel();
		frame.getContentPane().add(BandagePanel);
		BandagePanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnBandage = new JButton("Bandage");
		BandagePanel.add(btnBandage);
		
		JLabel lblCost = new JLabel("Cost: 25");
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		BandagePanel.add(lblCost);
		
		JLabel lblMedical = new JLabel("Type: Medical");
		lblMedical.setHorizontalAlignment(SwingConstants.CENTER);
		BandagePanel.add(lblMedical);
		
		JLabel lblEffectiveness = new JLabel("Effectiveness: 25");
		lblEffectiveness.setHorizontalAlignment(SwingConstants.CENTER);
		BandagePanel.add(lblEffectiveness);
		
		JLabel lblNewLabel = new JLabel("Held: {number here}");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BandagePanel.add(lblNewLabel);
		
		JPanel FirstAidKitPanel = new JPanel();
		frame.getContentPane().add(FirstAidKitPanel);
		FirstAidKitPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnFirstAidKid = new JButton("First Aid Kit");
		FirstAidKitPanel.add(btnFirstAidKid);
		
		JLabel lblCost_1 = new JLabel("Cost: 50");
		lblCost_1.setHorizontalAlignment(SwingConstants.CENTER);
		FirstAidKitPanel.add(lblCost_1);
		
		JLabel label_1 = new JLabel("Type: Medical");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		FirstAidKitPanel.add(label_1);
		
		JLabel lblEffectiveness_1 = new JLabel("Effectiveness: 50");
		lblEffectiveness_1.setHorizontalAlignment(SwingConstants.CENTER);
		FirstAidKitPanel.add(lblEffectiveness_1);
		
		JLabel label_3 = new JLabel("Held: {number here}");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		FirstAidKitPanel.add(label_3);
		
		JPanel ElixirPanel = new JPanel();
		frame.getContentPane().add(ElixirPanel);
		ElixirPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnElixir = new JButton("Elixir");
		ElixirPanel.add(btnElixir);
		
		JLabel lblCost_2 = new JLabel("Cost: 100");
		lblCost_2.setHorizontalAlignment(SwingConstants.CENTER);
		ElixirPanel.add(lblCost_2);
		
		JLabel label_5 = new JLabel("Type: Medical");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		ElixirPanel.add(label_5);
		
		JLabel lblEffectiveness_2 = new JLabel("Effectiveness: 100");
		lblEffectiveness_2.setHorizontalAlignment(SwingConstants.CENTER);
		ElixirPanel.add(lblEffectiveness_2);
		
		JLabel label_7 = new JLabel("Held: {number here}");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		ElixirPanel.add(label_7);
		
		JPanel BreadPanel = new JPanel();
		frame.getContentPane().add(BreadPanel);
		BreadPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnBread = new JButton("Bread");
		BreadPanel.add(btnBread);
		
		JLabel lblCost_3 = new JLabel("Cost: 30");
		lblCost_3.setHorizontalAlignment(SwingConstants.CENTER);
		BreadPanel.add(lblCost_3);
		
		JLabel lblTypeFood = new JLabel("Type: Food");
		lblTypeFood.setHorizontalAlignment(SwingConstants.CENTER);
		BreadPanel.add(lblTypeFood);
		
		JLabel lblEffectiveness_3 = new JLabel("Effectiveness: 30");
		lblEffectiveness_3.setHorizontalAlignment(SwingConstants.CENTER);
		BreadPanel.add(lblEffectiveness_3);
		
		JLabel label_11 = new JLabel("Held: {number here}");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		BreadPanel.add(label_11);
		
		JPanel FlourPanel = new JPanel();
		frame.getContentPane().add(FlourPanel);
		FlourPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnFlour = new JButton("Flour");
		FlourPanel.add(btnFlour);
		
		JLabel lblCost_4 = new JLabel("Cost: 5");
		lblCost_4.setHorizontalAlignment(SwingConstants.CENTER);
		FlourPanel.add(lblCost_4);
		
		JLabel lblTypeFood_1 = new JLabel("Type: Food");
		lblTypeFood_1.setHorizontalAlignment(SwingConstants.CENTER);
		FlourPanel.add(lblTypeFood_1);
		
		JLabel lblEffectiveness_4 = new JLabel("Effectiveness: 5");
		lblEffectiveness_4.setHorizontalAlignment(SwingConstants.CENTER);
		FlourPanel.add(lblEffectiveness_4);
		
		JLabel label_15 = new JLabel("Held: {number here}");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		FlourPanel.add(label_15);
		
		JPanel RicePanel = new JPanel();
		frame.getContentPane().add(RicePanel);
		RicePanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnRice = new JButton("Rice");
		RicePanel.add(btnRice);
		
		JLabel lblCost_5 = new JLabel("Cost: 20");
		lblCost_5.setHorizontalAlignment(SwingConstants.CENTER);
		RicePanel.add(lblCost_5);
		
		JLabel lblTypeFood_2 = new JLabel("Type: Food");
		lblTypeFood_2.setHorizontalAlignment(SwingConstants.CENTER);
		RicePanel.add(lblTypeFood_2);
		
		JLabel lblEffectiveness_5 = new JLabel("Effectiveness: 20");
		lblEffectiveness_5.setHorizontalAlignment(SwingConstants.CENTER);
		RicePanel.add(lblEffectiveness_5);
		
		JLabel label_19 = new JLabel("Held: {number here}");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		RicePanel.add(label_19);
		
		JPanel NutriPastePanel = new JPanel();
		frame.getContentPane().add(NutriPastePanel);
		NutriPastePanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnNutripaste = new JButton("NutriPaste");
		NutriPastePanel.add(btnNutripaste);
		
		JLabel lblCost_6 = new JLabel("Cost: 100");
		lblCost_6.setHorizontalAlignment(SwingConstants.CENTER);
		NutriPastePanel.add(lblCost_6);
		
		JLabel lblTypeFood_3 = new JLabel("Type: Food");
		lblTypeFood_3.setHorizontalAlignment(SwingConstants.CENTER);
		NutriPastePanel.add(lblTypeFood_3);
		
		JLabel lblEffectiveness_6 = new JLabel("Effectiveness: 100");
		lblEffectiveness_6.setHorizontalAlignment(SwingConstants.CENTER);
		NutriPastePanel.add(lblEffectiveness_6);
		
		JLabel label_23 = new JLabel("Held: {number here}");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		NutriPastePanel.add(label_23);
		
		JPanel AssortedFruitsPanel = new JPanel();
		frame.getContentPane().add(AssortedFruitsPanel);
		AssortedFruitsPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnAssortedFruits = new JButton("Assorted Fruits");
		AssortedFruitsPanel.add(btnAssortedFruits);
		
		JLabel lblCost_7 = new JLabel("Cost: 50");
		lblCost_7.setHorizontalAlignment(SwingConstants.CENTER);
		AssortedFruitsPanel.add(lblCost_7);
		
		JLabel lblTypeFood_4 = new JLabel("Type: Food");
		lblTypeFood_4.setHorizontalAlignment(SwingConstants.CENTER);
		AssortedFruitsPanel.add(lblTypeFood_4);
		
		JLabel lblEffectiveness_7 = new JLabel("Effectiveness: 50");
		lblEffectiveness_7.setHorizontalAlignment(SwingConstants.CENTER);
		AssortedFruitsPanel.add(lblEffectiveness_7);
		
		JLabel label_27 = new JLabel("Held: {number here}");
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		AssortedFruitsPanel.add(label_27);
		
		JPanel SnapfrozenCurryPanel = new JPanel();
		frame.getContentPane().add(SnapfrozenCurryPanel);
		SnapfrozenCurryPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnSnapfrozenCurry = new JButton("Snapfrozen Curry");
		SnapfrozenCurryPanel.add(btnSnapfrozenCurry);
		
		JLabel lblCost_8 = new JLabel("Cost: 70");
		lblCost_8.setHorizontalAlignment(SwingConstants.CENTER);
		SnapfrozenCurryPanel.add(lblCost_8);
		
		JLabel lblTypeFood_5 = new JLabel("Type: Food");
		lblTypeFood_5.setHorizontalAlignment(SwingConstants.CENTER);
		SnapfrozenCurryPanel.add(lblTypeFood_5);
		
		JLabel lblEffectiveness_8 = new JLabel("Effectiveness: 70");
		lblEffectiveness_8.setHorizontalAlignment(SwingConstants.CENTER);
		SnapfrozenCurryPanel.add(lblEffectiveness_8);
		
		JLabel label_31 = new JLabel("Held: {number here}");
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		SnapfrozenCurryPanel.add(label_31);
		
		JPanel BudgetSpacePlagueCurePanel = new JPanel();
		frame.getContentPane().add(BudgetSpacePlagueCurePanel);
		BudgetSpacePlagueCurePanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnBudgetSpacePlague = new JButton("<html><p>Budget Space <br>Plague Cure<html><p>");
		BudgetSpacePlagueCurePanel.add(btnBudgetSpacePlague);
		
		JLabel lblCost_9 = new JLabel("Cost: 50");
		lblCost_9.setHorizontalAlignment(SwingConstants.CENTER);
		BudgetSpacePlagueCurePanel.add(lblCost_9);
		
		JLabel lblTypeCure = new JLabel("Type: Cure");
		lblTypeCure.setHorizontalAlignment(SwingConstants.CENTER);
		BudgetSpacePlagueCurePanel.add(lblTypeCure);
		
		JLabel lblEffectiveness_9 = new JLabel("Effectiveness: 50");
		lblEffectiveness_9.setHorizontalAlignment(SwingConstants.CENTER);
		BudgetSpacePlagueCurePanel.add(lblEffectiveness_9);
		
		JLabel label_35 = new JLabel("Held: {number here}");
		label_35.setHorizontalAlignment(SwingConstants.CENTER);
		BudgetSpacePlagueCurePanel.add(label_35);
		
		JPanel SpacePlagueCurePanel = new JPanel();
		frame.getContentPane().add(SpacePlagueCurePanel);
		SpacePlagueCurePanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton btnSpacePlagueCure = new JButton("<html><p>Space Plague <br>Cure<html><p>");
		btnSpacePlagueCure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		SpacePlagueCurePanel.add(btnSpacePlagueCure);
		
		JLabel lblCost_10 = new JLabel("Cost: 100");
		lblCost_10.setHorizontalAlignment(SwingConstants.CENTER);
		SpacePlagueCurePanel.add(lblCost_10);
		
		JLabel lblTypecure = new JLabel("Type:Cure");
		lblTypecure.setHorizontalAlignment(SwingConstants.CENTER);
		SpacePlagueCurePanel.add(lblTypecure);
		
		JLabel lblEffectiveness_10 = new JLabel("Effectiveness: 100");
		lblEffectiveness_10.setHorizontalAlignment(SwingConstants.CENTER);
		SpacePlagueCurePanel.add(lblEffectiveness_10);
		
		JLabel label_39 = new JLabel("Held: {number here}");
		label_39.setHorizontalAlignment(SwingConstants.CENTER);
		SpacePlagueCurePanel.add(label_39);
		
		JPanel CancelPanel = new JPanel();
		frame.getContentPane().add(CancelPanel);
		CancelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			MainScreen.callScreen();
			frame.dispose();
			}
		});
		CancelPanel.add(btnCancel);
	}

}
