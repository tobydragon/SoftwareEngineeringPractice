package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount{
    protected double interestRate;
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

    /**
     * Sets the interest rate of the account (ex. input 10.5 corresponds to 10.5%)
     * @throws IllegalArgumentException if the input rate to set is less than or equal to 0
     * @throws IllegalArgumentException if the input rate to set is greater than or equal to 100
     * @throws IllegalArgumentException if the input rate to set has more than 2 decimal places precision
     */
    public void setInterestRate(double rate){

    }

    public double getInterestRate(){
        return this.interestRate;
    }
}
