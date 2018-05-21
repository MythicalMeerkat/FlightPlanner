/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * 05/20/2018
 * 
 * HelpGUI.java (GUI)
 * 
 * This file creates the Help/FAQ GUI that gives users instructions on how to use the program in addiotion to addressing certain missing features.
 * 
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class HelpGUI extends JFrame {


	private static final long serialVersionUID = 67L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpGUI frame = new HelpGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HelpGUI() {
		
		setResizable(false);
		setTitle("Help");
		setBounds(100, 100, 450, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Btn_Close = new JButton("Close");
		Btn_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		Btn_Close.setBounds(5, 555, 89, 23);
		contentPane.add(Btn_Close);
		
		JTextPane HelpTextBox = new JTextPane();
		HelpTextBox.setText("---Projectile \u2122 Flight Planner ALPHA Version 1.0---\r\n\r\nEnter the Waypoint Properties under the Flight Plan Summary Window/Waypoint Creation Section. (Distance from previous Waypoint, Ideal Speed during Waypoint, Desired Heading at Waypoint, and Altitude of Waypoint)\r\n\r\nYou also have the choice to have the Vertical Speed and ETA to the next waypoint calculated.\r\n\r\nClear the Flight Plan Data with the 'Reset All' Button.\r\n\r\nSimply type the Waypoint to be deleted under 'Waypoint Deletion' and press the \"Delete Waypoint Button\".\r\n\r\nNotes can be created and saved in the 'Create Notes' Window, just push the button.\r\n\r\n\r\n---FEATURES NOT YET IMPLEMENTED---\r\n\r\n-Load Flight Plan\r\n\r\n\r\n\r\n\r\n\r\n");
		HelpTextBox.setEditable(false);
		HelpTextBox.setBounds(5, 5, 417, 523);
		contentPane.add(HelpTextBox);
		
		JScrollPane scrollPane = new JScrollPane(HelpTextBox);
		scrollPane.setBounds(5, 5, 434, 539);
		contentPane.add(scrollPane);
	}
}
