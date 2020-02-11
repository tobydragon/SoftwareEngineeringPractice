package edu.ithaca.dragon.bank;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String email;
    public double balance;
    public  boolean acctFrozen;
    private String acctId;
    private String acctPass;
    private boolean susAct;
    List<String> history = new ArrayList<>();


    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) {
        this.acctFrozen = false;
        this.susAct= false;
        this.acctId = "B000";
        this.acctPass = "000000";
        if (isEmailValid(email) && isAmountValid(startingBalance)) {
            this.email = email;
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance() {
        history.add("Check balance: " + balance);
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getAcctId(){return acctId;}

    public void setAcctId(String IDin){
        this.acctId = IDin;
    }

    public void setAcctPass(String passwordIn){
        this.acctPass = passwordIn;
    }

    public String getAcctPass() {
        return acctPass;
    }

    public void setSusAct(Boolean in){
        this.susAct = in;
    }

    public boolean getSusAct(){
        return this.susAct;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if amount is negative or larger than balance, balance stays the same
     */
    public void withdraw(double amount) throws IllegalAccessException {
        bankAccountFrozenCheck();

        if (isAmountValid(amount)) {
            if (!(balance < amount) && amount > 0) {
                balance -= amount;
                history.add("Withdraw: " + amount);
            }
        }else{
            throw new IllegalArgumentException("withdraw amount: " + amount + " is invalid, amount cannot be withdrawn");
        }
    }
    public List<String> getHistory(){
        return history;
    }
    public void setAcctFrozen(boolean status){
        this.acctFrozen = status;
    }

    public boolean getAcctFrozen(){return acctFrozen;}


    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        }
        if (email.startsWith(".")) {
            return false;
        }
        if (!email.endsWith(".com")) {
            return false;
        }
        if(email.contains("-@")){
            return false;
        }
        if(email.contains("..")){
            return false;
        }
        if(email.contains("--")){
            return false;
        }
        if(email.contains("__")){
            return false;
        }
        if(email.contains("#")){
            return false;
        }
        if(email.contains("##")){
            return false;
        }
        return email.substring(email.length() - 4, email.length() - 1).contains("c");
    }

    /**
     * @param amount
     * @return function returns a boolean telling whether an amount is a valid input
     * valid inputs are positive and 2 decimal points or less
     */
    public static boolean isAmountValid(double amount){
        boolean isValid = true;

        if (amount <= 0){
            isValid = false;
        }
        else {
            String amountString = Double.toString(amount);
            if (amountString.indexOf('.')+2 < amountString.length()-1){
                isValid=false;
            }
        }

        return isValid;
    }

    /**
     * function will add money of specified amount to the balance of the account
     */
    public void deposit (double amount) throws IllegalAccessException {
        bankAccountFrozenCheck();

        if (isAmountValid(amount)) {
            if (!(balance < amount) && amount > 0) {
                balance += amount;
                history.add("Deposit: " + amount);
            }
        }else{
            throw new IllegalArgumentException("deposit amount: " + amount + " is invalid, amount cannot be deposited");
        }
    }

    /**
     * function withdraws valid amount from account and deposits to new account
     * @param amount
     * @param bankAccounta
     */
    public void transfer (double amount, BankAccount bankAccounta)throws IllegalAccessException {
        bankAccountFrozenCheck();

        if (isAmountValid(amount) && amount < this.balance){
            history.add("Transfer: " + amount + "To" + bankAccounta.getAcctId());
            this.withdraw(amount);
            bankAccounta.balance = bankAccounta.balance + amount;
        }else{
            throw new IllegalArgumentException("transfer amount: " + amount + " is invalid, amount cannot be transfered");
        }
    }

    public void bankAccountFrozenCheck() throws IllegalAccessException {
        if (this.acctFrozen == true){
            throw new IllegalAccessException("This Account is Frozen, No Actions can be taken on it");
        }
    }
}
