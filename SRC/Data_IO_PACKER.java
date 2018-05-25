import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * 05/21/2018
 * 
 * DataExporter.java
 * 
 * This file creates the skeleton of the DataExporter object that will wirte out the .txt summary as well as the 
 * program specific file (.fpp) that can be used to load a flight plan.
 * 
 */

public class Data_IO_PACKER{
	
	
	//private final String project_File_Extension = ".fpp";
	
	//private final String formatStr = "%-20s %-15s %-15s %-15s %-15s%n";
	
	private BufferedWriter writer = null;
	
	private static JFileChooser chooser;
	
	private FileNameExtensionFilter filter = null;
	
	private BufferedReader in = null;

/*
    Function: DataExporter(FlightPlanData) (DEFAULT Constructor)

    Author(s): Jeff Wilson
    05/22/18

    Parameters: passFlightPlan (FlightPlanData); the flight plan to be exported
    Returns: nothing; stores the flight plan for exporting/saving 
    				  initializes the chooser dialog for saving files
*/
	
	public Data_IO_PACKER()
	{
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Desktop")); // Default browser directory set to DESKTOP
	}
	
/*
    Function: writeOutTextFile(JTextArea) (void)

    Author(s): Jeff Wilson
    05/22/18

    Parameters: NotesTextBox (JTextArea); the JTextArea whose content will be saved externally to as a .txt file
    Returns: nothing; saves the contents of the JTextArea externally as a .txt file
*/
	
	public void writeOutTextFile(JTextArea NotesTextBox)
	{
		int returnValue = chooser.showSaveDialog(null);
		File file = chooser.getSelectedFile();
		
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			String  content = NotesTextBox.getText();
			content = content.replaceAll("(?!\\r)\\n", "\r\n");
			
		    try
		    {
		    	writer = new BufferedWriter(new FileWriter( file.getAbsolutePath() + ".txt"));
		    	writer.write(content);
		    	writer.close();
		    }
		    catch (IOException e1)
		    {
		    	JOptionPane.showMessageDialog(null, "ERROR: Could not be saved!", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
		    	
		    }
		}
	}
	
/*
    Function: writeOutTextFile(FlightPlanData) (void)

    Author(s): Jeff Wilson
    05/22/18

    Parameters: PASS_FLIGHT_PLAN (FlightPlanData); the flight plan whose content will be saved externally to as a .txt file
    			current_row (int); the amount of waypoints in the plan
    Returns: nothing; saves the contents of the flight plan externally as a .txt file
*/
	
	public void writeOutTextFile(FlightPlanData MASTER_FLIGHT_PLAN, int amount_waypoints, double total_distance, double total_time)
	{
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
				
		    	for( int waypoint = 0; waypoint < amount_waypoints; ++waypoint )
		    	{
		    		/*
		    		writer.write(String.format(formatStr, MASTER_FLIGHT_PLAN.getWPT(waypoint).getDistToNext(), MASTER_FLIGHT_PLAN.getWPT(waypoint).getHDG(), 
		    					                          MASTER_FLIGHT_PLAN.getWPT(waypoint).getALT(), MASTER_FLIGHT_PLAN.getWPT(waypoint).getSpeed(), 
		    					                          MASTER_FLIGHT_PLAN.getWPT(waypoint).getETA(), MASTER_FLIGHT_PLAN.getWPT(waypoint).getFPM()));
					*/
    		
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
		    	writer.write("Total Flight Distance: " + total_distance + " NM");
		    	writer.newLine();
		    	writer.write("Total Flight Time: " + total_time + " Minutes");
		    	writer.newLine();
		    	writer.newLine();
		    	writer.write("Notes: ");
		    	writer.close( );
		    }
		    catch (IOException e1)
		    {
		    	JOptionPane.showMessageDialog(null, "ERROR: Could not be saved!", "InfoBox: " + "ERROR", JOptionPane.INFORMATION_MESSAGE);
		    }
		}
		
	}
	
/*
    Function: loadInTextFile(JTextArea) (void)

    Author(s): Jeff Wilson
    05/22/18

    Parameters: NotesTextBox (JTextArea); the text box to be loaded
    Returns: nothing; loads a .txt file into a text box
*/
	
	public void loadInTextFile(JTextArea NotesTextBox)
	{
		filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		
		int returnVal = chooser.showOpenDialog(null); //replace null with your swing container
		
		File file = null;
		String line = null;
		
		if(returnVal == JFileChooser.APPROVE_OPTION)  
		  file = chooser.getSelectedFile();    

		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		}
		
		try {
			NotesTextBox.setText(" ");
			line = in.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
			}
		
		while(line != null)
		{
		  NotesTextBox.append(line + "\n");
		  try {
			line = in.readLine();
		  } catch (IOException e1) {
			e1.printStackTrace();
		  }
		}
	}
	
/*
    Function: writeOutProjectFile()

    Author(s): Jeff Wilson
    05/22/18

    Parameters: TBD
    Returns: TBD
    
    will externally save a .fpp file (FlightPlannerProject) that can later be used to load the app
*/
	
	public void writeOutProjectFile()
	{
		
	}

/*
    Function: writeOutProjectFile()

    Author(s): Jeff Wilson
    05/22/18

    Parameters: TBD
    Returns: TBD
    
    will load a .fpp file (FlightPlannerProject) into the application
*/
	
	public void loadInProjectFile()
	{
		
	}
	
}