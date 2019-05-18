package graphicalInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.*;
import backendGUIExtensions.CrewMemberExtended;
import backendGUIExtensions.CrewMemberImages;
import backendGUIExtensions.GUIImage;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GUI for crew selection
 * Allows player to create crew member and add them to the ship's crew
 * @author hoo42
 * @author rvo16
 *
 */
public class CrewSelection {

	private JFrame frmPleaseCustomiseYour;
	private JTextField tfNameEntry;
	private JTextField tfNameEntry2;
	private JTextField tfNameEntry3;
	private JTextField tfNameEntry4;
	
	private ArrayList<GUIImage> possibleImages;
	private ArrayList<JLabel> crewImageLabels = new ArrayList<JLabel>();
	private ArrayList<JComboBox<GUIImage>> crewImageCB = new ArrayList<JComboBox<GUIImage>>();
	private ArrayList<JComboBox<CrewClass>> crewClassesCB = new ArrayList<JComboBox<CrewClass>>();
	
	private int nameClicked = 0b0;

	/**
	 * Launch the application.
	 */
	public static void callScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrewSelection window = new CrewSelection();
					window.frmPleaseCustomiseYour.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrewSelection() {
		this.possibleImages = StartApplication.getPossibleCrewImages();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmPleaseCustomiseYour = new JFrame();
		frmPleaseCustomiseYour.setTitle("Please customise your crew");
		frmPleaseCustomiseYour.setPreferredSize(new Dimension(800, 600));
		frmPleaseCustomiseYour.getContentPane().setPreferredSize(new Dimension(800, 600));
		frmPleaseCustomiseYour.setResizable(false);
		frmPleaseCustomiseYour.setBounds(100, 100, 800, 600);
		frmPleaseCustomiseYour.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmPleaseCustomiseYour.getContentPane().setLayout(springLayout);
		
		JPanel panelCrew = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelCrew, 0, SpringLayout.NORTH, frmPleaseCustomiseYour.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelCrew, 0, SpringLayout.WEST, frmPleaseCustomiseYour.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelCrew, 400, SpringLayout.NORTH, frmPleaseCustomiseYour.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelCrew, 794, SpringLayout.WEST, frmPleaseCustomiseYour.getContentPane());
		frmPleaseCustomiseYour.getContentPane().add(panelCrew);
		panelCrew.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panelMember = new JPanel();
		panelCrew.add(panelMember);
		SpringLayout sl_panelMember = new SpringLayout();
		panelMember.setLayout(sl_panelMember);
		
		JLabel lblImage = new JLabel();
		sl_panelMember.putConstraint(SpringLayout.NORTH, lblImage, 0, SpringLayout.NORTH, panelMember);
		sl_panelMember.putConstraint(SpringLayout.WEST, lblImage, 0, SpringLayout.WEST, panelMember);
		sl_panelMember.putConstraint(SpringLayout.SOUTH, lblImage, 256, SpringLayout.NORTH, panelMember);
		sl_panelMember.putConstraint(SpringLayout.EAST, lblImage, 198, SpringLayout.WEST, panelMember);
		lblImage.setMinimumSize(new Dimension(30, 256));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember.add(lblImage);
		
		JPanel panelMemberOptions = new JPanel();
		sl_panelMember.putConstraint(SpringLayout.NORTH, panelMemberOptions, 256, SpringLayout.NORTH, panelMember);
		sl_panelMember.putConstraint(SpringLayout.WEST, panelMemberOptions, 0, SpringLayout.WEST, panelMember);
		sl_panelMember.putConstraint(SpringLayout.SOUTH, panelMemberOptions, 400, SpringLayout.NORTH, panelMember);
		sl_panelMember.putConstraint(SpringLayout.EAST, panelMemberOptions, 198, SpringLayout.WEST, panelMember);
		panelMember.add(panelMemberOptions);
		panelMemberOptions.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox<GUIImage> cbImageSelect = new JComboBox<GUIImage>();
		panelMemberOptions.add(cbImageSelect);
		
