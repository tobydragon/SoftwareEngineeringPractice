package edu.ithaca.dragon.bank;

public class CheckingAccount extends Account {
    public CheckingAccount(String email, double startingBalance){
        super(email, startingBalance);
    }
    public void deposit(double amount){}
    public void withdraw(double amount) throws InsufficientFundsException{ }
    public void transfer(Account transferTo, double amount) throws InsufficientFundsException{}
}
