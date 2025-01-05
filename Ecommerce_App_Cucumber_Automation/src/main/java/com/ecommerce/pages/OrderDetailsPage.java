package com.ecommerce.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.abstractComponents.AbstractComponents;
import com.ecommerce.base.PageContext;

public class OrderDetailsPage {

	PageContext pageContext;

	public OrderDetailsPage(PageContext pageContext) {
		PageFactory.initElements(pageContext.getDriver(), this);
		this.pageContext = pageContext;
	}

	@FindBy(xpath = "//ul[@class='cartWrap ng-star-inserted']")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//button[@class='btn btn-primary' and contains(text(),'Checkout')]")
	WebElement checkoutButton;

	public List<String> getSortedProductsInCart() {
		pageContext.getAbstractComponents().waitForElementsToAppear(cartProducts);

		return (cartProducts.stream().map(cartProduct -> cartProduct.findElement(By.tagName("h3")).getText()).sorted()
				.collect(Collectors.toList()));
	}

	public CheckoutPage navigateToCheckout() {

		pageContext.getAbstractComponents().waitForElementToBeClickable(checkoutButton);
		pageContext.getAbstractComponents().scrollToTheWebElement(checkoutButton);
		pageContext.getAbstractComponents().clickElementUsingJavacript(checkoutButton);
		return new CheckoutPage(pageContext);

	}

}
