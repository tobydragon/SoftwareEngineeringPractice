package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {
    private double interest;
    private double maxWithdraw;
    /**
     * @param email
     * @param startingBalance
     * @throws IllegalArgumentException if email is invalid
     */
    public SavingsAccount(String email, double startingBalance, double interest, double maxWithdraw) throws IllegalArgumentException {
        super(email, startingBalance);
        if(interest < 0){
            throw new IllegalArgumentException("Interest must be at least 0%");
        }
        this.interest = interest;
        this.maxWithdraw = maxWithdraw;
    }

    /**
     * Compounds interest of the day and adds it to balance
     */
    public void compoundInterest(){
        double compounded = interest *  this.getBalance();
        this.deposit(compounded);
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * throws InsufficientFundsException if the amount is larger than the balance
     * If balance is negative or has more than 2 decimal places, throws IllegalArgumentException
     * If amount to withdraw is more than withdraw limit, it throws an InsufficientFundsException
     */
   /*@Override
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException  {
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount must have 2 decimal places and must be positive ");
        }
        if (this.getBalance() >= amount && amount >= 0) {
            withdraw(amount);
        }
        else{
            throw new InsufficientFundsException("Amount requested is more than in your account by " + (amount - balance));
        }

    }*/
}
