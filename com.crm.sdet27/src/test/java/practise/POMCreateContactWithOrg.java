package practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.TestYantra_ObjectRepository.ContactInfoPage;
import com.crm.TestYantra_ObjectRepository.ContactsPage;
import com.crm.TestYantra_ObjectRepository.CreateContactPage;
import com.crm.TestYantra_ObjectRepository.CreateOrganizationPage;
import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_ObjectRepository.LoginPage;
import com.crm.TestYantra_ObjectRepository.OrganizationPage;
import com.crm.TestYantra_genericutility.ExcelUtility;
import com.crm.TestYantra_genericutility.FileUtility;
import com.crm.TestYantra_genericutility.JavaUtility;
import com.crm.TestYantra_genericutility.WebDriverUtility;

public class POMCreateContactWithOrg 
{
	public static void main(String[] args) throws Throwable 
	{
		//importing generic libraries
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();

		int randomNum = jLib.getRandomNumber();


		//fetching data from property file
		String URL=fLib.getPropertyKeyValue("url");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		String LASTNAME=fLib.getPropertyKeyValue("lastname")+randomNum;


		//fetching data from excel sheet
		String orgname = eLib.getDataFromExcelSheet("Sheet1", 1, 2) + randomNum;


		//launching the browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}


		driver.manage().window().maximize();
		wLib.waitForPageToLoad(driver);
		driver.get(URL);

		//login to the aplication
		LoginPage lp=new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//clicking on organication
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationBtn();

		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrg();

		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.createOrg(orgname);

		WebElement headerWb = driver.findElement(By.className("dvHeaderText"));

		wLib.waitForPageToBeClickable(driver, headerWb);

		hp.clickOnContactBtn();
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactImg();

		//creating contact with organization
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContactWithOrg(driver, LASTNAME, orgname, orgname);


		//switching back to parent window
		wLib.SwitchToWindow(driver, "Accounts");
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		wLib.SwitchToWindow(driver, "Contacts");
		
		ccp.Savebtnn();

		ContactInfoPage cip=new ContactInfoPage(driver);
		 String actContact = cip.getContactInfo();
		if(actContact.equals(LASTNAME))
		{
			System.out.println("contact is successfully added with org name");
		}
		else 
		{
			System.out.println("failed");
		}

		//logout
		hp.logout(driver);
		driver.quit();


	}

}
