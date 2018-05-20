import java.text.DecimalFormat;

public class ConversionData {
	
	private final DecimalFormat df = new DecimalFormat("#.##"); 
	
	public String calculateETA(String passDistance, String passSpeed)
	{
		if(Double.parseDouble(passSpeed) == 0)
			return "0";
		else
			return String.valueOf(df.format((((Double.parseDouble(passDistance)) / (Double.parseDouble(passSpeed)))* 60)));
	}
	public String calculateVertSpeed(String altDifference, String Time)
	{
		if (Double.parseDouble(Time) == 0)
			return "0";
		else
			return String.valueOf(df.format((((Double.parseDouble(altDifference)) / (Double.parseDouble(Time))))));
	}
}
