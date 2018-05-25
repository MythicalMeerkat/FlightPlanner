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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;

public class FlightPlannerWindow extends JFrame {
	
	private FlightPlanData MASTER_FLIGHT_PLAN = new FlightPlanData(); // Holds the FLight Plan with ALL Waypoints
	
	public static final long serialVersionUID = 67L; 
	
	private final DecimalFormat DF = new DecimalFormat("#.##"); 

	private ConversionData conversions = new ConversionData();
	
	public static Data_IO_PACKER DataIO = new Data_IO_PACKER();

	private JPanel contentPane;
	private JLabel WaypointLabelTop;
	private JLabel DistanceLabelTop;
	
	private JTable WaypointTable;
	
	private JTextField SpeedInput;
	private JTextField HeadingInput;
	private JTextField AltitudeInput;
	private JTextField DistanceInput;
	private JTextField WPTNumberLabel;
	private JTextField InputDeleteWaypoint;
	private JTextField TotalTimeField;
	private JTextField TotalDistanceTextField;
	
	JLabel EditorWarningLabel = new JLabel("");
	
	private int current_row = 0;
	
/*
    Function: setNewProject() (void)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: nothing; resets all flight data and text areas to their original state.
*/
	
	private void setNewProject()
	{
		EditorWarningLabel.setText("");
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
	
/*
    Function: DrawTable() (void)

    Author(s): Jeff Wilson
    05/20/18

    NO PARAMETERS
    Returns: nothing; creates the table and draws it on the user's window
*/
	
	private void DrawTable()
	{
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		WaypointTable.setDefaultRenderer(String.class, centerRenderer);
		
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
					{22, MASTER_FLIGHT_PLAN.getWPT(22).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(22).getHDG(), MASTER_FLIGHT_PLAN.getWPT(22).getALT(), MASTER_FLIGHT_PLAN.getWPT(22).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(22).getETA(), MASTER_FLIGHT_PLAN.getWPT(22).getFPM()},
					{23, MASTER_FLIGHT_PLAN.getWPT(23).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(23).getHDG(), MASTER_FLIGHT_PLAN.getWPT(23).getALT(), MASTER_FLIGHT_PLAN.getWPT(23).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(23).getETA(), MASTER_FLIGHT_PLAN.getWPT(23).getFPM()},
					{24, MASTER_FLIGHT_PLAN.getWPT(24).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(24).getHDG(), MASTER_FLIGHT_PLAN.getWPT(24).getALT(), MASTER_FLIGHT_PLAN.getWPT(24).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(24).getETA(), MASTER_FLIGHT_PLAN.getWPT(24).getFPM()},
					{25, MASTER_FLIGHT_PLAN.getWPT(25).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(25).getHDG(), MASTER_FLIGHT_PLAN.getWPT(25).getALT(), MASTER_FLIGHT_PLAN.getWPT(25).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(25).getETA(), MASTER_FLIGHT_PLAN.getWPT(25).getFPM()},
					{26, MASTER_FLIGHT_PLAN.getWPT(26).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(26).getHDG(), MASTER_FLIGHT_PLAN.getWPT(26).getALT(), MASTER_FLIGHT_PLAN.getWPT(26).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(26).getETA(), MASTER_FLIGHT_PLAN.getWPT(26).getFPM()},
					{27, MASTER_FLIGHT_PLAN.getWPT(27).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(27).getHDG(), MASTER_FLIGHT_PLAN.getWPT(27).getALT(), MASTER_FLIGHT_PLAN.getWPT(27).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(27).getETA(), MASTER_FLIGHT_PLAN.getWPT(27).getFPM()},
					{28, MASTER_FLIGHT_PLAN.getWPT(28).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(28).getHDG(), MASTER_FLIGHT_PLAN.getWPT(28).getALT(), MASTER_FLIGHT_PLAN.getWPT(28).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(28).getETA(), MASTER_FLIGHT_PLAN.getWPT(28).getFPM()},
					{29, MASTER_FLIGHT_PLAN.getWPT(29).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(29).getHDG(), MASTER_FLIGHT_PLAN.getWPT(29).getALT(), MASTER_FLIGHT_PLAN.getWPT(29).getSpeed(), MASTER_FLIGHT_PLAN.getWPT(29).getETA(), MASTER_FLIGHT_PLAN.getWPT(29).getFPM()},
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jeffrey\\Desktop\\FlightPlanner\\SOURCE\\AeroNavigationPlanner\\src\\FlightPlannerICON.png"));
		
		setResizable(false);
		setTitle("Flight Plan Summary ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1123, 576);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNewProject();
			}
		});
		mnFile.add(mntmNewProject);
		
