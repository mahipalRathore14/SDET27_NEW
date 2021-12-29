package com.crm.Autodesk_contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.TestYantra_genericutility.ExcelUtility;
import com.crm.TestYantra_genericutility.FileUtility;
import com.crm.TestYantra_genericutility.JavaUtility;
import com.crm.TestYantra_genericutility.WebDriverUtility;

public class CreatingContactWithOrgName 
{
	public static void main(String[] args) throws Throwable 
	{
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();

		/*
		System.setProperty("wedriver.chrome.driver", "E:\\com.crm.sdet27\\src\\chromedriver.exe");
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


		//read test data from Excel file
		FileInputStream fis_e=new FileInputStream("./data/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis_e);
		Sheet sh=wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		String orgname = row.getCell(2).getStringCellValue()+randomNum;
		 */
		int randomNum = jLib.getRandomNumber();
		String URL=fLib.getPropertyKeyValue("url");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		String LASTNAME=fLib.getPropertyKeyValue("lastname");
	
		String orgname = eLib.getDataFromExcelSheet("Sheet1", 1, 2) + randomNum;



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

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//explicit wait given as to wait till the organization is created 
		//as while clicking on save button contact button is disabled so expplicit wait works on conditions
		/*WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("dvHeaderText"))));
*/
		WebElement headerWb = driver.findElement(By.className("dvHeaderText"));

        wLib.waitForPageToBeClickable(driver, headerWb);
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		/*//to get to the child window
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		while(it.hasNext())
		{
			String CurrentID=it.next();
			driver.switchTo().window(CurrentID);
			String cPageTitle =driver.getTitle();
			if(cPageTitle.contains("Accounts"))
			{
				break;
			}
		}*/
		wLib.SwitchToWindow(driver, "Accounts");

/*
		//searching and selecting org nme
		Set<String> set1=driver.getWindowHandles();
		Iterator<String> it1=window.iterator();
		while(it1.hasNext())
		{
			String CurrentID=it1.next();
			driver.switchTo().window(CurrentID);
			String cPageTitle =driver.getTitle();
			if(cPageTitle.contains("Contacts"))
			{
				break;
			}
		}
		*/
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		//to get back to parent window
		wLib.SwitchToWindow(driver, "Contacts");
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		//verification
		String actSuc_msg= driver.findElement(By.className("dvHeaderText")).getText();
		if(actSuc_msg.contains(LASTNAME))
		{
			System.out.println("Contact is successfully created : pass");
		}
		else
		{
			System.out.println("FAIL");
		}
		String actOrg=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actOrg.contains(orgname))
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


