package edu.ithaca.dragon.bank;

public class CheckingAccount extends Account {
    public CheckingAccount(String email, double startingBalance){
        super(email, startingBalance);
    }
    public void deposit(double amount){}
    public void withdraw(double amount) throws InsufficientFundsException{
        if((amount * 100) % 1 != 0)
            throw new IllegalArgumentException("invalid input");

        else if (balance >= amount && amount > 0)
            balance -= amount;

        else if(balance < amount)
            throw new InsufficientFundsException("Amount requested is more than in your account by " + (amount - balance));

        else if(amount<=0)
            throw new IllegalArgumentException("Enter some amount to withdraw");
    }


    public void transfer(Account transferTo, double amount) throws InsufficientFundsException{}
}
