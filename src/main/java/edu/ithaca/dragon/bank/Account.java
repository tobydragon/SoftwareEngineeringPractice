package edu.ithaca.dragon.bank;


public abstract class Account {
    protected String ID;
    protected double balance;
    protected boolean frozen;



    public Account(String IDIn, double startingBalance){
        if(!isAmountValid(startingBalance))throw new IllegalArgumentException("Invalid starting amount");
        if(IDIn == "")throw new IllegalArgumentException("Invalid ID");
        ID = IDIn;
        balance = startingBalance;
        frozen = false;
    }
    public static boolean isAmountValid(double amountIn){
        if (amountIn < 0) return false;
        double scale = Math.pow(10, 9);
        amountIn = Math.round(amountIn*scale)/scale;
        if(Double.compare(amountIn, Math.round(amountIn*100)/100.0)!= 0) return false;
        else return true;
    }
    public double getBalance(){return balance;}
    public String getID(){return ID;}
    public boolean getFrozen(){return frozen;}
    public void toggleFrozen(){frozen = !frozen;}
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InsufficientFundsException, IllegalArgumentException;
    public abstract void transfer(Account transferTo, double amount) throws InsufficientFundsException;


}
