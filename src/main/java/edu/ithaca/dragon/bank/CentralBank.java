package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CentralBank implements AdvancedAPI, AdminAPI {

    //----------------- BasicAPI methods -------------------------//


    public Map<String, BankAccount>accountMap = new HashMap<>();





    public boolean confirmCredentials(String acctId, String password){

        if(accountMap.containsKey(acctId)){
            return accountMap.get(acctId).getPassword().equals(password);
        }



        return false;
    }


    public double checkBalance(String acctId) throws IllegalArgumentException {
        if(!accountMap.containsKey(acctId)){
            throw new IllegalArgumentException("Account does not exist with name: " + acctId);
        }
        return accountMap.get(acctId).getBalance();
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException, IllegalArgumentException {
        if(!accountMap.containsKey(acctId)){
            throw new IllegalArgumentException("Account does not exist with name: " + acctId);
        }

        accountMap.get(acctId).withdraw(amount);
    }


    public void deposit(String acctId, double amount) {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }


    //----------------- AdvancedAPI methods -------------------------//


    public void createAccount(String acctId, String email, String password, double startingBalance) {
        BankAccount account = new BankAccount(acctId, email, password, startingBalance);
        accountMap.put(acctId, account);

    }




    public void closeAccount(String acctId) {
        accountMap.remove(acctId);





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
