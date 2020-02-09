package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class CentralBank implements AdvancedAPI, AdminAPI {

    private Map<String, BankAccount> accounts = new HashMap<>();
    //Added
    private Map<String, BankAccount> transactionHist = new HashMap<>();

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

    public void withdraw(String acctId, double amount) throws InsufficientFundsException,
            AccountDoesNotExistException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        // Added
        transactionHist.put(acctId, account);
    }

    public void deposit(String acctId, double amount) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        account.deposit(amount);
        // Added
        transactionHist.put(acctId, account);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount)
            throws InsufficientFundsException, AccountDoesNotExistException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctIdToWithdrawFrom)) throw new AccountDoesNotExistException("Account with this id does not exists");
        if (!accounts.containsKey(acctIdToDepositTo)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount accountW = accounts.get(acctIdToWithdrawFrom);
        BankAccount accountD = accounts.get(acctIdToDepositTo);

        BankAccount.transfer(accountD, accountW, amount);
        // Added
        transactionHist.put(acctIdToWithdrawFrom, accountW);
        transactionHist.put(acctIdToDepositTo, accountD);
    }

    public String transactionHistory(String acctId) throws AccountDoesNotExistException, AccountAlreadyExistsException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        return transactionHist.get(acctId, );
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, String password, double startingBalance, boolean savings) throws AccountAlreadyExistsException, IllegalArgumentException {
        if (accounts.containsKey(acctId)) throw new AccountAlreadyExistsException("Account with this id already exists");

        BankAccount account;
        if (savings) account = new SavingsAccount(acctId, password, startingBalance);
        else account = new CheckingAccount(acctId, password, startingBalance);

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

    }

    public void freezeAccount(String acctId) {
        // Object.Freeze = freezes object and stops any changes
        for(int i = 0; i < accounts.size(); i++){
            if(accounts[i].equals(acctId)){
                accounts[i].freeze();
            }
        }
    }

    public void unfreezeAcct(String acctId) {

    }
}
