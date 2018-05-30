/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * Created: 05/20/2018
 * 
 * IntroScreen.java (GUI)
 * 
 * This file creates the simple intro screen that allows users to eighter create a new flight plan, or load an existing flight plan.
 * 
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Toolkit;

public class IntroScreen extends JFrame {

	
	private static final long serialVersionUID = FlightPlannerWindow.serialVersionUID;
	private JPanel contentPane;
	private JLabel txtWelcomeToThe;
	private JLabel txtCreateANew;
	private JLabel txtLoadAnExisting;
	
	public static boolean loadPlanIn = false;
	
	@SuppressWarnings("unused")
	private Data_IO_PACKER Data_LOADER;

	// Create the Frame
	public IntroScreen() {
		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jeffrey\\Desktop\\FlightPlanner\\SOURCE\\AeroNavigationPlanner\\src\\FlightPlannerICON.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Flight Planner ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{151, 109, 162, 0};
		gbl_contentPane.rowHeights = new int[]{37, 14, 120, 14, 35, 0, 0, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtWelcomeToThe = new JLabel();
		txtWelcomeToThe.setForeground(Color.BLACK);
		txtWelcomeToThe.setBackground(Color.LIGHT_GRAY);
		txtWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		txtWelcomeToThe.setText("Welcome to the Projectile Studios Flight Planner");
		GridBagConstraints gbc_txtWelcomeToThe = new GridBagConstraints();
		gbc_txtWelcomeToThe.anchor = GridBagConstraints.NORTH;
		gbc_txtWelcomeToThe.insets = new Insets(0, 0, 5, 0);
		gbc_txtWelcomeToThe.gridwidth = 3;
		gbc_txtWelcomeToThe.gridx = 0;
		gbc_txtWelcomeToThe.gridy = 1;
		contentPane.add(txtWelcomeToThe, gbc_txtWelcomeToThe);
		
		txtCreateANew = new JLabel();
		txtCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreateANew.setBackground(Color.LIGHT_GRAY);
		txtCreateANew.setText("Create a New Flight Plan");
		GridBagConstraints gbc_txtCreateANew = new GridBagConstraints();
		gbc_txtCreateANew.anchor = GridBagConstraints.NORTH;
		gbc_txtCreateANew.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCreateANew.insets = new Insets(0, 0, 5, 5);
		gbc_txtCreateANew.gridx = 0;
		gbc_txtCreateANew.gridy = 3;
		contentPane.add(txtCreateANew, gbc_txtCreateANew);
		
		txtLoadAnExisting = new JLabel();
		txtLoadAnExisting.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoadAnExisting.setBackground(Color.LIGHT_GRAY);
		txtLoadAnExisting.setText("Load an Existing Flight Plan");
		GridBagConstraints gbc_txtLoadAnExisting = new GridBagConstraints();
		gbc_txtLoadAnExisting.anchor = GridBagConstraints.NORTH;
		gbc_txtLoadAnExisting.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLoadAnExisting.insets = new Insets(0, 0, 5, 0);
		gbc_txtLoadAnExisting.gridx = 2;
		gbc_txtLoadAnExisting.gridy = 3;
		contentPane.add(txtLoadAnExisting, gbc_txtLoadAnExisting);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setToolTipText("Create a new Flight Plan");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FlightPlannerWindow PlannerWindow = new FlightPlannerWindow(loadPlanIn);
				PlannerWindow.setVisible(true);
				dispose();
			}
		});
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.anchor = GridBagConstraints.NORTH;
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 4;
		contentPane.add(btnCreate, gbc_btnCreate);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setToolTipText("Load a FlightPlan (.fpp) ");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				loadPlanIn = true;
				FlightPlannerWindow PlannerWindow = new FlightPlannerWindow(loadPlanIn);
				PlannerWindow.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoad.anchor = GridBagConstraints.NORTH;
		gbc_btnLoad.gridx = 2;
		gbc_btnLoad.gridy = 4;
		contentPane.add(btnLoad, gbc_btnLoad);
	}
	
	// Launch the Application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroScreen frame = new IntroScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
