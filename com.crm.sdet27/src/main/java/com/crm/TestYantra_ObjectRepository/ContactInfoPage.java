package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage 
{
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactHeaderText;
	
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getcontactHeaderText() 
	{
		return contactHeaderText;
	}
	
	public String getContactInfo()
	{
		return(contactHeaderText).getText();
	}

	
	
}
