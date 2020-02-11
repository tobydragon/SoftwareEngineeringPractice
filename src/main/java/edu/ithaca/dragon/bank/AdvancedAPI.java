package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(String email, String password, double startingBalance) throws AccountAlreadyExistsException, IllegalArgumentException;  //Implemented

    public void closeAccount(String acctId) throws AccountNotFoundException;  //Implemented
}
