package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.TestYantra_genericutility.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility

{
	
	@FindBy(name="accountname")
	private WebElement organizationNameEdt;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//font[text()='Banking']")
	private WebElement industryTypeText;
	
	@FindBy(xpath="//font[text()='Press']")
	private WebElement indTypeTypeText;
	
	@FindBy(className="dvHeaderText")
	private WebElement contactButtton; 
	
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getContactButtton() {
		return contactButtton;
	}

	public WebElement getOrganizationNameEdt() {
		return organizationNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getIndustryTypeText() 
	{
		return industryTypeText;
	}

	public WebElement getIndTypeTypeText() 
	{
		return indTypeTypeText;
	}


	//business lib for Create Organization
	public void createOrg(String orgName)
	{
		organizationNameEdt.sendKeys(orgName);
	saveBtn.click();
	}
	
	//business lib for Create Organization with industry type
		public void createOrgWithIndustry(String orgName,String industryType,String Type)
		{
			organizationNameEdt.sendKeys(orgName);
			select(industryDropDown,industryType);
			select(typeDropDown,Type);
		
			saveBtn.click();
		}
	public String industryTypeText()
	{
		String indTpeText =industryTypeText.getText();
		return indTpeText;
	}
	
	public String indTypeTypeText()
	{
		String indTypTypeText =indTypeTypeText.getText();
		return indTypTypeText;
	}

	
	@Override
	public void waitForPageToBeClickable(WebDriver driver, WebElement element)
	{
		super.waitForPageToBeClickable(driver, contactButtton);
	}

}
