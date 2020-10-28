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
		if(number == 1)
			return "Monday";
		else if(number == 2)
			return "Tuesday";
		else if(number == 3)
			return "Wednesday";
		else if(number == 4)
			return "Thurstday";
		else if(number == 5)
			return "Friday";
		else if(number == 6)
			return "Saturday";
		else if(number == 7)
			return "Sunday";
		return null;
	}
	
	public int length;
	public String toString(int length)
	{
		char[] arrDayOfWeek = getDay(number).toString().toCharArray();
		String result = "";
		if(length <= arrDayOfWeek.length)
			 for(int i = 0 ; i < length ; i++)
			 {
				 result += arrDayOfWeek[i];
			 }
		else System.out.println("invalid date" + "\n" + getDay(number).toString() + " don't have " + length + " symbols");
			//izvejda tolkova bukvi kolkoto mu e zadadeno
		return result;
	}

	int getValue() {
		return number;
	}
}
