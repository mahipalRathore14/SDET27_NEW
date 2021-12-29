package com.crm.Autodesk_contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.TestYantra_genericutility.FileUtility;
import com.crm.TestYantra_genericutility.JavaUtility;
import com.crm.TestYantra_genericutility.WebDriverUtility;

public class CreatingContactOnVTiger 
{
	public static void main(String[] args) throws Throwable 
	{
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
        FileUtility fLib = new FileUtility();
		
		/*System.setProperty("wedriver.chrome.driver", "E:\\com.crm.sdet27\\src\\chromedriver.exe");
		//read common data from properties file
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		Properties pObj=new Properties();
		pObj.load(fis);

		//creating random number
		Random ran =new Random();
		int randomNum=ran.nextInt(10000);

		String URL =pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD =pObj.getProperty("password");
		String BROWSER =pObj.getProperty("browser");

		String LASTNAME=pObj.getProperty("lastname")+randomNum;

		*/
        int randomNum = jLib.getRandomNumber();

        
        String URL=fLib.getPropertyKeyValue("url");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		String LASTNAME=fLib.getPropertyKeyValue("lastname")+randomNum;


		WebDriver driver=null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		wLib.waitForPageToLoad(driver);
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actSuc_msg= driver.findElement(By.className("dvHeaderText")).getText();

		if(actSuc_msg.contains(LASTNAME))
		{
			System.out.println("org is successfully created : pass");
		}
		else
		{
			System.out.println("FAIL");
		}
		wLib.mouseOverOnElement(driver,driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		//Actions  a= new Actions(driver);
		//a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

		
	}

}
