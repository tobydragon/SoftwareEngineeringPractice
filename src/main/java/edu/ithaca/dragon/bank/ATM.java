package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI{

    public Admin admin;

    public ATM(Admin adminIn){
        admin = adminIn;
    }

    public boolean confirmCredentials(String acctId, String password) throws IllegalArgumentException, AcctFrozenException{
        Account acct = admin.getAccount(acctId);
        if (acct == null){
            throw new IllegalArgumentException("Account not found");
        }
        if (acct.getFrozenStatus()){
            throw new AcctFrozenException("This account is frozen.");
        }
        if (acct.getAcctPassword() != password){
            return false;
        }
        return true;

    }

    public double checkBalance(String acctId, String password) throws IllegalArgumentException, AcctFrozenException{
        if (!confirmCredentials(acctId, password)){
            throw new IllegalArgumentException("ID and Password incorrect");
        }
        Account acctToCheck = admin.getAccount(acctId);
        return acctToCheck.checkBalance(acctId);

    }

    public void withdraw(String acctId, String password, double amount) throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException {
        if (!confirmCredentials(acctId, password)){
            throw new IllegalArgumentException("ID and Password incorrect");
        }
        Account acctToCheck = admin.getAccount(acctId);
        acctToCheck.withdraw(acctId,amount);
    }

    public void deposit(String acctId, String password, double amount) throws IllegalArgumentException, AcctFrozenException{
        if (!confirmCredentials(acctId, password)){
            throw new IllegalArgumentException("ID and Password incorrect");
        }
        Account acctToCheck = admin.getAccount(acctId);
        acctToCheck.deposit(acctId,amount);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, String passToWithdrawFrom, String passToDepositTo,double amount) throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException{
        if (!confirmCredentials(acctIdToWithdrawFrom, passToWithdrawFrom) || !confirmCredentials(acctIdToDepositTo,passToDepositTo)){
            throw new IllegalArgumentException("One of the accounts is not valid");
        }
        Account acctFrom = admin.getAccount(acctIdToWithdrawFrom);
        Account acctTo = admin.getAccount(acctIdToDepositTo);
        if (acctFrom.getFrozenStatus() || acctTo.getFrozenStatus()){
            throw new AcctFrozenException("One of the accounts is frozen");
        }
        acctFrom.withdraw(acctIdToWithdrawFrom, amount);
        acctTo.deposit(acctIdToDepositTo,amount);
    }

    public String transactionHistory(String acctId, String password) throws IllegalArgumentException, AcctFrozenException{
        if (!confirmCredentials(acctId, password)){
            throw new IllegalArgumentException("ID and Password incorrect");
        }
        Account acctToCheck = admin.getAccount(acctId);
        return acctToCheck.transactionHistory(acctId);
    }
}
