package problem28_10;

import java.time.LocalDate;

public class DateMain {

	

		public static void output(Date[] arrDates)
		{
			for(int i = 0 ; i < arrDates.length ; i++)
			{
				System.out.println(arrDates[i]);
			}
		}
		public static void SortDates(Date[] arrDates)
		{
			for(int i = 0 ; i < (arrDates.length - 1) ; i++)
			{
				for(int k = i+1 ; k < arrDates.length ; k++)
				{
					if(arrDates[k].compareTo(arrDates[i]) == 1)
					{
						Date temp = new Date(arrDates[k].getDay(), arrDates[k].getMonth(), arrDates[k].getYear(),
								arrDates[k].getHours(), arrDates[k].getMinutes(), arrDates[k].getSeconds());
						arrDates[k] = arrDates[i];
						arrDates[i] = temp;
					}
				}
			}
			output(arrDates);
		}
		public static void main(String[] args) {
			/*FIRST PART
			Date someDate = new Date(31,12,2021,3,2,1);
			Date otherDate = new Date();
			
			System.out.println(someDate);
			System.out.println(otherDate);
			
			if(otherDate.compareTo(someDate) == -1) System.out.println("first date is before second");
			else if(otherDate.compareTo(someDate) == 1) System.out.println("second date is before first");
			else System.out.println("they are same");

			System.out.println(someDate.getYear() + " is leap year = " + someDate.isLeapYear());
			System.out.println("days : " + someDate.days());
			System.out.println("weeks : " + someDate.weeks());
			
			Date firstDate = new Date(1,11,2021,3,2,1);
			Date secondDate = new Date(1,10,2019,6,5,4);
			Date thirdDate = new Date(30,9,2022,3,5,2);
			
			System.out.print(thirdDate + " + 1 second = ");
			thirdDate.up();
			System.out.println(thirdDate);
			
			System.out.println("Sorted array: ");
			Date[] arrDates = {otherDate,someDate,firstDate,secondDate,thirdDate};
			SortDates(arrDates); */
			
			//Date someDate = new Date(31,12,2021,18,30,0);
			/*SECOND PART
			System.out.println("Today is " + Date.today());
			DayOfWeek day = DayOfWeek.MONDAY;
			System.out.println(day.toString(7));
			*/
			
			//THIRD PART
			Date someDate = new Date(31,12,2021,18,30,0);
			Date firstDate = new Date(31,10,2021,21,30,0);
			Date secondDate = new Date(25,12,2019,22,0,0);
			Date thirdDate = new Date(31,10,2021,23,30,0);
			
			Event someEvent = new Event(someDate,"New Year party");
			Event firstEvent = new Event(firstDate, "Halloween party");
			Event secondEvent = new Event(secondDate, "Christmas party");
			Event thirdEvent = new Event(thirdDate, "movie night");
			
			Reminder plans = new Reminder();
			plans.addEvent(someEvent);
			plans.addEvent(firstEvent);
			plans.addEvent(secondEvent);
			plans.addEvent(thirdEvent);
			
			plans.print(plans.getAllEventsAt(thirdDate));
			
			//events for today
			int day = LocalDate.now().getDayOfMonth();
			int month = LocalDate.now().getMonthValue();
			int year = LocalDate.now().getYear();
			Date today = new Date(day, month, year);
			Event todayEvent = new Event(today, "party");
			plans.addEvent(todayEvent);
			plans.remind();
		}

	}


