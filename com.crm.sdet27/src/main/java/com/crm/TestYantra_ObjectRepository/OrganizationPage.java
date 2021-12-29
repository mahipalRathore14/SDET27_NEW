package com.crm.TestYantra_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage 
{
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createOrganizationImg;

	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateOrganizationImg() {
		return createOrganizationImg;
	}
	
	public void clickOnCreateOrg()
	{
		createOrganizationImg.click();
	}
}
