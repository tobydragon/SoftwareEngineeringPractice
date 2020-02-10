package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CentralBank implements AdvancedAPI, AdminAPI {
    List<BankAccount> accountList;
    int accountCount;

    public CentralBank(){
        accountList = new ArrayList<>();

    }

    public int accountCount(){
        return accountCount;
    }

    /**
     * Searches account list for BankAccount with ID (String)
     * @return Bank Account object**/

    public BankAccount findAccountWithId(String id){
        for(int i  = 0; i<accountList.size(); i++){
            if(accountList.get(i).getAcctId().compareTo(id) == 0){
                return accountList.get(i);
            }
        }

        return null;

    }


    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return true;
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


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, double startingBalance, String emailIn) {
        accountCount +=1;
        BankAccount b = new BankAccount(emailIn, startingBalance);
        b.setAcctId(acctId);
        accountList.add(b);


    }

    public void closeAccount(String acctId) {

    }


    //------------------ AdminAPI methods -------------------------//

    public double calcTotalAssets() {
        return 0;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        Collection<String> susActCollection = new ArrayList<>();

        for(int i  = 0; i<accountList.size(); i++){
            if(accountList.get(i).getSusAct() == true){
                susActCollection.add(accountList.get(i).getAcctId()) ;
            }
        }

        if(susActCollection.size()>0){
            return susActCollection;
        }
        else{
        return null;}
    }

    public void freezeAccount(String acctId) {

    }

    public void unfreezeAcct(String acctId) {

    }



}
