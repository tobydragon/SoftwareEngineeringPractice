package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(String acctId, double startingBalance) throws AccountAlreadyExistsException, IllegalArgumentException, AccountDoesNotExistException;

    public void closeAccount(String acctId);
}
