package edu.ithaca.dragon.bank;

import java.lang.reflect.Array;
import java.util.Collection;

public class CentralBank implements AdvancedAPI, AdminAPI {

    public CentralBank (){
        BankAccount customerCollection[]= new BankAccount[1];
        customerCollection[0] = new BankAccount("a@b.com",305);
    }

    private boolean isAmountValid(double amount) throws IllegalArgumentException{
        String numString = Double.toString(amount);
        int length = numString.length();

        if (length > 3){ //only runs this test if amount has more than 3 chars
            int period = numString.lastIndexOf(".");
            if (length > (period + 3)) throw new IllegalArgumentException("The amount you entered " + amount + " is invalid because it has more than three decimal places.");
        }

        else if (amount < 0){  //checks ifd the number is negative
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid because it is negative");
        }

        return true; // returns true if amount is valid
    }

    //----------------- BasicAPI methods -------------------------//

    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }


    public double checkBalance(String acctId, BankAccount[] customerCollection) throws IllegalArgumentException {
        int length = customerCollection.length;
        for(int i =0; i < length; i++){
            if (acctId == customerCollection[i].email)
                return customerCollection[i].getBalance();
        }
        throw new IllegalArgumentException("The account ID you entered is not in the system");
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    public void deposit(String acctId, double amount, BankAccount[] customerCollection) {
        int length = customerCollection.length;

        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }
        else if (amount == 0){
            throw new IllegalArgumentException("You cannot deposit $0");
        }
        else
            for(int i =0; i < length; i++){
                if (acctId == customerCollection[i].email)
                    customerCollection[i].balance += amount;
            }
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }


    //----------------- AdvancedAPI methods -------------------------//

    public void createAccount(String acctId, double startingBalance) {

    }

    public void closeAccount(String acctId) {

    }


    //------------------ AdminAPI methods -------------------------//

    public double checkTotalAssets() {
        return 0;
    }

    public double calcTotalAssets() {
        return 0;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    public void freezeAccount(String acctId) {

    }

    public void unfreezeAcct(String acctId) {

    }

}
