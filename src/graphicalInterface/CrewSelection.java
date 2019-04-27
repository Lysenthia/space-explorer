package graphicalInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import backend.CrewClass;

public class CrewSelection {

	private JFrame frame;
	private JTextField tfNameEntry;

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
		
		JComboBox cbImageSelect = new JComboBox();
		cbImageSelect.setModel(new DefaultComboBoxModel(new String[] {"Image"}));
		panelMemberOptions.add(cbImageSelect);
		
		tfNameEntry = new JTextField();
		tfNameEntry.setHorizontalAlignment(SwingConstants.CENTER);
		tfNameEntry.setText("Member Name");
		panelMemberOptions.add(tfNameEntry);
		tfNameEntry.setColumns(10);
		
		JComboBox cbMemberClass = new JComboBox();
		cbMemberClass.setModel(new DefaultComboBoxModel(CrewClass.values()));
		panelMemberOptions.add(cbMemberClass);
		
		JPanel panelMember2 = new JPanel();
		panelCrew.add(panelMember2);
		panelMember2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblImage2 = new JLabel("Image");
		lblImage2.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember2.add(lblImage2);
		
		JPanel panelMember2Options = new JPanel();
		panelMember2.add(panelMember2Options);
		panelMember2Options.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelMember3 = new JPanel();
		panelCrew.add(panelMember3);
		panelMember3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblImage3 = new JLabel("Image");
		lblImage3.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember3.add(lblImage3);
		
		JPanel panelMember3Options = new JPanel();
		panelMember3.add(panelMember3Options);
		panelMember3Options.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelMember4 = new JPanel();
		panelCrew.add(panelMember4);
		panelMember4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblImage4 = new JLabel("Image");
		lblImage4.setHorizontalAlignment(SwingConstants.CENTER);
		panelMember4.add(lblImage4);
		
		JPanel panelMember4Options = new JPanel();
		panelMember4.add(panelMember4Options);
		panelMember4Options.setLayout(new GridLayout(1, 0, 0, 0));
		
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
