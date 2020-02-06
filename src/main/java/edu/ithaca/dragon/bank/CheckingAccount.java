package edu.ithaca.dragon.bank;

import java.math.BigDecimal;

public class CheckingAccount extends Account {

    public CheckingAccount(String ID, double balance){
        super(ID,balance);
    }

    public void deposit(double amount){

    }
    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws IllegalArgumentException if amount is invalid
     */
    public void withdraw(double amount) throws InsufficientFundsException{
        if (isAmountValid(amount) && amount <= balance){
            balance -= amount;
        }
        else if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("invalid amount ");

        }
        else {
            throw new InsufficientFundsException("Not enough money");

        }
    }

    public static boolean isAmountValid(double amount) {


        return BigDecimal.valueOf(amount).scale() <= 2 && amount > 0;

    }
    public void transfer(Account transferTo, double amount) throws InsufficientFundsException{}
}
