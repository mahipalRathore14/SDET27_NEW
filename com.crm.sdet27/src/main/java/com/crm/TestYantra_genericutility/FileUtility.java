package com.crm.TestYantra_genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/*
 * @author mahipal
 */
public class FileUtility 
{
	public String getPropertyKeyValue(String key) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		Properties p=new Properties();
		p.load(fis);
		String PropertyKeyValue=p.getProperty(key);
		return PropertyKeyValue;
		
	}

	
}
