package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.Consumable;
import backend.CrewMember;
import backend.Ship;
import backendGUIExtensions.CrewMemberExtended;

public class UseItemScreen {

	private JFrame frmUseItemScreen;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup itemButtonGroup = new ButtonGroup();
	

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
		ArrayList<CrewMember> crew = Ship.getReadyCrew();
		ArrayList<JLabel> lblImages = new ArrayList<JLabel>();
		ArrayList<Consumable> consumables = Ship.getInventory();
		int itemsPanelSize = 45 * consumables.size();
		
		frmUseItemScreen = new JFrame();
		frmUseItemScreen.setResizable(false);
		frmUseItemScreen.setPreferredSize(new Dimension(800, 600));
		frmUseItemScreen.setTitle("Use Item Screen");
		frmUseItemScreen.setBounds(100, 100, 800, 600);
		frmUseItemScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmUseItemScreen.getContentPane().setLayout(springLayout);
		
		JPanel InfoPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, InfoPanel, 0, SpringLayout.NORTH, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, InfoPanel, 0, SpringLayout.WEST, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, InfoPanel, -553, SpringLayout.SOUTH, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, InfoPanel, 0, SpringLayout.EAST, frmUseItemScreen.getContentPane());
		frmUseItemScreen.getContentPane().add(InfoPanel);
		InfoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Please select a crew and an item to use");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		InfoPanel.add(lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 53, SpringLayout.NORTH, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 86, SpringLayout.WEST, frmUseItemScreen.getContentPane());
		
		JPanel CrewMemberPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, CrewMemberPanel, 1, SpringLayout.SOUTH, InfoPanel);
		springLayout.putConstraint(SpringLayout.WEST, CrewMemberPanel, 10, SpringLayout.WEST, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, CrewMemberPanel, -10, SpringLayout.EAST, InfoPanel);
		frmUseItemScreen.getContentPane().add(CrewMemberPanel);
		SpringLayout sl_crewMember = new SpringLayout();
		CrewMemberPanel.setLayout(sl_crewMember);
		
		JPanel MenuPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, MenuPanel, 516, SpringLayout.NORTH, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, MenuPanel, -1, SpringLayout.SOUTH, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, MenuPanel, 0, SpringLayout.WEST, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, MenuPanel, 800, SpringLayout.WEST, frmUseItemScreen.getContentPane());
		frmUseItemScreen.getContentPane().add(MenuPanel);
		MenuPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 312, SpringLayout.NORTH, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.NORTH, MenuPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, CrewMemberPanel, -6, SpringLayout.NORTH, panel);
		panel.setPreferredSize(new Dimension (800, 600));
		panel.setBounds(0, 0, 800, 600);
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frmUseItemScreen.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, frmUseItemScreen.getContentPane());
		frmUseItemScreen.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel itemsInternal = new JPanel();
		itemsInternal.setBounds(new Rectangle(0, 0, 750, itemsPanelSize));
		itemsInternal.setPreferredSize(new Dimension(750, itemsPanelSize));
		itemsInternal.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane(itemsInternal);
		panel.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
				
		//Cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatusScreen.callScreen();
				frmUseItemScreen.dispose();
			}
		});
		
		JButton btnRest = new JButton("Use item");
		MenuPanel.add(btnRest);
		btnRest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selected = buttonGroup.getSelection().getActionCommand();
				CrewMember member = crew.get(Integer.parseInt(selected));
				String selected2 = itemButtonGroup.getSelection().getActionCommand();
				Consumable item = consumables.get(Integer.parseInt(selected2));
				item.use(member);
				//Dialog box to notify player of action
				JOptionPane.showMessageDialog(frmUseItemScreen, String.format("Crewmember, %s, has used %s", member.getName(), item.getName()));
				UseItemScreen.callScreen();
				frmUseItemScreen.dispose();
			}
		});
		MenuPanel.add(btnCancel);
		
		frmUseItemScreen.pack();		
		
		for (int i = 0; i < crew.size(); i++) {
			int widthMinor = i * (frmUseItemScreen.getWidth() / crew.size());
			int widthMajor = (i + 1) * (frmUseItemScreen.getWidth() / crew.size());
			int panelWidth = frmUseItemScreen.getWidth() / crew.size();
			JPanel temp = new JPanel();
			sl_crewMember.putConstraint(SpringLayout.NORTH, temp, 0, SpringLayout.NORTH, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.WEST, temp, widthMinor, SpringLayout.WEST, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.SOUTH, temp, 400, SpringLayout.NORTH, CrewMemberPanel);
			sl_crewMember.putConstraint(SpringLayout.EAST, temp, widthMajor, SpringLayout.WEST, CrewMemberPanel);
			CrewMemberPanel.add(temp);
			SpringLayout sl_temp = new SpringLayout();
			temp.setLayout(sl_temp);
			
			JLabel image = new JLabel();
			sl_temp.putConstraint(SpringLayout.NORTH, image, 1, SpringLayout.NORTH, temp);
			sl_temp.putConstraint(SpringLayout.WEST, image, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, image, 180, SpringLayout.NORTH, temp);
			sl_temp.putConstraint(SpringLayout.EAST, image, panelWidth, SpringLayout.WEST, temp);
			image.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(image);
			lblImages.add(image);
			
			JLabel name = new JLabel("name");
			sl_temp.putConstraint(SpringLayout.NORTH, name, 0, SpringLayout.SOUTH, image);
			sl_temp.putConstraint(SpringLayout.WEST, name, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, name, 20, SpringLayout.SOUTH, image);
			sl_temp.putConstraint(SpringLayout.EAST, name, panelWidth, SpringLayout.WEST, temp);
			name.setHorizontalAlignment(SwingConstants.CENTER);
			name.setText(String.format("Name: %s", crew.get(i).getName()));
			temp.add(name);
			
			JLabel health = new JLabel("Health");
			sl_temp.putConstraint(SpringLayout.NORTH, health, 0, SpringLayout.SOUTH, name);
			sl_temp.putConstraint(SpringLayout.WEST, health, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, health, 20, SpringLayout.SOUTH, name);
			sl_temp.putConstraint(SpringLayout.EAST, health, panelWidth, SpringLayout.WEST, temp);
			health.setText(String.format("Health: %d", crew.get(i).getHealth()));
			health.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(health);
			
			JLabel hunger = new JLabel("Health");
			sl_temp.putConstraint(SpringLayout.NORTH, hunger, 0, SpringLayout.SOUTH, health);
			sl_temp.putConstraint(SpringLayout.WEST, hunger, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, hunger, 20, SpringLayout.SOUTH, health);
			sl_temp.putConstraint(SpringLayout.EAST, hunger, panelWidth, SpringLayout.WEST, temp);
			hunger.setText(String.format("Hunger: %d", crew.get(i).getHunger()));
			hunger.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(hunger);
			
			JLabel spacePlagueStatus = new JLabel("Space Plague Status");
			sl_temp.putConstraint(SpringLayout.NORTH, spacePlagueStatus, 0, SpringLayout.SOUTH, hunger);
			sl_temp.putConstraint(SpringLayout.WEST, spacePlagueStatus, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, spacePlagueStatus, 20, SpringLayout.SOUTH, hunger);
			sl_temp.putConstraint(SpringLayout.EAST, spacePlagueStatus, panelWidth, SpringLayout.WEST, temp);
			spacePlagueStatus.setText(String.format("Has space plague: %s", crew.get(i).hasSpacePlague()));
			spacePlagueStatus.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(spacePlagueStatus);
			
			JRadioButton rdbtnSelected = new JRadioButton("");
			sl_temp.putConstraint(SpringLayout.NORTH, rdbtnSelected, 0, SpringLayout.SOUTH, spacePlagueStatus);
			sl_temp.putConstraint(SpringLayout.WEST, rdbtnSelected, 0, SpringLayout.WEST, temp);
			sl_temp.putConstraint(SpringLayout.SOUTH, rdbtnSelected, 35, SpringLayout.SOUTH, spacePlagueStatus);
			sl_temp.putConstraint(SpringLayout.EAST, rdbtnSelected, panelWidth, SpringLayout.WEST, temp);
			rdbtnSelected.setActionCommand(Integer.toString(i));
			buttonGroup.add(rdbtnSelected);
			rdbtnSelected.setHorizontalAlignment(SwingConstants.CENTER);
			temp.add(rdbtnSelected);
			if (i == 0) {
				rdbtnSelected.setSelected(true);
			}
		}
		
		
		//Code that populates the panel with crew images
		frmUseItemScreen.pack();
		for (int i = 0; i < lblImages.size(); i++) {
			JLabel image = lblImages.get(i);
			image.setIcon(new ImageIcon(((CrewMemberExtended)crew.get(i)).getImage().getContents(image.getWidth(), image.getHeight())));
		}
		
		//Code to populate the panel with item in the inventory
		for (int i = 0; i < consumables.size(); i++) {
			Consumable item = consumables.get(i);
			JPanel itemSubPanel = new JPanel();
			itemSubPanel.setLayout(new GridLayout(1, 5, 0, 0));
			itemsInternal.add(itemSubPanel);
			
			JLabel lblName = new JLabel(String.format("<html><p style='text-align: center;'>%s</p></html>", item.getName()));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblName);
			
			JLabel lblType = new JLabel(String.format("<html><p style='text-align: center;'>Type: %s</p></html>", item.getItemType()));
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblType);
			
			JLabel lblEffectiveness = new JLabel(String.format("Effectiveness: %d", item.getPrice()));
			lblEffectiveness.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblEffectiveness);
			
			JLabel lblHeld = new JLabel(String.format("Held: %d", item.getHeld()));
			lblHeld.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(lblHeld);
			
			JRadioButton rdbtnSelected = new JRadioButton("");
			rdbtnSelected.setActionCommand(Integer.toString(i));
			itemButtonGroup.add(rdbtnSelected);
			rdbtnSelected.setHorizontalAlignment(SwingConstants.CENTER);
			itemSubPanel.add(rdbtnSelected);
			if (i == 0) {
				rdbtnSelected.setSelected(true);
			}
			
			}
		
	}
}
