package problem28_10;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

import problem28_10.DayOfWeek;

public class Date {
	private int seconds;
	private int minutes;
	private int hours;
	private int day;
	private int month;
	private int year;
	public int getSeconds()
	{
		return seconds;
	}
	public int getMinutes()
	{
		return minutes;
	}
	public int getHours()
	{
		return hours;
	}
	public int getday()
	{
		return day;
	}
	public int getMonth()
	{
		return month;
	}
	public int getYear()
	{
		return year;
	}
	public void setSeconds(int secondsValue)
	{
		if(secondsValue < 60 && secondsValue >= 0) seconds = secondsValue;
	}
	public void setMinutes(int minutesValue)
	{
		if(minutesValue < 60 && minutesValue >= 0) minutes = minutesValue;
	}
	public void setHours(int hoursValue)
	{
		if(hoursValue < 24 && hoursValue >= 0) hours = hoursValue;
	}
	public void setDayMonthYear(int dayValue,int monthValue, int yearValue)
	{
		if(yearValue > 0)
		{
			year = yearValue;
		}
		if(dateExist(dayValue, monthValue,yearValue))
		{
			month = monthValue;
			day = dayValue;
		}
		else 
		{
			day = 1;
			month = 1;
		}
	}
	public Date(int dayValue , int monthValue , int yearValue, int hoursValue, int secondsValue , int minutesValue)
	{
		setSeconds(secondsValue);
		setMinutes(minutesValue);
		setHours(hoursValue);
		setDayMonthYear(dayValue, monthValue, yearValue);
	}
	public Date()
	{
		day = 1;
		month = 1;
		year = 2020;
		hours = 0;
		minutes = 0;
		seconds = 0;
		//default 0:0:0 1/1/2020
	}
	public boolean isLeapYear()
	{
		if(this.year % 4 == 0 )
		{
			if(this.year % 100 == 0) 
			{
				if(this.year % 400 == 0)
				return true;
			}
			else return true;
		}
		return false;
	}
	private boolean dateExist(int dayValue,int monthValue,int yearValue)
	{
		if(monthValue <= 12 && monthValue >= 1)
		{
			if(monthValue == 2)
			{
				if(dayValue <= 28 && isLeapYear()) return true;
			}
			else if(dayValue <= 30  && dayValue > 0) return true;
			else if(dayValue == 31)
			{
				if(monthValue <= 7 && monthValue % 2 == 1)return true;
				else if(monthValue >= 8 && monthValue % 2 == 0) return true;
			}
		}
		return false;
	}
	public void up()
	{
		
		if(this.seconds < 59) this.seconds ++;
		else
		{
			this.seconds = 0;
			this.minutes++;
			if(this.minutes == 60)
			{
				this.minutes = 0;
				this.hours++;
			}
			if(this.hours == 24)
			{
				this.hours = 0;
				this.day++;
			}
			if(dateExist((this.day + 1), this.month,this.year))
			{
				this.day++; //ako sledwashtiq den sushestvuva to uvelichavame denq
			}
			else
			{
				this.day = 1;
				if(dateExist(this.day, (this.month + 1),this.year)) this.month++; //ako sledwashtiq mesec sushestvuva to uvelichavame meseca
				else
				{
					this.month = 1;
					this.year++;
				}
			}
		}
		
	}
	public int days()
	{
		int days;
		if(this.month == 1) days = this.day;
		else days = this.month * 30; //priemame che vsichki meseci sa s po 30 dni
		
		if(isLeapYear()) days--; //zadadi 29.02
		else days -= 2; //28.02
		
		int count = 0;
		for(int i = 1 ; i <= this.month ; i++)
		{
			if(i <= 7 && i % 2 == 1)count++;
			else if(i >= 8 && i % 2 == 0) count++;
		}
		//prebroqwane kolko meseca imame s po 31 dena
		days+=count;
		return days;
	}
	public int weeks()
	{
		int weeks = Math.round(days() / 7); 
		if(weeks == 0) return 1; // ako e pyrwata sedmica ot godinata 
		return weeks;
	}
	public int compareTo(Date someDate)
	{
		if(this.year == someDate.year && this.month == someDate.month && this.day == someDate.day
				&& this.hours == someDate.hours && this.minutes == someDate.minutes
				&& this.seconds == someDate.seconds) return 0;
		else 
		{
			if(this.year > someDate.year) return 1;
			else if(this.year == someDate.year)
			{
				if(this.month > someDate.month) return 1;
				else if(this.month == someDate.month)
				{
					if(this.day > someDate.day) return 1;
					else if(this.day == someDate.day)
					{
						if(this.hours > someDate.hours) return 1;
						else if(this.hours == someDate.hours)
						{
							if(this.minutes > someDate.minutes) return 1;
							else if(this.minutes == someDate.minutes)
							{
								if(this.seconds > someDate.seconds) return 1;
							}
						}
					}
				}
			} // ako purvata data e sled vtorata
		}
		return -1;//ako e obratnoto (vtorata data e sled purvata )
	}
	
	public String toString()
	{
		String result = hours + ":" + minutes + ":" + seconds;
		result += " " + day + "/" + month + "/" + year + "\n";
		result += "Day of week " + currentDay();
		return result;
	}
	public static DayOfWeek today()
	{
		Calendar c = Calendar.getInstance();
		TimeZone timeZone = c.getTimeZone();
		c.setTimeZone(timeZone);
		int day = c.get(Calendar.DAY_OF_WEEK);
		DayOfWeek dayOfWeek = DayOfWeek.getDay(day);
		return dayOfWeek;
	}
	public DayOfWeek currentDay()
	{
		LocalDate date =  LocalDate.of(this.year, this.month, this.day);
		int day = date.getDayOfWeek().getValue();
		DayOfWeek dayOfWeek = DayOfWeek.getDay(day);
		return dayOfWeek;
	}
}

