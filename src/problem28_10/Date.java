package problem28_10;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

public class Date {
	private int seconds;
	private int minutes;
	private int hours;
	private int day;
	private int month;
	private int year;
	
	public final int MIN_SECOND = 0;
	public final int MAX_SECOND = 60;
	public final int MIN_MINUTE = 0;
	public final int MAX_MINUTE = 60;
	public final int MIN_HOUR = 0;
	public final int MAX_HOUR = 24;
	public final int MIN_MONTH = 1;
	public final int MAX_MONTH = 12;
	public final int MIN_DAY = 1;
	public final int MIN_YEAR = 1;
	public final int MAX_YEAR = 10000;
	
	public Date(int dayValue, int monthValue, int yearValue, int hoursValue, int minutesValue, int secondsValue)
	{
		setYear(yearValue);
		setMonth(monthValue);
		setDay(dayValue);
		setHours(hoursValue);
		setMinutes(minutesValue);
		setSeconds(secondsValue);
	}
	
	public Date(int dayValue, int monthValue, int yearValue)
	{
		setYear(yearValue);
		setMonth(monthValue);
		setDay(dayValue);
	}
	
	public Date()
	{
		//default 0:0:0 0/0/0
		day = 0;
		month = 0;
		year = 0;
		hours = MIN_HOUR;
		minutes = MIN_MINUTE;
		seconds = MIN_SECOND;
	} 
	
	public int getSeconds()
	{
		return seconds;
	}
	
	public void setSeconds(int secondsValue)
	{
		if (MIN_SECOND <= secondsValue && secondsValue < MAX_SECOND) seconds = secondsValue;
	}
	
	public int getMinutes()
	{
		return minutes;
	}
	
	public void setMinutes(int minutesValue)
	{
		if (MIN_MINUTE <= minutesValue && minutesValue < MAX_MINUTE) minutes = minutesValue;
	}
	
	public int getHours()
	{
		return hours;
	}
	
	public void setHours(int hoursValue)
	{
		if (MIN_HOUR <= hoursValue && hoursValue < MAX_HOUR) hours = hoursValue;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear(int yearValue)
	{
		if (MIN_YEAR <= yearValue && yearValue <= MAX_YEAR) this.year = yearValue;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public void setMonth(int monthValue)
	{
		if (MIN_MONTH <= monthValue && monthValue <= MAX_MONTH) this.month = monthValue;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public void setDay(int dayValue)
	{
		if (dateExist (dayValue, month)) this.day = dayValue;
		else day = MIN_DAY;
	}

	public boolean isLeapYear()
	{
		if (this.year % 4 == 0 )
		{
			if (this.year % 100 == 0) 
			{
				return (this.year % 400 == 0);
			}
			else 
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean dateExist(int dayValue, int monthValue)
	{
		if (MIN_MONTH <= monthValue && monthValue <= MAX_MONTH)
		{
			if (monthValue == 2)
			{
				if (isLeapYear() && dayValue == 29) return true;
				else if (dayValue < 29) return true;
			}
			else if (MIN_DAY <= dayValue && dayValue <= 30) 
			{
				return true;
			}
			else if (dayValue == 31)
			{
				if (monthValue <= 7 && monthValue % 2 == 1) return true;
				else if (monthValue >= 8 && monthValue % 2 == 0) return true;
			}
		}
		
		return false;
	}
	
	public void up()
	{	
		if (this.seconds < (MAX_SECOND - 1)) // < 59
		{
			this.seconds++;
		}
		else
		{
			this.seconds = MIN_SECOND; // 0
			this.minutes++;
			
			if (this.minutes == MAX_MINUTE) // == 60
			{
				this.minutes = MIN_MINUTE; // 0
				this.hours++;
			}
			
			if(this.hours == MAX_HOUR) // == 24
			{
				this.hours = MIN_HOUR; // 0
				this.day++;
			}
			
			if(dateExist((this.day + 1), this.month)) // if next day exists
			{
				this.day++; 
			}
			else
			{
				this.day = MIN_DAY; // 1
				
				if(dateExist(this.day, (this.month + 1))) // if next month exists 
				{
					this.month++; 
				}
				else
				{
					this.month = 1;
					setYear(this.year++);
				}
			}
			
		}
	}
	
	public int daysInMonth()
	{
		if (this.month == 2) 
		{
			if(isLeapYear()) return 29;
			else return 28;
		}
		
		if ((month <= 7 && month % 2 == 1) ||
				(month >= 8 && month % 2 == 0))
		{
			return 31;
		}
		
		return 30;
	}
	public int days()
	{
		int days;
		if(this.month == 1) days = this.day;
		else days = this.month * 30; //default: every months have 30 days
		
		if(isLeapYear()) days--; // 29.02
		else days -= 2; // 28.02
		
		int count = 0;
		
		//the number of months with 31 days
		for(int i = 1 ; i <= this.month ; i++)
		{
			if(i <= 7 && i % 2 == 1)count++;
			else if(i >= 8 && i % 2 == 0) count++;
		}
		
		days+=count;
		return days;
	}
	
	public int weeks()
	{
		int weeks = Math.round(days() / 7); 
		if(weeks == 0) return 1; //if is in the first week
		return weeks;
	}
	
	public int compareTo(Date someDate)
	{
		// 0 : if two dates are the same 
		// 1 : if the first date is before the second  
		// -1 : if the first date is after the second
		
		if (this.year == someDate.year && this.month == someDate.month
				&& this.day == someDate.day && this.hours == someDate.hours
				&& this.minutes == someDate.minutes && this.seconds == someDate.seconds)
		{
			return 0;
		}
		else 
		{
			if (this.year > someDate.year)
			{
				return 1;
			}
			else if (this.year == someDate.year)
			{
				if (this.month > someDate.month)
				{
					return 1;
				}
				else if (this.month == someDate.month)
				{
					if (this.day > someDate.day)
					{
						return 1;
					}
					else if (this.day == someDate.day)
					{
						if (this.hours > someDate.hours)
						{
							return 1;
						}
						else if (this.hours == someDate.hours)
						{
							if (this.minutes > someDate.minutes)
							{
								return 1;
							}
							else if (this.minutes == someDate.minutes)
							{
								if (this.seconds > someDate.seconds) return 1;
							}
						}
					}
				}
			} 
		}
		
		return -1; 
	}

	public static boolean isEqual(Date firstDate , Date secondDate)
	{
		if(firstDate.getDay() == secondDate.getDay() 
				&& firstDate.getMonth() == secondDate.getMonth()
				&& firstDate.getYear() == secondDate.getYear()) return true;
		else return false;
	}
	
	public static DayOfWeek today() // which day of week is today
	{
		Calendar c = Calendar.getInstance();
		TimeZone timeZone = c.getTimeZone();
		c.setTimeZone(timeZone);
		
		int day = c.get(Calendar.DAY_OF_WEEK);
	
		return DayOfWeek.getDay(day);
	}
	
	public DayOfWeek currentDay() // which day of week is current date
	{
		LocalDate date =  LocalDate.of(this.year, this.month, this.day);
		int day = date.getDayOfWeek().getValue();
		DayOfWeek dayOfWeek = DayOfWeek.getDay(day);
		
		return dayOfWeek;
	}
	
	public String toString()
	{ 
		String result = day + "/" + month + "/" + year + " ";
		result += hours + ":" + minutes + ":" + seconds + "\n";
		result += "Day of week " + currentDay() + "\n";
		return result;
	}
	
	public String toStringFormatOnlyDateAndHour()
	{
		String result = day + "/" + month + "/" + year + " ";
		result += hours + ":" + minutes ;
		return result;
	}
}