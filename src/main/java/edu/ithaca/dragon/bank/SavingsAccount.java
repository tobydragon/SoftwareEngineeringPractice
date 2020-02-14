package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount{
    protected double interestRate;
    protected double dailyMax;
    protected double withdrawnToday;


    public SavingsAccount(String email, double startingBalance, String id){
        super(email, startingBalance, id);
        super.type="Savings";
    }

    public void applyCompoundInterest(){
        double nextBalance = super.balance * (1 + (this.interestRate / 100));
        this.balance = nextBalance;
    }

    @Override
    public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
        if(!isAmountValid(amount) || amount == 0){
            throw new IllegalArgumentException("Invalid amount");
        }
        double nextWithdrawRemain = (this.dailyMax - (this.withdrawnToday + amount));
        if(amount > this.balance || nextWithdrawRemain < 0){
            throw new InsufficientFundsException("Insufficient amount to withdraw");
        }
        this.withdrawnToday = this.withdrawnToday + amount;
        this.balance -= amount;
    }


    /**
     * Transfers money between accounts
     * @throws IllegalArgumentException if the amount to transfer is less than or equal to 0
     * @throws IllegalArgumentException if the account to transfer to is the same as the account being transferred from
     * @throws InsufficientFundsException if the amount to transfer is greater than or equal to the remaining withdrawal limit
     */
    @Override
    public void transfer(BankAccount account, double amount) throws InsufficientFundsException {
        if(!isAmountValid(amount) || amount == 0){
            throw new IllegalArgumentException("Invalid amount");
        }
        if(account == this){
            throw new IllegalArgumentException("Same account being transferred to");
        }
        if(amount > (this.dailyMax - this.withdrawnToday)){
            throw new InsufficientFundsException("Insufficient funds to withdraw");
        }
        this.withdrawnToday += amount;
        this.balance -= amount;
        account.deposit(amount);
    }

    /**
     * Sets the interest rate of the account (ex. input 10.5 corresponds to 10.5%)
     * @throws IllegalArgumentException if the input rate to set is less than or equal to 0
     * @throws IllegalArgumentException if the input rate to set is greater than or equal to 100
     * @throws IllegalArgumentException if the input rate to set has more than 2 decimal places precision
     */
    public void setInterestRate(double rate){
        if(!isAmountValid(rate)){
            throw new IllegalArgumentException("Invalid rate");
        }
        if(rate <= 0 || rate > 99.99){
            throw new IllegalArgumentException("Invalid rate");
        }
        this.interestRate = rate;
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
     */
    public void setDailyMax(double max) throws InsufficientFundsException{
        if(!isAmountValid(max)){
            throw new IllegalArgumentException("Invalid max");
        }
        if(max > this.balance){
            throw new InsufficientFundsException("Not enough funds left to withdraw");
        }
        this.dailyMax = max;
    }

    public double getDailyMax(){
        return this.dailyMax;
    }

    public double getWithdrawnToday(){
        return this.withdrawnToday;
    }

}
