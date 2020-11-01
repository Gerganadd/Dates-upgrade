package problem28_10;

public class Event {
	private Date date;
	private String name;
	
	public Event(Date date, String name)
	{
		this.date = date;
		setName(name);
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String value)
	{
		if(value != null && !value.isEmpty() && value.length() < 40)
		name = value;
	}
	
	public String toString()
	{
		String dateString = date.getDay() + "." + date.getMonth() + "." + date.getYear();
		String hourString = date.getHours() + ":" + date.getMinutes();
		String result = dateString + ", " + hourString + " - " + name;
		return result;
	}
}
