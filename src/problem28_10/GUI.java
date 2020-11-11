package problem28_10;

import java.awt.Color;
import java.time.Month;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class GUI {

	public static final int WINDOW_WIDTH = 655;
	public static final int WINDOW_HEIGHT = 610; 
	public static final int LABLE_DAY_OF_WEEK_WIDTH = 20; 
	public static final int LABLE_DAY_OF_WEEK_HEIGHT = 20; 
	public static final int FIELD_WIDTH = 80; 
	public static final int FIELD_HEIGHT = 80;
	public static final int FIRST_SPACE = 10;
	
	public static void makeCalendar(Date date)
	{
		String monthAndYear = Month.of(date.getMonth()).name().toLowerCase() + ", " + date.getYear();
		JFrame window = new JFrame(monthAndYear); // calendar for month of date
		window.setLayout(null);
		window.setVisible(true);
		window.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel[] arrTxtDayOfWeek = makeLabels();
		for(int i = 0 ; i < 7 ; i++)
		{
			window.add(arrTxtDayOfWeek[i]);
		}
		
		JPanel[] arrPnlFields = new JPanel[42]; // 6 rows, 7 columns
		
		makeEmptyDays(date, arrPnlFields);
		
		int CountEmptyDays = CountEmptyDays(date);
		
		makeDaysOfMonth(date, arrPnlFields, CountEmptyDays);
		
		for(int i = 0 ; i < arrPnlFields.length; i++)
		{
			JPanel panel = arrPnlFields[i];
			if (panel != null)
			{
				window.add(panel);	
			}
		}
		
		window.repaint();
	}
	
	public static JLabel[] makeLabels()
	{
		JLabel[] arrTxtDayOfWeek = new JLabel[7];
		int xLable = 10;
		
		for(int i = 0 ; i < 7 ; i++)
		{
			String day = DayOfWeek.getDay(i+1).toString(3);
			
		    JLabel txtDayOfWeek = new JLabel(day);
			txtDayOfWeek.setBounds(xLable, 10, day.length()*10, LABLE_DAY_OF_WEEK_HEIGHT);
			arrTxtDayOfWeek[i] = txtDayOfWeek;
			
			xLable += 10 + FIELD_WIDTH;
		}
		
		return arrTxtDayOfWeek;
	}
	
	public static int CountEmptyDays(Date date)
	{
		Date firstDateOfMonth = new Date(1, date.getMonth(), date.getYear());
		DayOfWeek dayOfWeek = firstDateOfMonth.currentDay();
		int counter = dayOfWeek.getValue() - 1;
		
		return counter;
	}
	
	public static JPanel setEmptyDayElements(int i)
	{
		int row = i * (FIELD_HEIGHT + 10) + FIRST_SPACE;
		int col = FIRST_SPACE + LABLE_DAY_OF_WEEK_WIDTH;
		
		Date emptyDate = new Date();
		Reminder emptyRemainder = new Reminder();
		Field currentField = new Field(emptyDate, emptyRemainder);
		JPanel pnlField = currentField.make(row, col);
		pnlField.setBackground(Color.gray);
		pnlField.setVisible(true);
		
		return pnlField;
	}
	
	public static void makeEmptyDays(Date date, JPanel[] arrPnlFields)
	{
		Date firstDateOfMonth = new Date(1, date.getMonth(), date.getYear());
		DayOfWeek dayOfWeek = firstDateOfMonth.currentDay();
		int counter = 0;
		
		for	(int i = 0; i < dayOfWeek.getValue() - 1; i++)
		{	
			arrPnlFields[counter] = setEmptyDayElements(i);
			counter++;
		}
	}
	
	public static void makeDaysOfMonth(Date date, JPanel[] arrPnlFields, int counter)
	{
		int daysOfCurrentMonth = date.daysInMonth();
		int day = 1;
		for (int i = counter; i < counter + daysOfCurrentMonth; i++)
		{
			int j = i / 7;		
			int row = (i % 7) * (FIELD_HEIGHT + 10) + FIRST_SPACE;
			int col = j * (FIELD_WIDTH + 10) + LABLE_DAY_OF_WEEK_WIDTH + FIRST_SPACE;
			
			Date currentDay = new Date(day, date.getMonth(), date.getYear());		
			Reminder plans = new Reminder();
			
			if (i % 3 == 0)
			{
				Event event = new Event(currentDay, "training");
				plans.addEvent(event);
			}
			
			Field currentField = new Field(currentDay, plans);
			formatField(day, date, currentField);
			arrPnlFields[i] = currentField.make(row, col);
			
			day++;			
		}
	}
	
	public static void formatField(int day, Date date, Field currentField)
	{
		Date currentDay = new Date(day, date.getMonth(), date.getYear());
		int dayOfWeekIndex = currentDay.currentDay().getValue();
		
		//set different background color for weekend
		if (dayOfWeekIndex % 6 == 0 || dayOfWeekIndex % 7 == 0)
		{
			currentField.getPnlField().setBackground(Color.LIGHT_GRAY);
		}
		
		//set different background color for today
		if (day == date.getDay()) 
		{
			Color todayColor = new Color(255,77,77);
			currentField.getPnlField().setBackground(todayColor);;
		}
	}
	
	public static void main(String[] args) {
		
		Date date = new Date(11,11,2020);	
		makeCalendar(date);
	}
}
