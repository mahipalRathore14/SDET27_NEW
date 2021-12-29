package practise;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.TestYantra_ObjectRepository.CreateOrganizationPage;
import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_ObjectRepository.OrganizationInfoPage;
import com.crm.TestYantra_ObjectRepository.OrganizationPage;
import com.crm.TestYantra_genericutility.BaseClass;

public class TestNGCreateOrg extends BaseClass
{
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
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(actMsg.contains(orgName));
	}
	

}
