package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CentralBank implements AdvancedAPI, AdminAPI, BasicAPI {
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
        throw new NullPointerException("Null Pointer - ID not found");
        //return null;

    }


    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) throws IllegalArgumentException {
        if(findAccountWithId(acctId)==null){
            throw new IllegalArgumentException("confirmCredentials: Can not find ID");
        }else{
            if(findAccountWithId(acctId)!= null && findAccountWithId(acctId).getAcctPass()==password){
                return true;
            }else{
                return false;
            }
        }

    }

    public double checkBalance(String acctId) throws IllegalArgumentException {
        if(findAccountWithId(acctId)==null){
            throw new IllegalArgumentException("checkBalance: Can not find ID");
        }else{
            return findAccountWithId(acctId).getBalance();
        }
    }

    public void withdraw(String acctId, double amount) throws IllegalArgumentException, IllegalAccessException {
        if(findAccountWithId(acctId)==null){
            throw new IllegalArgumentException("withdraw: Can not find ID");
        }else{
            findAccountWithId(acctId).withdraw(amount);
        }
    }

    public void deposit(String acctId, double amount) throws IllegalAccessException {
        if(findAccountWithId(acctId)==null){
            throw new IllegalArgumentException("deposit: Can not find ID");
        }else{
            findAccountWithId(acctId).deposit(amount);
        }
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws  IllegalArgumentException,IllegalAccessException {
        if(findAccountWithId(acctIdToWithdrawFrom)==null||findAccountWithId(acctIdToDepositTo)==null){
            throw new IllegalArgumentException("transfer: Can not find ID");
        }else{
            findAccountWithId(acctIdToWithdrawFrom).transfer(amount,findAccountWithId(acctIdToDepositTo));
        }
    }

    public String transactionHistory(String acctId)throws IllegalArgumentException {
        if(findAccountWithId(acctId)==null){
            throw new IllegalArgumentException("transactionHistory: Can not find ID");
        }else{
            String history = findAccountWithId(acctId).getHistory().toString().replace(", ","\n").replace("[","").replace("]","");
            return history;
        }
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, double startingBalance, String emailIn, String accPassword) {
        accountCount +=1;
        BankAccount b = new BankAccount(emailIn, startingBalance);
        b.setAcctId(acctId);
        b.setAcctPass(accPassword);
        accountList.add(b);

    }

    public void closeAccount(String acctId) throws IllegalArgumentException {
        if(findAccountWithId(acctId)==null) {
            throw new IllegalArgumentException("closeAccount: Can not find ID");
        }else if(findAccountWithId(acctId).getBalance()!=0){
            throw new IllegalArgumentException("closeAccount: balance greater than 0");
        }else{
            BankAccount b = findAccountWithId(acctId);
            accountList.remove(b);
            accountCount --;
        }
    }


    //------------------ AdminAPI methods -------------------------//

    public double calcTotalAssets() {
        double totalAssets = 0;

        for(int i  = 0; i<accountList.size(); i++){
             totalAssets+= accountList.get(i).getBalance();
           }


        return totalAssets;
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

    public void freezeAccount(String acctId) throws IllegalArgumentException {
        if(findAccountWithId(acctId)==null){
            throw new IllegalArgumentException("freezeAccount: Can not find ID");
        }else {
            BankAccount b = findAccountWithId(acctId);
            b.setAcctFrozen(true);
        }
    }

    public void unfreezeAcct(String acctId) throws IllegalArgumentException{
        if(findAccountWithId(acctId)==null){
            throw new IllegalArgumentException("unfreezeAcct: Can not find ID");
        }else {
            BankAccount b = findAccountWithId(acctId);
            b.setAcctFrozen(false);
        }
    }

    public static void main(String [] args){

    }


}
