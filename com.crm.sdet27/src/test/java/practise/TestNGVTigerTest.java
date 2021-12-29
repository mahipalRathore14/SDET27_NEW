package practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.TestYantra_ObjectRepository.ContactInfoPage;
import com.crm.TestYantra_ObjectRepository.ContactsPage;
import com.crm.TestYantra_ObjectRepository.CreateContactPage;
import com.crm.TestYantra_ObjectRepository.CreateOrganizationPage;
import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_ObjectRepository.LoginPage;
import com.crm.TestYantra_ObjectRepository.OrganizationInfoPage;
import com.crm.TestYantra_ObjectRepository.OrganizationPage;
import com.crm.TestYantra_genericutility.BaseClass;

public class TestNGVTigerTest extends BaseClass
{
	@Test(groups = "smokeSuite")
	public void CreatingContactTest() throws Throwable
	{
		int rno=jLib.getRandomNumber();
		String LASTNAME=fLib.getPropertyKeyValue("lastname")+rno;
	
	HomePage hp=new HomePage(driver);
	hp.clickOnContactBtn();
	
	ContactsPage cp=new ContactsPage(driver);
	cp.clickOnCreateContactImg();
	
	CreateContactPage ccp=new CreateContactPage(driver);
	ccp.createContact(LASTNAME);
	
	ContactInfoPage cip=new ContactInfoPage(driver);
	String actMsg=cip.getContactInfo();
	if(actMsg.contains(LASTNAME))
	{
		System.out.println("contact added successfully");
	}
	else
	{
		System.out.println("contact not added");
	}
	}
	@Test(groups = "smokeSuite")
	public void createOrganizationTest() throws Throwable
	{
		int rno=jLib.getRandomNumber();
		String orgName=eLib.getDataFromExcelSheet("Sheet1",1,2)+rno;
		
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationBtn();
		
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrg();
		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.createOrg(orgName);
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actMsg=oip.OrgInfo();
		if(actMsg.contains(orgName))
		{
			System.out.println("organization created successfully");
		}
		else
		{
			System.out.println("failed to create organization");
		}
		
	}
	
	@Test(groups = "regressionSuite")
	public void creatOrgWithIndustry() throws Throwable
	{
		int rno=jLib.getRandomNumber();
		String orgName=eLib.getDataFromExcelSheet("Sheet1",1,2)+rno;
		String Industry =eLib.getDataFromExcelSheet("Sheet1", 2, 3);
		String Type =eLib.getDataFromExcelSheet("Sheet1", 2, 4);
		
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationBtn();
		
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrg();
		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		
		cop.createOrgWithIndustry(orgName,Industry, Type);
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actMsg=oip.OrgInfo();
		if(actMsg.contains(orgName))
		{
			System.out.println("organization created successfully");
		}
		else
		{
			System.out.println("failed to create organization");
		}
		
		String actIndMsg=oip.industryTypeText();
		if(actIndMsg.contains(Industry))
		{
			System.out.println("industry Selected successfully");
		}
		else
		{
			System.out.println("failed to select industry");
		}
		 
		String actTypeMsg=oip.indTypeTypeText();
		if(actTypeMsg.contains(Type))
		{
			System.out.println("industry type selected successfully");
		}
		else
		{
			System.out.println("failed to select industry type");
		}
	}
	@Test(groups = "regressionSuite")
	public void createContactWithOrgTest() throws Throwable
	{
		int rno=jLib.getRandomNumber();
		String LASTNAME=fLib.getPropertyKeyValue("lastname")+rno;
	
		String orgName=eLib.getDataFromExcelSheet("Sheet1",1,2)+rno;
		
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationBtn();
		
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnCreateOrg();
		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.createOrg(orgName);
		
		WebElement headerWb = driver.findElement(By.className("dvHeaderText"));

		wLib.waitForPageToBeClickable(driver, headerWb);
		
		hp.clickOnContactBtn();
		
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		CreateContactPage ccp=new CreateContactPage(driver);

		ccp.createContactWithOrg(driver, LASTNAME, orgName, orgName);
		//switching back to parent window
		wLib.SwitchToWindow(driver, "Accounts");
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		wLib.SwitchToWindow(driver, "Contacts");
		
		ccp.Savebtnn();

		ContactInfoPage cip=new ContactInfoPage(driver);
		 String actContact = cip.getContactInfo();
		if(actContact.contains(LASTNAME))
		{
			System.out.println("contact is successfully added with org name");
		}
		else 
		{
			System.out.println("failed");
		}

		
		
	}
	@Test(groups = "regressionSuite")
	public void createContactEnableNotifyOwnerTest() throws Throwable
	{
		int rno=jLib.getRandomNumber();
		String LASTNAME=fLib.getPropertyKeyValue("lastname")+rno;
		String FIRSTNAME=fLib.getPropertyKeyValue("firstname");
		
		HomePage hp=new HomePage(driver);
		hp.clickOnContactBtn();
		
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContactEnableNotify(driver, LASTNAME, FIRSTNAME);
	WebElement notifyEnabled = ccp.getClickOnNotify();
		if(notifyEnabled.isEnabled())
		{
			System.out.println("notify is enabled");
			
		}
		else
		{
			System.out.println("failed");
		}
	}
	
	@Test(groups = "regressionSuite")
	public void createContactEnableReferenceTest() throws Throwable
	{
		int rno=jLib.getRandomNumber();
		String LASTNAME=fLib.getPropertyKeyValue("lastname")+rno;
		String FIRSTNAME=fLib.getPropertyKeyValue("firstname");
		
		HomePage hp=new HomePage(driver);
		hp.clickOnContactBtn();
		
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContactEnableNotify(driver, LASTNAME, FIRSTNAME);
		WebElement referenceEnabled = ccp.getClickOnReference();
		if(referenceEnabled.isEnabled())
		{
			System.out.println("reference box is enabled");
		}
		else
		{
			System.out.println("failed to enable reference box");
		}
	}
}
