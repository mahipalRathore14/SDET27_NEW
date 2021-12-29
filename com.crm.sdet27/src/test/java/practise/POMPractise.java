package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.TestYantra_ObjectRepository.ContactsPage;
import com.crm.TestYantra_ObjectRepository.CreateContactPage;
import com.crm.TestYantra_ObjectRepository.HomePage;
import com.crm.TestYantra_ObjectRepository.LoginPage;
import com.crm.TestYantra_genericutility.FileUtility;
import com.crm.TestYantra_genericutility.JavaUtility;
import com.crm.TestYantra_genericutility.WebDriverUtility;

public class POMPractise 
{
	public static void main(String[] args) throws Throwable 
	{
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
        FileUtility fLib = new FileUtility();
        
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
		driver.get(URL);
		
		LoginPage lp=new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.clickOnContactBtn();
		
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContact(LASTNAME);

		
			hp.logout(driver);
			driver.quit();
	}

}
