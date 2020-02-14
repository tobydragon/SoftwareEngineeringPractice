package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {


    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId, String password) {
        int num1 = 5;
        int num2 = 5;
        return num1 + num2;
    }

    public void withdraw(String acctId, String password, double amount) throws InsufficientFundsException {

    }

    public void deposit(String acctId, String password, double amount) throws IllegalArgumentException {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, String passToWithdrawFrom, String passToDepositTo,double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId, String password) {
        return null;
    }

    //comment
    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, String name, String password, double startingBalance) {

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
