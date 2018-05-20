public class Waypoint {


private String WPTNumber;
private String Altitude;
private String FPM; // Vertical Speed
private String Speed; // Knots
private String Distance; // NM
private String ETA;
private String Heading;
private String WPTNotes;

	public Waypoint(String WayPointValue, String Alt, String VertSpeed, String speed, String distnext, String ETimeA, String HDG){
		WPTNumber = WayPointValue;
		Altitude = Alt;
		FPM = VertSpeed;
		Speed = speed;
		Distance = distnext;
		ETA = ETimeA;
		Heading = HDG;
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