		tfNameEntry = new JTextField();
		tfNameEntry.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry.setText("Member Name");
		panelMemberOptions.add(tfNameEntry);
		tfNameEntry.setColumns(10);
		
		JComboBox<CrewClass> cbMemberClass = new JComboBox<CrewClass>();
	
		cbMemberClass.setModel(new DefaultComboBoxModel<CrewClass>(CrewClass.values()));
		cbMemberClass.setToolTipText(((CrewClass)cbMemberClass.getSelectedItem()).getDescription());
		panelMemberOptions.add(cbMemberClass);
		
		JPanel panelMember2 = new JPanel();
		panelCrew.add(panelMember2);
		SpringLayout sl_panelMember2 = new SpringLayout();
		panelMember2.setLayout(sl_panelMember2);
		
		JLabel lblImage2 = new JLabel();
		sl_panelMember2.putConstraint(SpringLayout.NORTH, lblImage2, 0, SpringLayout.NORTH, panelMember2);
		sl_panelMember2.putConstraint(SpringLayout.WEST, lblImage2, 0, SpringLayout.WEST, panelMember2);
		sl_panelMember2.putConstraint(SpringLayout.SOUTH, lblImage2, 256, SpringLayout.NORTH, panelMember2);
		sl_panelMember2.putConstraint(SpringLayout.EAST, lblImage2, 198, SpringLayout.WEST, panelMember2);
		lblImage2.setMinimumSize(new Dimension(30, 256));
		lblImage2.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember2.add(lblImage2);
		
		JPanel panelMember2Options = new JPanel();
		sl_panelMember2.putConstraint(SpringLayout.NORTH, panelMember2Options, 256, SpringLayout.NORTH, panelMember2);
		sl_panelMember2.putConstraint(SpringLayout.WEST, panelMember2Options, 0, SpringLayout.WEST, panelMember2);
		sl_panelMember2.putConstraint(SpringLayout.SOUTH, panelMember2Options, 400, SpringLayout.NORTH, panelMember2);
		sl_panelMember2.putConstraint(SpringLayout.EAST, panelMember2Options, 198, SpringLayout.WEST, panelMember2);
		panelMember2.add(panelMember2Options);
		panelMember2Options.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox<GUIImage> cbImageSelect2 = new JComboBox<GUIImage>();
		panelMember2Options.add(cbImageSelect2);
		
		tfNameEntry2 = new JTextField();
		tfNameEntry2.setText("Member Name");
		tfNameEntry2.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry2.setColumns(10);
		panelMember2Options.add(tfNameEntry2);
		
		JComboBox<CrewClass> cbMemberClass2 = new JComboBox<CrewClass>();
		cbMemberClass2.setModel(new DefaultComboBoxModel<CrewClass>(CrewClass.values()));
		cbMemberClass2.setToolTipText(((CrewClass)cbMemberClass2.getSelectedItem()).getDescription());
		panelMember2Options.add(cbMemberClass2);
		
		JPanel panelMember3 = new JPanel();
		panelCrew.add(panelMember3);
		SpringLayout sl_panelMember3 = new SpringLayout();
		panelMember3.setLayout(sl_panelMember3);
		
		JLabel lblImage3 = new JLabel();
		sl_panelMember3.putConstraint(SpringLayout.NORTH, lblImage3, 0, SpringLayout.NORTH, panelMember3);
		sl_panelMember3.putConstraint(SpringLayout.WEST, lblImage3, 0, SpringLayout.WEST, panelMember3);
		sl_panelMember3.putConstraint(SpringLayout.SOUTH, lblImage3, 256, SpringLayout.NORTH, panelMember3);
		sl_panelMember3.putConstraint(SpringLayout.EAST, lblImage3, 198, SpringLayout.WEST, panelMember3);
		lblImage3.setMinimumSize(new Dimension(30, 256));
		lblImage3.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember3.add(lblImage3);
		
