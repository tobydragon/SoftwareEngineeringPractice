package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class CentralBank implements BasicAPI, AdvancedAPI, AdminAPI {

    private String bankName;
    private HashMap<String, BankAccount> bankAccounts;
    private HashMap<String, BankAccount> frozenAccounts;

    public CentralBank(String bankName){
       this.bankName = bankName;
       this.bankAccounts = new HashMap<String, BankAccount>();
       this.frozenAccounts= new HashMap<String, BankAccount>();
    }



    //----------------- BasicAPI methods -------------------------//

    /**
     *
     * @param acctId
     * @param password
     * @return if password is valid
     * @throws IllegalArgumentException if account ID does not exist
     */
    public boolean confirmCredentials(String acctId, String password) throws IllegalArgumentException{
        if (!bankAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account ID does not exist");
        } else if (password.equals(bankAccounts.get(acctId).getPassword())) {
            return true;
        } else {
            return false;
        }
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
            return bankAccounts.get(acctId).getBalance();
        }
    }

    /**
     * Takes money out of the user account
     * @param acctId
     * @param amount
     * @throws InsufficientFundsException if amount is more than balance or account does not exist
     */
    public void withdraw(String acctId, double amount) throws InsufficientFundsException {
        if (!bankAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account not found");
        } else if(BankAccount.isAmountValid(amount)){
            double balance = bankAccounts.get(acctId).getBalance();
            if (amount <= balance){
                balance -= amount;
                BigDecimal bd = new BigDecimal(balance).setScale(2, RoundingMode.HALF_UP);
                double roundedBalance = bd.doubleValue();
                bankAccounts.get(acctId).setBalance(roundedBalance);
                bankAccounts.get(acctId).newTransaction("Withdraw: " + amount + "\n");
                bankAccounts.get(acctId).incTranCount();
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
        if (!bankAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account not found");
        } else if (BankAccount.isAmountValid(amount)){
            double balance = bankAccounts.get(acctId).getBalance();
            balance += amount;

            BigDecimal bd = new BigDecimal(balance).setScale(2, RoundingMode.HALF_UP);
            double roundedBalance = bd.doubleValue();
            bankAccounts.get(acctId).setBalance(roundedBalance);

            bankAccounts.get(acctId).setBalance(balance);
            bankAccounts.get(acctId).newTransaction("Deposit: " + amount + "\n");
            bankAccounts.get(acctId).incTranCount();

        }
        else{
            throw new IllegalArgumentException("Invalid amount entry.");
        }
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {
        if (BankAccount.isAmountValid(amount)){
            this.withdraw(acctIdToWithdrawFrom, amount);
            this.deposit(acctIdToDepositTo, amount);
        }
        else{
            throw new IllegalArgumentException("Invalid amount entry.");
        }
    }

    public String transactionHistory(String acctId) {
        String transactions = bankAccounts.get(acctId).getTransHist();
        if (transactions == ""){
            throw new IllegalArgumentException("There are no transactions logged for this account");
        }
        else if(!checkAccountExists(acctId)){
            throw new NullPointerException("This account does not exist");
        }
        else{
            return transactions;
        }
    }

    public int getTranCount(String acctId) throws NullPointerException{
        if(!checkAccountExists(acctId)){
            throw new NullPointerException("Account does not exist");
        }
        else{
            int tranCount = bankAccounts.get(acctId).getTranCount();
            return tranCount;
        }

    }


    //----------------- AdvancedAPI methods -------------------------//

    /**
     * Creates account and adds it to central bank's hash map of accounts if account is valid
     * @param acctId
     * @param startingBalance
     * @throws IllegalArgumentException if ID already exists or balance isn't valid
     */
    public void createAccount(String acctId, double startingBalance, String password, boolean isSavings) throws IllegalArgumentException{
        if (bankAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("Account ID already exists");
        } else if (frozenAccounts.containsKey(acctId)) {
            throw new IllegalArgumentException("ID exists on a frozen account");
        } else if (!BankAccount.isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Balance specified is not a valid amount");
        } else if (acctId.equals("") || acctId.contains(" ")) {
            throw new IllegalArgumentException("Must enter an ID");
        } else if (!isSavings){
            BankAccount newAccount = new BankAccount(acctId,startingBalance, password);
            bankAccounts.put(acctId, newAccount);
        } else {
            SavingsAccount newSavingsAccount = new SavingsAccount(acctId,startingBalance,password, 5);
            bankAccounts.put(acctId, newSavingsAccount);
        }
    }

    public void closeAccount(String acctId) {
        if(bankAccounts.containsKey(acctId)){
            bankAccounts.remove(acctId);
        }
        else{
            throw new IllegalArgumentException("Account with that ID does not exist");
        }
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
            Iterator<Map.Entry<String, BankAccount>> accIterator = bankAccounts.entrySet().iterator();
            while (accIterator.hasNext()) {
                Map.Entry<String, BankAccount> amount = accIterator.next();
                sum+=amount.getValue().getBalance();
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
            BankAccount account = bankAccounts.get(acctId);
            bankAccounts.remove(acctId);
            frozenAccounts.put(acctId, account);
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
            BankAccount account =  frozenAccounts.get(acctId);
            frozenAccounts.remove(acctId);
            bankAccounts.put(acctId, account);
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
