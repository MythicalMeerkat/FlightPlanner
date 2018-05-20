import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.ListSelectionModel;

import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.JToolBar;

public class FlightPlannerWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private final DecimalFormat df = new DecimalFormat("#.##"); 

	ConversionData conversions = new ConversionData();
	private static ArrayList<Waypoint> WaypointList = new ArrayList<Waypoint>();
	
	private JPanel contentPane;
	private JLabel WaypointLabelTop;
	private JLabel DistanceLabelTop;
	

	private final static Waypoint DUMMYWAYPOINT = new Waypoint(" "," "," "," "," "," "," ");
	private BufferedWriter writer = null;
	
	private static JFileChooser chooser;
	
	private JTable table;
	private JTextField SpeedInput;
	private JTextField HeadingInput;
	private JTextField AltitudeInput;
	private JTextField DistanceInput;
	private JTextField WPTNumberLabel;
	private JLabel WarningLabel;
	private JTextField InputDeleteWaypoint;
	private JTextField TotalTimeField;
	private JTextField TotalDistanceTextField;
	
	private int current_row = 0;
	private double total_waypoint_time = 0;	
	private double total_distance = 0;
	
	public void addWaypoint(Waypoint pass)
	{
		WaypointList.add(pass);
	}
	
	// Used for Initializing the Table to prevent a runtime error
	//TODO: Find a better method for this as it is extremely inefficient
	
	public void redrawTableNull()
	{
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{0, null, null, null, null, null, null},
					{1, null, null, null, null, null, null},
					{2, null, null, null, null, null, null},
					{3, null, null, null, null, null, null},
					{4, null, null, null, null, null, null},
					{5, null, null, null, null, null, null},
					{6, null, null, null, null, null, null},
					{7, null, null, null, null, null, null},
					{8, null, null, null, null, null, null},
					{9, null, null, null, null, null, null},
					{10, null, null, null, null, null, null},
					{11, null, null, null, null, null, null},
					{12, null, null, null, null, null, null},
					{13, null, null, null, null, null, null},
					{14, null, null, null, null, null, null},
					{15, null, null, null, null, null, null},
					{16, null, null, null, null, null, null},
					{17, null, null, null, null, null, null},
					{18, null, null, null, null, null, null},
					{19, null, null, null, null, null, null},
					{20, null, null, null, null, null, null},
					{21, null, null, null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
			));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(69);
		table.getColumnModel().getColumn(1).setPreferredWidth(69);
		table.getColumnModel().getColumn(2).setPreferredWidth(69);
		table.getColumnModel().getColumn(3).setPreferredWidth(69);
		table.getColumnModel().getColumn(4).setPreferredWidth(69);
		table.getColumnModel().getColumn(5).setPreferredWidth(69);
	}
	
	public void fillListNull()
	{
		for(int i = 0; i < 22; ++i)
		{
		    WaypointList.add(i, DUMMYWAYPOINT);
		}
	}
	
	public void redrawTable()
	{
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{0, WaypointList.get(0).getDistToNext(), WaypointList.get(0).getHDG(), WaypointList.get(0).getALT(), WaypointList.get(0).getSpeed(), WaypointList.get(0).getETA(), WaypointList.get(0).getFPM()},
					{1, WaypointList.get(1).getDistToNext(), WaypointList.get(1).getHDG(), WaypointList.get(1).getALT(), WaypointList.get(1).getSpeed(), WaypointList.get(1).getETA(), WaypointList.get(1).getFPM()},
					{2, WaypointList.get(2).getDistToNext(), WaypointList.get(2).getHDG(), WaypointList.get(2).getALT(), WaypointList.get(2).getSpeed(), WaypointList.get(2).getETA(), WaypointList.get(2).getFPM()},
					{3, WaypointList.get(3).getDistToNext(), WaypointList.get(3).getHDG(), WaypointList.get(3).getALT(), WaypointList.get(3).getSpeed(), WaypointList.get(3).getETA(), WaypointList.get(3).getFPM()},
					{4, WaypointList.get(4).getDistToNext(), WaypointList.get(4).getHDG(), WaypointList.get(4).getALT(), WaypointList.get(4).getSpeed(), WaypointList.get(4).getETA(), WaypointList.get(4).getFPM()},
					{5, WaypointList.get(5).getDistToNext(), WaypointList.get(5).getHDG(), WaypointList.get(5).getALT(), WaypointList.get(5).getSpeed(), WaypointList.get(5).getETA(), WaypointList.get(5).getFPM()},
					{6, WaypointList.get(6).getDistToNext(), WaypointList.get(6).getHDG(), WaypointList.get(6).getALT(), WaypointList.get(6).getSpeed(), WaypointList.get(6).getETA(), WaypointList.get(6).getFPM()},
					{7, WaypointList.get(7).getDistToNext(), WaypointList.get(7).getHDG(), WaypointList.get(7).getALT(), WaypointList.get(7).getSpeed(), WaypointList.get(7).getETA(), WaypointList.get(7).getFPM()},
					{8, WaypointList.get(8).getDistToNext(), WaypointList.get(8).getHDG(), WaypointList.get(8).getALT(), WaypointList.get(8).getSpeed(), WaypointList.get(8).getETA(), WaypointList.get(8).getFPM()},
					{9, WaypointList.get(9).getDistToNext(), WaypointList.get(9).getHDG(), WaypointList.get(9).getALT(), WaypointList.get(9).getSpeed(), WaypointList.get(9).getETA(), WaypointList.get(9).getFPM()},
					{10, WaypointList.get(10).getDistToNext(), WaypointList.get(10).getHDG(), WaypointList.get(10).getALT(), WaypointList.get(10).getSpeed(), WaypointList.get(10).getETA(), WaypointList.get(10).getFPM()},
					{11, WaypointList.get(11).getDistToNext(), WaypointList.get(11).getHDG(), WaypointList.get(11).getALT(), WaypointList.get(11).getSpeed(), WaypointList.get(11).getETA(), WaypointList.get(11).getFPM()},
					{12, WaypointList.get(12).getDistToNext(), WaypointList.get(12).getHDG(), WaypointList.get(12).getALT(), WaypointList.get(12).getSpeed(), WaypointList.get(12).getETA(), WaypointList.get(12).getFPM()},
					{13, WaypointList.get(13).getDistToNext(), WaypointList.get(13).getHDG(), WaypointList.get(13).getALT(), WaypointList.get(13).getSpeed(), WaypointList.get(13).getETA(), WaypointList.get(13).getFPM()},
					{14, WaypointList.get(14).getDistToNext(), WaypointList.get(14).getHDG(), WaypointList.get(14).getALT(), WaypointList.get(14).getSpeed(), WaypointList.get(14).getETA(), WaypointList.get(14).getFPM()},
					{15, WaypointList.get(15).getDistToNext(), WaypointList.get(15).getHDG(), WaypointList.get(15).getALT(), WaypointList.get(15).getSpeed(), WaypointList.get(15).getETA(), WaypointList.get(15).getFPM()},
					{16, WaypointList.get(16).getDistToNext(), WaypointList.get(16).getHDG(), WaypointList.get(16).getALT(), WaypointList.get(16).getSpeed(), WaypointList.get(16).getETA(), WaypointList.get(16).getFPM()},
					{17, WaypointList.get(17).getDistToNext(), WaypointList.get(17).getHDG(), WaypointList.get(17).getALT(), WaypointList.get(17).getSpeed(), WaypointList.get(17).getETA(), WaypointList.get(17).getFPM()},
					{18, WaypointList.get(18).getDistToNext(), WaypointList.get(18).getHDG(), WaypointList.get(18).getALT(), WaypointList.get(18).getSpeed(), WaypointList.get(18).getETA(), WaypointList.get(18).getFPM()},
					{19, WaypointList.get(19).getDistToNext(), WaypointList.get(19).getHDG(), WaypointList.get(19).getALT(), WaypointList.get(19).getSpeed(), WaypointList.get(19).getETA(), WaypointList.get(19).getFPM()},
					{20, WaypointList.get(20).getDistToNext(), WaypointList.get(20).getHDG(), WaypointList.get(20).getALT(), WaypointList.get(20).getSpeed(), WaypointList.get(20).getETA(), WaypointList.get(20).getFPM()},
					{21, WaypointList.get(21).getDistToNext(), WaypointList.get(21).getHDG(), WaypointList.get(21).getALT(), WaypointList.get(21).getSpeed(), WaypointList.get(21).getETA(), WaypointList.get(21).getFPM()},
				},
				new String[] {
					"New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
			));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(69);
		table.getColumnModel().getColumn(1).setPreferredWidth(69);
		table.getColumnModel().getColumn(2).setPreferredWidth(69);
		table.getColumnModel().getColumn(3).setPreferredWidth(69);
		table.getColumnModel().getColumn(4).setPreferredWidth(69);
		table.getColumnModel().getColumn(5).setPreferredWidth(69);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					FlightPlannerWindow frame = new FlightPlannerWindow();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FlightPlannerWindow() {
		
		chooser =  new JFileChooser();
		fillListNull(); // NEEDED TO PREVENT A NULL BOUNDS EXCEPTION
		setResizable(false);
		setTitle("Projectile\u2122 Flight Plan Summary ALPHA V 1.00");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1123, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Alerts user when attempting to delete non existent cell
		
		JLabel DeleteWarningLabel = new JLabel("");
		DeleteWarningLabel.setBounds(683, 302, 424, 14);
		DeleteWarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(DeleteWarningLabel);
		
		// Determines of the ETA is calculate for current way-point
		
		JCheckBox CalculateEtaCheck = new JCheckBox("Calculate Vert. Speed/ETA");
		CalculateEtaCheck.setBounds(839, 177, 218, 23);
		CalculateEtaCheck.setSelected(true);
		contentPane.add(CalculateEtaCheck);
		
		// Create the Way-point
		
		JButton CreateWPTButton = new JButton("Add Waypoint");
		CreateWPTButton.setBounds(20, 485, 118, 23);
		CreateWPTButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteWarningLabel.setText("");
				if(current_row < 22)
				{
					if(CalculateEtaCheck.isSelected() == true)
					{
						String ETA = conversions.calculateETA(DistanceInput.getText(),SpeedInput.getText());

						if(current_row == 0  || Integer.parseInt(DistanceInput.getText()) == 0)
						{
						
							Waypoint waypoint = new Waypoint((String.valueOf(current_row)),AltitudeInput.getText(),"0", SpeedInput.getText(),DistanceInput.getText(),"0",HeadingInput.getText());
							WaypointList.add(current_row, waypoint);
						}
						
						else
						{
							String altDifference = String.valueOf((Double.parseDouble(AltitudeInput.getText()) - Double.parseDouble(WaypointList.get(current_row - 1).getALT())));
							Waypoint waypoint = new Waypoint((String.valueOf(current_row)),AltitudeInput.getText(),conversions.calculateVertSpeed(altDifference, ETA), SpeedInput.getText(),DistanceInput.getText(),ETA,HeadingInput.getText());
							WaypointList.add(current_row, waypoint);
							total_waypoint_time += Double.parseDouble(ETA);
						}
					}
				
				else
				{
					Waypoint waypoint = new Waypoint((String.valueOf(current_row)),AltitudeInput.getText(),"N/C", SpeedInput.getText(),DistanceInput.getText(),"N/C",HeadingInput.getText());
					WaypointList.add(current_row, waypoint);
				}
				total_distance += Double.parseDouble(WaypointList.get(current_row).getDistToNext());
				current_row++;
				WPTNumberLabel.setText(String.valueOf((current_row)));
				TotalTimeField.setText(String.valueOf(df.format((total_waypoint_time))));
				TotalDistanceTextField.setText(String.valueOf(df.format(total_distance)));
				redrawTable();
				}
				else{
					WarningLabel = new JLabel("");
					WarningLabel.setBounds(754, 283, 353, 14);
					contentPane.add(WarningLabel);
					WarningLabel.setText("Too Many Waypoints!");
				}
				
			}
		});
		contentPane.add(CreateWPTButton);
		
		// Delete a way-point
		
		JButton DeleteWaypointButton = new JButton("Delete Waypoint");
		DeleteWaypointButton.setBounds(229, 485, 132, 23);
		DeleteWaypointButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( (!(InputDeleteWaypoint.getText().isEmpty() )) && Integer.parseInt(InputDeleteWaypoint.getText()) >= 0 && Integer.parseInt(InputDeleteWaypoint.getText()) < current_row )
				{
					
					DeleteWarningLabel.setText("");
					total_waypoint_time -= Double.parseDouble(WaypointList.get(Integer.parseInt(InputDeleteWaypoint.getText())).getETA());
					total_distance -= Double.parseDouble(WaypointList.get(Integer.parseInt(InputDeleteWaypoint.getText())).getDistToNext());
					WaypointList.remove(Integer.parseInt(InputDeleteWaypoint.getText()));
					TotalTimeField.setText(String.valueOf(df.format((total_waypoint_time))));
					TotalDistanceTextField.setText(String.valueOf(df.format(total_distance)));
					redrawTable();
					current_row--;
					WPTNumberLabel.setText(String.valueOf(current_row));
				}
				else{
					DeleteWarningLabel.setText("Cannot delete cell that does not exist.");
				}

				
			}
		});
		contentPane.add(DeleteWaypointButton);
		
		// Will allow user to Save the flight plan
		
		JButton SavePlanButton = new JButton("Save Flight Plan");
		SavePlanButton.setEnabled(true);
		SavePlanButton.setBounds(450, 419, 124, 23);
		SavePlanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int returnVal = chooser.showSaveDialog(null);
				File file = chooser.getSelectedFile();

				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
				    try
				    {
						writer = new BufferedWriter( new FileWriter( file.getAbsolutePath() + ".txt" ));
						writer.write("Flight Plan Name: ");
						writer.newLine();
						writer.newLine();
						writer.newLine();
						writer.newLine();
						writer.write("Waypoint | " + "Distance from Previous | " + "HDG from Previous | " + "Altitude | " + "Speed | " + "E.T.A. from Previous | " + "Vertical Speed(FPM) |");
						writer.newLine();
						writer.write("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						writer.newLine();
						
				    	for( int waypoint = 0; waypoint < current_row; ++waypoint )
				    	{
				    		writer.write( String.valueOf(waypoint) + "                           ");
				    		writer.write( WaypointList.get(waypoint).getDistToNext() + "                                         ");
				    		writer.write( WaypointList.get(waypoint).getHDG() + "                                 ");
				    		writer.write( WaypointList.get(waypoint).getALT() + "               ");
				    		writer.write( WaypointList.get(waypoint).getSpeed() + "                     ");
				    		writer.write( WaypointList.get(waypoint).getETA() + "                           ");
				    		writer.write( WaypointList.get(waypoint).getFPM());
				    		writer.newLine();
				    		writer.write("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				    		writer.newLine();
				    	}
				    	writer.newLine();
				    	writer.write("Notes: ");
				    	writer.close( );
				    }
				    catch (IOException e1)
				    {
				    JOptionPane.showMessageDialog(null, "ERROR: Could not be saved!", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
				    dispose();
				    }
				}
			}
		});
		contentPane.add(SavePlanButton);
		
		// Clears all data for user
		
		JButton ClearDataButton = new JButton("Reset All");
		ClearDataButton.setBounds(20, 419, 118, 23);
		ClearDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteWarningLabel.setText("");
				current_row = 0;
				total_waypoint_time = 0;
				TotalTimeField.setText(String.valueOf(df.format((total_waypoint_time))));
				WPTNumberLabel.setText(String.valueOf((current_row)));
				SpeedInput.setText("0");
				HeadingInput.setText("0");
				AltitudeInput.setText("0");
				DistanceInput.setText("0");
				InputDeleteWaypoint.setText("0");
				fillListNull();
				table.validate();
				redrawTableNull();
				
			}
		});
		contentPane.add(ClearDataButton);
		
		// Display Table
		
		table = new JTable();
		table.setBounds(20, 42, 653, 352);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		redrawTableNull();
		contentPane.add(table);
		
		// Displays Help Window
		
		JButton HelpButton = new JButton("Help");
		HelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpGUI Help_GUI_Frame = new HelpGUI();
				Help_GUI_Frame.setAlwaysOnTop(true);
				Help_GUI_Frame.setVisible(true);
			}
		});
		HelpButton.setBounds(450, 485, 124, 23);
		contentPane.add(HelpButton);
		
		// Displays the Notes Window
		
		JButton NotesButton = new JButton("Notes");
		NotesButton.setBounds(229, 419, 132, 23);
		NotesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NotesGUI Notes_GUI_Frame = new NotesGUI();
				Notes_GUI_Frame.setAlwaysOnTop(true);
				Notes_GUI_Frame.setVisible(true);;
			}
		});
		contentPane.add(NotesButton);
		
		// TOP Column Labels
		
		WaypointLabelTop = new JLabel();
		WaypointLabelTop.setBounds(10, 11, 86, 20);
		WaypointLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		WaypointLabelTop.setText("Waypoint");
		contentPane.add(WaypointLabelTop);
				
		DistanceLabelTop = new JLabel();
		DistanceLabelTop.setBounds(109, 11, 86, 20);
		DistanceLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		DistanceLabelTop.setText("Distance(NM)");
		contentPane.add(DistanceLabelTop);
				
		JLabel HDGLabelTop = new JLabel("Heading");
		HDGLabelTop.setBounds(229, 14, 46, 14);
		HDGLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(HDGLabelTop);
				
		JLabel AltLabelTop = new JLabel("Altitude");
		AltLabelTop.setBounds(315, 14, 46, 14);
		AltLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(AltLabelTop);
				
		JLabel SpeedLabelTop = new JLabel("Speed(GRD)");
		SpeedLabelTop.setBounds(394, 14, 91, 14);
		SpeedLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(SpeedLabelTop);
				
		JLabel ETALabelTop = new JLabel("ETA(MIN)");
		ETALabelTop.setBounds(495, 14, 68, 14);
		ETALabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ETALabelTop);
				
		JLabel VSLabelTop = new JLabel("Vert. Speed(FPM)");
		VSLabelTop.setBounds(581, 14, 104, 14);
		VSLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(VSLabelTop);
				
		JLabel NotesLabelTop = new JLabel("Waypoint Creation");
		NotesLabelTop.setBounds(853, 14, 110, 14);
		NotesLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(NotesLabelTop);		
				
		JLabel SpeedLabelCreate = new JLabel("Speed(KIAS):");
		SpeedLabelCreate.setBounds(822, 42, 97, 14);
		SpeedLabelCreate.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(SpeedLabelCreate);
				
		JLabel HeadingLabelCreate = new JLabel("Heading:");
		HeadingLabelCreate.setBounds(853, 73, 66, 14);
		HeadingLabelCreate.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(HeadingLabelCreate);
				
		JLabel AltitudeLabelSide = new JLabel("Altitude(FT):");
		AltitudeLabelSide.setBounds(849, 107, 70, 14);
		AltitudeLabelSide.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(AltitudeLabelSide);
				
		JLabel DistanceLabelSide = new JLabel("Distance(NM):");
		DistanceLabelSide.setBounds(833, 146, 86, 14);
		DistanceLabelSide.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(DistanceLabelSide);
		
		// Way-point Data input fields
		
		SpeedInput = new JTextField();
		SpeedInput.setBounds(929, 39, 86, 20);
		SpeedInput.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedInput.setText("0");
		contentPane.add(SpeedInput);
		SpeedInput.setColumns(10);
		
		HeadingInput = new JTextField();
		HeadingInput.setBounds(929, 70, 86, 20);
		HeadingInput.setHorizontalAlignment(SwingConstants.CENTER);
		HeadingInput.setText("0");
		contentPane.add(HeadingInput);
		HeadingInput.setColumns(10);
		
		AltitudeInput = new JTextField();
		AltitudeInput.setBounds(929, 104, 86, 20);
		AltitudeInput.setHorizontalAlignment(SwingConstants.CENTER);
		AltitudeInput.setText("0");
		contentPane.add(AltitudeInput);
		AltitudeInput.setColumns(10);
		
		DistanceInput = new JTextField();
		DistanceInput.setBounds(929, 140, 86, 20);
		DistanceInput.setHorizontalAlignment(SwingConstants.CENTER);
		DistanceInput.setText("0");
		contentPane.add(DistanceInput);
		DistanceInput.setColumns(10);
		
		WPTNumberLabel = new JTextField();
		WPTNumberLabel.setBounds(929, 349, 86, 20);
		WPTNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WPTNumberLabel.setText(String.valueOf((current_row)));
		WPTNumberLabel.setEditable(false);
		WPTNumberLabel.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(WPTNumberLabel);
		
		InputDeleteWaypoint = new JTextField();
		InputDeleteWaypoint.setBounds(929, 274, 86, 20);
		InputDeleteWaypoint.setHorizontalAlignment(SwingConstants.CENTER);
		InputDeleteWaypoint.setText("0");
		contentPane.add(InputDeleteWaypoint);
		InputDeleteWaypoint.setColumns(10);
		
		TotalTimeField = new JTextField();
		TotalTimeField.setEditable(false);
		TotalTimeField.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTimeField.setText("0");
		TotalTimeField.setBounds(929, 377, 86, 20);
		contentPane.add(TotalTimeField);
		TotalTimeField.setColumns(10);
		
		// Displays Current Way-point the User is editing
		
		JLabel WaypointNumberSode = new JLabel("Current WPT:");
		WaypointNumberSode.setHorizontalAlignment(SwingConstants.RIGHT);
		WaypointNumberSode.setBounds(839, 352, 80, 14);
		contentPane.add(WaypointNumberSode);
		
		// Used to display relevant warnings to the user
		
		JLabel WarningLabel = new JLabel("");
		WarningLabel.setBounds(683, 207, 434, 14);
		WarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(WarningLabel);
		
		// Outlines the fields for Deleting a Way-point
		
		JLabel lblWaypointDeletion = new JLabel("Waypoint Deletion");
		lblWaypointDeletion.setBounds(853, 244, 132, 14);
		lblWaypointDeletion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWaypointDeletion);
		
		// Label for directly deleting way-point
		
		JLabel lblDeleteWaypoint = new JLabel("Delete WPT:");
		lblDeleteWaypoint.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeleteWaypoint.setBounds(822, 277, 97, 14);
		contentPane.add(lblDeleteWaypoint);
		
		// Outlines fields for the overall info regarding the flight plan
		
		JLabel PlanInfo = new JLabel("Plan Info");
		PlanInfo.setBounds(897, 327, 88, 14);
		contentPane.add(PlanInfo);
		
		// Labels the field showing the total time of the flight plan
		
		JLabel LabelTotalTime = new JLabel("Total Time:");
		LabelTotalTime.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelTotalTime.setBounds(839, 380, 80, 14);
		contentPane.add(LabelTotalTime);
		
		JLabel lblTotalDistance = new JLabel("Total Distance(NM):");
		lblTotalDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalDistance.setBounds(801, 409, 118, 14);
		contentPane.add(lblTotalDistance);
		
		TotalDistanceTextField = new JTextField();
		TotalDistanceTextField.setHorizontalAlignment(SwingConstants.CENTER);
		TotalDistanceTextField.setText("0");
		TotalDistanceTextField.setEditable(false);
		TotalDistanceTextField.setBounds(929, 408, 86, 20);
		contentPane.add(TotalDistanceTextField);
		TotalDistanceTextField.setColumns(10);
		
		JLabel lblCreatedBy = new JLabel("Created By: ");
		lblCreatedBy.setBounds(822, 494, 68, 14);
		contentPane.add(lblCreatedBy);
		
		JLabel lblJeffreyAWilson = new JLabel("Jeffrey A. Wilson");
		lblJeffreyAWilson.setBounds(917, 494, 98, 14);
		contentPane.add(lblJeffreyAWilson);
		
	
	}
}
