package edu.ithaca.dragon.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends BankAccount {

    private double interest;
    private double withdrawLimit = 3;
    private int daysPassed = 0;

    /**
     * @param id
     * @param startingBalance
     * @param interestIn
     * @throws IllegalArgumentException if email is invalid
     */
    public SavingsAccount(String id, double startingBalance, String password, double interestIn) {
        super(id, startingBalance, password);
        if (isAmountValid(interestIn)){
            interest = interestIn;
        }
        else{
            throw new IllegalArgumentException("Interest Amount is invalid.");
        }
    }

    public void compoundInterest(){
        double newBalance = this.getBalance() * (Math.pow(1 + ((this.interest/100)), (1 * this.daysPassed)));
        daysPassed++;
        BigDecimal bd = new BigDecimal(newBalance).setScale(2, RoundingMode.HALF_UP);
        double roundedBalance = bd.doubleValue();
        this.setBalance(roundedBalance);
    }

    public double getInterest() {
        return interest;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }
}
