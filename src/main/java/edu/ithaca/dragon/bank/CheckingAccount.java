package edu.ithaca.dragon.bank;

public class CheckingAccount extends BankAccount{
    public CheckingAccount(String email, double startingBalance, String id){
        super(email,startingBalance, id);
        super.type="Checking";
    }
}
