package edu.ithaca.dragon.bank;

public class BankAccount {

    private String id;
    private double balance;
    private String transHist;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String id, double startingBalance){
        if (isAmountValid(startingBalance)){
            this.id = id;
            this.balance = startingBalance;
            this.transHist = "";
        }
        else{
            throw new IllegalArgumentException("Starting Balance Amount is invalid.");
        }
    }

    public void newTransaction(String tran){
        transHist += tran;
    }

    public double getBalance(){
        return balance;
    }

    public String getTransHist(){
        return transHist;
    }

    public String getId(){
        return id;
    }

    public void setBalance(double newBalance){this.balance = newBalance;}

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException {
        if (isAmountValid(amount)){
            if (amount <= balance){
                balance-=amount;
            }
            else{
                throw new InsufficientFundsException("Not enough funds for withdrawal amount");
            }
        }
        else{
            throw new IllegalArgumentException("Withdraw amount: " + amount + " is invalid, cannot withdraw money");
        }
    }

    /**
     * Checks if the amount is in proper format
     * @param amount
     * @return boolean (true if amount is positive and has two decimal points or less, otherwise false)
     */
    public static boolean isAmountValid(double amount){
        String[] splitter = Double.toString(amount).split("\\.");
        splitter[0].length();   // Before Decimal Count
        int decimalLength = splitter[1].length();
        if(amount > -1 && decimalLength < 3){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * adds amount to balance if amountIsValid
     * @param amount
     */
    public void deposit(double amount){
        if (isAmountValid(amount)){
            balance+=amount;
        }
        else{
            throw new IllegalArgumentException("Deposit amount: " + amount + " is invalid, cannot withdraw money");
        }
    }

    /**
     * subtracts from bank account and adds to balance of account, transfer
     * @param amount
     * @param account
     */
    public void transfer(double amount, BankAccount account) throws InsufficientFundsException {
        if (isAmountValid(amount)){
            this.withdraw(amount);
            account.deposit(amount);
        }
        else{
            throw new IllegalArgumentException("Transfer amount: " + amount + " is invalid, cannot withdraw money");
        }
    }

    public static boolean isEmailValid(String email) {
        if (email == "") {
            return false;
        }
        if (email.indexOf('@') == -1) {
            return false;
        } else if (email.indexOf(".") == 0) {
            return false;
        } else if (email.indexOf("-") == 0) {
            return false;
        }else if (email.indexOf("_") == 0) {
            return false;
        }else if (email.indexOf("..") != -1) {
            return false;
        }
        else if (email.indexOf("--") != -1){
            return false;
        }
        else if (email.indexOf("__") != -1){
            return false;
        }
        else if (email.indexOf("_@") != -1){
            return false;
        }
        else if (email.indexOf(".@") != -1){
            return false;
        }
        else if (email.indexOf("-@") != -1) {
            return false;
        } else if (email.indexOf("#") != -1) {
            return false;
        } else {
            String eDomain = email.substring(email.indexOf("@"));

            if (eDomain.indexOf(".") == -1) {
                return false;
            }

            else if (eDomain.indexOf("#") != -1){
                return false;
            }

            else{
                String emailEnd = eDomain.substring((eDomain.indexOf(".")));

                if (emailEnd.length() < 3 && emailEnd.length() < 4){
                    return false;
                }

                else if (eDomain.indexOf(".") == -1){
                    return false;
                }
                int periodCount = 0;
                int hyCount = 0;
                int unCount = 0;

                char perChar = '.';
                char hyChar = '-';
                char unChar = '_';

                for (int i = 0; i < emailEnd.length(); i++){
                    if(emailEnd.charAt(i) == perChar){
                        periodCount++;
                    }
                    if(emailEnd.charAt(i) == hyChar){
                        hyCount++;
                    }
                    if(emailEnd.charAt(i) == unChar){
                        unCount++;
                    }
                }
                if(periodCount > 1){
                    return false;
                }
                if(hyCount > 0){
                    return false;
                }
                if(unCount > 0){
                    return false;
                }
            }
        }
        return true;
    }
}

