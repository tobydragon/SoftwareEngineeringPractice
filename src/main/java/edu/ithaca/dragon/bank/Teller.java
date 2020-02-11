package edu.ithaca.dragon.bank;

public class Teller extends ATM {

    public Teller(UserArrayList userAccounts){
        super(userAccounts); //calls ATM's constructor
    }

    public void createUserAccount(String username, String password, String email, int userID){

    }

    public void closeUserAccount(int userId) throws IllegalArgumentException{

    }

    public void createBankAccount(String acctId, double startingBalance){

    }

    public void closeBankAccount(String acctId){

    }
}
