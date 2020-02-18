package edu.ithaca.dragon.bank;


public class savingsAccount extends BankAccount {
    double maxWithdraw;
    double withdrawCurrent;
    double interestRate;

    /**
     * @param email
     * @param startingBalance
     * @throws IllegalArgumentException if email is invalid
     */
    public savingsAccount(String email, String acctId, String acctPass, double startingBalance, double maxWithdraw, double interestRate) {
        super(email, acctId, acctPass, startingBalance);
        this.maxWithdraw = maxWithdraw;
        this.withdrawCurrent = 0;
        this.interestRate = interestRate;
    }

    /**
     * this method calculates the interest on a savings account
     * @return
     */
    public double calcInterest()throws IllegalAccessException {
        bankAccountFrozenCheck();
        if (interestRate >= 0) {
            balance += balance * interestRate;
            return this.balance;
        }else{
            throw new IllegalArgumentException("interest rate: " + interestRate + " is not a valid interest rate.");
        }
    }

    /**
     * this method checks whether daily max withdraw has been met, and only withdraws if max has not been met yet
     * @param amount
     */
    public void withdraw(double amount) throws IllegalAccessException {
        bankAccountFrozenCheck();
        if (amount < .01){
            throw new IllegalArgumentException("withdraw amount: " + amount + " is invalid, amount cannot be withdrawn");
        }
        if (withdrawCurrent < maxWithdraw && (withdrawCurrent+ amount) <= maxWithdraw){
            super.withdraw(amount);
        }else{
            throw new IllegalArgumentException("withdraw amount: " + amount + " is invalid because it exceeds withdraw limit.");
        }
    }
}
