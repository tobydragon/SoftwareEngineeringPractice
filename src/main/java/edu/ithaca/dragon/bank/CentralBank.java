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
        if (account.getPassword().equals(password)) return true;
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
        String trans = "withdraw " + String.format("%.2f", amount);
        addToHistory(trans, acctId);
    }

    public void deposit(String acctId, double amount) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        account.deposit(amount);
        // Added
        String trans = "deposit " + String.format("%.2f", amount);
        addToHistory(trans, acctId);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount)
            throws InsufficientFundsException, AccountDoesNotExistException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctIdToWithdrawFrom)) throw new AccountDoesNotExistException("Account with this id does not exists");
        if (!accounts.containsKey(acctIdToDepositTo)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount accountW = accounts.get(acctIdToWithdrawFrom);
        BankAccount accountD = accounts.get(acctIdToDepositTo);

        BankAccount.transfer(accountD, accountW, amount);
        // Added
        String transD = "transfer from " + acctIdToWithdrawFrom + " " + String.format("%.2f", amount);
        String transW = "transfer to " + acctIdToDepositTo + " " + String.format("%.2f", amount);
        addToHistory(transD, acctIdToDepositTo);
        addToHistory(transW, acctIdToWithdrawFrom);
    }

    public String transactionHistory(String acctId) throws AccountDoesNotExistException, AccountAlreadyExistsException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctId)) throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        return transactionHist.get(acctId);
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
        Collection<String> suspiciousAccts = new HashSet<>();

//        for (String acctId:transactionHist.keySet()) {
//            int wCount = 0;
//            int dCount = 0;
//            int tCount = 0;
//            String history = transactionHist.get(acctId);
//            String[] trans = history.split(",");
//            for(String t:trans) {
//                if (t.toCharArray()[0] == 'w') wCount++;
//                if (t.toCharArray()[0] == 'd') dCount++;
//                if (t.toCharArray()[0] == 't') tCount++;
//            }
//            //if there are a lot of withdraws out not interspersed with deposits
//            //or too many transfers, it's suspicious
//            if ((wCount >= 5 && dCount < 3) || tCount >= 5) {
//                suspiciousAccts.add(acctId);
//            }
//        }

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
