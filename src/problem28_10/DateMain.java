package problem28_10;

import problem28_10.Date;
import problem28_10.DayOfWeek;

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
						Date temp = new Date(arrDates[k].getday(), arrDates[k].getMonth(), arrDates[k].getYear(),
								arrDates[k].getHours(), arrDates[k].getMinutes(), arrDates[k].getSeconds());
						arrDates[k] = arrDates[i];
						arrDates[i] = temp;
					}
				}
			}
			output(arrDates);
		}
		public static void main(String[] args) {
			/*Date someDate = new Date(31,12,2021,3,2,1);
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
			
			System.out.println("Sorted arrey: ");
			Date[] arrDates = {otherDate,someDate,firstDate,secondDate,thirdDate};
			SortDates(arrDates);*/
			

			DayOfWeek day = DayOfWeek.MONDAY;
			System.out.println(day.toString(7));
			
			
			System.out.println("Today is " + Date.currentDay());
		}

	}


