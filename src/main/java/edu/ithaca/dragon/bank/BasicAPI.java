package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    /**
     * @param email is a string describing an email address unique to the account to get the password from
     * @param password is a string of the password that is expected
     * @returns true if the given password matches the one from the account, false if not
     * @throws AccountNotFoundException if the given email is not associated with an account
     */
    boolean confirmCredentials(String email, String password) throws AccountNotFoundException;

    /**
     * @param email is a string describing an email address unique to the account to be checked
     * @returns the current balance of the account as a double
     * @throws AccountNotFoundException if the given email is not associated with an account
     */
    double checkBalance(String email) throws AccountNotFoundException;

    /**
     * @param email is a string describing an email address unique to the account to be withdrawn from
     * @param amount is a double representing the amount to be withdrawn
     * @post the account's balance is decreased by the given amount
     * @throws AccountNotFoundException if the given email is not associated with an account
     * @throws InsufficientFundsException if the given account does not have enough money to withdraw the given amount
     */
    void withdraw(String email, double amount) throws InsufficientFundsException, AccountNotFoundException;

    /**
     * @param email is a string describing an email address unique to the account to be deposited to
     * @param amount is a double representing the amount to be deposited
     * @post the account's balance is increased by the given amount
     * @throws AccountNotFoundException if the given email is not associated with an account
     */
    void deposit(String email, double amount) throws AccountNotFoundException;

    /**
     * @param emailToWithdrawFrom is a string describing an email address unique to the account to be withdrawn from
     * @param emailToDepositTo is a string describing an email address unique to the account to be deposited to
     * @param amount is a double representing the amount to be transferred
     * @post one account's balance is decreased by the given amount, and the other is increased by the given amount
     * @throws AccountNotFoundException if either of the given emails is not associated with an account
     * @throws InsufficientFundsException if the account to withdraw from does not have enough money to withdraw the given amount
     */
    void transfer(String emailToWithdrawFrom, String emailToDepositTo, double amount) throws InsufficientFundsException, AccountNotFoundException;

    /**
     * @param email is a string describing an email address unique to the account to get the history from
     * @returns a string representing the entire account's history
     * @throws AccountNotFoundException if the given email is not associated with an account
     */
    String transactionHistory(String email) throws AccountNotFoundException;

}