		JPanel panelMember3Options = new JPanel();
		sl_panelMember3.putConstraint(SpringLayout.NORTH, panelMember3Options, 256, SpringLayout.NORTH, panelMember3);
		sl_panelMember3.putConstraint(SpringLayout.WEST, panelMember3Options, 0, SpringLayout.WEST, panelMember3);
		sl_panelMember3.putConstraint(SpringLayout.SOUTH, panelMember3Options, 400, SpringLayout.NORTH, panelMember3);
		sl_panelMember3.putConstraint(SpringLayout.EAST, panelMember3Options, 198, SpringLayout.WEST, panelMember3);
		panelMember3.add(panelMember3Options);
		panelMember3Options.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox<GUIImage> cbImageSelect3 = new JComboBox<GUIImage>();
		panelMember3Options.add(cbImageSelect3);
		
		tfNameEntry3 = new JTextField();
		tfNameEntry3.setText("Member Name");
		tfNameEntry3.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry3.setColumns(10);
		panelMember3Options.add(tfNameEntry3);
		
		JComboBox<CrewClass> cbMemberClass3 = new JComboBox<CrewClass>();
		cbMemberClass3.setModel(new DefaultComboBoxModel<CrewClass>(CrewClass.values()));
		cbMemberClass3.setToolTipText(((CrewClass)cbMemberClass3.getSelectedItem()).getDescription());
		panelMember3Options.add(cbMemberClass3);
		
		JPanel panelMember4 = new JPanel();
		panelCrew.add(panelMember4);
		SpringLayout sl_panelMember4 = new SpringLayout();
		panelMember4.setLayout(sl_panelMember4);
		
		JLabel lblImage4 = new JLabel();
		sl_panelMember4.putConstraint(SpringLayout.NORTH, lblImage4, 0, SpringLayout.NORTH, panelMember4);
		sl_panelMember4.putConstraint(SpringLayout.WEST, lblImage4, 0, SpringLayout.WEST, panelMember4);
		sl_panelMember4.putConstraint(SpringLayout.SOUTH, lblImage4, 256, SpringLayout.NORTH, panelMember4);
		sl_panelMember4.putConstraint(SpringLayout.EAST, lblImage4, 198, SpringLayout.WEST, panelMember4);
		lblImage4.setMinimumSize(new Dimension(256, 14));
		lblImage4.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember4.add(lblImage4);
		
		JPanel panelMember4Options = new JPanel();
		sl_panelMember4.putConstraint(SpringLayout.NORTH, panelMember4Options, 256, SpringLayout.NORTH, panelMember4);
		sl_panelMember4.putConstraint(SpringLayout.WEST, panelMember4Options, 0, SpringLayout.WEST, panelMember4);
		sl_panelMember4.putConstraint(SpringLayout.SOUTH, panelMember4Options, 400, SpringLayout.NORTH, panelMember4);
		sl_panelMember4.putConstraint(SpringLayout.EAST, panelMember4Options, 198, SpringLayout.WEST, panelMember4);
		panelMember4.add(panelMember4Options);
		panelMember4Options.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox<GUIImage> cbImageSelect4 = new JComboBox<GUIImage>();
		panelMember4Options.add(cbImageSelect4);
		
		tfNameEntry4 = new JTextField();
		tfNameEntry4.setText("Member Name");
		tfNameEntry4.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry4.setColumns(10);
		panelMember4Options.add(tfNameEntry4);
		
		JComboBox<CrewClass> cbMemberClass4 = new JComboBox<CrewClass>();
		cbMemberClass4.setModel(new DefaultComboBoxModel<CrewClass>(CrewClass.values()));
		cbMemberClass4.setToolTipText(((CrewClass)cbMemberClass4.getSelectedItem()).getDescription());
		panelMember4Options.add(cbMemberClass4);
		
