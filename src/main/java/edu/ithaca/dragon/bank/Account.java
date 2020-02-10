package edu.ithaca.dragon.bank;


public abstract class Account {
    protected String ID;
    protected double balance;
    protected boolean frozen;



    public Account(String IDIn, double startingBalance){
        ID = IDIn;
        balance = startingBalance;
        frozen = false;
    }
    public double getBalance(){return balance;}
    public String getID(){return ID;}
    public boolean getFrozen(){return frozen;}
    public void toggleFrozen(){frozen = !frozen;}
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InsufficientFundsException, IllegalArgumentException;
    public abstract void transfer(Account transferTo, double amount) throws InsufficientFundsException;


}
