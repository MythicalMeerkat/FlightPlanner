/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * Created: 05/20/2018
 * 
 * Waypoint.java
 * 
 * This file creates the skeleton of the Waypoint object of which the flight plan is comprised of.
 * 
 */

public class Waypoint {

private String WPTNumber;
private String Altitude;
private String FPM; // Vertical Speed
private String Speed; // Knots
private String Distance; // NM
private String ETA;
private String Heading;

/*
Function: Waypoint() (OVERLOADED constructor)

Author(s): Jeff Wilson
05/20/18

Parameters: (String) WPTNumber - The Waypoint Number in the List
			(String) Altitude - the altitude of the waypoint
			(String) FPM = the Vertical speed of needed to meet the waypoint given the current linear speed of the aircraft
			(String) Speed - the ground speed of the aircraft
			(String) Distance - the distance from the previous waypoint to the current waypoint
			(String) ETA - the ETA from the previous waypoint to the current waypoint
			(String) Heading - the heading of the current waypoint relative to the prevoius waypoint
Returns: nothing; sets the member variables of the class to the appropriate values in the function header.
*/

	public Waypoint(String WPTNumber, String Altitude, String FPM, String Speed, String Distance, String ETA, String Heading){
		this.WPTNumber = WPTNumber;
		this.Altitude = Altitude;
		this.FPM = FPM;
		this.Speed = Speed;
		this.Distance = Distance;
		this.ETA = ETA;
		this.Heading = Heading;
	}
	
/*
	Function: Waypoint() (DEFAULT constructor)

	Author(s): Jeff Wilson
	05/20/18

	NO PARAMETERS
	Returns: nothing; sets the member variables of the class to empty strings
*/
	
	public Waypoint(){
		WPTNumber = "";
		Altitude = "";
		FPM = "";
		Speed = "";
		Distance = "";
		ETA = "";
		Heading = "";
	}

/*
	Waypoint "Getter" Functions

	Author(s): Jeff Wilson
	05/29/18

	return the appropriate member variables named in the function header.
*/
	
	public String getWPTNumber()
	{
		return WPTNumber;
	}
	public String getALT()
	{
		return Altitude;
	}
	public String getFPM()
	{
		return FPM;
	}
	public String getSpeed()
	{
		return Speed;
	}
	public String getDistToNext()
	{
		return Distance;
	}
	public String getETA()
	{
		return ETA;
	}
	public String getHDG()
	{
		return Heading;
	}
	
/*
	Waypoint "Setter" Functions

	Author(s): Jeff Wilson
	05/29/18

	set the appropriate member variables named in the function header.
*/
	
	public void setALT(String Altitude)
	{
		this.Altitude = Altitude;
	}
	public void setFPM(String FPM)
	{
		this.FPM = FPM;
	}
	public void setSpeed(String Speed)
	{
		this.Speed = Speed;
	}
	public void setDistance(String Distance)
	{
		this.Distance = Distance;
	}
	public void setETA(String ETA)
	{
		this.ETA = ETA;
	}
	public void setHDG(String Heading)
	{
		this.Heading = Heading;
	}

}
