package org.jsp.Bank.Config;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "org.jsp.Bank")
public class BankConfig {
	
	@Bean
	public JdbcTemplate makeConnection()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getConnection());
		return jdbcTemplate;
	}
	
	@Bean
	public DataSource getConnection()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/information?");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		return dataSource;
	}
	
	@Bean("sc")
	public Scanner getScanner()
	{
		Scanner sc = new Scanner(System.in);
		return sc;
	}
}
