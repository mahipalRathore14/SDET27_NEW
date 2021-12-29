package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.TestYantra_genericutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility
{
	@FindBy(name="user_name")
	private WebElement usernameTb;
	
	@FindBy(name="user_password")
	private WebElement passwordTb;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getUsernametb() {
		return usernameTb;
	}


	public WebElement getPasswordtb() {
		return passwordTb;
	}


	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void login(String username ,String password )
	{
		usernameTb.sendKeys(username);
		passwordTb.sendKeys(password);
		loginBtn.click();
	}
	
	
	
}
