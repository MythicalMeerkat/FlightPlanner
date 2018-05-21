/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * 05/20/2018
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

Parameters: (String) WaypointValue - The Waypoint Number in the List
			(String) Alt - the altitude of the waypoint
			(String) VertSpeed = the Vertical speed of needed to meet the waypoint given the current linear speed of the aircraft
			(String) speed - the ground speed of the aircraft
			(String) distnext - the distance from the previous waypoint to the current waypoint
			(String) ETimeA - the ETA from the previous waypoint to the current waypoint
			(String) HDG - the heading of the current waypoint relative to the prevoius waypoint
Returns: nothing; sets the member variables of the class to the appropriate values in the function header.
*/

	public Waypoint(String WayPointValue, String Alt, String VertSpeed, String speed, String distnext, String ETimeA, String HDG){
		WPTNumber = WayPointValue;
		Altitude = Alt;
		FPM = VertSpeed;
		Speed = speed;
		Distance = distnext;
		ETA = ETimeA;
		Heading = HDG;
	}
	
/*
	Function: Waypoint() (DEFAULT constructor)

	Author(s): Jeff Wilson
	05/20/18

	Parameters: NONO
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


}
