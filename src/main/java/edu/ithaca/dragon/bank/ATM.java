package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Iterator;

public class ATM implements BasicAPI{
    CentralBank bank;

    public ATM(CentralBank bank){
        this.bank=bank;
    }

    public boolean confirmCredentials(String username, String password) {
        Iterator<User> itr = this.bank.users.iterator();
        while (itr.hasNext()) {
            User current = itr.next();
            if (current.getUsername() == username) {
                if (current.getPassword() == password) {
                    return true;
                }
            }
        }

        return false;
    }

    public double checkBalance(String acctId) {
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()) {
            BankAccount current = itr.next();
            if (current.getAcctId() == acctId) {
                return current.getBalance();
            }
        }
        throw new IllegalArgumentException("invalid account id");
    }


    public void withdraw(String acctId, double amount) throws InsufficientFundsException,IllegalArgumentException {
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()) {
            BankAccount current = itr.next();
            if (current.getAcctId() == acctId) {
                if (current.getBalance()<amount){
                    throw new InsufficientFundsException("Not enough funds");
                }
                if (current.isAmountValid(amount)){
                    double balance=current.getBalance();
                    balance-=amount;
                    current.setBalance(balance);
                }
                if (!current.isAmountValid(amount)){
                    throw new IllegalArgumentException("Invalid Amount");
                }
            }
            else{
                throw new IllegalArgumentException("invalid account id");
            }
        }

    }

    public void deposit(String acctId, double amount) {
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()) {
            BankAccount current = itr.next();
            if (current.getAcctId() == acctId) {
                if (current.isAmountValid(amount)){
                    double balance=current.getBalance();
                    balance+=amount;
                    current.setBalance(balance);
                }
                if (!current.isAmountValid(amount)){
                    throw new IllegalArgumentException("Invalid Amount");
                }
            }
            else{
                throw new IllegalArgumentException("invalid account id");
            }
        }
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }

}
