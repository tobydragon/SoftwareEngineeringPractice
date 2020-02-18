package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) {
        return 0;
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

    public boolean isFrozen(String actId) throws IllegalArgumentException{return false;}


    //----------------- AdvancedAPI methods -------------------------//

    public void createCustomerWithAccount(String actID, String password, double startingBalance) throws IllegalArgumentException{}

    public void createCustomer(String acctId, String password) throws IllegalArgumentException{}

    public void addAccount(String actID, double startingBalance) throws IllegalArgumentException{}

    public void closeCustomer(String acctId){}


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
