package edu.ithaca.dragon.bank;

public class checkingAccount extends BankAccount {
    /**
     * @param email
     * @param startingBalance
     * @throws IllegalArgumentException if email is invalid
     */
    public checkingAccount(String email, String acctId, String acctPass, double startingBalance) {
        super(email, acctId, acctPass, startingBalance);
    }
}
