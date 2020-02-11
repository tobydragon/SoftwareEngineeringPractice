package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {

    private UserArrayList userAccounts;
    private BankAccountCollection bankAccountCollection;

    public CentralBank(){
        userAccounts = new UserArrayList();
    }

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(int acctId, String password) {
        return false;
    }

    public double checkBalance(int acctId) {
        return 0;
    }

    public void withdraw(int acctId, double amount) throws InsufficientFundsException {
        //userAccounts.findAccount(acctId).
    }

    public void deposit(int acctId, double amount) {

    }

    public void transfer(int acctIdToWithdrawFrom, int acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(int acctId) {
        return null;
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(int acctId, double startingBalance) {

    }

    public void closeAccount(int acctId) {

    }


    //------------------ AdminAPI methods -------------------------//

    public double calcTotalAssets() {
        return 0;
    }

    public Collection<Integer> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    public void freezeAccount(int acctId) {

    }

    public void unfreezeAcct(int acctId) {

    }

}
