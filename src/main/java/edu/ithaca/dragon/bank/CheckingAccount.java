package edu.ithaca.dragon.bank;

public class CheckingAccount extends BankAccount {
    /**
     * @param email
     * @param startingBalance
     * @throws IllegalArgumentException if email is invalid
     */
    public CheckingAccount(String email, double startingBalance) {
        super(email, startingBalance);
    }
}
