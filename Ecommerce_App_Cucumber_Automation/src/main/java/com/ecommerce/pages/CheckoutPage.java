package com.ecommerce.pages;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.abstractComponents.AbstractComponents;
import com.ecommerce.base.PageContext;

public class CheckoutPage{

	PageContext pageContext;

	public CheckoutPage(PageContext pageContext) {
		PageFactory.initElements(pageContext.getDriver(), this);
		this.pageContext=pageContext;
	}

	@FindBy(xpath = "//input[@class='input txt text-validated' and @placeholder='Select Country']")
	WebElement countryInputBox;

	@FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> countryList;

	@FindBy(id = "toast-container")
	WebElement toastElement;

	@FindBy(xpath = "//span[contains(text(),'Total')]")
	WebElement totalAmount;

	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	WebElement placeOrderButton;

	@FindBy(xpath = "//td[@class='box']//h1[@class='hero-primary']/parent::td/parent::tr/parent::tbody/tr")
	List<WebElement> orderConfirmationDetails;

	@FindBy(xpath = "//tr[@class='ng-star-inserted']/td/label")
	List<WebElement> orderConfirmationIds;

	public String enterCountry(String countryName) {

		pageContext.getAbstractComponents().waitForElementToBeClickable(countryInputBox);
		countryInputBox.click();
		countryInputBox.sendKeys(countryName);

		pageContext.getAbstractComponents().waitForElementsToAppear(countryList);
		Optional<WebElement> countryElement = countryList.stream()
				.filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst();
		if (countryElement.isPresent())
			countryElement.get().click();
		else
			System.out.println(
					"The provided country: '" + countryList + "' is not available in the system to be selected!");

		pageContext.getAbstractComponents().waitForElementToBeClickable(placeOrderButton);
		placeOrderButton.click();

		pageContext.getAbstractComponents().waitForElementToAppear(toastElement);
		return toastElement.getText();

	}

	public List<String> fetchOrderDetailsFromConfirmationPage() {
		
		pageContext.getAbstractComponents().scrollWindowTo(0, 0);
		String OrderStatusMessage = orderConfirmationDetails.get(0).getText();
		
		List<String> orderDetails = orderConfirmationIds.stream().map(id -> id.getText()).collect(Collectors.toList());
		orderDetails.add(0,OrderStatusMessage);
		return orderDetails;

	}

}
