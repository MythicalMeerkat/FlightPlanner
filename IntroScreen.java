import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IntroScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtWelcomeToThe;
	private JTextField txtCreateANew;
	private JTextField txtLoadAnExisting;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public IntroScreen() {
		setBackground(Color.WHITE);
		setTitle("Projectile Flight Planner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{156, 104, 162, 0};
		gbl_contentPane.rowHeights = new int[]{22, 106, 22, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtWelcomeToThe = new JTextField();
		txtWelcomeToThe.setBackground(Color.WHITE);
		txtWelcomeToThe.setEditable(false);
		txtWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		txtWelcomeToThe.setText("Welcome to the Projectile Flight Planner");
		GridBagConstraints gbc_txtWelcomeToThe = new GridBagConstraints();
		gbc_txtWelcomeToThe.anchor = GridBagConstraints.NORTH;
		gbc_txtWelcomeToThe.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWelcomeToThe.insets = new Insets(0, 0, 5, 0);
		gbc_txtWelcomeToThe.gridwidth = 3;
		gbc_txtWelcomeToThe.gridx = 0;
		gbc_txtWelcomeToThe.gridy = 0;
		contentPane.add(txtWelcomeToThe, gbc_txtWelcomeToThe);
		txtWelcomeToThe.setColumns(10);
		
		txtCreateANew = new JTextField();
		txtCreateANew.setBackground(Color.WHITE);
		txtCreateANew.setEditable(false);
		txtCreateANew.setText("Create a New Flight Plan");
		GridBagConstraints gbc_txtCreateANew = new GridBagConstraints();
		gbc_txtCreateANew.anchor = GridBagConstraints.NORTH;
		gbc_txtCreateANew.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCreateANew.insets = new Insets(0, 0, 5, 5);
		gbc_txtCreateANew.gridx = 0;
		gbc_txtCreateANew.gridy = 2;
		contentPane.add(txtCreateANew, gbc_txtCreateANew);
		txtCreateANew.setColumns(10);
		
		txtLoadAnExisting = new JTextField();
		txtLoadAnExisting.setBackground(Color.WHITE);
		txtLoadAnExisting.setEditable(false);
		txtLoadAnExisting.setText("Load an Existing Flight Plan");
		GridBagConstraints gbc_txtLoadAnExisting = new GridBagConstraints();
		gbc_txtLoadAnExisting.insets = new Insets(0, 0, 5, 0);
		gbc_txtLoadAnExisting.anchor = GridBagConstraints.NORTH;
		gbc_txtLoadAnExisting.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLoadAnExisting.gridx = 2;
		gbc_txtLoadAnExisting.gridy = 2;
		contentPane.add(txtLoadAnExisting, gbc_txtLoadAnExisting);
		txtLoadAnExisting.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				FlightPlannerWindow PlannerWindow = new FlightPlannerWindow();
				PlannerWindow.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 4;
		contentPane.add(btnCreate, gbc_btnCreate);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				// TODO: Add Loading from a saved file
				FlightPlannerWindow PlannerWindow = new FlightPlannerWindow();
				PlannerWindow.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.gridx = 2;
		gbc_btnLoad.gridy = 4;
		contentPane.add(btnLoad, gbc_btnLoad);
	}
}
