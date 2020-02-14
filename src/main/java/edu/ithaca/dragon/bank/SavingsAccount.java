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
    public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
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

    /**
     * Sets the daily maximum of the account to withdraw (ex. input 100 corresponds to 100 daily max to withdraw)
     * @throws IllegalArgumentException if the amount to withdraw is less than or equal to 0
     * @throws IllegalArgumentException if the amount to withdraw has more than 2 decimal places of precision
     * @throws InsufficientFundsException if the amount to withdraw is greater than the starting amount
     * @throws InsufficientFundsException if the amount to withdraw is greater than the current balance
     * @throws InsufficientFundsException if the amount to withdraw is greater than what is left to withdraw
     */
    public void setDailyMax(double max) throws InsufficientFundsException{

    }

    public double getDailyMax(){
        return this.dailyMax;
    }

}
