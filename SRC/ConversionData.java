/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * 05/20/2018
 * 
 * ConversionData.java
 * 
 * This file creates the object that houses several useful functions for calculating some of the more complex mathematic member variables.
 * 
 */

import java.text.DecimalFormat;

public class ConversionData {
	
	private final DecimalFormat DF = new DecimalFormat("#.##"); 
	
/*
    Function: calculateETA() (String)

    Author(s): Jeff Wilson
    05/20/18

    Parameters: (String) passDistance - the distance to next waypoint. Passed by value from the calling routine.
    			(String) passSpeed - the speed going from the current waypoint to next waypoint. Passed by value from the calling routine.
    Returns: String; the ETA to the Next waypoint.
*/
	
	public String calculateETA(String passDistance, String passSpeed)
	{
		if(Double.parseDouble(passSpeed) == 0)
			return "0";
		else
			return String.valueOf(DF.format((((Double.parseDouble(passDistance)) / (Double.parseDouble(passSpeed)))* 60)));
	}
	
/*
    Function: calculateVertSpeed() (String)
    
    Author(s): Jeff Wilson
    05/20/18

    Parameters: (String) altDifference - the altitude distance between the two waypoints. Passed by value from the calling routine.
    			(String) Time - the time taken to reach the waypoint given the current speed. Passed by value from the calling routine. 
    							ETA Originally calculated by the function 'calculateVertSpeed'.
    Returns: String; the ETA to the Next waypoint.
*/
	
	public String calculateVertSpeed(String altDifference, String Time)
	{
		if (Double.parseDouble(Time) == 0)
			return "0";
		else
			return String.valueOf(DF.format((((Double.parseDouble(altDifference)) / (Double.parseDouble(Time))))));
	}
}
