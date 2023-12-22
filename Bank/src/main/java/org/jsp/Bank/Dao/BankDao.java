package org.jsp.Bank.Dao;

import org.jsp.Bank.Model.Bank;

public interface BankDao {
	public boolean payment(long senderNumber);
	public boolean insert(Bank bank);
}
