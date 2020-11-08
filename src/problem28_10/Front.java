package problem28_10;

import java.awt.Color;
import java.time.Month;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Front {

	public static final int WINDOW_WIDTH = 655;
	public static final int WINDOW_HEIGHT = 600; 
	public static final int LABLE_DAY_OF_WEEK_WIDTH = 20; 
	public static final int LABLE_DAY_OF_WEEK_HEIGHT = 20; 
	public static final int FIELD_WIDTH = 80; 
	public static final int FIELD_HEIGHT = 80;
	public static final int FIRST_SPACE = 10;
	
	public static void main(String[] args) {
		
		Date date = new Date(7,11,2020);
		String monthAndYear = Month.of(date.getMonth()).name().toLowerCase() + ", " + date.getYear();
		JFrame window = new JFrame(monthAndYear); // calendar for month of date
		window.setLayout(null);
		window.setVisible(true);
		window.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Write labels (Monday - Sunday)
		JLabel[] arrTxtDayOfWeek = new JLabel[7];
		int xLable = 10;
		for(int i = 0 ; i < 7 ; i++)
		{
			String day = DayOfWeek.getDay(i+1).toString(3);
			
		    JLabel txtDayOfWeek = new JLabel(day);
			txtDayOfWeek.setBounds(xLable, 10, day.length()*10, LABLE_DAY_OF_WEEK_HEIGHT);
			arrTxtDayOfWeek[i] = txtDayOfWeek;
			
			xLable += 10 + FIELD_WIDTH;
			window.add(arrTxtDayOfWeek[i]);
		}
		
        JPanel[] arrPnlFields = new JPanel[42]; // 6 rows, 7 columns
        
		int daysOfCurrentMonth = date.daysInMonth();
		Date firstDateOfMonth = new Date(1, date.getMonth(), date.getYear());
		DayOfWeek dayOfWeek = firstDateOfMonth.currentDay();
		
		// Create empty days
		int counter = 0;
		for	(int i = 0; i < dayOfWeek.getValue() - 1; i++)
		{
			int row = i * (FIELD_HEIGHT + 10) + FIRST_SPACE;
			int col = FIRST_SPACE + LABLE_DAY_OF_WEEK_WIDTH;
			
			var emptyDate = new Date();
			var emptyRemainder = new Reminder();
			Field currentField = new Field(emptyDate, emptyRemainder);
			JPanel pnlField = currentField.make(row, col);
			pnlField.setBackground(Color.gray);
			
			arrPnlFields[counter] = pnlField;
			counter++;
		}
		
		// Create month days
		int weekCounter = 1;
		for (int i = counter; i < counter + daysOfCurrentMonth; i++)
		{
			int j = i / 7;			
			int row = (i % 7) * (FIELD_HEIGHT + 10) + FIRST_SPACE;
			int col = j * (FIELD_WIDTH + 10) + LABLE_DAY_OF_WEEK_WIDTH + FIRST_SPACE;
			
			Date currentDay = new Date(weekCounter, date.getMonth(), date.getYear());		
			Reminder plans = new Reminder();
			if (i % 3 == 0)
			{
				Event event = new Event(currentDay, "training");
				plans.addEvent(event);
			}
			Field currentField = new Field(currentDay, plans);
			arrPnlFields[i] = currentField.make(row, col);
			
			weekCounter++;			
		}

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

}
