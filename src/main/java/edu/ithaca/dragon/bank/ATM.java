<<<<<<< HEAD
package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI {

    public boolean confirmCredentials(String acctId, String password){
        return false;
    }

    public double checkBalance(String acctEmail){
        BankAccount testAcct = new BankAccount("a@b.com", 1); //TODO redo to use acct collection later
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
=======
package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI {

    ATM(){

    }

    public boolean confirmCredentials(String acctId, String password){
        return false;
    }

    public double checkBalance(String acctId){
        BankAccount testAccount = new BankAccount("ekuznetsov@ithaca.edu", 1);
        return testAccount.getBalance();
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException{

    }

    public void deposit(String acctId, double amount){

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException{

    }

    public String transactionHistory(String acctId){
        return "Hello world";
    }
}
>>>>>>> master
