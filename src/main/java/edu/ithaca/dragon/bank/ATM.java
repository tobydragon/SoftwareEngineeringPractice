package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ATM implements BasicAPI {
    CentralBank bank;

    public ATM(CentralBank bank) {
        this.bank = bank;
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


    public void withdraw(String acctId, double amount) throws InsufficientFundsException, IllegalArgumentException {
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()) {
            BankAccount current = itr.next();
            if (current.getAcctId() == acctId) {
                if (current.getBalance() < amount) {
                    throw new InsufficientFundsException("Not enough funds");
                }
                if (current.isAmountValid(amount)) {
                    double balance = current.getBalance();
                    balance -= amount;
                    current.setBalance(balance);
                    return;
                }
                if (!current.isAmountValid(amount)) {
                    throw new IllegalArgumentException("Invalid Amount");
                }
            }
        }
        throw new IllegalArgumentException("invalid account id");

    }

    public void deposit(String acctId, double amount) {
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()) {
            BankAccount current = itr.next();
            if (current.getAcctId() == acctId) {
                if (current.isAmountValid(amount)) {
                    double balance = current.getBalance();
                    balance += amount;
                    current.setBalance(balance);
                    return;
                }
                if (!current.isAmountValid(amount)) {
                    throw new IllegalArgumentException("Invalid Amount");
                }

            }
        }
        throw new IllegalArgumentException("invalid account id");

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {
        int count = 0;
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()) {
            BankAccount current = itr.next();
            if (current.getAcctId() == acctIdToWithdrawFrom) {
                if (!current.isAmountValid(amount)) {
                    throw new IllegalArgumentException("Invalid Amount");
                }
                if (current.getBalance() < amount) {
                    throw new InsufficientFundsException("Not enough funds");
                }
                if (current.isAmountValid(amount)) {
                    double balance = current.getBalance();
                    balance -= amount;
                    current.setBalance(balance);
                    count += 1;
                }
            }

            if (current.getAcctId() == acctIdToDepositTo) {
                if (!current.isAmountValid(amount)) {
                    throw new IllegalArgumentException("Invalid Amount");
                }

                if (current.isAmountValid(amount)) {
                    double balance = current.getBalance();
                    balance += amount;
                    current.setBalance(balance);
                    count += 1;
                }
            }
        }
        if (count == 2) {
            return;
        } else {
            throw new IllegalArgumentException("invalid account id");
        }

    }

    public ArrayList transactionHistory(String acctId) {
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()) {
            BankAccount current = itr.next();
            if (current.getAcctId() == acctId) {
                return current.getTransactionHistory();
            }
        }
        throw new IllegalArgumentException("Invalid account id");
    }
}

