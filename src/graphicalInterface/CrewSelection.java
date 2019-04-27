package graphicalInterface;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.CrewClass;

public class CrewSelection {

	private JFrame frame;
	private JTextField tfNameEntry;
	private JTextField tfNameEntry2;
	private JTextField tfNameEntry3;
	private JTextField tfNameEntry4;

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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelCrew = new JPanel();
		frame.getContentPane().add(panelCrew);
		panelCrew.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panelMember = new JPanel();
		panelCrew.add(panelMember);
		panelMember.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember.add(lblImage);
		
		JPanel panelMemberOptions = new JPanel();
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
		panelMember2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblImage2 = new JLabel("Image");
		lblImage2.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember2.add(lblImage2);
		
		JPanel panelMember2Options = new JPanel();
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
		panelMember3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblImage3 = new JLabel("Image");
		lblImage3.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember3.add(lblImage3);
		
		JPanel panelMember3Options = new JPanel();
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
		panelMember4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblImage4 = new JLabel("Image");
		lblImage4.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember4.add(lblImage4);
		
		JPanel panelMember4Options = new JPanel();
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
	}

}
