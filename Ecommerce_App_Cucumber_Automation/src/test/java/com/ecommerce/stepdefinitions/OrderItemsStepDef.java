package com.ecommerce.stepdefinitions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.base.PageContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class OrderItemsStepDef {

	PageContext pageContext;
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	List<String> dbitems;

	public OrderItemsStepDef(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Given("User collects DB name, table name, username and password")
	public void user_collects_db_name_table_name_username_and_password() throws IOException, SQLException {
		pageContext.getDbUtil().fetchDBDetails();

	}

	@Then("User establish connection with the database")
	public void user_establish_connection_with_the_database() throws SQLException {
		this.connection = pageContext.getDbUtil().getConnection();

	}

	@And("User fetch record from the table with id {string}")
	public void user_fetch_record_from_the_table_with_id(String id) throws SQLException {
		dbitems = new ArrayList<String>();

		List<String> itemIds = new ArrayList<>(Arrays.asList(id.split(",")));

		String sqlQuery = "select * from ItemDetails where itemId in (?,?,?)";

		if (connection != null) {
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, itemIds.get(0));
			preparedStatement.setString(2, itemIds.get(1));
			preparedStatement.setString(3, itemIds.get(2));

			if (preparedStatement != null) {
				resultSet = preparedStatement.executeQuery();
			} else {
				System.out.println("PreparedStatement object is null!!");
			}
		} else {
			System.out.println("Connection object is null!!");
		}

		while (resultSet.next()) {
			dbitems.add(resultSet.getString(2));
		}

		System.out.println("Items fetched from database: ");
		dbitems.stream().forEach(item -> System.out.println(item));
		
		resultSet.close();
		preparedStatement.close();
		connection.close();
	

	}

}
