package graphicalInterface;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PlanetSearchScreen {

	private JFrame frmPlanetSearchScreen;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanetSearchScreen window = new PlanetSearchScreen();
					window.frmPlanetSearchScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlanetSearchScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPlanetSearchScreen = new JFrame();
		frmPlanetSearchScreen.setResizable(false);
		frmPlanetSearchScreen.setAlwaysOnTop(true);
		frmPlanetSearchScreen.setTitle("Planet Search Screen");
		frmPlanetSearchScreen.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel DescriptionPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(DescriptionPanel);
		FlowLayout fl_DescriptionPanel = new FlowLayout(FlowLayout.CENTER, 10, 10);
		DescriptionPanel.setLayout(fl_DescriptionPanel);
		
		JLabel lblPleaseSelectAPlanet = new JLabel("Please select a plane to travel to");
		DescriptionPanel.add(lblPleaseSelectAPlanet);
		lblPleaseSelectAPlanet.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel MercuryPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(MercuryPanel);
		MercuryPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnMercury = new JButton("Mercury");
		MercuryPanel.add(btnMercury);
		
		JLabel lblDescriptionGoesHere = new JLabel("Description goes here");
		lblDescriptionGoesHere.setHorizontalAlignment(SwingConstants.CENTER);
		MercuryPanel.add(lblDescriptionGoesHere);
		
		JLabel lblPartsFoundFalse = new JLabel("Parts Found: False");
		lblPartsFoundFalse.setHorizontalAlignment(SwingConstants.CENTER);
		MercuryPanel.add(lblPartsFoundFalse);
		
		JPanel VenusPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(VenusPanel);
		VenusPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnVenus = new JButton("Venus");
		VenusPanel.add(btnVenus);
		
		JLabel label = new JLabel("Description goes here");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		VenusPanel.add(label);
		
		JLabel label_1 = new JLabel("Parts Found: False");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		VenusPanel.add(label_1);
		
		JPanel EarthPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(EarthPanel);
		EarthPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnEarth = new JButton("Earth");
		EarthPanel.add(btnEarth);
		
		JLabel label_2 = new JLabel("Description goes here");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		EarthPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Parts Found: False");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		EarthPanel.add(label_3);
		
		JPanel MarsPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(MarsPanel);
		MarsPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnMars = new JButton("Mars");
		MarsPanel.add(btnMars);
		
		JLabel label_4 = new JLabel("Description goes here");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		MarsPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Parts Found: False");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		MarsPanel.add(label_5);
		
		JPanel JupiterPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(JupiterPanel);
		JupiterPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnJupiter = new JButton("Jupiter");
		JupiterPanel.add(btnJupiter);
		
		JLabel label_6 = new JLabel("Description goes here");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		JupiterPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Parts Found: False");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		JupiterPanel.add(label_7);
		
		JPanel SaturnPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(SaturnPanel);
		SaturnPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton brnSaturn = new JButton("Saturn");
		SaturnPanel.add(brnSaturn);
		
		JLabel label_8 = new JLabel("Description goes here");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		SaturnPanel.add(label_8);
		
		JLabel label_9 = new JLabel("Parts Found: False");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		SaturnPanel.add(label_9);
		
		JPanel UranusPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(UranusPanel);
		UranusPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnUranus = new JButton("Uranus");
		UranusPanel.add(btnUranus);
		
		JLabel label_10 = new JLabel("Description goes here");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		UranusPanel.add(label_10);
		
		JLabel label_11 = new JLabel("Parts Found: False");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		UranusPanel.add(label_11);
		
		JPanel NeptunePanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(NeptunePanel);
		NeptunePanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnNeptune = new JButton("Neptune");
		NeptunePanel.add(btnNeptune);
		
		JLabel label_12 = new JLabel("Description goes here");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		NeptunePanel.add(label_12);
		
		JLabel label_13 = new JLabel("Parts Found: False");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		NeptunePanel.add(label_13);
		
		JPanel PlutoPanel = new JPanel();
		frmPlanetSearchScreen.getContentPane().add(PlutoPanel);
		PlutoPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnPluto = new JButton("Neptune");
		PlutoPanel.add(btnPluto);
		
		JLabel label_14 = new JLabel("Description goes here");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		PlutoPanel.add(label_14);
		
		JLabel label_15 = new JLabel("Parts Found: False");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		PlutoPanel.add(label_15);
		
		JPanel CancelPanel = new JPanel();
		CancelPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		frmPlanetSearchScreen.getContentPane().add(CancelPanel);
		CancelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCancel = new JButton("Cancel");
		CancelPanel.add(btnCancel);
	}
}
