
package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI {

    UserArrayList userAccounts;

    public ATM(UserArrayList userAccounts){
        this.userAccounts = userAccounts;
    }

    public boolean confirmCredentials(String acctId, String password){
        return false;
    }

    public double checkBalance(String acctEmail){
        BankAccount testAcct = new BankAccount("a@b.com", 1,1234); //TODO redo to use acct collection later
        //test for push
        return testAcct.getBalance();

    }
    public void withdraw(String acctId, double amount) throws InsufficientFundsException{

    }

    public void deposit(String acctId, double amount){

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException{

    }

    public String transactionHistory(String acctId){
        return null;
    }
}