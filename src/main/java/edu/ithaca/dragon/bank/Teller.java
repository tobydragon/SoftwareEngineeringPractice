package edu.ithaca.dragon.bank;

public class Teller extends ATM {

    public Teller(CentralBank bank){

        super(bank); //calls ATM's constructor
    }

    public void createUserAccount(String username, String password, String email, int userID){
        bank.createUser(username, password, email, userID);
    }

    public void closeUserAccount(int userId) throws IllegalArgumentException, NonExistentAccountException{
        bank.closeUserAccount(userId);
    }

    public void createBankAccount(int acctId, double startingBalance) throws NonExistentAccountException{
        bank.createBankAccount(acctId, startingBalance);
    }

    public void closeBankAccount(int userId, int acctId) throws NonExistentAccountException{
        //TODO not implemented yet, closing user should also close its bank accounts (and test it)
        bank.closeBankAccount(userId, acctId);
    }
}
