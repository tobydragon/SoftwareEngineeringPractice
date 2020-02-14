package edu.ithaca.dragon.bank;


public class BankTeller implements AdvancedAPI {
    private CustomerCollection customers;

    public BankTeller(){ customers = new CustomerCollection(); }

    public CustomerCollection getCustomers() {
        return customers;
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
            customers.deposit( acctId,  amount);;
        }
        else {
            throw new IllegalArgumentException("invalid amount ");

        }

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, IllegalArgumentException{
        if(amount < 0 || (amount * 100) % 1 != 0){
            throw new IllegalArgumentException("invalid input");
        }
        else{
            customers.withdraw(acctIdToWithdrawFrom, amount);
            customers.deposit( acctIdToDepositTo,  amount);;
        }
    }

    public String transactionHistory(String acctId) {
        return null;
    }

    public void createCustomer(String acctId, String password) throws IllegalArgumentException {
        customers.addCustomer(acctId, password);
    }

    public void addAccount(String actID, double startingBalance) throws IllegalArgumentException{
        customers.createAccount(actID, startingBalance);
    }

    public void createCustomerWithAccount(String actID, String password, double startingBalance) throws IllegalArgumentException{
        customers.addCustomer(actID, password);
        customers.createAccount(actID, startingBalance);

    }

    public void closeCustomer(String acctId) throws IllegalArgumentException {
        customers.closeCustomer(acctId);
    }

    public int getNumCustomers(){
        return customers.getNumCustomers();

    }
    public static boolean isAmountValid(double amountIn){
        if (amountIn < 0) return false;
        double scale = Math.pow(10, 9);
        amountIn = Math.round(amountIn*scale)/scale;
        if(Double.compare(amountIn, Math.round(amountIn*100)/100.0)!= 0) return false;
        else return true;
    }
}
