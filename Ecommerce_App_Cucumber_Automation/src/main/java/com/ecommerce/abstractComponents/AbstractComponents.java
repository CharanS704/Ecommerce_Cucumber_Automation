package com.ecommerce.abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.base.PageContext;
import com.ecommerce.pages.OrderDetailsPage;

public class AbstractComponents {
	
	PageContext pageContext;
	
	@FindBy(xpath = "//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")
	WebElement cartIcon;

	public AbstractComponents(PageContext pageContext) {
		PageFactory.initElements(pageContext.getDriver(), this);
		this.pageContext = pageContext;
	}

	public void waitForElementToAppear(WebElement webElement) {
		pageContext.getWait().until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForElementToBeClickable(WebElement webElement) {
		pageContext.getWait().until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitForElementToDisappear(WebElement webElement) {
		pageContext.getWait().until(ExpectedConditions.invisibilityOf(webElement));
	}

	public void waitForElementsToAppear(List<WebElement> webElements) {
		pageContext.getWait().until(ExpectedConditions.visibilityOfAllElements(webElements));
	}

	public void scrollToTheWebElement(WebElement element) {
		pageContext.getJavaScriptExecutor().executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void scrollWindowTo(int x, int y) {
		pageContext.getJavaScriptExecutor().executeScript("window.scrollTo(" + x + "," + y + ");");
	}

	public void clickElementUsingJavacript(WebElement element) {
		pageContext.getJavaScriptExecutor().executeScript("arguments[0].click();", element);
	}

	public OrderDetailsPage navigateToCartDetails() {
		waitForElementToAppear(cartIcon);
		scrollWindowTo(0, 0);
		waitForElementToBeClickable(cartIcon);

		cartIcon.click();
		return new OrderDetailsPage(pageContext);
	}

}
