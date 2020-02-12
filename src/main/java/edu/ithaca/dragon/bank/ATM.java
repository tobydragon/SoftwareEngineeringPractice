
package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI {

    private UserArrayList userAccounts;
    private CentralBank bank;

    public ATM(UserArrayList userAccounts, CentralBank bank){

        this.userAccounts = userAccounts;
        this.bank = bank;
    }

    public boolean confirmCredentials(String username, String password){

        return bank.confirmCredentials(username,password);
    }

    public double checkBalance(int userID) throws NonExistentAccountException{
        BankAccount testAcct = new BankAccount("a@b.com", 1,1234); //TODO redo to use acct collection later
        //test for push
        return testAcct.getBalance();

    }
    public void withdraw(int acctId, double amount) throws InsufficientFundsException, NonExistentAccountException{

    }

    public void deposit(int acctId, double amount) throws InsufficientFundsException, NonExistentAccountException{

    }

    public void transfer(int userIDFrom, int acctIdToWithdrawFrom, int userIDTo, int acctIdToDepositTo, double amount) throws InsufficientFundsException{

    }

    public String transactionHistory(int acctId){
        return null;
    }
}