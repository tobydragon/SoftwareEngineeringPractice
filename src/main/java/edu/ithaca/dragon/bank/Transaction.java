package edu.ithaca.dragon.bank;

public class Transaction {

    private double balance;
    private double transactionAmount;
    private boolean flagSuspicious;

    public Transaction(double balanceIn, double transactionAmountIn, boolean flagSuspiciousIn) {
        this.balance = balanceIn;
        this.transactionAmount = transactionAmountIn;
        this.flagSuspicious = flagSuspiciousIn;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getTransactionAmount() {
        return this.transactionAmount;
    }

    public boolean getFlagSuspicious() {
        return this.flagSuspicious;
    }

    public String toString() {
        return balance + " " + transactionAmount + " " + flagSuspicious;
    }
}
