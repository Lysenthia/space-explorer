package graphicalInterface;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.*;
import backendGUIExtensions.*;
import java.awt.Dimension;
import javax.swing.SpringLayout;

public class CrewSelection {

	private JFrame frame;
	private JTextField tfNameEntry;
	private JTextField tfNameEntry2;
	private JTextField tfNameEntry3;
	private JTextField tfNameEntry4;
	
	private ArrayList<GUIImage> possibleImages;
	private ArrayList<JLabel> crewImageLabels = new ArrayList<JLabel>();
	private ArrayList<JComboBox<String>> crewImageCB = new ArrayList<JComboBox<String>>();
	private ArrayList<JComboBox<String>> crewClassesCB = new ArrayList<JComboBox<String>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrewSelection window = new CrewSelection();
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
	public CrewSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] classNames = new String[CrewClass.values().length];
		for (int i = 0; i < classNames.length; i++) {
			classNames[i] = CrewClass.values()[i].getClassName();
		}
		try {
			CrewMemberImages.fetchImages();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		possibleImages = CrewMemberImages.getImages();
		
		frame = new JFrame();
		frame.getContentPane().setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panelCrew = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelCrew, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelCrew, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelCrew, 400, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelCrew, 794, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panelCrew);
		panelCrew.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panelMember = new JPanel();
		panelCrew.add(panelMember);
		SpringLayout sl_panelMember = new SpringLayout();
		panelMember.setLayout(sl_panelMember);
		
		JLabel lblImage = new JLabel("Image");
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
		
		JComboBox<String> cbImageSelect = new JComboBox<String>();
		cbImageSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Image"}));
		panelMemberOptions.add(cbImageSelect);
		
		tfNameEntry = new JTextField();
		tfNameEntry.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry.setText("Member Name");
		panelMemberOptions.add(tfNameEntry);
		tfNameEntry.setColumns(10);
		
		JComboBox<String> cbMemberClass = new JComboBox<String>();
		cbMemberClass.setModel(new DefaultComboBoxModel<String>(classNames));
		panelMemberOptions.add(cbMemberClass);
		
		JPanel panelMember2 = new JPanel();
		panelCrew.add(panelMember2);
		SpringLayout sl_panelMember2 = new SpringLayout();
		panelMember2.setLayout(sl_panelMember2);
		
		JLabel lblImage2 = new JLabel("Image");
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
		
		JComboBox<String> cbImageSelect2 = new JComboBox<String>();
		cbImageSelect2.setModel(new DefaultComboBoxModel<String>(new String[] {"Image"}));
		panelMember2Options.add(cbImageSelect2);
		
		tfNameEntry2 = new JTextField();
		tfNameEntry2.setText("Member Name");
		tfNameEntry2.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry2.setColumns(10);
		panelMember2Options.add(tfNameEntry2);
		
		JComboBox<String> cbMemberClass2 = new JComboBox<String>();
		cbMemberClass2.setModel(new DefaultComboBoxModel<String>(classNames));
		panelMember2Options.add(cbMemberClass2);
		
		JPanel panelMember3 = new JPanel();
		panelCrew.add(panelMember3);
		SpringLayout sl_panelMember3 = new SpringLayout();
		panelMember3.setLayout(sl_panelMember3);
		
		JLabel lblImage3 = new JLabel("Image");
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
		
		JComboBox<String> cbImageSelect3 = new JComboBox<String>();
		panelMember3Options.add(cbImageSelect3);
		
		tfNameEntry3 = new JTextField();
		tfNameEntry3.setText("Member Name");
		tfNameEntry3.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry3.setColumns(10);
		panelMember3Options.add(tfNameEntry3);
		
		JComboBox<String> cbMemberClass3 = new JComboBox<String>();
		cbMemberClass3.setModel(new DefaultComboBoxModel<String>(classNames));
		panelMember3Options.add(cbMemberClass3);
		
		JPanel panelMember4 = new JPanel();
		panelCrew.add(panelMember4);
		SpringLayout sl_panelMember4 = new SpringLayout();
		panelMember4.setLayout(sl_panelMember4);
		
		JLabel lblImage4 = new JLabel("Image");
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
		
		JComboBox<String> cbImageSelect4 = new JComboBox<String>();
		panelMember4Options.add(cbImageSelect4);
		
		tfNameEntry4 = new JTextField();
		tfNameEntry4.setText("Member Name");
		tfNameEntry4.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry4.setColumns(10);
		panelMember4Options.add(tfNameEntry4);
		
		JComboBox<String> cbMemberClass4 = new JComboBox<String>();
		cbMemberClass4.setModel(new DefaultComboBoxModel<String>(classNames));
		panelMember4Options.add(cbMemberClass4);
		
		JPanel panelOptions = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panelOptions, 400, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelOptions, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panelOptions, 572, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelOptions, 794, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panelOptions);
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
		
		crewImageCB.add(cbImageSelect);
		crewImageCB.add(cbImageSelect2);
		crewImageCB.add(cbImageSelect3);
		crewImageCB.add(cbImageSelect4);
		
		frame.pack();
		
		for (JLabel lbl : crewImageLabels) {
			lbl.setIcon(new ImageIcon(CrewMemberImages.getDefaultImage().getContents(lbl.getWidth(), lbl.getHeight())));
		}
		
		ArrayList<String> filenames = new ArrayList<String>(); 
		for (GUIImage image : CrewMemberImages.getImages() ) {
			filenames.add(image.getName().toString());
		}
		for (JComboBox<String> cb : crewImageCB) {
			cb.setModel(new DefaultComboBoxModel<String>(filenames.toArray(new String[filenames.size()])));
		}
		
		
	}

}
