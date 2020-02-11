package edu.ithaca.dragon.bank;

public class Teller implements AdvancedAPI {
    public boolean confirmCredentials(String acctId, String password) throws IllegalArgumentException, AcctFrozenException{
        return false;
    }

    public double checkBalance(String acctId) throws IllegalArgumentException, AcctFrozenException{
        return 0;
    }

    public void withdraw(String acctId, double amount) throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException {

    }

    public void deposit(String acctId, double amount) throws IllegalArgumentException, AcctFrozenException{

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException{

    }

    public String transactionHistory(String acctId) throws IllegalArgumentException, AcctFrozenException{
        return null;
    }

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
