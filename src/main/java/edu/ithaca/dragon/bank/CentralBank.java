package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class CentralBank implements AdvancedAPI, AdminAPI {

    private Map<String, BankAccount> accounts = new HashMap<>();
    //Added
    //private Map<String, BankAccount> transactionHist = new HashMap<>();

    //I think you could be on the right track with a map for this, however as it is, this is basically a rehash of the accounts map
    //I think it would be more useful to map account ids to the history string (since that's what we're returning for that method)
    private Map <String, String> transactionHist = new HashMap<>();

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
            AccountDoesNotExistException, ExceedsMaxWithdrawalException, AccountFrozenException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);

        // Added
        if (account.isFrozen()) throw new AccountFrozenException("Account is frozen, cannot withdraw");
        //add to history
        String trans = "withdraw " + String.format("%.2f", amount);
        addToHistory(trans, acctId);
    }

    public void deposit(String acctId, double amount) throws AccountDoesNotExistException, AccountFrozenException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        account.deposit(amount);
        // Added
        if (account.isFrozen()) throw new AccountFrozenException("Account is frozen, cannot deposit");
        String trans = "deposit " + String.format("%.2f", amount);
        addToHistory(trans, acctId);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount)
            throws InsufficientFundsException, AccountDoesNotExistException, ExceedsMaxWithdrawalException, AccountFrozenException {
        if (!accounts.containsKey(acctIdToWithdrawFrom)) throw new AccountDoesNotExistException("Account with this id does not exists");
        if (!accounts.containsKey(acctIdToDepositTo)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount accountW = accounts.get(acctIdToWithdrawFrom);
        BankAccount accountD = accounts.get(acctIdToDepositTo);

        BankAccount.transfer(accountD, accountW, amount);
        // Added
        if (accountD.isFrozen()) throw new AccountFrozenException("Account is frozen, cannot transfer");
        if (accountW.isFrozen()) throw new AccountFrozenException("Account is frozen, cannot transfer");
        String transD = "transfer from " + acctIdToWithdrawFrom + " " + String.format("%.2f", amount);
        String transW = "transfer to " + acctIdToDepositTo + " " + String.format("%.2f", amount);
        addToHistory(transD, acctIdToDepositTo);
        addToHistory(transW, acctIdToWithdrawFrom);
    }

    public String transactionHistory(String acctId) throws AccountDoesNotExistException, AccountAlreadyExistsException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
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

        return suspiciousAccts;
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
//        for(int i = 0; i < accounts.size(); i++){
//            if(accounts[i].equals(acctId)){
//                accounts[i].freeze();
//            }
//        }

        //my suggestion here is to not rely on the built-in freeze method. It was good thinking, but I'm just not sure it's what we want
        //instead, I would add a private member to BankAccount that is a boolean of whether or not the acct is frozen
        //there is a freeze and unfreeze method to go along with this in bank account; I would just call those here

        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        account.freeze();
    }

    /**
     *
     * @param acctId
     *
     * Change boolean method input of account to be false in frozen
     * If called on frozen account, have outside object = null and change frozen to false
     */
    public void unfreezeAcct(String acctId) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        account.unfreeze();
    }
}
