package com.ecommerce.Utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import com.ecommerce.base.PageContext;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {

	PageContext pageContext;
	HikariDataSource hikariDataSource;

	public DBUtil(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void fetchDBDetails() throws IOException, SQLException {
		HikariConfig hikariConfig = new HikariConfig(
				System.getProperty("user.dir") + "//src//test//resources//DBProperties.properties");

		hikariDataSource = new HikariDataSource(hikariConfig);
	}

	public Connection getConnection() throws SQLException {

		return hikariDataSource.getConnection();

	}


}
