package edu.ithaca.dragon.bank;

import java.util.Collection;

<<<<<<< HEAD
public class CentralBank implements BasicAPI, AdvancedAPI, AdminAPI {

    String acctId;
    double balance;

    public CentralBank(String accountId, double balance){
        this.acctId = accountId;
        this.balance = balance;
    }

    private String acctID;
    private double amount;

    public CentralBank(String acctID, double amount) {
        this.acctID = acctID;
        this.amount = amount;
    }
||||||| merged common ancestors
public class CentralBank implements AdvancedAPI, AdminAPI {
=======
public class CentralBank implements BasicAPI, AdvancedAPI, AdminAPI {

    String acctId;
    double balance;

    public CentralBank(String accountId, double balance){
        this.acctId = accountId;
        this.balance = balance;
    }
>>>>>>> d7f2e37a069825de396abfc726be296abe5170d1

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) {
<<<<<<< HEAD
<<<<<<< HEAD
        return this.amount;
=======
        return this.balance;
>>>>>>> d7f2e37a069825de396abfc726be296abe5170d1
||||||| merged common ancestors
        return 0;
=======
        return this.balance;
>>>>>>> d7f2e37a069825de396abfc726be296abe5170d1
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
