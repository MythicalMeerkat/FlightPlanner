public class Waypoint {


private int WPTNumber;
private int Altitude;
private int FPM; // Vertical Speed
private int Speed; // Knots
private int distanceToNext; // NM
private int ETA;
private int Heading;

	public Waypoint(int WayPointValue, int Alt, int VertSpeed, int speed, int distnext, int ETimeA, int HDG){
		WPTNumber = WayPointValue;
		Altitude = Alt;
		FPM = VertSpeed;
		Speed = speed;
		distanceToNext = distnext;
		ETA = ETimeA;
		Heading = HDG;
	}


}
