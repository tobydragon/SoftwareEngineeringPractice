package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {

    private UserArrayList userAccounts;
    private BankAccountCollection bankAccountCollection;

    public CentralBank(){
        userAccounts = new UserArrayList();
        bankAccountCollection = new BankAccountCollection();
    }

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String username, String password) {

        //Try/catch with nonexistentaccountexception


        return false;
    }

    public double checkBalance(int userID) throws NonExistentAccountException{
        return bankAccountCollection.retrieveAccount(userID, 0).getBalance();
    }

    public double checkBalance(int userID, int acctId) {
        return 0;
    }

    public void withdraw(int userId, double amount) throws InsufficientFundsException, NonExistentAccountException{
        bankAccountCollection.retrieveAccount(userId, 0).withdraw(amount);
    }

    public void withdraw(int userId, int bankAccID, double amount) throws InsufficientFundsException {

    }

    public void deposit(int userId, double amount) throws InsufficientFundsException, NonExistentAccountException{
        bankAccountCollection.retrieveAccount(userId, 0).deposit(amount);
    }

    public void deposit(int userId, int bankAccID, double amount) throws InsufficientFundsException {
        //TODO
    }

    public void transfer(int userIDFrom, int acctIdToWithdrawFrom, int userIDTo, int acctIdToDepositTo, double amount) throws InsufficientFundsException {
        //TODO
    }

    public String transactionHistory(int userId) {
        return null;
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createUser(String userName, String password, String email, int userID){
        userAccounts.addAccount(new UserAccount(userName, password, email, userID));
    }

    public void createBankAccount(int userId, double startingBalance) throws NonExistentAccountException{
        UserAccount currentAccount = userAccounts.findAccount(userId);
        BankAccount newAccount = new BankAccount(currentAccount.getEmail(), startingBalance, userId);
        bankAccountCollection.addBankAccount(newAccount);
    }

    public void closeBankAccount(int userId) {

    }

    public void createUserAccount(String username, String password, String email, int userID){
        UserAccount newAccount = new UserAccount(username, password, email, userID);
        userAccounts.addAccount(newAccount);
    }

    public void closeUserAccount(int userId) throws IllegalArgumentException, NonExistentAccountException{
        UserAccount removedAccount = userAccounts.findAccount(userId);
        userAccounts.removeAccount(removedAccount);

    }

    //------------------ AdminAPI methods -------------------------//

    public double calcTotalAssets() {
        return 0;
    }

    public Collection<Integer> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    public void freezeAccount(int userId) {

    }

    public void unfreezeAcct(int userId) {

    }

}
