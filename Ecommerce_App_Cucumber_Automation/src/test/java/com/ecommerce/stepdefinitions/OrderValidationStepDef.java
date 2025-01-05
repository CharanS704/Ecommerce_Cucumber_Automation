package com.ecommerce.stepdefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ecommerce.base.PageContext;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.OrderDetailsPage;
import com.ecommerce.pages.ProductCatalogPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class OrderValidationStepDef {

	PageContext pageContext;
	ArrayList<String> expectedProductsList;

	public OrderValidationStepDef(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Given("User is on ecommerce login page and login with correct username and password")
	public void user_is_on_ecommerce_landing_page_and_login_with_username_and_password(DataTable dt) {
		String userName = dt.asMap().get("username");
		System.out.println("entered username: " + userName);
		String password = dt.asMap().get("password");
		System.out.println("entered password: " + password);

		String expectedMessage = "Login Successfully";

		pageContext.setProductCatalogPage(pageContext.getEcomLandingPage().loginToApp(userName, password));

		Assert.assertEquals(pageContext.getEcomLandingPage().fetchMessage(), expectedMessage,
				"Expected message is incorrect after entering correct username and password!");
		System.out.println("Login successfull");

	}

	@When("User selects multiple orders {string} from product catalog page and navigate to orders page")
	public void user_selects_multiple_orders_from_product_catalog_page_and_navigate_to_orders_page(String orders) {
		expectedProductsList = new ArrayList<>(Arrays.asList(orders.split(",")));

		for (String product : expectedProductsList) {
			WebElement productElement = pageContext.getProductCatalogPage().getProductWebElementByName(product);
			Assert.assertTrue(product.equals(productElement.findElement(By.tagName("b")).getText()));

			String message = pageContext.getProductCatalogPage().addProductToCart(productElement);
			Assert.assertEquals(message, "Product Added To Cart", "Message is not as expected!!");
		}

	}

	@When("User validate actual selected orders from orders page against the list of expected orders")
	public void user_validate_actual_selected_orders_from_orders_page_against_the_list_of_expected_orders() {

		pageContext.setOrderDetailsPage(pageContext.getAbstractComponents().navigateToCartDetails());

		List<String> actualSortedProductsList = pageContext.getOrderDetailsPage().getSortedProductsInCart();
		Assert.assertTrue(
				(expectedProductsList.stream().sorted().collect(Collectors.toList())).equals(actualSortedProductsList),
				"Products added in the cart are not as expected!!");
	}

	@Then("User navigate to checkout page")
	public void user_navigate_to_checkout_page() {
		pageContext.setCheckoutPage(pageContext.getOrderDetailsPage().navigateToCheckout());
		String message = pageContext.getCheckoutPage().enterCountry("india");
		Assert.assertTrue("Order Placed Successfully".equals(message), "Checkout message is not as expected!");

		Assert.assertTrue("THANKYOU FOR THE ORDER.".equals(
				pageContext.getCheckoutPage().fetchOrderDetailsFromConfirmationPage().stream().findFirst().get()));

	}

	@Then("User verify the order status and fetch order Ids")
	public void user_verify_the_order_status_and_fetch_order_ids() {
		System.out.println("Order details: ");
		pageContext.getCheckoutPage().fetchOrderDetailsFromConfirmationPage()
				.forEach(details -> System.out.println(details));
	}

}
