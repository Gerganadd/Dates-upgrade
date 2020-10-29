package problem28_10;

import problem28_10.DayOfWeek;

public enum DayOfWeek {
	MONDAY(1),
	TUESDAY(2),
	WEDNESDAY(3),
	THURSDAY(4),
	FRIDAY(5),
	SATURDAY(6),
	SUNDAY(7);
	
	private int number;
	
	private DayOfWeek(int numberValue)
	{
		this.number = numberValue;
	}
	
	int getValue()
	{
		return number;
	}
	
	public static DayOfWeek getDay(int n) 
	{
		switch(n) 
		{
		case 1: return MONDAY;
		case 2: return TUESDAY;
		case 3: return WEDNESDAY;
		case 4: return THURSDAY;
		case 5: return FRIDAY;
		case 6: return SATURDAY;
		case 7: return SUNDAY;
		default: return null;
		}
	}
	
	public String toString()
	{
		switch(number) 
		{
		case 1: return "Monday";
		case 2: return "Tuesday";
		case 3: return "Wednesday";
		case 4: return "Thurstday";
		case 5: return "Friday";
		case 6: return "Saturday";
		case 7: return "Sunday";
		default: return null;
		}
	}
	
	public int length;
	public String toString(int length)
	{
		char[] arrDayOfWeek = getDay(number).toString().toCharArray();
		String result = "";
		if (length <= arrDayOfWeek.length)
		{
			 for(int i = 0 ; i < length ; i++)
			 {
				 result += arrDayOfWeek[i];
			 }
		}
		else
		{
			System.out.println("invalid date" + "\n"
					+ getDay(number).toString() + " don't have " + length + " symbols");
		}
			
		return result;
	}
}
