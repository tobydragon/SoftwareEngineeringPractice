package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class CentralBank implements AdvancedAPI, AdminAPI {

    private Map<String, BankAccount> accounts = new HashMap<>();

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) {
        BankAccount account = accounts.get(acctId);
        return account.getBalance();
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    public void deposit(String acctId, double amount) {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, double startingBalance) throws AccountIdTakenException, IllegalArgumentException {
        if (accounts.containsKey(acctId)) throw new AccountIdTakenException("Account with this id already exists");

        BankAccount account = new BankAccount(acctId, startingBalance);
        accounts.put(acctId, account);
    }

    //for testing createAccount function - one must exist to test the other
    public boolean accountExists(String acctId) {
        return accounts.containsKey(acctId);
    }

    public void closeAccount(String acctId) {

    }


    //------------------ AdminAPI methods -------------------------//

    public double calcTotalAssets() {
        return 0;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    public void freezeAccount(String acctId) {

    }

    public void unfreezeAcct(String acctId) {

    }

}
