package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class CustomerCollection {
    private ArrayList<Customer> customers;

    public CustomerCollection(){
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(String idIn, String passwordIn){
        for(int x = 0; x < customers.size(); x++){
            if(customers.get(x).getId() == idIn)throw new IllegalArgumentException("Account Already Exists");
        }
        customers.add(new Customer(idIn, passwordIn));
    }

    public void deposit(String ID, double amount){}

    public void withdraw(String ID, double amount){}

    public double getBalance(String ID){
        return -1234.00;
    }

    public boolean checkCredentials(String ID, String password){
        return false;
    }

    public void createAccount(String actID, double startingBalance){}

    public void closeAccount(String actID){}

    public void closeCustomer(String actID){}

    public int getNumCustomers(){
        return customers.size();
    }

}
