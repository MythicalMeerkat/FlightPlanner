/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * 05/20/2018
 * 
 * FlightPlannerWindow.java (GUI)
 * 
 * This file creates the GUI of the Main Flight Planner Window and uses FLightPlanData.java to populate the gui and reference the list of waypoints.
 * 
 */

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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import java.awt.Color;

public class FlightPlannerWindow extends JFrame {
	
	private FlightPlanData MASTER_FLIGHT_PLAN = new FlightPlanData(); // Holds the FLight Plan with ALL Waypoints
	
	private static final long serialVersionUID = 67L; 
	
	private final DecimalFormat DF = new DecimalFormat("#.##"); 

	ConversionData conversions = new ConversionData();
	
	private JPanel contentPane;
	private JLabel WaypointLabelTop;
	private JLabel DistanceLabelTop;
	
	private BufferedWriter writer = null;
	
	private static JFileChooser chooser;
	
	private JTable WaypointTable;
	private JTextField SpeedInput;
	private JTextField HeadingInput;
	private JTextField AltitudeInput;
	private JTextField DistanceInput;
	private JTextField WPTNumberLabel;
	private JTextField InputDeleteWaypoint;
	private JTextField TotalTimeField;
	private JTextField TotalDistanceTextField;
	
	private int current_row = 0;

/*
    Function: DrawTable() (void)

    Author(s): Jeff Wilson
    05/20/18

    NO PARAMETERS
    Returns: nothing; creates the table and draws it on the user's window
*/

