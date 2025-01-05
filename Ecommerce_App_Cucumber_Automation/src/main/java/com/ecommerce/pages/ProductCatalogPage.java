package com.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.abstractComponents.AbstractComponents;
import com.ecommerce.base.PageContext;

public class ProductCatalogPage{

	PageContext pageContext;

	public ProductCatalogPage(PageContext pageContext) {
		PageFactory.initElements(pageContext.getDriver(), this);
		this.pageContext=pageContext;
	}


	@FindBy(css = ".container .row div.col-lg-4")
	List<WebElement> products;

	@FindBy(id = "toast-container")
	WebElement toastElement;

	@FindBy(css = ".ng-animating")
	WebElement spinnerElement;

	@FindBy(xpath = "//button[@class='btn btn-custom']/i[@class='fa fa-shopping-cart']")
	WebElement cartButton;

	public WebElement getProductWebElementByName(String name) {
		pageContext.getAbstractComponents().waitForElementsToAppear(products);
		return products.stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(name)).findFirst()
				.orElse(null);
	}

	public String addProductToCart(WebElement product) {
		pageContext.getAbstractComponents().waitForElementToDisappear(toastElement);

		pageContext.getAbstractComponents().waitForElementToAppear(
				product.findElement(By.cssSelector(".container .row div.col-lg-4 button:last-of-type")));
		pageContext.getAbstractComponents().scrollToTheWebElement(product);
		product.findElement(By.cssSelector(".container .row div.col-lg-4 button:last-of-type")).click();

		pageContext.getAbstractComponents().waitForElementToDisappear(spinnerElement);
		pageContext.getAbstractComponents().waitForElementToAppear(toastElement);
		return toastElement.getText();
	}



}
