package edu.ithaca.dragon.bank;

import java.util.*;

public class CentralBank implements AdvancedAPI, AdminAPI {

    private Map<String, BankAccount> accounts = new HashMap<>();
    //Added
    private Map<String, String> transactionHist = new HashMap<>();

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId))
            throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        if (account.getPassword().equals(password)) return true;
        else return false;
    }

    public double checkBalance(String acctId) throws AccountDoesNotExistException {
        if (!accounts.containsKey(acctId))
            throw new AccountDoesNotExistException("Account with this id does not exists");
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
        if (!accounts.containsKey(acctId))
            throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        if (account.isFrozen()) throw new AccountFrozenException("This account is frozen");
        account.withdraw(amount);
        // Added
        String trans = "withdraw " + String.format("%.2f", amount);
        addToHistory(trans, acctId);
    }

    public void deposit(String acctId, double amount) throws AccountDoesNotExistException, AccountFrozenException {
        if (!accounts.containsKey(acctId))
            throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        if (account.isFrozen()) throw new AccountFrozenException("This account is frozen");
        account.deposit(amount);
        // Added
        String trans = "deposit " + String.format("%.2f", amount);
        addToHistory(trans, acctId);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount)
            throws InsufficientFundsException, AccountDoesNotExistException, ExceedsMaxWithdrawalException, AccountFrozenException {
        if (!accounts.containsKey(acctIdToWithdrawFrom))
            throw new AccountDoesNotExistException("Account with this id does not exists");
        if (!accounts.containsKey(acctIdToDepositTo))
            throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount accountW = accounts.get(acctIdToWithdrawFrom);
        BankAccount accountD = accounts.get(acctIdToDepositTo);
        if (accountW.isFrozen()) throw new AccountFrozenException("This account is frozen");
        if (accountD.isFrozen()) throw new AccountFrozenException("This account is frozen");

        BankAccount.transfer(accountD, accountW, amount);
        // Added
        String transD = "transfer from " + acctIdToWithdrawFrom + " " + String.format("%.2f", amount);
        String transW = "transfer to " + acctIdToDepositTo + " " + String.format("%.2f", amount);
        addToHistory(transD, acctIdToDepositTo);
        addToHistory(transW, acctIdToWithdrawFrom);
    }

    public String transactionHistory(String acctId) throws AccountDoesNotExistException, AccountAlreadyExistsException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        if (!accounts.containsKey(acctId))
            throw new AccountDoesNotExistException("Account with this id does not exists");
        BankAccount account = accounts.get(acctId);
        return transactionHist.get(acctId);
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, String password, double startingBalance, boolean savings) throws AccountAlreadyExistsException, IllegalArgumentException {
        if (accounts.containsKey(acctId))
            throw new AccountAlreadyExistsException("Account with this id already exists");

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
        for (BankAccount account : accounts.values()) {
            total += account.getBalance();
        }

        return total;
    }

    private static double mean(List<Double> list) {
        double sum = 0.0;
        for (double num : list) {
            sum += num;
        }
        return sum / list.size();
    }

    private static double standardDeviation(List<Double> list) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = list.size();
        for (double num : list) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : list) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }

    public boolean isSuspicious(List<Double> transactions) {
        double mean = mean(transactions);
        double sd = standardDeviation(transactions);

        for (double d : transactions) {
            double high = mean + (2 * sd);
            double low = mean - (2 * sd);
            if (d > high || d < low) {
                return true;
            }
        }
        return false;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        Collection<String> suspiciousAccts = new HashSet<>();

        for (String acctId : transactionHist.keySet()) {

            List<Double> ws = new ArrayList<>();
            List<Double> ds = new ArrayList<>();

            Set<String> taccts = new HashSet<>();
            double transferTotal = 0;

            String history = transactionHist.get(acctId);
            String[] transactions = history.split(",");
            for (String t : transactions) {
                if (t.toCharArray()[0] == 'w') {
                    String[] withdrawal = t.split(" ");
                    double amount = Double.parseDouble(withdrawal[1]);
                    ws.add(amount);
                }
                if (t.toCharArray()[0] == 'd') {
                    String[] deposit = t.split(" ");
                    double amount = Double.parseDouble(deposit[1]);
                    ds.add(amount);
                }
                if (t.toCharArray()[0] == 't') {
                    String[] transfer = t.split(" "); //1 - to or from, 2 - account, 3 - amount
                    double amount = Double.parseDouble(transfer[3]);
                    if (transfer[1].toCharArray()[0] == 't') {
                        taccts.add(transfer[2]);
                        transferTotal += amount;
                    }
                }
            }

            //account is suspicious if any withdraws or deposits are more than 2s.d. off the average
            if (isSuspicious(ws) || isSuspicious(ds)) suspiciousAccts.add(acctId);
            //or if more money than is left in the account was transfered (all involved accounts are suspicious)
            if (transferTotal > accounts.get(acctId).getBalance()) {
                suspiciousAccts.add(acctId);
                for (String id : taccts) suspiciousAccts.add(id);
            }
            if (isSuspicious(ws) || isSuspicious(ds) || transferTotal > accounts.get(acctId).getBalance()) {
                Scanner actMessage = new Scanner(System.in);
                System.out.println("Have you done any of these purchases(y or n): ");

                String actAnswer = actMessage.nextLine();
                if (actAnswer.equals("y")) {
                    System.out.println("Then you have nothing to worry about");
                    suspiciousAccts.clear();
                    return suspiciousAccts;
                } else {
                    System.out.println("We will freeze you account due to the activity");
                    return suspiciousAccts;
                }
            }
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
