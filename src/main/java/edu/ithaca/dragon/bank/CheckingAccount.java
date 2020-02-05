package edu.ithaca.dragon.bank;

public class CheckingAccount extends BankAccount{
    String type;
    public CheckingAccount(String email, double startingBalance){
        super(email,startingBalance);
        this.type="Checking";
    }
}
