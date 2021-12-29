package com.crm.Autodesk_org;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.TestYantra_genericutility.ExcelUtility;
import com.crm.TestYantra_genericutility.FileUtility;
import com.crm.TestYantra_genericutility.JavaUtility;
import com.crm.TestYantra_genericutility.WebDriverUtility;

public class CreateOrganizationWithIndustry 
{
	public static void main(String[] args) throws Throwable 
	{
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();

		/*System.setProperty("wedriver.chrome.driver", "E:\\com.crm.sdet27\\src\\chromedriver.exe");
		//read common data from properties file
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		Properties pObj=new Properties();
		pObj.load(fis);

		String URL =pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD =pObj.getProperty("password");
		String BROWSER =pObj.getProperty("browser");


		//creating random number
		Random ran =new Random();
		int randomNum=ran.nextInt(10000);

		//read test data from Excel file
		FileInputStream fis_e=new FileInputStream("./data/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis_e);
		Sheet sh=wb.getSheet("Sheet1");
		Row row = sh.getRow(2);
		String orgname = row.getCell(2).getStringCellValue()+randomNum;

		String Industry=row.getCell(3).getStringCellValue();
		String Type=row.getCell(4).getStringCellValue();
		*/
		int randomNum = jLib.getRandomNumber();
		String URL=fLib.getPropertyKeyValue("url");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
	
		String orgname = eLib.getDataFromExcelSheet("Sheet1", 2, 2) + randomNum;

		String Industry =eLib.getDataFromExcelSheet("Sheet1", 2, 3);
		String Type =eLib.getDataFromExcelSheet("Sheet1", 2, 4);
		WebDriver driver = null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		//login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		//to select industry type from dropdown
		
		wLib.select(driver.findElement(By.name("industry")), Industry);
		//Select s=new Select(driver.findElement(By.name("industry")));
		//s.selectByVisibleText(Industry);
		//to select type from dropdown
		wLib.select(driver.findElement(By.name("accounttype")), Type);
		//Select s1=new Select(driver.findElement(By.name("accounttype")));
		//s1.selectByVisibleText(Type);
		
		
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verification
		String actSuc_msg= driver.findElement(By.className("dvHeaderText")).getText();

		if(actSuc_msg.contains(orgname))
		{
			System.out.println("org is successfully created : pass");
		}
		else
		{
			System.out.println("FAIL");
		}
		String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.contains(Industry))
		{
			System.out.println("Industry is successfully selected : pass");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		String actType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.contains(Type))
		{
			System.out.println("type is successfully selected : pass");
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
