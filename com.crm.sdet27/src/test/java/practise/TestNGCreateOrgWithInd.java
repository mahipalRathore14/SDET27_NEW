package practise;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.TestYantra_ObjectRepository.CreateOrganizationPage;
import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_ObjectRepository.OrganizationInfoPage;
import com.crm.TestYantra_ObjectRepository.OrganizationPage;
import com.crm.TestYantra_genericutility.BaseClass;

public class TestNGCreateOrgWithInd extends BaseClass
{
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
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(actMsg.contains(orgName), "succesfully created organization");
		
		String actIndMsg=oip.industryTypeText();
		sa.assertTrue(actIndMsg.contains(Industry), "ind successfully added");
		String actTypeMsg=oip.indTypeTypeText();
		sa.assertTrue(actTypeMsg.contains(Type), "type successfully added");
		sa.assertAll();
	}

}
