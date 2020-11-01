package problem28_10;

import java.time.LocalDate;

public class Reminder {
	public final int ARRAY_LENGTH = 30;
	
	private Event[] eventsArray;
	private int pointer;
	
	public Event[] getEvents()
	{
		return eventsArray;
	}
	public Reminder()
	{
		eventsArray = new Event[ARRAY_LENGTH];
		pointer = 0;
	}
	
	public void addEvent(Event newEvent)
	{
		if (pointer < ARRAY_LENGTH)
		{
			eventsArray[pointer] = newEvent;
			pointer++;
		}
		else 
		{
			System.out.println("There is no more space in the array / the array is already full");
		}
	}
	
	public boolean isEqual(Date firstDate , Date secondDate)
	{
		if(firstDate.getDay() == secondDate.getDay() 
				&& firstDate.getMonth() == secondDate.getMonth()
				&& firstDate.getYear() == secondDate.getYear()) return true;
		else return false;
	}
	
	public Event[] getAllEventsAt(Date date)
	{
		Event[] eventsOfCurrentDay = new Event[pointer];
		int eventsOfCurrentDayPointer = 0;
		
		for (int i = 0 ; i < pointer ; i++)
		{
			if (isEqual(eventsArray[i].getDate(), date))
			{
				eventsOfCurrentDay[eventsOfCurrentDayPointer] = eventsArray[i];
				eventsOfCurrentDayPointer++;
			}
		}
		
		return eventsOfCurrentDay;
	}
	
	public void print(Event[] arr)
	{
		int i = 0;
		while(arr[i] != null)
		{
			System.out.println(arr[i]);
			i++;
		}
	}
	
	public void remind()
	{
		int day = LocalDate.now().getDayOfMonth();
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		Date today = new Date(day, month, year);
		
		print(getAllEventsAt(today));
	}//no finish yet
}
