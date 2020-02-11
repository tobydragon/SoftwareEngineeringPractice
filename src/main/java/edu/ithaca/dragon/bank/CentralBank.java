package edu.ithaca.dragon.bank;

import java.util.*;

public class CentralBank implements AdvancedAPI, AdminAPI {

    private Map<String, BankAccount> accounts = new HashMap<>();
    //Added
    private Map<String, String> transactionHist = new HashMap<>();

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) throws AccountDoesNotExistException{
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        if (account.getPassword() == password) return true;
        else return false;
    }

    public double checkBalance(String acctId) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        return account.getBalance();
    }

    private void addToHistory(String transaction, String acctId) {
        if (transactionHist.containsKey(acctId)) {
            String history = transactionHist.get(acctId);
            history += "," + transaction;
            transactionHist.put(acctId, history);
        } else {
            transactionHist.put(acctId, transaction);
        }
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException,
            AccountDoesNotExistException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        account.withdraw(amount);
        // Added
        addToHistory("w", acctId);
    }

    public void deposit(String acctId, double amount) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        account.deposit(amount);
        // Added
        addToHistory("d", acctId);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount)
            throws InsufficientFundsException, AccountDoesNotExistException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctIdToWithdrawFrom)) throw new AccountDoesNotExistException("Account with this id does not exists");
        if (!accounts.containsKey(acctIdToDepositTo)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount accountW = accounts.get(acctIdToWithdrawFrom);
        BankAccount accountD = accounts.get(acctIdToDepositTo);

        BankAccount.transfer(accountD, accountW, amount);
        // Added
        addToHistory("t", acctIdToWithdrawFrom);
        addToHistory("t", acctIdToDepositTo);
    }

    public String transactionHistory(String acctId) throws AccountDoesNotExistException, AccountAlreadyExistsException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        return transactionHist.get(acctId);
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, String password, double startingBalance, boolean savings, boolean frozen) throws AccountAlreadyExistsException, IllegalArgumentException {
        if (accounts.containsKey(acctId)) throw new AccountAlreadyExistsException("Account with this id already exists");

        BankAccount account;
        if (savings) account = new SavingsAccount(acctId, password, startingBalance, frozen);
        else account = new CheckingAccount(acctId, password, startingBalance, frozen);

        accounts.put(acctId, account);
    }

    //for testing createAccount function - one must exist to test the other
    public boolean accountExists(String acctId) {
        return accounts.containsKey(acctId);
    }

    public void closeAccount(String acctId) throws AccountDoesNotExistException, BalanceRemainingException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account does not exist");

        BankAccount account = accounts.get(acctId);
        if (account.getBalance() != 0) throw new BalanceRemainingException("Only empty accounts may be closed");

        accounts.remove(acctId);
    }


    //------------------ AdminAPI methods -------------------------//

    public double calcTotalAssets() throws AccountDoesNotExistException {
        if (accounts.keySet().size() == 0) throw new AccountDoesNotExistException("Bank does not contain accounts");

        double total = 0;
        for (BankAccount account:accounts.values()) {
            total += account.getBalance();
        }

        return total;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        BankAccount suspiciousAct;
        Collection<String> suspiciousAccts = new LinkedList<>();

        //check for suspicious activity
        //I recommend using the first character in each transaction string - it will be t, d, or w
        //You'll have to split the string by the comma delimiter
        //you can compare items in the list and see if there are too many withdraws/deposits together
        Iterator<Map.Entry<String, String>> iterator = transactionHist.entrySet().iterator();
        int wCount = 0;
        int dCount = 0;
        int tCount = 0;

        while(iterator.hasNext()){
            if (transactionHist.equals("w")){
                wCount++;
            }
            else if(transactionHist.equals("d")){
                dCount++;
            }
            else if(transactionHist.equals("t")){
                tCount++;
            }
            else{
                iterator.next();
            }
        }
        if(wCount >= 5 && tCount >= 5){
            System.out.println("You account has done" + wCount + " Withdraws" + tCount + " Transfers");
        }
        else if(wCount >= 5){
            System.out.println("You account has done" + wCount + " Withdraws");
        }
        else{
            System.out.println("You account has done" + tCount + " Transfers");
        }
        return suspiciousAccts;
    }

    public boolean isFrozen(String acctId) throws AccountDoesNotExistException{
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account does not exist");
        BankAccount account = accounts.get(acctId);
        return account.isFrozen();
    }

    /**
     *
     * @param acctId
     *
     * Have an outside object to hole bank account, meant to be the account and balance when frozen
     * If the account is frozen, the outside object would replace any changes, keeping account same
     * Follow a boolean, having another method to check if account is frozen
     * if frozen == true, replace account
     */
    public void freezeAccount(String acctId) throws AccountDoesNotExistException {
        // Object.Freeze = freezes object and stops any changes
        /*
        for(int i = 0; i < accounts.size(); i++){
            if(accounts[i].equals(acctId)){
                accounts[i].freeze();
            }
        }
        */
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account does not exist");
        BankAccount account = accounts.get(acctId);
        if(account.isFrozen() == false) {
            account.freeze();
        }
    }

    /**
     *
     * @param acctId
     *
     * Change boolean method input of account to be false in frozen
     * gitIf called on frozen account, have outside object = null and change frozen to false
     */
    public void unfreezeAcct(String acctId) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account does not exist");
        BankAccount account = accounts.get(acctId);
        if(account.isFrozen() == true) {
            account.unfreeze();
        }
    }
}
