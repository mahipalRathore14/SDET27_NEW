package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage 
{
	@FindBy(xpath="(//span[@class='dvHeaderText'])")
	private WebElement orgHeaderText;
	
	@FindBy(xpath="//font[text()='Banking']")
	private WebElement industryTypeText;
	
	@FindBy(xpath="//font[text()='Press']")
	private WebElement indTypeTypeText;
	
	
	public OrganizationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getIndustryTypeText() 
	{
		return industryTypeText;
	}

	public WebElement getIndTypeTypeText() 
	{
		return indTypeTypeText;
	}



	public WebElement getorgHeaderText() 
	{
		return orgHeaderText;
	}
	public String OrgInfo()
	{
		return(orgHeaderText).getText();
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
}
