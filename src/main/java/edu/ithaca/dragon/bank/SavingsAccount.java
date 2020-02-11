package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {

    private double interest;
    private double withdrawLimit = 3;

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
        //TODO
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
