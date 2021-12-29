package com.crm.TestYantra_genericutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer
{

	int count=0;
	int retrycount=4;
	@Override
	public boolean retry(ITestResult result)
	{
		while(count<retrycount)
		{
			count++;
			return true;
		}
		
		return false;
	}
	

}
