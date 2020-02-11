package edu.ithaca.dragon.bank;

public class Teller extends ATM implements AdvancedAPI {
    /**
     *
     * Creates a checking account
     * @throws IllegalArgumentException
     */
    public void createAccount(String acctId, String name, String password, double startingBalance) throws IllegalArgumentException{

    }

    /**
     *
     * Creates a savings account
     * @throws IllegalArgumentException
     */
    public void createAccount(String acctId, String name, String password, double startingBalance, double interestRate, double maxWithdraw) throws IllegalArgumentException{

    }

    public void closeAccount(String acctId) throws IllegalArgumentException, AcctFrozenException{

    }
}
