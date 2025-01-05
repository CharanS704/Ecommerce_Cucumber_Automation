package com.ecommerce.stepdefinitions;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ecommerce.base.PageContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ErrorMessageStepDef{

	PageContext pageContext;

	public ErrorMessageStepDef(PageContext pageContext) {
		this.pageContext=pageContext;
	}
	
	@Then("User validate error message on login page")
	public void user_validate_error_message_on_login_page() {
		String expectedErrorMessage = "Incorrect email or password.";

		String actualErrorMMessage = pageContext.getEcomLandingPage().fetchMessage();
		Assert.assertTrue(expectedErrorMessage.equals(actualErrorMMessage), "Error message is displayed incorrect!!");
	}

	@Given("User is on ecommerce login page and login with incorrect username and password")
	public void user_is_on_ecommerce_landing_page_and_login_with_username_and_password(DataTable dt) {
		String userName = dt.asMap().get("username");
		System.out.println("entered username: " + userName);
		String password = dt.asMap().get("password");
		System.out.println("entered password: " + password);

		String expectedMessage = "Incorrect email or password.";

		pageContext.getEcomLandingPage().loginToApp(userName, password);

		Assert.assertEquals(pageContext.getEcomLandingPage().fetchMessage(), expectedMessage,
				"Expected message is incorrect after entering incorrect username and password!");
		
	}

}
