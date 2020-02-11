package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    /**
     * @param email is a string describing an email address, this email is used as the account ID
     * @param password is a string representing a password used for user validation
     * @param startingBalance is a double representing the amount of money in the account to start with
     * @post creates an account with the given traits and adds it to the list of accounts in the Central Bank
     * @throws AccountAlreadyExistsException if the given email is already associated with an account
     */
    public void createAccount(String email, String password, double startingBalance) throws AccountAlreadyExistsException;  //Implemented

    /**
     * @param email is a string describing an email address unique to the account to be deleted
     * @post removes the account with the given email address if one exists
     * @throws AccountNotFoundException if the given email is not associated with an account
     */
    public void closeAccount(String email) throws AccountNotFoundException;  //Implemented
}
