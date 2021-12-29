package practise;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.TestYantra_ObjectRepository.ContactInfoPage;
import com.crm.TestYantra_ObjectRepository.ContactsPage;
import com.crm.TestYantra_ObjectRepository.CreateContactPage;
import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_genericutility.BaseClass;
@Listeners(com.crm.TestYantra_genericutility.ListnerImplementation.class)
public class TestNGCreateContact extends BaseClass
{
	@Test(groups = "smokeSuite",retryAnalyzer = com.crm.TestYantra_genericutility.RetryAnalyser.class)
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
	//Assert.fail();
	
	ContactInfoPage cip=new ContactInfoPage(driver);
	String actMsg=cip.getContactInfo();
	SoftAssert sa=new SoftAssert();
	sa.assertTrue(actMsg.contains(LASTNAME));
	}

}
