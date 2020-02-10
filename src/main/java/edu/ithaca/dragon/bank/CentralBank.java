package edu.ithaca.dragon.bank;

import java.security.PublicKey;
import java.util.Collection;

public class CentralBank implements BasicAPI, AdvancedAPI, AdminAPI {

    private User[] users;
    private BankAccount[] accounts;
    private double netWorth;
    private int time;
    private String[] admins;
    private String[] allHistory;
    private String[] atmHistory;
    private String[] tellerHistory;
    private String[] adminHistory;
    private int numAccounts;

    public CentralBank(String[] admins) {

        int defaultArraySize = 10; //this may change

        users = new User[defaultArraySize];
        accounts = new BankAccount[defaultArraySize];
        this.admins = admins;
        allHistory = new String[defaultArraySize];
        atmHistory = new String[defaultArraySize];
        tellerHistory = new String[defaultArraySize];
        adminHistory = new String[defaultArraySize];

        numAccounts = 0;
        time = 0;
        netWorth = 0;
    }

    //----------------- edu.ithaca.dragon.bank.BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) {
        return 0;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    /**
     * Deposits money to an account
     * @param acctId Account Identifier
     * @param amount Amount to be deposited
     */
    public void deposit(String acctId, double amount) {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }

    public String getAccountId(String email, String accountType){
        for (int i = 0; i < accounts.length; i++){
            if(accounts[i].getEmail() == email && accounts[i].type == accountType){
                return accounts[i].acctId;
            }
        }
        throw new IllegalArgumentException("Account not found");
    }


    //----------------- edu.ithaca.dragon.bank.AdvancedAPI methods -------------------------//

    /**
     * Creates an acct of type defined and adds acct to acct list
     * @param email
     * @param startingBalance
     * @param acctType
     */
    public void createAccount(String email, double startingBalance, String acctType) {

    }

    public void closeAccount(String acctId) {

    }


    //------------------ edu.ithaca.dragon.bank.AdminAPI methods -------------------------//

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

    //------------------ CentralBank Getters -------------------------//


    public User[] getUsers() {
        return users;
    }

    public BankAccount[] getAccounts() {
        return accounts;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public int getTime() {
        return time;
    }

    public String[] getAdmins() {
        return admins;
    }

    public String[] getAllHistory() {
        return allHistory;
    }

    public String[] getAtmHistory() {
        return atmHistory;
    }

    public String[] getTellerHistory() {
        return tellerHistory;
    }

    public String[] getAdminHistory() {
        return adminHistory;
    }

    public int getNumAccounts() {
        return numAccounts;
    }
}
