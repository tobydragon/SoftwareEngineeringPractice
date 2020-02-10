package edu.ithaca.dragon.bank;

public class BankTeller implements AdvancedAPI {
    private CustomerCollection customers;

    public BankTeller(){
        customers = new CustomerCollection();
    }

    public CustomerCollection getCustomers() {
        return customers;
    }

    public boolean confirmCredentials(String acctId, String password) {
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
    

    public void createAccount(String acctId, String password, double startingBalance) {

    }

    public void closeAccount(String acctId) {}
}
