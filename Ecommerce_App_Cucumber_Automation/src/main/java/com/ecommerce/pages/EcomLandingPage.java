package com.ecommerce.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.ecommerce.abstractComponents.AbstractComponents;
import com.ecommerce.base.PageContext;
import com.ecommerce.commons.GenerateExtentReport;

public class EcomLandingPage {

	PageContext pageContext;

	public EcomLandingPage(PageContext pageContext) {
		PageFactory.initElements(pageContext.getDriver(), this);
		this.pageContext=pageContext;
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(id = "toast-container")
	WebElement toastElement;

	public ProductCatalogPage loginToApp(String userEmailId, String userActualPassword) {

		pageContext.getAbstractComponents().waitForElementToAppear(userEmail);
		userEmail.sendKeys(userEmailId);

		pageContext.getAbstractComponents().waitForElementToAppear(password);
		password.sendKeys(userActualPassword);

		pageContext.getAbstractComponents().waitForElementToBeClickable(submit);
		submit.click();

		return new ProductCatalogPage(pageContext);

	}
	
	
	public String fetchMessage() {
		
		pageContext.getAbstractComponents().waitForElementToAppear(toastElement);
		return toastElement.getText();
	}

}
