package graphicalInterface;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ErrorWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void callScreen(String error, Exception trace) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorWindow window = new ErrorWindow(error, trace);
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
	public ErrorWindow(String error, Exception trace) {
		initialize(error, trace);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String error, Exception trace) {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel messagePanel = new JPanel();
		frame.getContentPane().add(messagePanel);
		messagePanel.setLayout(new CardLayout(0, 0));
		
		JPanel basicMessagePanel = new JPanel();
		messagePanel.add(basicMessagePanel, "name_39051669503860");
		basicMessagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblBasicMessage = new JLabel(error);
		basicMessagePanel.add(lblBasicMessage);
		lblBasicMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel stackTracePanel = new JPanel();
		messagePanel.add(stackTracePanel, "name_39007966828256");
		stackTracePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea taStackTrace = new JTextArea();
		taStackTrace.setEditable(false);
		taStackTrace.setText(trace.toString());
		stackTracePanel.add(taStackTrace);
		
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnConfirm = new JButton("Confirm");
		
		buttonPanel.add(btnConfirm);
		
		JButton btnTrace = new JButton("See Stack Trace");
		btnTrace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) messagePanel.getLayout();
				layout.last(messagePanel);
			}
		});
		buttonPanel.add(btnTrace);
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	

}
