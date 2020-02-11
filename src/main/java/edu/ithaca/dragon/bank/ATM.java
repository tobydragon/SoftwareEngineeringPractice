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
        while (itr.hasNext()){
            User current = itr.next();
            if (current.getUsername()== username){
                if (current.getPassword()==password){
                    return true;
                }

                else{
                    return false;
                }
            }
        }

        return false;
    }

    public double checkBalance(String acctId) {
        return 0;
    }


    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    public void deposit(String acctId, double amount) {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }

}
