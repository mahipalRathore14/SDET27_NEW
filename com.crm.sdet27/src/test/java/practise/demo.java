package practise;

import java.util.Date;

public class demo 
{
	public static void main(String[] args) 
	{
		Date date=new Date();
		String DateAndTime=date.toString();
		String yyyy=DateAndTime.split(" ")[5];
		String dd=DateAndTime.split(" ")[2];
		int mm=date.getMonth()+1;
		String FinalFormat=yyyy+"-"+mm+"-"+dd;
		
		System.out.println(FinalFormat);
		System.out.println(DateAndTime);
		
	}

}
