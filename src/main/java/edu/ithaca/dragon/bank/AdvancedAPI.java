package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(int acctId, double startingBalance);

    public void closeAccount(int acctId);
}
