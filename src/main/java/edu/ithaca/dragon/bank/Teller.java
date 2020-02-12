package edu.ithaca.dragon.bank;

public class Teller extends ATM {

    public Teller(UserArrayList userAccounts, CentralBank bank){
        super(userAccounts, bank); //calls ATM's constructor
    }
//TODO use central bank
    public void createUserAccount(String username, String password, String email, int userID){

    }

    public void closeUserAccount(int userId) throws IllegalArgumentException, NonExistentAccountException{

    }

    public void createBankAccount(int acctId, double startingBalance){

    }

    public void closeBankAccount(int acctId){

    }
}
