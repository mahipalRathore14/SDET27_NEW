package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage//step1 create saperate clss 
{
	//step2 identify all the webelements
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createContactLookUpImg;
	
	@FindAll({@FindBy(name=""),@FindBy(xpath="")})
	private WebElement searchTestEdt;

	@FindBy(name="")
	private WebElement searchNowBtn;
	
	
	//initialize using constructor
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getCreateContactLookUpImg() {
		return createContactLookUpImg;
	}

	public WebElement getSearchTestEdt() {
		return searchTestEdt;
	}

	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	
	//Business library
	public void clickOnCreateContactImg()
	{
		createContactLookUpImg.click();
	}

}
