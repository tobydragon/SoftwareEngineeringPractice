package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {


    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) {
        int num1 = 5;
        int num2 = 5;
        return num1 + num2;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    public void deposit(String acctId, double amount) throws IllegalArgumentException {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }

    //comment
    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, double startingBalance) {

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