		JPanel panelOptions = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelOptions, 400, SpringLayout.NORTH, frmPleaseCustomiseYour.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelOptions, 0, SpringLayout.WEST, frmPleaseCustomiseYour.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelOptions, 572, SpringLayout.NORTH, frmPleaseCustomiseYour.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelOptions, 794, SpringLayout.WEST, frmPleaseCustomiseYour.getContentPane());
		frmPleaseCustomiseYour.getContentPane().add(panelOptions);
		panelOptions.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCrewCount = new JLabel("How many crew members do you wish to have?");
		lblCrewCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrewCount.setHorizontalTextPosition(SwingConstants.CENTER);
		panelOptions.add(lblCrewCount);
		
		JSlider sliderCrewCount = new JSlider();
		sliderCrewCount.setMajorTickSpacing(1);
		sliderCrewCount.setBorder(new EmptyBorder(0, 100, 0, 100));
		sliderCrewCount.setMinimum(2);
		sliderCrewCount.setMaximum(4);
		sliderCrewCount.setPaintTicks(true);
		sliderCrewCount.setPaintLabels(true);
		panelOptions.add(sliderCrewCount);
		
		JButton btnContinue = new JButton("Continue");
		panelOptions.add(btnContinue);
		
		crewImageLabels.add(lblImage);
		crewImageLabels.add(lblImage2);
		crewImageLabels.add(lblImage3);
		crewImageLabels.add(lblImage4);
		
		crewClassesCB.add(cbMemberClass);
		crewClassesCB.add(cbMemberClass2);
		crewClassesCB.add(cbMemberClass3);
		crewClassesCB.add(cbMemberClass4);
		cbMemberClass.setToolTipText(((CrewClass)cbMemberClass.getSelectedItem()).getDescription());
		cbMemberClass2.setToolTipText(((CrewClass)cbMemberClass.getSelectedItem()).getDescription());
		cbMemberClass3.setToolTipText(((CrewClass)cbMemberClass.getSelectedItem()).getDescription());
		cbMemberClass4.setToolTipText(((CrewClass)cbMemberClass.getSelectedItem()).getDescription());
		
		crewImageCB.add(cbImageSelect);
		crewImageCB.add(cbImageSelect2);
		crewImageCB.add(cbImageSelect3);
		crewImageCB.add(cbImageSelect4);
		
		frmPleaseCustomiseYour.pack();
		
		cbMemberClass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cbMemberClass.setToolTipText(((CrewClass)cbMemberClass.getSelectedItem()).getDescription());
			}
		});
		
		cbMemberClass2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cbMemberClass2.setToolTipText(((CrewClass)cbMemberClass2.getSelectedItem()).getDescription());
			}
		});
		
		cbMemberClass3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cbMemberClass3.setToolTipText(((CrewClass)cbMemberClass3.getSelectedItem()).getDescription());
			}
		});
		
		cbMemberClass4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cbMemberClass4.setToolTipText(((CrewClass)cbMemberClass4.getSelectedItem()).getDescription());
			}
		});
		
		for (JLabel lbl : crewImageLabels) {
			lbl.setIcon(new ImageIcon(CrewMemberImages.getDefaultImage().getContents(lblImage.getWidth(), lblImage.getHeight())));
		}
		
		for (JComboBox<GUIImage> cb : crewImageCB) {
			cb.setModel(new DefaultComboBoxModel<GUIImage>(possibleImages.toArray(new GUIImage[possibleImages.size()])));
			cb.setSelectedItem(CrewMemberImages.getDefaultImage());
		}
		
		cbImageSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GUIImage selected = (GUIImage) cbImageSelect.getSelectedItem();
				lblImage.setIcon(new ImageIcon(selected.getContents(lblImage.getWidth(), lblImage.getHeight())));
			}
		});
		
		cbImageSelect2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GUIImage selected = (GUIImage) cbImageSelect2.getSelectedItem();
				lblImage2.setIcon(new ImageIcon(selected.getContents(lblImage.getWidth(), lblImage.getHeight())));
			}
		});
		
		cbImageSelect3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GUIImage selected = (GUIImage) cbImageSelect3.getSelectedItem();
				lblImage3.setIcon(new ImageIcon(selected.getContents(lblImage.getWidth(), lblImage.getHeight())));
			}
		});
		
		cbImageSelect4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				GUIImage selected = (GUIImage) cbImageSelect4.getSelectedItem();
				lblImage4.setIcon(new ImageIcon(selected.getContents(lblImage.getWidth(), lblImage.getHeight())));
			}
		});
		tfNameEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((nameClicked | 0b0111) == 0b0111) {
					tfNameEntry.setText("");
					nameClicked ^= 0b1000;
				}
			}
		});
		
		tfNameEntry2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((nameClicked | 0b1011) == 0b1011) {
					tfNameEntry2.setText("");
					nameClicked ^= 0b0100;
				}
			}
		});
		
		tfNameEntry3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((nameClicked | 0b1101) == 0b1101) {
					tfNameEntry3.setText("");
					nameClicked ^= 0b0010;
				}
			}
		});
		
		tfNameEntry4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((nameClicked | 0b1110) == 0b1110) {
					tfNameEntry4.setText("");
					nameClicked ^= 0b0001;
				}
			}
		});
		
		tfNameEntry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfNameEntry.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		
		tfNameEntry2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfNameEntry2.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		
		tfNameEntry3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfNameEntry3.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		
		tfNameEntry4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfNameEntry4.getText().length() >= 20) {
					e.consume();
				}
			}
		});
		
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int crewSize = sliderCrewCount.getValue();
				switch (crewSize) {
				case 4:
					if (tfNameEntry4.getText().replaceAll("\\s*","").equals("") || tfNameEntry4.getText() == null) {
						Ship.addCrewMember(new CrewMemberExtended("Eve", (CrewClass)cbMemberClass4.getSelectedItem(), (GUIImage)cbImageSelect4.getSelectedItem()));
					} else {
						Ship.addCrewMember(new CrewMemberExtended(tfNameEntry4.getText(), (CrewClass)cbMemberClass4.getSelectedItem(), (GUIImage)cbImageSelect4.getSelectedItem()));
					}
				case 3:
					if (tfNameEntry3.getText().replaceAll("\\s*","").equals("") || tfNameEntry3.getText() == null) {
						Ship.addCrewMember(new CrewMemberExtended("Charlie", (CrewClass)cbMemberClass3.getSelectedItem(), (GUIImage)cbImageSelect3.getSelectedItem()));
					} else {
						Ship.addCrewMember(new CrewMemberExtended(tfNameEntry3.getText(), (CrewClass)cbMemberClass3.getSelectedItem(), (GUIImage)cbImageSelect3.getSelectedItem()));
					}
				case 2:
					if (tfNameEntry2.getText().replaceAll("\\s*","").equals("") || tfNameEntry2.getText() == null) {
						Ship.addCrewMember(new CrewMemberExtended("Bob", (CrewClass)cbMemberClass2.getSelectedItem(), (GUIImage)cbImageSelect2.getSelectedItem()));
					} else {
						Ship.addCrewMember(new CrewMemberExtended(tfNameEntry2.getText(), (CrewClass)cbMemberClass2.getSelectedItem(), (GUIImage)cbImageSelect2.getSelectedItem()));
					}
					if (tfNameEntry.getText().replaceAll("\\s*","").equals("") || tfNameEntry.getText() == null) {
						Ship.addCrewMember(new CrewMemberExtended("Alice", (CrewClass)cbMemberClass.getSelectedItem(), (GUIImage)cbImageSelect.getSelectedItem()));
					} else {
						Ship.addCrewMember(new CrewMemberExtended(tfNameEntry.getText(), (CrewClass)cbMemberClass.getSelectedItem(), (GUIImage)cbImageSelect.getSelectedItem()));
					}
				}
				
				MainScreen.callScreen();
				frmPleaseCustomiseYour.dispose();
			}
		});
	}
}
