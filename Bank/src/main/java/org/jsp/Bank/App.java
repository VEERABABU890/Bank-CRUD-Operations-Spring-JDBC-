package org.jsp.Bank;

import java.util.Scanner;

import org.jsp.Bank.BankMethods.BankHelperMethods;
import org.jsp.Bank.Config.BankConfig;
import org.jsp.Bank.Dao.BankDao;
import org.jsp.Bank.Dao.BankDaoImplementationClass;
import org.jsp.Bank.Model.Bank;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	
        ApplicationContext context = new AnnotationConfigApplicationContext(BankConfig.class);
        Bank bank = context.getBean("bank",Bank.class);
        BankDao bDao = context.getBean("bDimp",BankDaoImplementationClass.class);
        Scanner sc = context.getBean("sc",Scanner.class);
        BankHelperMethods bankHelper = context.getBean("bankHelper",BankHelperMethods.class);
        System.out.println("...............Welcome to Bank...............");
        System.out.println("_____________________________________________");
        System.out.println("Enter 1 for inserting the details into bank database");
        System.out.println("Enter 2 for transfering the amount");
        System.out.print("Enter your choice: ");
        int choice =sc.nextInt();
        bankHelper.choiceMethod(choice, bank, bDao);
    }
}
