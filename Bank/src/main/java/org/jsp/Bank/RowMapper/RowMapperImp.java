package org.jsp.Bank.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jsp.Bank.Model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RowMapperImp implements RowMapper<Bank> {

	@Autowired
	private Bank bank;
	public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt(1);
		String name = rs.getString(2);
		String mobileNumber = rs.getString(3);
		long phoneNumber=Long.parseLong(mobileNumber);
		int balance = rs.getInt(4);
		int pin = rs.getInt(5);
		bank.setBankId(id);
		bank.setBalance(balance);
		bank.setName(name);
		bank.setPhoneNumber(phoneNumber);
		bank.setPin(pin);
		return bank;
	}

}
