/*
 * Jeffrey A. Wilson
 * Projectile Studios
 * 
 * 05/21/2018
 * 
 * FlightPlanData.java
 * 
 * This file creates the list of waypoints that will make up the flight plan.
 * 
 */

import java.util.ArrayList;

public class FlightPlanData {
	
	private ArrayList<Waypoint> WaypointList = new ArrayList<Waypoint>();
	
	private final static Waypoint DUMMYWAYPOINT = new Waypoint();
	
	private final int MAX_LIST_SIZE = 30; // (ROWS + 1)
	
	private double total_distance = 0;
	private double total_flight_plan_time = 0;

/*
    Function: FlightPlanData() (DEFUALTCONSTRUCTOR)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: nothing; initialize the flight plan with dummy waypoints to prvent a null draw call on the GUI table.
*/
	
	public FlightPlanData()
	{
		initialPopulateFlightPlan();
	}
	
/*
    Function: getWPT() (Waypoint)

    Author(s): Jeff Wilson
    05/21/18

    Parameters: (int) index; the index of the waypoint in the list to be returned to the calling routine
    Returns: a single Waypoint from the list to the calling routine 
*/
	
	public Waypoint getWPT(int index)
	{
		return WaypointList.get(index);
	}
	
/*
    Function: setWPT() (void)

    Author(s): Jeff Wilson
    05/21/18

    Parameters: (int) index; the index of the list in which the waypoint will be added
    			(Waypoint) newPoint; the Waypoint OBJECT to be added into the list at the provided index
    Returns: nothing; adds a waypoint into the list
*/
	
	public void setWPT(int index, Waypoint newPoint)
	{
		WaypointList.set(index, newPoint);
		if(WaypointList.get(index).getETA() != "N/C")
		{
			total_flight_plan_time += Double.parseDouble(getWPT(index).getETA());
		}
		total_distance += Double.parseDouble(getWPT(index).getDistToNext());
		
	}
	
/*
    Function: deleteWPT() (void)

    Author(s): Jeff Wilson
    05/21/18

    Parameters: (int) index; the index of the list in which the waypoint will be deleted from
    Returns: nothing; deletes a waypoint from the list
*/	

	public void deleteWPT(int index)
	{
		if(WaypointList.get(index).getETA() != "N/C")
		{
			total_flight_plan_time -= Double.parseDouble(WaypointList.get(index).getETA());
		}
		total_distance -= Double.parseDouble(WaypointList.get(index).getDistToNext());
		WaypointList.remove(index);
		WaypointList.add(DUMMYWAYPOINT);
	}
	
/*
    Function: resetFlightData() (void)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: nothing; resets all flight data by setting the list with dummy waypoints and the plan details to 0
*/	
	
	public void resetFlightData()
	{
		total_distance = 0;
		total_flight_plan_time = 0;
		setPlanNull();
	}
	
/*
    Function: getTotalDistance() (double)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: the total distance of the flight plan to the calling routine
*/	
	
	public double getTotalDistance()
	{
		return total_distance;
	}
	
/*
    Function: getTotalFlightTime() (double)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: the total time of the flight plan to the calling routine
*/	
	
	public double getTotalFlightTime()
	{
		return total_flight_plan_time;
	}
	
/*
    Function: initialPopulateFlightPlan() (void)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: nothing; adds dummy waypoints to COMPLETELY populate the previously empty list. (SHOULD ONLY BE CALLED DURING THE INITIAL CONSTRUCITON OF THE OBJECT)
*/		

	public void initialPopulateFlightPlan()
	{
		for(int i = 0; i < MAX_LIST_SIZE; ++i)
		{
		    WaypointList.add(i, DUMMYWAYPOINT);
		}
	}

/*
    Function: setPlanNull() (void)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: nothing; assumes the set has already been populate with either dummy waypoints or user defined waypoints. Sets all waypoints to dummy waypoints. (Essentially resetting the flight plan)
*/	
	
	public void setPlanNull()
	{
		for(int i = 0; i < MAX_LIST_SIZE; ++i)
		{
		    WaypointList.set(i, DUMMYWAYPOINT);
		}
	}
	
/*
    Function: getMaxListSize() (final int)

    Author(s): Jeff Wilson
    05/21/18

    NO PARAMETERS
    Returns: the MAXIMUM amount of waypoints the flight plan can contain
*/	

	public final int getMaxListSize()
	{
		return MAX_LIST_SIZE;
	}

}
