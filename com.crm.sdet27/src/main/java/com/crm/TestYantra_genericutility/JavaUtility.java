package com.crm.TestYantra_genericutility;

import java.util.Date;
import java.util.Random;
/*
 * @author mahipal
 */

public class JavaUtility 
{
	//used to give random data 
	public int getRandomNumber()
	{
		Random random=new Random();
		int randomnum=random.nextInt(10000);
		return randomnum;

	}
	//used to give System date and time in IST format
	//@return String 
	public String getSystemDateAndTime()
	{
		Date date=new Date();
		return date.toString();
	}
	//used to get System date in yyyy/mm/dd format
	//@return String yyyy/mm/dd
	public String getSystemDateWithFormat()
	{
		Date date=new Date();
		String DateAndTime=date.toString();
		String yyyy=DateAndTime.split(" ")[5];
		String dd=DateAndTime.split(" ")[2];
		int mm=date.getMonth()+1;
		String FinalFormat=yyyy+"-"+mm+"-"+dd;
		
		return FinalFormat;
		
	}

}
