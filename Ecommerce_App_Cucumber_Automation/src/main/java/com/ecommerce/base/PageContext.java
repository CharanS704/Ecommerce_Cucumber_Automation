package com.ecommerce.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.Utilities.DBUtil;
import com.ecommerce.abstractComponents.AbstractComponents;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.EcomLandingPage;
import com.ecommerce.pages.OrderDetailsPage;
import com.ecommerce.pages.ProductCatalogPage;

import lombok.Data;

@Data
public class PageContext {

	private WebDriver driver;
	private EcomLandingPage ecomLandingPage;
	private ProductCatalogPage productCatalogPage;
	private OrderDetailsPage orderDetailsPage;
	private CheckoutPage checkoutPage;
	private AbstractComponents abstractComponents;
	private WebDriverWait wait;
	private JavascriptExecutor javaScriptExecutor;
	private DBUtil dbUtil;

}
