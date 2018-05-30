/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * Created: 05/20/2018
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
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class HelpGUI extends JFrame {

	private static final long serialVersionUID = FlightPlannerWindow.serialVersionUID;
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
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jeffrey\\Desktop\\FlightPlanner\\SOURCE\\AeroNavigationPlanner\\src\\FlightPlannerICON.png"));
		
		setResizable(false);
		setTitle("Flight Planner Guide");
		setBounds(100, 100, 450, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane HelpTextBox = new JTextPane();
		HelpTextBox.setText("\t\t--- Flight Planner  ---\r\n\r\nGENERAL:\r\n\r\n-Hover over items to reveal tips on how to use them.\r\n\r\n-Clicking 'Export', located in the 'File' tab, will save the current plan as a .txt file.\r\n\r\n-Clicking 'Save Project', located in the 'File' tab, will save the current plan as a .fpp file.  (Only to be used for loading a flight plan into the application)\r\n\r\n\r\n\r\nUSING THE FLIGHT PLANNER:\r\n\r\n-Enter the Waypoint details in the Waypoint Creation Section. \r\n\r\n-Select the option to \"Calculate Vert. Speed/ETA\" if you would like to know the info.\r\n (Not selecting the option will show \"N/C\" for the ETA AND VERTICAL SPEED)\r\n\r\n-Clear the Flight Plan Data by choosing 'File' > 'New Project'\r\n\r\n-Type the Waypoint number to be deleted under 'Waypoint Deletion' and press the \"Delete  Waypoint Button\".\r\n\r\n\r\n\r\nADDITIONAL FEATURES:\r\n\r\n-Notes can be created and saved in the 'Create Notes' Window.\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
		HelpTextBox.setEditable(false);
		HelpTextBox.setBounds(1, 1, 432, 609);
		contentPane.add(HelpTextBox);
		
		JScrollPane scrollPane = new JScrollPane(HelpTextBox);
		scrollPane.setBounds(5, 5, 434, 584);
		contentPane.add(scrollPane);
	}
}
