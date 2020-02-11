package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI {
    private CustomerCollection customers;


    public ATM(CustomerCollection customersIn){
        customers = customersIn;
    }



    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    public double checkBalance(String acctId) {

     return customers.getBalance(acctId);

    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException, IllegalArgumentException {
        customers.withdraw(acctId, amount);
    }

    public void deposit(String acctId, double amount) {
        if (isAmountValid(amount)){
          customers.deposit( acctId,  amount);
        }
        else {
            throw new IllegalArgumentException("invalid amount ");

        }

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }

    public static boolean isAmountValid(double amountIn){
        if (amountIn < 0) return false;
        double scale = Math.pow(10, 9);
        amountIn = Math.round(amountIn*scale)/scale;
        if(Double.compare(amountIn, Math.round(amountIn*100)/100.0)!= 0) return false;
        else return true;
    }
}
