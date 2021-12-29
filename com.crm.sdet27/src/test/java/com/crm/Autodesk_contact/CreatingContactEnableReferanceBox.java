package com.crm.Autodesk_contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.TestYantra_genericutility.FileUtility;
import com.crm.TestYantra_genericutility.WebDriverUtility;

public class CreatingContactEnableReferanceBox 
{
	public static void main(String[] args) throws Throwable 
	{
		
		WebDriverUtility wLib = new WebDriverUtility();
        FileUtility fLib = new FileUtility();
		
		//setting path of data property file
		/*System.setProperty("webdriver.chrome.driver", "E:\\com.crm.sdet27\\src\\chromedriver.exe");
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		Properties p=new Properties();
		p.load(fis);

		//fetching data from property file
		String URL=p.getProperty("url");
		String BROWSER=p.getProperty("browser");
		String USERNAME=p.getProperty("username");
		String PASSWORD=p.getProperty("password");
		String LASTNAME=p.getProperty("lastname");
		String FIRSTNAME=p.getProperty("firstname");
*/
        String URL=fLib.getPropertyKeyValue("url");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		String LASTNAME=fLib.getPropertyKeyValue("lastname");
		String FIRSTNAME=fLib.getPropertyKeyValue("firstname");

		WebDriver driver;
		if(BROWSER.equals("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else
		{
			driver= new ChromeDriver();
		}

		//opening the browser
		driver.get(URL);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wLib.waitForPageToLoad(driver);
		//login into application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//click on contact button
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//filling the details with enabling the referance
		driver.findElement(By.name("firstname")).sendKeys(FIRSTNAME);
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		WebElement actReference = driver.findElement(By.name("reference"));
		if(actReference.isEnabled())
		{
			System.out.println("referance is successfully enabled : pass");
		}
		else
		{
			System.out.println("referance is not created :FAIL");
		}

		actReference.click();
		//saving the contact
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actSuc_msg= driver.findElement(By.className("dvHeaderText")).getText();

		if(actSuc_msg.contains(LASTNAME))
		{
			System.out.println("contact is successfully created : pass");
		}
		else
		{
			System.out.println("contact is not created :FAIL");
		}
		
		wLib.mouseOverOnElement(driver,driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		//Actions  a= new Actions(driver);
		//a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();


	}


}
