package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount{
    protected double interest;
    protected double dailyMax;
    protected double withdrawnToday;


    public SavingsAccount(String email, double startingBalance, String id){
        super(email, startingBalance, id);
        super.type="Savings";
    }
    public void compoundInterest(){
        //TODO
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        //TODO
    }


    @Override
    public void transfer(BankAccount account, double amount) throws InsufficientFundsException {
        //TODO
    }
}
