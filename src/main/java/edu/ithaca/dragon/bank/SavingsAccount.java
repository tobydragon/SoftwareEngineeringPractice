package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {
    private double interest;
    private double withdrawLimit;
    /**

     * @throws IllegalArgumentException if email is invalid
     */
    public SavingsAccount(String email, double startingBalance, double interest, double withdrawLimit) throws IllegalArgumentException {
        super(email, startingBalance);
        if(interest < 0 || withdrawLimit <= 0){
            throw new IllegalArgumentException("Interest must be at least 0% and you must be able to withdraw from the account");
        }
        this.interest = interest / 100;
        this.withdrawLimit = withdrawLimit;
    }

    /**
     * Compounds interest of the day and adds it to balance
     */
    public void compoundInterest(){
        double compounded = interest *  this.getBalance();
        this.deposit(compounded);
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * throws InsufficientFundsException if the amount is larger than the balance
     * If balance is negative or has more than 2 decimal places, throws IllegalArgumentException
     * If amount to withdraw is more than withdraw limit, it throws an InsufficientFundsException
     */
    @Override
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException  {

        if(withdrawLimit - amount < 0)
        {
            throw new IllegalArgumentException("Cannot withdraw more than the daily limit");
        }

            super.withdraw(amount);
            withdrawLimit -= amount;
    }
}
