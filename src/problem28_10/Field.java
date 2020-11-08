package problem28_10;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Field {

	private JPanel pnlField;
	private JLabel lblDate;
	private JLabel[] lblEvents;
	
	public static final int FIELD_WIDTH = 80; 
	public static final int FIELD_HEIGHT = 80;
	public static final int LABLE_DAY_OF_WEEK_WIDTH = 20; 
	public static final int LABLE_DAY_OF_WEEK_HEIGHT = 20; 
	public static final int FIX_HEIGHT = 10;
	public static final int SPACE = 15;
	
	public Field(Date date, Reminder plans)
	{
		setLblDate(date);
		setLblEvents(date,plans);
		setPnlField();
	}
	
	public void setLblDate(Date date)
	{
		int xLblDay = 5;
		int yLblDay = 5;
		int widthLblDay = 15;
		int heightLblDay = 10;
		
		int day = date.getDay();
		JLabel lblDay = new JLabel();
		if (day != 0) {
			lblDay.setText(String.valueOf(day));
			lblDay.setBounds(xLblDay, yLblDay, widthLblDay, heightLblDay); // 5, 5, 10, 10
		}
		
		this.lblDate = lblDay;
	}
	
	public JLabel getLblDate()
	{
		return lblDate;
	}
	
	public void setLblEvents(Date date, Reminder plans)
	{
		if(plans == null)
		{
			JLabel newLbl = new JLabel("");
			this.lblEvents[0] = newLbl;
		}
		else 
		{
			Event[] eventsArr = plans.getAllEventsAt(date);
			this.lblEvents = toJLabelsArray(eventsArr);
		}
	}
	
	public JLabel[] getLblEvents()
	{
		return lblEvents; 
	}
	
	public void setPnlField()
	{
		JPanel pnlNewField = new JPanel();
		pnlNewField.setLayout(null);
		pnlNewField.setBackground(Color.white);
		pnlNewField.setVisible(true);
		this.pnlField = pnlNewField;
	}
	
	public JLabel[] toJLabelsArray(Event[] eventsArr) //event[] to JLabel[]
	{
		int lenght = eventsArr.length;
		JLabel[] lblEventsArray = new JLabel[lenght];
		for(int i = 0 ; i < lenght ; i++)
		{
			JLabel lblEvent = new JLabel();
			lblEvent.setBackground(Color.cyan);
			//lblEvent.setOpaque(true);
			lblEvent.setText(String.valueOf(eventsArr[i].getName()));
			lblEventsArray[i] = lblEvent;
		}
		return lblEventsArray;
	}
	
	public boolean hasEvents()
	{
		if (this.lblEvents[0] == null) return false;
		else return true;
	}
	
	public JPanel make(int x, int y) //add date and events
	{
		pnlField.setBounds(x, y, FIELD_WIDTH, FIELD_HEIGHT);
		pnlField.add(lblDate);
		
		int xEvent = lblDate.getX();
		int yEvent = lblDate.getY() + lblDate.getHeight() + SPACE;
		int widthEvent = FIELD_WIDTH - xEvent; 
		int heightEvent = FIX_HEIGHT; //10
		
		if(lblEvents != null)
		{
			for(JLabel element : lblEvents)
			{
				element.setBounds(xEvent, yEvent, widthEvent, heightEvent);
				element.setVisible(true); 
				element.setBackground(Color.cyan);
				element.setOpaque(true);
				
				pnlField.add(element);
				yEvent += SPACE; //10
			}
		}
		
		return this.pnlField;
	}
}
