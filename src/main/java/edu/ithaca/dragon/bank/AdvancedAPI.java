package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(int userId, double startingBalance)  throws NonExistentAccountException;

    public void closeAccount(int userID, int acctId)  throws NonExistentAccountException;
}
