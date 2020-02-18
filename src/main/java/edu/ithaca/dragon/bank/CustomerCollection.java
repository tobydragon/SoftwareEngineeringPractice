package edu.ithaca.dragon.bank;

import java.util.HashMap;

public class CustomerCollection {
    private HashMap<String, Customer> customers;

    public CustomerCollection(){
        customers = new HashMap<>();
    }

    public void addCustomer(String idIn, String passwordIn){
        if(customers.get(idIn)!= null)throw new IllegalArgumentException("Customer Already Exists");
        customers.put(idIn, new Customer(idIn, passwordIn));
    }

    public void deposit(String ID, double amount){
        if(customers.get(ID)== null)throw new IllegalArgumentException("Account doesn't exist");
        customers.get(ID).deposit(amount);
    }

    public void withdraw(String ID, double amount) throws IllegalArgumentException, InsufficientFundsException {
        if(customers.get(ID)== null)throw new IllegalArgumentException("Account doesn't exist");
        customers.get(ID).withdraw(amount);
    }

    public double getBalance(String ID){
        if(customers.get(ID)== null)throw new IllegalArgumentException("Account doesn't exist");
        return customers.get(ID).getBalance();

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, IllegalArgumentException {
        if(amount < 0 || (amount * 100) % 1 != 0)
            throw new IllegalArgumentException("invalid input");
        else{
            customers.get(acctIdToWithdrawFrom).withdraw(amount);
            customers.get(acctIdToDepositTo).deposit(amount);;
        }
    }

    public boolean checkCredentials(String ID, String password){
        if(customers.get(ID) == null) throw new IllegalArgumentException(("Account does not exist"));
        return customers.get(ID).getPassword()==password;
    }

    public void createAccount(String actID, double startingBalance) throws IllegalArgumentException{
        if(customers.get(actID)== null)throw new IllegalArgumentException("Account doesn't exist");
        customers.get(actID).createAccount(startingBalance);
    }

    public void closeCustomer(String actID) throws IllegalArgumentException{
        if(customers.get(actID)== null)throw new IllegalArgumentException("Account doesn't exist");
        customers.remove(actID);
    }

    public int getNumCustomers(){
        return customers.size();
    }

    public void toggleFreeze(String actID){
        if(customers.get(actID)== null)throw new IllegalArgumentException("Account doesn't exist");
        customers.get(actID).toggleFreeze();
    }

    public boolean getFrozen(String actID){
        if(customers.get(actID)== null)throw new IllegalArgumentException("Account doesn't exist");
        return customers.get(actID).getFrozen();
    }

}