package rmgyantra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class AddingProjectOnRMGYantra 
{
	public static void main(String[] args) throws Throwable 
	{
		

		System.setProperty("webdriver.chrome.driver","./src/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		driver.findElement(By.xpath("//a[text()='Projects']")).click();

		driver.findElement(By.xpath("//span[text()='Create Project']")).click();

		driver.findElement(By.name("projectName")).sendKeys("mahi");
		driver.findElement(By.name("createdBy")).sendKeys("mahipal");
		Select s = new Select(driver.findElement(By.xpath("(//select[@name='status'])[2]")));
		s.selectByIndex(1);

		driver.findElement(By.cssSelector("input[value='Add Project']")).click();

		driver.close();

		System.out.println("start");
		String expectedCreatedby="mahipal";
		Connection connection=null;
		try
		{
			//registering database
			Driver driver1 = new Driver();
			DriverManager.registerDriver(driver1);

			//establish the connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");

			//issue the statement
			Statement statement = connection.createStatement();

			//execute statement
			ResultSet result = statement.executeQuery("select * from project");
			while(result.next())
			{
				if(result.getString(2).equals(expectedCreatedby))
				{
					System.out.println("passed");
				}
			}
		}
		finally
		{

			connection.close();
		}
		
		
		


	}

}
