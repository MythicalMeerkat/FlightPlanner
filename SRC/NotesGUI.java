/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * Created: 05/20/2018
 * 
 * NotesGUI.java (GUI)
 * 
 * This file creates the Note Taking/Loading GUI, allowing users to recod important information regarding their flight plan.
 * 
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;


public class NotesGUI extends JFrame {

	private static final long serialVersionUID = FlightPlannerWindow.serialVersionUID;
	private JPanel contentPane;
	private static JTextArea NotesTextBox;	
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotesGUI frame = new NotesGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NotesGUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jeffrey\\Desktop\\FlightPlanner\\SOURCE\\AeroNavigationPlanner\\src\\FlightPlannerICON.png"));
		
		NotesTextBox  = new JTextArea();
		setTitle("Note Editor");
		setBounds(100, 100, 960, 785);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// LOADING NOTES
		
		JMenuItem mntmLoadNotes = new JMenuItem("Load Notes");
		mntmLoadNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightPlannerWindow.DataIO.loadInTextFile(NotesTextBox);
			}
		});
		mnFile.add(mntmLoadNotes);
		
		// SAVING NOTES
		
		JMenuItem mntmSaveNotes = new JMenuItem("Save Notes");
		mntmSaveNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightPlannerWindow.DataIO.writeOutTextFile(NotesTextBox);
			}
		});
		mnFile.add(mntmSaveNotes);
		
		// EXIT THE WINDOW
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUserGuide = new JMenuItem("User Guide");
		mntmUserGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpGUI Help_GUI_Frame = new HelpGUI();
				Help_GUI_Frame.setAlwaysOnTop(true);
				Help_GUI_Frame.setVisible(true);
			}
		});
		mnHelp.add(mntmUserGuide);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 957, 735);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(NotesTextBox);
	}
}