	private void DrawTable()
	{
		WaypointTable.setModel(new DefaultTableModel(
				new Object[][] {
					{0, MASTER_FLIGHT_PLAN.getWPT(0).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(0).getHDG(), MASTER_FLIGHT_PLAN.getWPT(0).getALT(), MASTER_FLIGHT_PLAN.getWPT(0).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(0).getETA(), MASTER_FLIGHT_PLAN.getWPT(0).getFPM()},
					{1, MASTER_FLIGHT_PLAN.getWPT(1).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(1).getHDG(), MASTER_FLIGHT_PLAN.getWPT(1).getALT(), MASTER_FLIGHT_PLAN.getWPT(1).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(1).getETA(), MASTER_FLIGHT_PLAN.getWPT(1).getFPM()},
					{2, MASTER_FLIGHT_PLAN.getWPT(2).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(2).getHDG(), MASTER_FLIGHT_PLAN.getWPT(2).getALT(), MASTER_FLIGHT_PLAN.getWPT(2).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(2).getETA(), MASTER_FLIGHT_PLAN.getWPT(2).getFPM()},
					{3, MASTER_FLIGHT_PLAN.getWPT(3).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(3).getHDG(), MASTER_FLIGHT_PLAN.getWPT(3).getALT(), MASTER_FLIGHT_PLAN.getWPT(3).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(3).getETA(), MASTER_FLIGHT_PLAN.getWPT(3).getFPM()},
					{4, MASTER_FLIGHT_PLAN.getWPT(4).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(4).getHDG(), MASTER_FLIGHT_PLAN.getWPT(4).getALT(), MASTER_FLIGHT_PLAN.getWPT(4).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(4).getETA(), MASTER_FLIGHT_PLAN.getWPT(4).getFPM()},
					{5, MASTER_FLIGHT_PLAN.getWPT(5).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(5).getHDG(), MASTER_FLIGHT_PLAN.getWPT(5).getALT(), MASTER_FLIGHT_PLAN.getWPT(5).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(5).getETA(), MASTER_FLIGHT_PLAN.getWPT(5).getFPM()},
					{6, MASTER_FLIGHT_PLAN.getWPT(6).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(6).getHDG(), MASTER_FLIGHT_PLAN.getWPT(6).getALT(), MASTER_FLIGHT_PLAN.getWPT(6).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(6).getETA(), MASTER_FLIGHT_PLAN.getWPT(6).getFPM()},
					{7, MASTER_FLIGHT_PLAN.getWPT(7).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(7).getHDG(), MASTER_FLIGHT_PLAN.getWPT(7).getALT(), MASTER_FLIGHT_PLAN.getWPT(7).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(7).getETA(), MASTER_FLIGHT_PLAN.getWPT(7).getFPM()},
					{8, MASTER_FLIGHT_PLAN.getWPT(8).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(8).getHDG(), MASTER_FLIGHT_PLAN.getWPT(8).getALT(), MASTER_FLIGHT_PLAN.getWPT(8).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(8).getETA(), MASTER_FLIGHT_PLAN.getWPT(8).getFPM()},
					{9, MASTER_FLIGHT_PLAN.getWPT(9).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(9).getHDG(), MASTER_FLIGHT_PLAN.getWPT(9).getALT(), MASTER_FLIGHT_PLAN.getWPT(9).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(9).getETA(), MASTER_FLIGHT_PLAN.getWPT(9).getFPM()},
					{10, MASTER_FLIGHT_PLAN.getWPT(10).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(10).getHDG(), MASTER_FLIGHT_PLAN.getWPT(10).getALT(), MASTER_FLIGHT_PLAN.getWPT(10).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(10).getETA(), MASTER_FLIGHT_PLAN.getWPT(10).getFPM()},
					{11, MASTER_FLIGHT_PLAN.getWPT(11).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(11).getHDG(), MASTER_FLIGHT_PLAN.getWPT(11).getALT(), MASTER_FLIGHT_PLAN.getWPT(11).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(11).getETA(), MASTER_FLIGHT_PLAN.getWPT(11).getFPM()},
					{12, MASTER_FLIGHT_PLAN.getWPT(12).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(12).getHDG(), MASTER_FLIGHT_PLAN.getWPT(12).getALT(), MASTER_FLIGHT_PLAN.getWPT(12).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(12).getETA(), MASTER_FLIGHT_PLAN.getWPT(12).getFPM()},
					{13, MASTER_FLIGHT_PLAN.getWPT(13).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(13).getHDG(), MASTER_FLIGHT_PLAN.getWPT(13).getALT(), MASTER_FLIGHT_PLAN.getWPT(13).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(13).getETA(), MASTER_FLIGHT_PLAN.getWPT(13).getFPM()},
					{14, MASTER_FLIGHT_PLAN.getWPT(14).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(14).getHDG(), MASTER_FLIGHT_PLAN.getWPT(14).getALT(), MASTER_FLIGHT_PLAN.getWPT(14).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(14).getETA(), MASTER_FLIGHT_PLAN.getWPT(14).getFPM()},
					{15, MASTER_FLIGHT_PLAN.getWPT(15).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(15).getHDG(), MASTER_FLIGHT_PLAN.getWPT(15).getALT(), MASTER_FLIGHT_PLAN.getWPT(15).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(15).getETA(), MASTER_FLIGHT_PLAN.getWPT(15).getFPM()},
					{16, MASTER_FLIGHT_PLAN.getWPT(16).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(16).getHDG(), MASTER_FLIGHT_PLAN.getWPT(16).getALT(), MASTER_FLIGHT_PLAN.getWPT(16).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(16).getETA(), MASTER_FLIGHT_PLAN.getWPT(16).getFPM()},
					{17, MASTER_FLIGHT_PLAN.getWPT(17).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(17).getHDG(), MASTER_FLIGHT_PLAN.getWPT(17).getALT(), MASTER_FLIGHT_PLAN.getWPT(17).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(17).getETA(), MASTER_FLIGHT_PLAN.getWPT(17).getFPM()},
					{18, MASTER_FLIGHT_PLAN.getWPT(18).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(18).getHDG(), MASTER_FLIGHT_PLAN.getWPT(18).getALT(), MASTER_FLIGHT_PLAN.getWPT(18).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(18).getETA(), MASTER_FLIGHT_PLAN.getWPT(18).getFPM()},
					{19, MASTER_FLIGHT_PLAN.getWPT(19).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(19).getHDG(), MASTER_FLIGHT_PLAN.getWPT(19).getALT(), MASTER_FLIGHT_PLAN.getWPT(19).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(19).getETA(), MASTER_FLIGHT_PLAN.getWPT(19).getFPM()},
					{20, MASTER_FLIGHT_PLAN.getWPT(20).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(20).getHDG(), MASTER_FLIGHT_PLAN.getWPT(20).getALT(), MASTER_FLIGHT_PLAN.getWPT(20).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(20).getETA(), MASTER_FLIGHT_PLAN.getWPT(20).getFPM()},
					{21, MASTER_FLIGHT_PLAN.getWPT(21).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(21).getHDG(), MASTER_FLIGHT_PLAN.getWPT(21).getALT(), MASTER_FLIGHT_PLAN.getWPT(21).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(21).getETA(), MASTER_FLIGHT_PLAN.getWPT(21).getFPM()},
				},
				new String[] {
					"Waypoint", "Distance", "Heading", "Altitude", "Speed", "ETA", "Vert. Speed"
				}
			));
		
		WaypointTable.getColumnModel().getColumn(0).setPreferredWidth(69);
		WaypointTable.getColumnModel().getColumn(1).setPreferredWidth(69);
		WaypointTable.getColumnModel().getColumn(2).setPreferredWidth(69);
		WaypointTable.getColumnModel().getColumn(3).setPreferredWidth(69);
		WaypointTable.getColumnModel().getColumn(4).setPreferredWidth(69);
		WaypointTable.getColumnModel().getColumn(5).setPreferredWidth(69);
		
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
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop"));
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
		DeleteWarningLabel.setForeground(Color.RED);
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
				if(current_row < MASTER_FLIGHT_PLAN.getMaxListSize())
				{
					if(CalculateEtaCheck.isSelected() == true)
					{
						String ETA = conversions.calculateETA(DistanceInput.getText(),SpeedInput.getText());

						if(current_row == 0  || Integer.parseInt(DistanceInput.getText()) == 0)
						{
						
							MASTER_FLIGHT_PLAN.setWPT(current_row, new Waypoint((String.valueOf(current_row)),AltitudeInput.getText(),"0", SpeedInput.getText(),DistanceInput.getText(),"0",HeadingInput.getText()));
						}
						
						else
						{
							String altDifference = String.valueOf((Double.parseDouble(AltitudeInput.getText()) - Double.parseDouble(MASTER_FLIGHT_PLAN.getWPT(current_row - 1).getALT())));
							MASTER_FLIGHT_PLAN.setWPT(current_row, new Waypoint((String.valueOf(current_row)),AltitudeInput.getText(),conversions.calculateVertSpeed(altDifference, ETA), SpeedInput.getText(),DistanceInput.getText(),ETA,HeadingInput.getText()));
						}
					}
				
					else
					{
						MASTER_FLIGHT_PLAN.setWPT(current_row, new Waypoint((String.valueOf(current_row)),AltitudeInput.getText(),"N/C", SpeedInput.getText(),DistanceInput.getText(),"N/C",HeadingInput.getText()));
					}
					
				current_row++;
				WPTNumberLabel.setText(String.valueOf((current_row)));
				TotalTimeField.setText(String.valueOf(DF.format((MASTER_FLIGHT_PLAN.getTotalFlightTime()))));
				TotalDistanceTextField.setText(String.valueOf(DF.format(MASTER_FLIGHT_PLAN.getTotalDistance())));
				DrawTable();
				
				}
				
				else{
					DeleteWarningLabel.setText("Maximum number of Waypoints Reached.");
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
					MASTER_FLIGHT_PLAN.deleteWPT(Integer.parseInt(InputDeleteWaypoint.getText()));
					TotalTimeField.setText(String.valueOf(DF.format((MASTER_FLIGHT_PLAN.getTotalFlightTime()))));
					TotalDistanceTextField.setText(String.valueOf(DF.format(MASTER_FLIGHT_PLAN.getTotalDistance())));
					DrawTable();
					current_row--;
					WPTNumberLabel.setText(String.valueOf(current_row));
				}
				else{
					DeleteWarningLabel.setText("Cannot delete cell that does not exist.");
				}
			}
		});
		contentPane.add(DeleteWaypointButton);
		
		// Will allow user to Save the flight plan as a text file.
		//TODO: Save flight plan as a proprietary file that can be used to load the table and other info.
		
		JButton SavePlanButton = new JButton("Save Flight Plan");
		SavePlanButton.setEnabled(true);
		SavePlanButton.setBounds(450, 419, 124, 23);
		SavePlanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				// SAVING AS A TEXT FILE
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
				    		writer.write( MASTER_FLIGHT_PLAN.getWPT(waypoint).getDistToNext() + "                                         ");
				    		writer.write( MASTER_FLIGHT_PLAN.getWPT(waypoint).getHDG() + "                                 ");
				    		writer.write( MASTER_FLIGHT_PLAN.getWPT(waypoint).getALT() + "               ");
				    		writer.write( MASTER_FLIGHT_PLAN.getWPT(waypoint).getSpeed() + "                     ");
				    		writer.write( MASTER_FLIGHT_PLAN.getWPT(waypoint).getETA() + "                           ");
				    		writer.write( MASTER_FLIGHT_PLAN.getWPT(waypoint).getFPM());
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
				
				// SAVING AS A PROPIETARY FILE FOR LOADING THE APP
				//TODO
			}
		});
		contentPane.add(SavePlanButton);
		
		// Clears all data for user
		
		JButton ResetAllButton = new JButton("Reset All");
		ResetAllButton.setBounds(20, 419, 118, 23);
		ResetAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteWarningLabel.setText("");
				MASTER_FLIGHT_PLAN.resetFlightData();
				current_row = 0;
				
				TotalTimeField.setText(String.valueOf(DF.format((MASTER_FLIGHT_PLAN.getTotalFlightTime()))));
				WPTNumberLabel.setText(String.valueOf((DF.format(MASTER_FLIGHT_PLAN.getTotalDistance()))));
				SpeedInput.setText("0");
				HeadingInput.setText("0");
				AltitudeInput.setText("0");
				DistanceInput.setText("0");
				InputDeleteWaypoint.setText("0");
				
				WaypointTable.validate();
				DrawTable();
				
			}
		});
		contentPane.add(ResetAllButton);
		
		// Display Table
		
		WaypointTable = new JTable();
		WaypointTable.setBounds(20, 42, 653, 352);
		WaypointTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DrawTable();
		contentPane.add(WaypointTable);
		
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
