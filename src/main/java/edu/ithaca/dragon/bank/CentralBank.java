package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class CentralBank implements BasicAPI, AdvancedAPI, AdminAPI {

    private String bankName;
    private HashMap<String, Double> bankAccounts;
    private HashMap<String, Double> frozenAccounts;

    public CentralBank(String bankName, HashMap<String, Double> bankAccounts, HashMap<String, Double> frozenAccounts){
       this.bankName = bankName;
       this.bankAccounts = new HashMap<String, Double>();
       this.frozenAccounts= new HashMap<String, Double>();
    }



    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    /**
     * Returns balance associated with ID if it exists
     * @param acctId
     * @return balance in account if it exists
     * @throws IllegalArgumentException if ID does not exist
     */
    public double checkBalance(String acctId) throws IllegalArgumentException{
        if (!bankAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account not found");
        } else {
            return bankAccounts.get(acctId);
        }
    }

    /**
     * Takes money out of the user account
     * @param acctId
     * @param amount
     * @throws InsufficientFundsException if amount is more than balance
     */
    public void withdraw(String acctId, double amount) throws InsufficientFundsException {
        double balance = bankAccounts.get(acctId);
        double newBalance;
        if(BankAccount.isAmountValid(amount)){
            if (amount <= balance){
                newBalance = balance - amount;
                bankAccounts.replace(acctId, newBalance);
            }
            else{
                throw new InsufficientFundsException("Not enough funds for withdrawal.");
            }
        }
        else{
            throw new IllegalArgumentException("Invalid amount entry.");
        }
    }

    public void deposit(String acctId, double amount) {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }


    //----------------- AdvancedAPI methods -------------------------//

    /**
     * Creates account and adds it to central bank's hash map of accounts if account is valid
     * @param acctId
     * @param startingBalance
     * @throws IllegalArgumentException if ID already exists or balance isn't valid
     */
    public void createAccount(String acctId, double startingBalance) throws IllegalArgumentException{
        if (bankAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account ID already exists");
        } else if (frozenAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("ID exists on a frozen account");
        } else if (!BankAccount.isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Balance specified is not a valid amount");
        } else if (acctId.equals("") || acctId.contains(" ")) {
            throw new IllegalArgumentException("Must enter an ID");
        } else {
            bankAccounts.put(acctId, startingBalance);
        }
    }

    public void closeAccount(String acctId) {

    }


    //------------------ AdminAPI methods -------------------------//
    /**
     * Adds up all money in all accounts for a specific bank
     * @return sum of all account balances of the bank, 0 if none
     */
    public double calcTotalAssets() {
        double sum = 0;
        if (bankAccounts.isEmpty()) {
            return 0;
        } else {
            Iterator<Map.Entry<String, Double>> accIterator = bankAccounts.entrySet().iterator();
            while (accIterator.hasNext()) {
                Map.Entry<String, Double> amount = accIterator.next();
                sum+=amount.getValue();
            }
            return sum;
        }
    }


    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }



    /**
     * freezes specified ID if exists and not already frozen
     * @param acctId
     * @throws IllegalArgumentException if ID doesn't exist or already frozen
     */
    public void freezeAccount(String acctId) throws IllegalArgumentException {
        if (frozenAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account already frozen");
        } else if (bankAccounts.containsKey(acctId)) {
            Double balance = bankAccounts.get(acctId);
            bankAccounts.remove(acctId);
            frozenAccounts.put(acctId, balance);
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
    }



    /**
     * unfreezes specified ID if exists and frozen
     * @param acctId
     * @throws IllegalArgumentException if ID doesn't exist or not currently frozen
     */
    public void unfreezeAcct(String acctId) throws IllegalArgumentException{
        if (bankAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account not frozen");
        } else if (frozenAccounts.containsKey(acctId)) {
            Double balance = frozenAccounts.get(acctId);
            frozenAccounts.remove(acctId);
            bankAccounts.put(acctId, balance);
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
    }



    /**
     *
     * @param acctID
     * @return if account exists or not
     */
    public boolean checkAccountExists(String acctID) {
        if (bankAccounts.containsKey(acctID)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param acctID
     * @return if frozen account exists or not
     */
    public boolean checkFrozenAccountExists(String acctID) {
        if (frozenAccounts.containsKey(acctID)) {
            return true;
        } else {
            return false;
        }
    }

}
