package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.TestYantra_genericutility.WebDriverUtility;

public class HomePage extends WebDriverUtility
{
	@FindBy(linkText="Organizations")
	private WebElement organizationBtn;

	@FindBy(linkText = "Contacts")
	private WebElement contactsBtn;

	@FindBy(linkText = "Email")
	private WebElement emailBtn;

	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminLogoutImg;

	@FindBy(linkText ="Sign Out")
	private WebElement logoutBtn;

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrganizationBtn() {
		return organizationBtn;
	}

	public WebElement getContactsBtn() {
		return contactsBtn;
	}

	public WebElement getEmailBtn() {
		return emailBtn;
	}

	public WebElement getAdminLogoutImg() {
		return adminLogoutImg;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	//business lib to click on organization
	public void clickOnOrganizationBtn()
	{
		organizationBtn.click();
	}

	//business lib to click on contact
		public void clickOnContactBtn()
		{
			contactsBtn.click();
		}

//business library to logout
	public void logout(WebDriver driver)
	{
		mouseOverOnElement(driver, adminLogoutImg);
		logoutBtn.click();
	}

	}
