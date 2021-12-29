package com.crm.TestYantra_genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_ObjectRepository.LoginPage;

public class BaseClass 
{
	public JavaUtility jLib=new JavaUtility();
	public DataBaseUtility dLib=new DataBaseUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public FileUtility fLib=new FileUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sdriver;
	@BeforeSuite(groups = {"smokeSuite","regressionSuite"})
	public void connectToDataBase()
	{
		dLib.ConnectToDb();
		System.out.println("data base connected successfully");
	}
	//@Parameters("browser")
	@BeforeClass(groups = {"smokeSuite","regressionSuite"})
	public void launchingBrowser() throws Throwable //parameters ke liye String BROWSER likhna
	//and neeche line ko comment out krna
	{
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL=fLib.getPropertyKeyValue("url");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();	
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		wLib.waitForPageToLoad(driver);
		wLib.maximizeWindow(driver);
		driver.get(URL);
	}
	@BeforeMethod(groups = {"smokeSuite","regressionSuite"})
	public void loginToAppl() throws Throwable
	{
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		
		LoginPage lp=new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);
		System.out.println("login successfully");
		
		
	}
	@AfterMethod(groups = {"smokeSuite","regressionSuite"})
	public void logoutOfAppl()
	{
		HomePage hp=new HomePage(driver);
		hp.logout(driver);
		System.out.println("logout successfully");
	}
	
	@AfterClass(groups = {"smokeSuite","regressionSuite"})
	public void closingBrowser()
	{
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeSuite","regressionSuite"})
	public void closingDataBase()
	{
		dLib.DisconnectToDb();
		System.out.println("database closed successfully");
	}

}
