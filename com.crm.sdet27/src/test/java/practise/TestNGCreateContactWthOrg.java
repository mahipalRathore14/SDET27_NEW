package practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.TestYantra_ObjectRepository.ContactInfoPage;
import com.crm.TestYantra_ObjectRepository.ContactsPage;
import com.crm.TestYantra_ObjectRepository.CreateContactPage;
import com.crm.TestYantra_ObjectRepository.CreateOrganizationPage;
import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_ObjectRepository.OrganizationPage;
import com.crm.TestYantra_genericutility.BaseClass;

public class TestNGCreateContactWthOrg extends BaseClass
{
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
		 SoftAssert sa=new SoftAssert();
		sa.assertTrue(actContact.contains(LASTNAME));
	}

}
