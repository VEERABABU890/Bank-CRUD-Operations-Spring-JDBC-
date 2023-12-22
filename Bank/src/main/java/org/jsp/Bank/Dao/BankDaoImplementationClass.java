package org.jsp.Bank.Dao;

import java.util.Scanner;

import org.jsp.Bank.BankMethods.BankHelperMethods;
import org.jsp.Bank.Model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("bDimp")
public class BankDaoImplementationClass implements BankDao{

	//dependency Object
	@Autowired
	private BankHelperMethods bankHelper;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// Payment method
	public boolean payment(long senderNumber) {
		Scanner sc = new Scanner(System.in);
		if(bankHelper.checkingNumberisValidOrNot(senderNumber)!=null)
		{
			System.out.print("Enter receiver number: ");
	        long reciverNumber = sc.nextLong();
			if(bankHelper.checkingNumberisValidOrNot(reciverNumber)!=null)
			{
				System.out.print("Enter the amount to be transfred: ");
		        int amount= sc.nextInt();
		        if(amount>0)
		        {
					if(bankHelper.checkingNumberisValidOrNot(senderNumber).getBalance()<=0 || bankHelper.checkingNumberisValidOrNot(senderNumber).getBalance()<amount)
					{
						System.out.println("Insufficient Balance...");
						return false;
					}
					else
					{
						System.out.print("Enter the pin: ");
				        int pin = sc.nextInt();
						String query1 = "update bankdetails set balance = balance-? where phonenumber=?";
						String query2 = "update bankdetails set balance = balance+? where phonenumber=?";
						if(bankHelper.checkingEnteredPinIsValidOrNot(pin,senderNumber))
						{
							bankHelper.updation(query1,amount, senderNumber);
							bankHelper.updation(query2,amount, reciverNumber);
							return true;
						}
						else
						{
							System.out.println("Entered Pin is Invalid...");
						}
					}
		        }
		        else
		        {
		        	System.out.println("Entered amount should be greater than Zero..");
		        }
			}
			else
			{
				System.out.println("Reciver number is invalid...");
			}
		}
		else
		{
			System.out.println("Your number is invalid...");
		}
		return false;
	}
	
	
	public boolean insert(Bank bank) {
		String insertQuery = "insert into bankdetails(name,phonenumber,balance,pin) values(?,?,?,?)";
		if(bankHelper.checkingNumberisValidOrNot(bank.getPhoneNumber())==null)
		{
			int numberOfRows = jdbcTemplate.update(insertQuery, bank.getName(),bank.getPhoneNumber(),bank.getBalance(),bank.getPin());
			if(numberOfRows!=0)
			{
				return true;
			}
			else
			{
				System.out.println("404 error...");
			}
		}
		else
		{
			System.out.println("Entered number is already present in bank database..");
		}
		return false;
	}
	

}
