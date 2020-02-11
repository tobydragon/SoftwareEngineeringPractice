package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createBankAccount(int acctId, double startingBalance)  throws NonExistentAccountException;

    public void closeBankAccount(int acctId)  throws NonExistentAccountException;

    public void createUserAccount(String username, String password, String email, int userID);

    public void closeUserAccount(int userId) throws IllegalArgumentException, NonExistentAccountException;
}
