package edu.ithaca.dragon.bank;


public class savingsAccount extends BankAccount {
    double maxWithdraw;
    double withdrawCurrent;

    /**
     * @param email
     * @param startingBalance
     * @throws IllegalArgumentException if email is invalid
     */
    public savingsAccount(String email, double startingBalance, double maxWithdraw) {
        super(email, startingBalance);
        this.maxWithdraw = maxWithdraw;
        this.withdrawCurrent = 0;
    }

    /**
     * this method calculates the interest on a savings account
     * @return
     */
    public double calcInterest(){
        return 0;
    }

    /**
     * this method checks whether daily max withdraw has been met, and only withdraws if max has not been met yet
     * @param amount
     */
    public void withdraw(double amount) {

    }
}
