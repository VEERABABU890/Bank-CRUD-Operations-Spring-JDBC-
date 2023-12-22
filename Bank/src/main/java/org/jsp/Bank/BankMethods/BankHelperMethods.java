
package org.jsp.Bank.BankMethods;

import java.util.Scanner;

import org.jsp.Bank.Dao.BankDao;
import org.jsp.Bank.Model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("bankHelper")
public class BankHelperMethods {
	
	// dependency Object
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RowMapper<Bank> row;
		
	private static Scanner sc = new Scanner(System.in);
	
	// Getting user information method
	public static void userDetails(Bank bank)
	{
		System.out.println("Enter the details");
		System.out.print("Enter your name: ");
		String name = sc.next();
		System.out.print("Enter your phoneNumber: ");
		long phoneNumber = sc.nextLong();
		System.out.print("Enter the balance: ");
		int balance = sc.nextInt();
		System.out.print("Enter your pin: ");
		int pin = sc.nextInt();
		bank.setBalance(balance);
		bank.setName(name);
		bank.setPhoneNumber(phoneNumber);
		bank.setPin(pin);
	}
	
	// Choice Method...
	public void choiceMethod(int choice,Bank bank,BankDao bDao)
	{
		switch(choice)
	    {
	    	case 1:
	    		while(true)
	    		{
	        		userDetails(bank);
	        		if(bDao.insert(bank))
	        		{
	        			System.out.println("Inserted..");
	        			break;
	        		}
	        		else
	        		{
	        			System.out.println("---------------------------------------");
	        			System.out.println("Details are already existed...");
	        			System.out.println("Do you want to try agian... Do yes or no");
	        			String confirmation = sc.next();
	        			if(confirmation.equalsIgnoreCase("NO"))
	        			{
	        				System.out.println("---Thank You---");
	        				break;
	        			}
	        			else
	        			{
	        				System.out.println("Re-Enter your details");
	        			}
	        			
	        		}
	    		}
	    		break;
	    	case 2:	
		        System.out.print("Enter your number: ");
		        long senderNumber = sc.nextLong();
		        while(true)
		        {
			        if( bDao.payment(senderNumber))
			        {
			        	System.out.println("Payment successful...");
			        	System.out.println("Thank you...");
			        	break;
			        }
			        else
			        {
			        	System.out.println("Payment Unsuccessful...");
			        	System.out.println("---------------------------------------------");
			        	System.out.println("Do you want to try again... do Yes or No");
			        	String confirmation = sc.next();
			        	if(confirmation.equalsIgnoreCase("YES"))
			        	{
			        		System.out.print("Re-Enter Your number: ");
			        		senderNumber = sc.nextLong();
			        	}
			        	else
			        	{
			        		System.out.println("Thank you...");
			        		break;
			        	}
			        	
			        }
		        }
		        break;
		    default:System.out.println("Please enter valid choice...");
	    }
	}
	
	// Pin is valid or not method
	public boolean checkingEnteredPinIsValidOrNot(int pin,long number)
	{
		String phoneNumber = ""+number;
		String query = "select * from bankdetails where phonenumber=?";
		try
		{
			Bank bank = jdbcTemplate.queryForObject(query, row,phoneNumber);
			if(bank.getPin()==pin)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
		
	// Query Updation method (Creadting and deducting money)		
	public boolean updation(String query,int amount,long number)
	{
		int num = jdbcTemplate.update(query, amount,number);
		if(num!=0)
		{
			return true;
		}
		return false;
	}
		
	// checking the number is valid or not method
	public Bank checkingNumberisValidOrNot(long number) {
		String phoneNumber = ""+number;
		String query = "select * from bankdetails where phonenumber= ?";
		try
		{
			Bank bank = jdbcTemplate.queryForObject(query, row,phoneNumber);
			return bank;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
