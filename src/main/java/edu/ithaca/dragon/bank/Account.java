package edu.ithaca.dragon.bank;

public abstract class Account {
    private String ID;
    private double balance;
    private boolean frozen;

    public double getBalance(){return balance;}
    public String getID(){return ID;}
    public boolean getFrozen(){return frozen;}
    public void toggleFrozen(){frozen = !frozen;}
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InsufficientFundsException;
    public abstract void transfer(Account transferTo, double amount) throws InsufficientFundsException;


}
