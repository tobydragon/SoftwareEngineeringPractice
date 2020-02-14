package edu.ithaca.dragon.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private double oldBalance;
    private double transactionAmount;
    private double newBalance;
    private boolean flagSuspicious;
    //private LocalDateTime transactionTime = LocalDateTime.now();
    private String transactionTime;

    public Transaction(double oldBalanceIn, double transactionAmountIn, double newBalanceIn, LocalDateTime transactionTimeIn, boolean flagSuspiciousIn) {
        this.oldBalance = oldBalanceIn;
        this.transactionAmount = transactionAmountIn;
        this.newBalance = newBalanceIn;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        this.transactionTime = transactionTimeIn.format(format);
        this.flagSuspicious = flagSuspiciousIn;
    }

    public double getBalance() {
        return this.oldBalance;
    }

    public double getTransactionAmount() {
        return this.transactionAmount;
    }

    public boolean getFlagSuspicious() {
        return this.flagSuspicious;
    }

    public String toString() {
        return "Balance before: " + oldBalance + " Transaction amount: " + transactionAmount +
                " Balance after: " + newBalance + " Time: " + transactionTime + " Suspicious: " + flagSuspicious + "\n";
    }
}
