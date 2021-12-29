package com.crm.TestYantra_genericutility;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;



/*
 * @author mahipal
 */
public class WebDriverUtility 
{
	//wait for page to load before identifyig any sychronized element in HTML
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


	//wait for page to load before identifying any assynchronized element 
	public void waitForPageToLoadForJSE(WebDriver driver)
	{
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	}


	//used to wait for element to be clickable in GUI and  check for element in every 500 miliseconds
	public void waitForPageToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}



	//used to wait for element to be clickable in GUI and  check for element in every 500 miliseconds
	public void waitForElementWithCustomTimeout( WebDriver driver, WebElement element, int pollingTime) throws Throwable
	{
		FluentWait wait=new FluentWait(driver);
		wait.pollingEvery(pollingTime,TimeUnit.SECONDS);
		wait.wait();
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}


	//used to switch to any window based on the window title
	public void SwitchToWindow(WebDriver driver , String partialWindowTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String wID = it.next();
			driver.switchTo().window(wID);
			String currentWindowTitle = driver.getTitle();
			if(currentWindowTitle.contains(partialWindowTitle))
			{
				break;	
			}
		}
	}

	//used to switch to alert window AND click on ok button
	public void switchToAlertWindowAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}

	//used to switch to alert window and click on cancel button
	public void switchToAlertWindowAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}

	//used to switch to frame window based on index  
	public void switchToFrame(WebDriver driver ,int index)
	{
		driver.switchTo().frame(index);
	}


	//used to switch to frame window based on is name attribute
	public void switchToFrame(WebDriver driver , String id_name_attribute)
	{
		driver.switchTo().frame(id_name_attribute);
	}


	//used to select  the value from dropdown based on index
	public void select(WebElement element , int index) 
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}


	//used to select the value from dropdown based on text
	public void select(WebElement element , String text) 
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}


	//used to place mouse cursor on specified element
	public void mouseOverOnElement(WebDriver driver , WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	//used to right click on any element
	public void rightClickOnElement(WebDriver driver , WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}

	//used to take screenshot
	public void takeScreenshot(WebDriver driver, String screenshotName) throws Throwable 
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/"+screenshotName+".PNG");
		Files.copy(src, dest);
	}
	//used to maximize the window
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	
	}



}
