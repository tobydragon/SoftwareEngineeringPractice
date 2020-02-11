package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CentralBank implements AdvancedAPI, AdminAPI {

    protected Map<String, BankAccount> acctMap;

    CentralBank(){
        acctMap = new HashMap<String, BankAccount>();
    }

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) throws AccountNotFoundException {
        return getAcctByID(acctId).getBalance();
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException, AccountNotFoundException{
        getAcctByID(acctId).withdraw(amount);
    }

    public void deposit(String acctId, double amount) throws AccountNotFoundException{
        getAcctByID(acctId).deposit(amount);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, AccountNotFoundException {
        getAcctByID(acctIdToWithdrawFrom).transfer(amount, getAcctByID(acctIdToDepositTo));
    }

    public String transactionHistory(String acctId) {
        return null;
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, double startingBalance) throws AccountAlreadyExistsException{
        BankAccount ba = new BankAccount("a@b.com", startingBalance);
        addAccount(ba, acctId);
    }

    public void closeAccount(String acctId) throws AccountNotFoundException{
        getAcctByID(acctId);
        acctMap.put(acctId, null);
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


    //------------------ Other useful methods -------------------------//

    protected void addAccount(BankAccount ba, String id) throws AccountAlreadyExistsException{
        if(acctMap.get(id) != null){
            throw new AccountAlreadyExistsException("Account with ID " + id + " already exists!");
        }
        else acctMap.put(id, ba);
    }

    protected BankAccount getAcctByID(String id) throws AccountNotFoundException{
        BankAccount ba = acctMap.get(id);
        if(ba != null){
            return ba;
        }
        else throw new AccountNotFoundException("Account with ID " + id + " not found!");
    }

}
