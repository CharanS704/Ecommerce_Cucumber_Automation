package com.ecommerce.hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.ecommerce.Utilities.DBUtil;
import com.ecommerce.abstractComponents.AbstractComponents;
import com.ecommerce.base.PageContext;
import com.ecommerce.commons.GenerateExtentReport;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.EcomLandingPage;
import com.ecommerce.pages.OrderDetailsPage;
import com.ecommerce.pages.ProductCatalogPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class EcommerceHooks {

	PageContext pageContext;

	public EcommerceHooks(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void initializeBrowser() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//GlobalProperties.properties");

		prop.load(fis);

		switch (prop.getProperty("browserName")) {
		case ("Chrome"):
			pageContext.setDriver(new ChromeDriver());
			break;
		case ("Firefox"):
			pageContext.setDriver(new FirefoxDriver());
			break;
		case ("Edge"):
			pageContext.setDriver(new EdgeDriver());
			break;
		}
	}

	@Before("@WebTest")
	public void launchWebApplication() throws IOException {
		initializeBrowser();
		pageContext.getDriver().get("https://rahulshettyacademy.com/client/");
		pageContext.getDriver().manage().window().maximize();
		pageContext.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		pageContext.setEcomLandingPage(new EcomLandingPage(pageContext));
		pageContext.setOrderDetailsPage(new OrderDetailsPage(pageContext));
		pageContext.setProductCatalogPage(new ProductCatalogPage(pageContext));
		pageContext.setCheckoutPage(new CheckoutPage(pageContext));
		pageContext.setAbstractComponents(new AbstractComponents(pageContext));
		pageContext.setWait(new WebDriverWait(pageContext.getDriver(), Duration.ofSeconds(5)));
		pageContext.setJavaScriptExecutor((JavascriptExecutor) pageContext.getDriver());
		pageContext.setDbUtil(new DBUtil(pageContext));
	}
	
	@Before("@DBValidation")
	public void launchApplication() throws IOException {
		pageContext.setDbUtil(new DBUtil(pageContext));
	}
	
	@AfterStep("@WebTest")
	public void  captureAfterEachStep(Scenario scenario) {
		TakesScreenshot screenshot = (TakesScreenshot)pageContext.getDriver();
		byte[] screenshotAs = screenshot.getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshotAs, "image/png", "Captured screen");
	}
	
	@After("@WebTest")	
	public void tearDown() {
		pageContext.getDriver().quit();
	}

}
