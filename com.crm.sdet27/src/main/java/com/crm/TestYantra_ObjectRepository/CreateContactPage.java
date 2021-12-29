package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.TestYantra_genericutility.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility 
{
	//declarations
	@FindBy(name="lastname")
	private WebElement  contactLastNameEdt;
	
	@FindBy(xpath="(//img[@title='Select'])[1]")
	private WebElement orgNameLookUpImg;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="firstname")
	private WebElement contactFirstName;
	
	@FindBy(name="notify_owner")
	private WebElement clickOnNotify;
	
	@FindBy(name="reference")
	private WebElement clickOnReference;
	
	
	public WebElement getClickOnReference() {
		return clickOnReference;
	}

	public WebElement getContactFirstName() {
		return contactFirstName;
	}

	public WebElement getClickOnNotify() {
		return clickOnNotify;
	}
	

	//initializations
	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

//utilization
	public WebElement getContactLastNameEdt() {
		return contactLastNameEdt;
	}


	public WebElement getOrgNameLookUpImg() {
		return orgNameLookUpImg;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}

	//business library for create contact
	public void createContact(String LASTNAME)
	{
		contactLastNameEdt.sendKeys(LASTNAME);
		saveBtn.click();
	}
	//create library for create contact with organization
	public void createContactWithOrg(WebDriver driver ,String lastname,String orgname,String partialWindowTitle)
	{
		contactLastNameEdt.sendKeys(lastname);
		orgNameLookUpImg.click();
	}
	public void Savebtnn()
	{
		saveBtn.click();
	}

	public void createContactEnableNotify(WebDriver driver ,String lastname,String firstname)
	{
	
		contactLastNameEdt.sendKeys(lastname);
		contactFirstName.sendKeys(firstname);
		
		
	}

}