		JMenuItem mntmSaveProject = new JMenuItem("Save Project");
		mntmSaveProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnFile.add(mntmSaveProject);
		
		JMenuItem mntmExportPlan = new JMenuItem("Export...");
		mntmExportPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataIO.writeOutTextFile(MASTER_FLIGHT_PLAN, current_row, MASTER_FLIGHT_PLAN.getTotalDistance(), MASTER_FLIGHT_PLAN.getTotalFlightTime());
			}
		});
		mntmExportPlan.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmExportPlan);
		mnFile.add(mntmExit);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmNotes = new JMenuItem("Notes");
		mntmNotes.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotesGUI Notes_GUI_Frame = new NotesGUI();
				Notes_GUI_Frame.setAlwaysOnTop(true);
				Notes_GUI_Frame.setVisible(true);;
			}
		});
		mnTools.add(mntmNotes);
		
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
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		// Determines of the ETA is calculate for current way-point
		
		JCheckBox CalculateEtaCheck = new JCheckBox("Calculate Vert. Speed/ETA");
		CalculateEtaCheck.setToolTipText("Unchecking this box sets the ETA and Vertical Speed to \"N/C\"");
		CalculateEtaCheck.setBounds(839, 177, 218, 23);
		CalculateEtaCheck.setSelected(true);
		contentPane.add(CalculateEtaCheck);
		
		// Create the Way-point
		
		JButton CreateWPTButton = new JButton("Add Waypoint");
		CreateWPTButton.setToolTipText("Adds a waypoint with the data entered under the 'Waypoint Creation' section");
		CreateWPTButton.setBounds(960, 485, 132, 23);
		CreateWPTButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditorWarningLabel.setText("");
				if(current_row < MASTER_FLIGHT_PLAN.getMaxListSize())
				{
					if(CalculateEtaCheck.isSelected())
					{
						String ETA = conversions.calculateETA(DistanceInput.getText(), SpeedInput.getText());

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
					EditorWarningLabel.setText("Maximum number of Waypoints Reached.");
				}
				
			}
		});
		contentPane.add(CreateWPTButton);
		
		// Delete a way-point
		
		JButton DeleteWPTButton = new JButton("Delete Waypoint");
		DeleteWPTButton.setToolTipText("Deletes the corresponding Waypoint to the entry under the section 'Waypoint Deletion'");
		DeleteWPTButton.setBounds(782, 485, 132, 23);
		DeleteWPTButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( (!(InputDeleteWaypoint.getText().isEmpty() )) && Integer.parseInt(InputDeleteWaypoint.getText()) >= 0 && Integer.parseInt(InputDeleteWaypoint.getText()) < current_row )
				{
					
					EditorWarningLabel.setText("");
					MASTER_FLIGHT_PLAN.deleteWPT(Integer.parseInt(InputDeleteWaypoint.getText()));
					TotalTimeField.setText(String.valueOf(DF.format((MASTER_FLIGHT_PLAN.getTotalFlightTime()))));
					TotalDistanceTextField.setText(String.valueOf(DF.format(MASTER_FLIGHT_PLAN.getTotalDistance())));
					DrawTable();
					current_row--;
					WPTNumberLabel.setText(String.valueOf(current_row));
				}
				else{
					EditorWarningLabel.setText("Cannot delete cell that does not exist.");
				}
			}
		});
		contentPane.add(DeleteWPTButton);
		
		// Display Table
		
		WaypointTable = new JTable();
		WaypointTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		WaypointTable.setRowSelectionAllowed(false);
		WaypointTable.setBounds(20, 35, 665, 480);
		WaypointTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DrawTable();
		contentPane.add(WaypointTable);
		
		
		// TOP Column Labels
		
		WaypointLabelTop = new JLabel();
		WaypointLabelTop.setBounds(10, 11, 86, 20);
		WaypointLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		WaypointLabelTop.setText("Waypoint");
		contentPane.add(WaypointLabelTop);
				
		DistanceLabelTop = new JLabel();
		DistanceLabelTop.setBounds(117, 11, 86, 20);
		DistanceLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		DistanceLabelTop.setText("Distance(NM)");
		contentPane.add(DistanceLabelTop);
				
		JLabel HDGLabelTop = new JLabel("Heading");
		HDGLabelTop.setBounds(229, 14, 46, 14);
		HDGLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(HDGLabelTop);
				
		JLabel AltLabelTop = new JLabel("Altitude");
		AltLabelTop.setBounds(324, 14, 46, 14);
		AltLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(AltLabelTop);
				
		JLabel SpeedLabelTop = new JLabel("Speed(GRD)");
		SpeedLabelTop.setBounds(394, 14, 91, 14);
		SpeedLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(SpeedLabelTop);
				
		JLabel ETALabelTop = new JLabel("ETA(MIN)");
		ETALabelTop.setToolTipText("The estimated time taken to reach a Waypoint from the previous Waypoint");
		ETALabelTop.setBounds(503, 14, 68, 14);
		ETALabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ETALabelTop);
				
		JLabel VSLabelTop = new JLabel("Vert. Speed(FPM)");
		VSLabelTop.setToolTipText("The estimated vertical speed needed to reach the Waypoint from the prevoius Waypoint");
		VSLabelTop.setBounds(581, 14, 104, 14);
		VSLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(VSLabelTop);
				
		JLabel WaypointCreationTOP = new JLabel("Waypoint Creation");
		WaypointCreationTOP.setForeground(Color.BLUE);
		WaypointCreationTOP.setBounds(875, 14, 110, 14);
		WaypointCreationTOP.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(WaypointCreationTOP);		
				
		JLabel SpeedLabelCreate = new JLabel("Speed(KNOTS):");
		SpeedLabelCreate.setBounds(822, 42, 97, 14);
		SpeedLabelCreate.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(SpeedLabelCreate);
				
		JLabel HeadingLabelCreate = new JLabel("Heading:");
		HeadingLabelCreate.setBounds(853, 73, 66, 14);
		HeadingLabelCreate.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(HeadingLabelCreate);
				
		JLabel AltitudeLabelCreate = new JLabel("Altitude(FT):");
		AltitudeLabelCreate.setBounds(849, 107, 70, 14);
		AltitudeLabelCreate.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(AltitudeLabelCreate);
				
		JLabel DistanceLabelCreate = new JLabel("Distance(NM):");
		DistanceLabelCreate.setBounds(833, 146, 86, 14);
		DistanceLabelCreate.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(DistanceLabelCreate);
		
		// Way-point Data input fields
		
		SpeedInput = new JTextField();
		SpeedInput.setToolTipText("Ground Speed of Aircraft");
		SpeedInput.setBounds(929, 39, 86, 20);
		SpeedInput.setHorizontalAlignment(SwingConstants.CENTER);
		SpeedInput.setText("0");
		contentPane.add(SpeedInput);
		SpeedInput.setColumns(10);
		
		HeadingInput = new JTextField();
		HeadingInput.setToolTipText("Heading taken to reach this Waypoint from the Previous Waypoint");
		HeadingInput.setBounds(929, 70, 86, 20);
		HeadingInput.setHorizontalAlignment(SwingConstants.CENTER);
		HeadingInput.setText("0");
		contentPane.add(HeadingInput);
		HeadingInput.setColumns(10);
		
		AltitudeInput = new JTextField();
		AltitudeInput.setToolTipText("Height above ground level (AGL)");
		AltitudeInput.setBounds(929, 104, 86, 20);
		AltitudeInput.setHorizontalAlignment(SwingConstants.CENTER);
		AltitudeInput.setText("0");
		contentPane.add(AltitudeInput);
		AltitudeInput.setColumns(10);
		
		DistanceInput = new JTextField();
		DistanceInput.setToolTipText("The Distance from the Previous Waypoint");
		DistanceInput.setBounds(929, 140, 86, 20);
		DistanceInput.setHorizontalAlignment(SwingConstants.CENTER);
		DistanceInput.setText("0");
		contentPane.add(DistanceInput);
		DistanceInput.setColumns(10);
		
		WPTNumberLabel = new JTextField();
		WPTNumberLabel.setToolTipText("The current Waypoint being edited");
		WPTNumberLabel.setBounds(929, 349, 86, 20);
		WPTNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WPTNumberLabel.setText(String.valueOf((current_row)));
		WPTNumberLabel.setEditable(false);
		WPTNumberLabel.setBackground(UIManager.getColor("Button.background"));
		contentPane.add(WPTNumberLabel);
		
		InputDeleteWaypoint = new JTextField();
		InputDeleteWaypoint.setToolTipText("The Waypoint to be deleted. Push the 'Delete Waypoint' button to remove the Waypoint");
		InputDeleteWaypoint.setBounds(929, 260, 86, 20);
		InputDeleteWaypoint.setHorizontalAlignment(SwingConstants.CENTER);
		InputDeleteWaypoint.setText("0");
		contentPane.add(InputDeleteWaypoint);
		InputDeleteWaypoint.setColumns(10);
		
		TotalTimeField = new JTextField();
		TotalTimeField.setToolTipText("Estimated time to Complete the flight plan");
		TotalTimeField.setEditable(false);
		TotalTimeField.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTimeField.setText("0");
		TotalTimeField.setBounds(929, 377, 86, 20);
		contentPane.add(TotalTimeField);
		TotalTimeField.setColumns(10);
		
		// Displays Current Way-point the User is editing
		
		JLabel CurrentWPTLabel = new JLabel("Current WPT:");
		CurrentWPTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		CurrentWPTLabel.setBounds(839, 352, 80, 14);
		contentPane.add(CurrentWPTLabel);
		
		// Outlines the fields for Deleting a Way-point
		
		JLabel DeleteWPTTop = new JLabel("Waypoint Deletion");
		DeleteWPTTop.setForeground(Color.BLUE);
		DeleteWPTTop.setBounds(863, 231, 132, 14);
		DeleteWPTTop.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(DeleteWPTTop);
		
		// Label for directly deleting way-point
		
		JLabel DeleteWPTLabel = new JLabel("Delete WPT:");
		DeleteWPTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		DeleteWPTLabel.setBounds(817, 263, 97, 14);
		contentPane.add(DeleteWPTLabel);
		
		// Outlines fields for the overall info regarding the flight plan
		
		JLabel PlanInfoTOP = new JLabel("Plan Info");
		PlanInfoTOP.setHorizontalAlignment(SwingConstants.CENTER);
		PlanInfoTOP.setForeground(Color.BLUE);
		PlanInfoTOP.setBounds(885, 327, 88, 14);
		contentPane.add(PlanInfoTOP);
		
		// Labels the field showing the total time of the flight plan
		
		JLabel TotalTimeLabel = new JLabel("Total Time:");
		TotalTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		TotalTimeLabel.setBounds(839, 380, 80, 14);
		contentPane.add(TotalTimeLabel);
		
		JLabel TotalDistanceLabel = new JLabel("Total Distance(NM):");
		TotalDistanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		TotalDistanceLabel.setBounds(801, 409, 118, 14);
		contentPane.add(TotalDistanceLabel);
		
		TotalDistanceTextField = new JTextField();
		TotalDistanceTextField.setToolTipText("Estimated Total distance covered by the Flight Plan");
		TotalDistanceTextField.setHorizontalAlignment(SwingConstants.CENTER);
		TotalDistanceTextField.setText("0");
		TotalDistanceTextField.setEditable(false);
		TotalDistanceTextField.setBounds(929, 408, 86, 20);
		contentPane.add(TotalDistanceTextField);
		TotalDistanceTextField.setColumns(10);
		
		
		EditorWarningLabel.setForeground(Color.RED);
		EditorWarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EditorWarningLabel.setBounds(782, 453, 325, 14);
		contentPane.add(EditorWarningLabel);
		
	
	}
}
